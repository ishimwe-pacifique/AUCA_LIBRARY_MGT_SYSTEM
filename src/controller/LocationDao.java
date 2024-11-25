package controller;

import model.Location;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;
import java.util.UUID;

public class LocationDao {

    
    public String createLocation(Location location) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.persist(location);
            transaction.commit();
            return "Location saved successfully";
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            return "Error saving location";
        }
    }

    
    public String updateLocation(Location location) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            session.merge(location);
            transaction.commit();
            return "Location updated successfully";
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            return "Error updating location";
        }
    }

   
    public Location findLocationById(UUID locationId) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.get(Location.class, locationId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

   
    public String deleteLocation(UUID locationId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();
            Location location = session.get(Location.class, locationId);
            if (location != null) {
                session.remove(location);
                transaction.commit();
                return "Location deleted successfully";
            } else {
                return "Location not found";
            }
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
            return "Error deleting location";
        }
    }
    
    // Method to get the location based on the phone number
    public String getLocationByPhoneNumber(String phoneNumber) {
        // Placeholder for database logic
        // Assume that you query a table that has phone numbers and associated locations
        String locationName = null;
        
        // Example of how you might query the database:
        // (You would need to replace this with actual database code)
        if (phoneNumber.equals("0729351008")) {
            locationName = "Kigali";
        } else if (phoneNumber.equals("0782336085")) {
            locationName = "Western ";
        }else if (phoneNumber.equals("0785168856")) {
            locationName = "Northern ";
        }else if (phoneNumber.equals("0785254009")) {
            locationName = "Eastern ";
        }
        
        return locationName;
    }


    
    public List<Location> getAllLocations() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            
            List<Location> locations = session.createQuery("select l FROM Location l", Location.class).getResultList();
            return locations;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
