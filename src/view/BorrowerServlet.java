package view;

import controller.BorrowerDao;
import model.Borrower;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/borrow-book")
public class BorrowerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BorrowerServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        request.getRequestDispatcher("/borrow-book-form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String borrowerName = request.getParameter("borrowerName");
        String bookTitle = request.getParameter("bookTitle");
        String borrowDateString = request.getParameter("borrowDate");
        String dueDateString = request.getParameter("dueDate");
        int chargeFees = Integer.parseInt(request.getParameter("chargeFees"));

     
        LocalDate borrowDate = LocalDate.parse(borrowDateString);
        LocalDate dueDate = LocalDate.parse(dueDateString);

       
        Borrower borrower = new Borrower();
        borrower.setBorrowerName(borrowerName);
        borrower.setBookTitle(bookTitle);
        borrower.setBorrowDate(borrowDate);
        borrower.setDueDate(dueDate);
        borrower.setChargeFees(chargeFees);

        
        BorrowerDao borrowerDao = new BorrowerDao();
        borrowerDao.createBorrower(borrower);

        // direct to the form or show success message
        request.setAttribute("message", "Book borrowed successfully!");
        request.getRequestDispatcher("/borrow-book-form.jsp").forward(request, response);
    }
}
