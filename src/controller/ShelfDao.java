package controller;

import model.Shelf;
import model.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.List;
import java.util.UUID;

public class ShelfDao {

    public String saveShelf(Shelf shelf) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.save(shelf);
            transaction.commit();
            return "Shelf successfully saved.";
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "Failed to save the shelf: " + e.getMessage();
        }
    }

    public String updateShelf(Shelf shelf) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.update(shelf);
            transaction.commit();
            return "Shelf successfully updated.";
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "Failed to update the shelf: " + e.getMessage();
        }
    }

    public String deleteShelf(UUID shelfId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            Shelf shelf = session.get(Shelf.class, shelfId);
            if (shelf != null) {
                session.delete(shelf);
                transaction.commit();
                return "Shelf successfully deleted.";
            } else {
                return "Shelf not found.";
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "Failed to delete the shelf: " + e.getMessage();
        }
    }

    public Shelf getShelfById(UUID shelfId) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.get(Shelf.class, shelfId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

   

        public List<Shelf> getAllShelves() {
            Session session = HibernateUtil.getSession().openSession();  
            Transaction transaction = null;
            List<Shelf> shelves = null;

            try {
                transaction = session.beginTransaction();
                shelves = session.createQuery("from Shelf", Shelf.class).list();
                transaction.commit(); 
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            } finally {
                session.close(); 
            }

            return shelves;
        }
    }