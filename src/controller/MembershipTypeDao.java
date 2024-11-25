package controller;



import model.MembershipType;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sun.xml.bind.v2.schemagen.xmlschema.List;

import util.HibernateUtil;

import java.util.UUID;

public class MembershipTypeDao {


    public String addMembershipType(MembershipType membershipType) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();


            session.persist(membershipType);

            transaction.commit();
            return "saved";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "error";
        }
    }

   
    public MembershipType findMembershipTypeById(UUID membershipTypeId) {
        try (Session session = HibernateUtil.getSession().openSession()) {

            return session.get(MembershipType.class, membershipTypeId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null; 
        }
    }

   
    public String updateMembershipType(MembershipType membershipType) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();

           
            session.merge(membershipType);

            transaction.commit();
            return "updated";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "error";
        }
    }
    public List getAllMembershipTypes() {
        List membershipTypes = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            session.beginTransaction();

            
            membershipTypes = (List) session.createQuery("FROM MembershipType", MembershipType.class).getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return membershipTypes;
    }


   
    public String deleteMembershipType(UUID membershipTypeId) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();

           
            MembershipType membershipType = session.get(MembershipType.class, membershipTypeId);
            if (membershipType != null) {
                session.remove(membershipType);
                transaction.commit();
                return "deleted";
            } else {
                return "not found";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "error";
        }
    }
}
