package view;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/getRoomBookCount")
public class RoomBookCountServlet extends HttpServlet {

    // Example database of room codes and book counts (replace with real database query)
    private static final Map<String, Integer> roomBookCounts = new HashMap<>();

    static {
        roomBookCounts.put("R001", 5);
        roomBookCounts.put("R002", 2);
        roomBookCounts.put("R003", 2);
        roomBookCounts.put("R004", 1);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String roomCode = request.getParameter("roomCode");
        
        if (roomCode != null && !roomCode.trim().isEmpty()) {
            Integer bookCount = roomBookCounts.get(roomCode.trim().toUpperCase());

            if (bookCount != null) {
                request.setAttribute("roomCode", roomCode);
                request.setAttribute("bookCount", bookCount);
            } else {
                request.setAttribute("errorMessage", "Room code not found.");
            }
        } else {
            request.setAttribute("errorMessage", "Room code cannot be empty.");
        }

        // Forward to JSP
        request.getRequestDispatcher("roomBookCount.jsp").forward(request, response);
    }
}

