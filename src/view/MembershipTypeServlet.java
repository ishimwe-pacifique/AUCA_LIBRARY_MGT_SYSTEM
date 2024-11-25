package view;

import model.MembershipType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import controller.MembershipTypeDao;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/createMembershipType") 
public class MembershipTypeServlet extends HttpServlet {

    private MembershipTypeDao membershipTypeDao;

    @Override
    public void init() {
        membershipTypeDao = new MembershipTypeDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

       
        String membershipName = request.getParameter("membershipName");
        String maxBooksStr = request.getParameter("maxBooks");
        String pricePerDayStr = request.getParameter("pricePerDay");
        
        try {
           
            if (membershipName == null || membershipName.trim().isEmpty()) {
                out.println("<h1>Membership name is required</h1>");
                return;
            }
            if (maxBooksStr == null || maxBooksStr.trim().isEmpty()) {
                out.println("<h1>Max books is required</h1>");
                return;
            }
            if (pricePerDayStr == null || pricePerDayStr.trim().isEmpty()) {
                out.println("<h1>Price per day is required</h1>");
                return;
            }

           
            int maxBooks = Integer.parseInt(maxBooksStr);
            int pricePerDay = Integer.parseInt(pricePerDayStr);

          
            MembershipType membershipType = new MembershipType();
            membershipType.setMembershipName(membershipName);
            membershipType.setMaxBooks(maxBooks);
            membershipType.setPricePerDay(pricePerDay);

          
            String result = membershipTypeDao.addMembershipType(membershipType);

            
            out.println("<h1>" + (result.equals("saved") 
                ? "Membership Type saved successfully." : "Error saving Membership Type.") + "</h1>");

        } catch (NumberFormatException e) {
            out.println("<h1>Invalid input. Please check your form data.</h1>");
        } catch (Exception e) {
            out.println("<h1>An error occurred while saving the membership type.</h1>");
            e.printStackTrace();
        } finally {
           
            RequestDispatcher dispatcher = request.getRequestDispatcher("memberShipType.html");
            dispatcher.include(request, response);
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("memberShipType.html"); 
    }
}
