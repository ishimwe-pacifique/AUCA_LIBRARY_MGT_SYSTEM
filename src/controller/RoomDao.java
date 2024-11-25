package controller;
import org.hibernate.Session;
import org.hibernate.query.Query;  // Ensure this import is correct
import java.util.List;
import org.hibernate.query.Query;
import model.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;
import java.util.UUID;



public class RoomDao {

 
    public String saveRoom(Room room) {
        Transaction trans = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            trans = session.beginTransaction();

            
     session.persist(room);

            trans.commit();
            return "Room saved successfully";
        } catch (Exception ex) {
            if (trans != null) trans.rollback();
            ex.printStackTrace();
                 return "Error saving room: " + ex.getMessage();
        }
    }

  
    public static Room findRoomById(UUID roomId) {
        try (Session session = HibernateUtil.getSession().openSession()) {
        	
            return session.get(Room.class, roomId); 
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

   
    public String updateRoom(Room room) {
        Transaction trans = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            trans = session.beginTransaction();

           
                   session.update(room);

            trans.commit();
            return "Room updated successfully";
        } catch (Exception ex) {
            if (trans != null) trans.rollback();
            ex.printStackTrace();
            return "Error updating room: " + ex.getMessage();
        }
    }

   
    public String deleteRoom(UUID roomId) {
        Transaction trans = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            trans = session.beginTransaction();

           
            Room room = session.get(Room.class, roomId);
            if (room != null) {
                session.delete(room);
                trans.commit();
                return "Room deleted successfully";
            } else {
                return "Room not found";
            }
        } catch (Exception ex) {
            if (trans != null) trans.rollback();
            ex.printStackTrace();
            return "Error deleting room: " + ex.getMessage();
        }
    }
    
   

       
    public List<Room> getAllRooms() {
        try (Session session = HibernateUtil.getSession().openSession()) {
           
            Query<Room> query = session.createQuery("from Room", Room.class);  
            return query.list();  
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

 
    public Room getRoomById(UUID roomId) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.get(Room.class, roomId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}