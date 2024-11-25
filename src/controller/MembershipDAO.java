package controller;

import model.Membership;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;
import java.util.UUID;

public class MembershipDAO {

    public void saveMembership(Membership membership) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.save(membership);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<Membership> getAllMemberships() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.createQuery("from Membership", Membership.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void approveMembership(UUID membershipId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            Membership membership = session.find(Membership.class, membershipId);
            if (membership != null) {
                membership.setStatus(model.MembershipStatus.APPROVED);
                session.update(membership);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void rejectMembership(UUID membershipId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            Membership membership = session.find(Membership.class, membershipId);
            if (membership != null) {
                membership.setStatus(model.MembershipStatus.REJECTED);
                session.update(membership);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
