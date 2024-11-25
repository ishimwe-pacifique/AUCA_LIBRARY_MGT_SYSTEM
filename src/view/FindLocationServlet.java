package view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.LocationDao;

@WebServlet(urlPatterns = "/findLocation")
public class FindLocationServlet extends HttpServlet {

    private LocationDao locationDao = new LocationDao(); // DAO to interact with the database

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        // Get the phone number from the request
        String phoneNumber = req.getParameter("phoneNumber");

        // Retrieve location name based on phone number
        String locationName = null;
        String errorMessage = null;

        try {
            // Find location by phone number (assuming the method exists in LocationDao)
            locationName = locationDao.getLocationByPhoneNumber(phoneNumber);

            if (locationName == null || locationName.isEmpty()) {
                errorMessage = "No location found for the given phone number.";
            }
        } catch (Exception e) {
            errorMessage = "An error occurred while retrieving the location: " + e.getMessage();
        }

        // Set attributes to be accessed in the JSP
        req.setAttribute("location", locationName);
        req.setAttribute("errorMessage", errorMessage);

        // Forward the request to the JSP to display the result
        RequestDispatcher dispatcher = req.getRequestDispatcher("/findLocation.jsp");
        dispatcher.forward(req, res);
    }
}
