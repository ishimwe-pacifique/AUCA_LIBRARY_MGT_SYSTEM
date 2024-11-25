package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@WebServlet("/calculateFine")
public class CalculateFineServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String borrowerName = request.getParameter("borrowerName");
        String returnDateStr = request.getParameter("returnDate");
        String dueDateStr = request.getParameter("dueDate");

        if (borrowerName != null && returnDateStr != null && dueDateStr != null) {
            LocalDate returnDate = LocalDate.parse(returnDateStr);
            LocalDate dueDate = LocalDate.parse(dueDateStr);

            int fine = 0;
            if (returnDate.isAfter(dueDate)) {
                long daysOverdue = ChronoUnit.DAYS.between(dueDate, returnDate);
                fine = (int) (daysOverdue * 10); 
            }

            
            request.setAttribute("borrowerName", borrowerName);
            request.setAttribute("fine", fine);
        }

        
        request.getRequestDispatcher("returnBorrower.jsp").forward(request, response);
    }
}

