package controller;

import model.User;
import model.Location;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import java.util.List;  // Correct import for List
import java.util.UUID;
import javax.persistence.Query;

public class UserDao {

    
    public String createAccount(User account) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction trans = session.beginTransaction();

        
            session.persist(account);

            trans.commit();
            return "saved";
        } catch (Exception ex) {
            ex.printStackTrace();
            return "error";
        }
    }

    
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            session.beginTransaction();

            
            Query query = session.createQuery("FROM User", User.class);
            users = query.getResultList();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

  
    public Location findLocationById(UUID locationId) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.get(Location.class, locationId);  
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;  
        }
    }

   
    public User findUserById(UUID userUUID) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.get(User.class, userUUID); 
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;  
    }
}
