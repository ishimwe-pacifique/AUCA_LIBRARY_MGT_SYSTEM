package controller;

import org.hibernate.Session;
import org.hibernate.Transaction;
import model.Borrower;
import util.HibernateUtil;
import java.util.UUID;

public class BorrowerDao {

  
    public String createBorrower(Borrower borrower) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();

           
            session.persist(borrower);

            transaction.commit();
            return "saved";
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "error";
        }
    }

  
    public Borrower findBorrowerById(UUID borrowerId) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.get(Borrower.class, borrowerId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   
    public String updateBorrower(Borrower borrower) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();

           
            session.update(borrower);

            transaction.commit();
            return "updated";
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "error";
        }
    }

   
    public String deleteBorrower(UUID borrowerId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();

           
            Borrower borrower = session.get(Borrower.class, borrowerId);
            if (borrower != null) {
                session.delete(borrower);
                transaction.commit();
                return "deleted";
            } else {
                return "not_found";
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "error";
        }
    }
}
