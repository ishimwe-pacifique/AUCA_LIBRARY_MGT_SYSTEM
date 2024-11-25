package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.LocationDao;
import model.Location;
import model.LocationType;

@WebServlet(urlPatterns = "/createLocation")
public class LocationServlet extends HttpServlet {

    private LocationDao locationDao = new LocationDao(); 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        // Retrieve form parameters
        String locationCode = req.getParameter("locationCode");
        String locationName = req.getParameter("locationName");
        String locationTypeParam = req.getParameter("locationType");
        String parentLocationIdParam = req.getParameter("parentLocationId");
        String province = req.getParameter("province"); // Retrieve province parameter

        try {
            // Create Location object
            Location location = createLocationInstance(locationCode, locationName, locationTypeParam, parentLocationIdParam, province, out);
            if (location == null) return; 

            // Save the Location object
            String saveLocationResponse = locationDao.createLocation(location);

            // Send response
            out.println("<h1>" + (saveLocationResponse.equalsIgnoreCase("Location saved successfully") 
                ? "Location created successfully" : "Location creation failed") + "</h1>");

        } catch (IllegalArgumentException e) {
            out.println("<h1>Invalid location type specified</h1>");
            e.printStackTrace();
        } catch (Exception e) {
            out.println("<h1>An error occurred while creating the location</h1>");
            e.printStackTrace();
        } finally {
            RequestDispatcher dispatcher = req.getRequestDispatcher("createLocation.html");
            dispatcher.include(req, res);
            out.close();
        }
    }

    private Location createLocationInstance(String locationCode, String locationName, String locationTypeParam,
                                            String parentLocationIdParam, String province, PrintWriter out) {
        try {
            Location location = new Location();
            location.setLocationId(UUID.randomUUID()); 
            location.setLocationCode(locationCode);
            location.setLocationName(locationName);

            // Set location type
            LocationType locationType = LocationType.valueOf(locationTypeParam.toUpperCase());
            location.setLocationType(locationType);

            // Set the province field
            // Set parent location if available
            if (parentLocationIdParam != null && !parentLocationIdParam.isEmpty()) {
                try {
                    UUID parentLocationId = UUID.fromString(parentLocationIdParam);
                    Location parentLocation = locationDao.findLocationById(parentLocationId);
                    if (parentLocation != null) {
                        location.setParentLocation(parentLocation);
                    } else {
                        out.println("<h1>Parent location not found</h1>");
                        return null; 
                    }
                } catch (IllegalArgumentException e) {
                    out.println("<h1>Invalid Parent Location ID format</h1>");
                    return null; 
                }
            }

            return location; 
        } catch (IllegalArgumentException e) {
            out.println("<h1>Invalid location type specified</h1>");
            return null; 
        }
    }
    
}
