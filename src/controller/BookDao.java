package controller;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Book;

import util.HibernateUtil;

public class BookDao {

	public boolean saveBook(Book book) {
	    Transaction transaction = null;
	    try (Session session = HibernateUtil.getSession().openSession()) {
	    transaction = session.beginTransaction();
	    session.save(book);
	     transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace(); 
	    }
	    return false;
	}

	
	public String createBook(Book book) {
		
		try(Session session = HibernateUtil.getSession().openSession()){
			
			Transaction trans = session.beginTransaction();
			
		
			session.persist(book);
			
			trans.commit();
			
			session.close();
			
			return "saved";
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return "error";
		}
	
	}
   



    
    public Book getBook(Book book) {
        try (Session session = HibernateUtil.getSession().openSession()) {
            return session.get(Book.class, book.getBookId());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

  
    public List<Book> getBooks() {
        try (Session session = HibernateUtil.getSession().openSession()) {
            Query<Book> query = session.createQuery("from Book", Book.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}