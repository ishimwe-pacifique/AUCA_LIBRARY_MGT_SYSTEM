package view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.BookDao;
import controller.ShelfDao;
import model.Book;
import model.BookStatus;
import model.Room;
import model.Shelf;



@WebServlet("/new-book")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BookServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ShelfDao service = new ShelfDao();
		List<Shelf> shelves = service.getAllShelves();
		request.setAttribute("shelves", shelves);
		request.getRequestDispatcher("/book-form.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	    String title = request.getParameter("bookTitle");
	    String isbnCode = request.getParameter("isbnCode");
	    String publisherName = request.getParameter("publisherName");
	    int edition = Integer.parseInt(request.getParameter("edition"));
	    String publicationYearString = request.getParameter("publication_year");
	    String shelfId = request.getParameter("shelf");

	   
	    Date publicationYear = null;
	    if (publicationYearString != null && !publicationYearString.isEmpty()) {
	        try {
	            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  // Corrected the date format
	            publicationYear = dateFormat.parse(publicationYearString);
	        } catch (ParseException e) {
	            e.printStackTrace();
	         
	        }
	    } else {
	        System.out.println("Publication year is required.");
	    }

	   
	    BookDao bookd = new BookDao();
	    Book book = new Book();

	   
	    book.setTitle(title);
	    book.setISBNCode(isbnCode);
	    book.setPublisherName(publisherName);
	    book.setEdition(edition);
	    book.setPublicationYear(publicationYear);  
	    
	    book.setStatus(BookStatus.AVAILABLE);
	    book.setStatus(BookStatus.RESERVED);
	    book.setStatus(BookStatus.BORROWED);

	   
	    bookd.createBook(book);

	  
	    request.setAttribute("message", "Book saved successfully");


	    request.getRequestDispatcher("/book-form.jsp").forward(request, response);
	}
}