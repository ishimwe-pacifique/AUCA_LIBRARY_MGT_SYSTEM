package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.LocationDao;
import controller.UserDao;
import model.User;
import model.Role;
import model.Location;

@WebServlet(urlPatterns = "/createUser")
public class UserServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocationDao dao = new LocationDao();
        
        List<Location> locations = dao.getAllLocations();
        List<Location> villages = new ArrayList<>();
        if (locations != null) {
            for (Location loc : locations) {
                villages.add(loc);
            }
        }
        req.setAttribute("villages", villages);
        req.getRequestDispatcher("create-user.jsp.html").forward(req, resp);        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String phoneNumber = req.getParameter("phoneNumber");  // Get phone number from the form
        String roleParam = req.getParameter("role");
        String villageIdParam = req.getParameter("villageId");

        try {
            String hashedPassword = hashPassword(password);

            // Create a new User object
            User user = new User();
            user.setUserId(UUID.randomUUID());  
            user.setUsername(username);
            user.setPassword(hashedPassword);
            user.setPhoneNumber(phoneNumber);  // Set the phone number

            // Set the role for the user
            Role role = Role.valueOf(roleParam.toUpperCase());
            user.setRole(role);

            // If a villageId is provided, set the village for the user
            if (villageIdParam != null && !villageIdParam.isEmpty()) { 
                try {
                    UUID villageId = UUID.fromString(villageIdParam);
                    UserDao userDao = new UserDao();
                    Location village = userDao.findLocationById(villageId);
                    user.setVillage(village);
                } catch (IllegalArgumentException e) {
                    out.println("<h1>Invalid Village ID format</h1>");
                    e.printStackTrace();
                    return;
                }
            }

            // Save the user to the database
            UserDao userDao = new UserDao();
            String saveUserResponse = userDao.createAccount(user);

            // Respond with success or failure
            RequestDispatcher dispatcher = req.getRequestDispatcher("createUser.html");
            if ("saved".equalsIgnoreCase(saveUserResponse)) {
                out.println("<h1>Account created successfully</h1>");
            } else {
                out.println("<h1>Account creation failed</h1>");
            }
            dispatcher.include(req, res);

        } catch (IllegalArgumentException e) {
            out.println("<h1>Invalid role specified</h1>");
            e.printStackTrace();
        } catch (Exception e) {
            out.println("<h1>An error occurred</h1>");
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
