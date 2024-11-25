package view;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.Role;  // Assuming the Role enum is defined in the model package
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import util.HibernateUtil;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validate the inputs
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("errorMessage", "Username and password are required.");
            request.getRequestDispatcher("login.html").forward(request, response);
            return;
        }

        // Hash the password using SHA-256
        String hashedPassword = null;
        try {
            hashedPassword = hashPassword(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error hashing password.");
            return;
        }

        try (Session session = HibernateUtil.getSession().openSession()) {
            Transaction transaction = session.beginTransaction();

            // Query the database for the user based on the username and hashed password
            String hql = "FROM User WHERE username = :username AND password = :password";
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            query.setParameter("password", hashedPassword);

            User user = query.uniqueResult();

            transaction.commit();

            if (user != null) {
                // Set the user in the session
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("username", username);
                httpSession.setAttribute("user", user);

                // Redirect based on the user's role
                redirectUserBasedOnRole(user, response);

            } else {
                // Invalid username or password
                request.setAttribute("errorMessage", "Invalid username or password.");
                request.getRequestDispatcher("login.html").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
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

    private void redirectUserBasedOnRole(User user, HttpServletResponse response) throws IOException {
        // Get the user's role and redirect accordingly
        Role role = user.getRole();

        if (role == Role.LIBRARIAN) {
            // Redirect to the home page for manager, dean, or librarian
            response.sendRedirect("home.html");
        } else if (role == Role.TEACHER || role == Role.STUDENT) {
            // Redirect to the student/teacher/HOD home page
            response.sendRedirect("studenthome.html");
        } else if (role == Role.HOD || role == Role.DEAN || role == Role.MANAGER) {
            // Redirect to the student/teacher/HOD home page
            response.sendRedirect("hod,dean,manager.html");
        } 
        else {
            // If the role is unrecognized, redirect to login page
            response.sendRedirect("login.html");
        }
    }
}
