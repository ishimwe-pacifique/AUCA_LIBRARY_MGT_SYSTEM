package view;

import controller.MembershipDAO;
import model.Membership;
import model.MembershipStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@WebServlet("/membership")
public class MembershipServlet extends HttpServlet {

    private MembershipDAO membershipDAO;

    @Override
    public void init() {
        membershipDAO = new MembershipDAO();
    }

    // Handles GET requests for general membership view (view all memberships)
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("manage".equals(action)) {
            request.setAttribute("memberships", membershipDAO.getAllMemberships());
            request.getRequestDispatcher("/librarian.jsp").forward(request, response);
        } else {
            request.setAttribute("memberships", membershipDAO.getAllMemberships());
            request.getRequestDispatcher("/membership.jsp").forward(request, response);
        }
    }

    // Handles POST requests for creating a new membership or managing membership status
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("approve".equals(action) || "reject".equals(action)) {
            UUID membershipId = UUID.fromString(request.getParameter("membershipId"));

            if ("approve".equals(action)) {
                membershipDAO.approveMembership(membershipId);
            } else if ("reject".equals(action)) {
                membershipDAO.rejectMembership(membershipId);
            }

            response.sendRedirect("membership?action=manage");
        } else {
            String membershipCode = request.getParameter("membershipCode");
            String membershipType = request.getParameter("membershipType");
            String readerId = request.getParameter("readerId");
            LocalDate expiryDate = LocalDate.parse(request.getParameter("expiryDate"));

            Membership membership = new Membership();
            membership.setMembershipId(UUID.randomUUID());
            membership.setMembershipCode(membershipCode);
            membership.setMembershipType(membershipType);
            membership.setReaderId(readerId);
            membership.setExpiryDate(expiryDate);
            membership.setRegistrationDate(LocalDate.now());
            membership.setStatus(MembershipStatus.PENDING);

            membershipDAO.saveMembership(membership);

            response.sendRedirect("membership");
        }
    }
}
