package view;

import model.Membership;
import model.MembershipStatus;
import util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/MembershipApprovalServlet")
public class MembershipApprovalServlet extends HttpServlet {

    private SessionFactory sessionFactory;

    @Override
    public void init() {
        // Initialize Hibernate SessionFactory
        sessionFactory = HibernateUtil.getSession();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        String userRole = (String) httpSession.getAttribute("userRole");

        // Check if the user is a Librarian
        if (!"Librarian".equalsIgnoreCase(userRole)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: Only Librarians can access this page.");
            return;
        }

        List<Membership> pendingMemberships;

        // Retrieve pending memberships
        try (Session session = sessionFactory.openSession()) {
            pendingMemberships = session.createQuery(
                    "FROM Membership WHERE status = :status", Membership.class)
                    .setParameter("status", MembershipStatus.PENDING)
                    .list();
        }

        // Pass the pending memberships to the JSP
        request.setAttribute("memberships", pendingMemberships);
        request.getRequestDispatcher("approveReject.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        String userRole = (String) httpSession.getAttribute("userRole");

        // Check if the user is a Librarian
        if (!"Librarian".equalsIgnoreCase(userRole)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access Denied: Only Librarians can perform this action.");
            return;
        }

        String membershipId = request.getParameter("membershipId");
        String action = request.getParameter("action");

        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession().openSession()) {
            transaction = session.beginTransaction();

            // Retrieve the membership by ID
            Membership membership = session.find(Membership.class, UUID.fromString(membershipId));

            if (membership != null && MembershipStatus.PENDING.equals(membership.getStatus())) {
                if ("approve".equalsIgnoreCase(action)) {
                    membership.setStatus(MembershipStatus.APPROVED);
                } else if ("reject".equalsIgnoreCase(action)) {
                    membership.setStatus(MembershipStatus.REJECTED);
                }

                session.update(membership); // Update the membership in the database
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }

        response.sendRedirect("MembershipApprovalServlet");
    }

    @Override
    public void destroy() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
