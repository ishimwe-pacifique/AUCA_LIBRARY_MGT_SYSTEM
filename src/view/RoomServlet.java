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

import com.sun.xml.bind.v2.schemagen.xmlschema.List;

import controller.RoomDao;
import model.Room;

@WebServlet(urlPatterns = "/createRoom")
public class RoomServlet extends HttpServlet {

    private RoomDao roomDao = new RoomDao(); 

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

       
        String roomCode = req.getParameter("roomCode");

        try {
          
            if (roomCode == null || roomCode.trim().isEmpty()) {
                out.println("<h1>Room code is required</h1>");
                return;
            }

            
            Room room = new Room();
            room.setRoomId(UUID.randomUUID()); 
            room.setRoomCode(roomCode); 

           
            String saveRoomResponse = roomDao.saveRoom(room);

           
            out.println("<h1>" + (saveRoomResponse.equalsIgnoreCase("Room saved successfully") 
                ? "Room created successfully" : "Room creation failed") + "</h1>");

        } catch (Exception e) {
            out.println("<h1>An error occurred while creating the room</h1>");
            e.printStackTrace();
        } finally {
           
            RequestDispatcher dispatcher = req.getRequestDispatcher("createRoom.html");
            dispatcher.include(req, res);
            out.close();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve all rooms from the database
        List rooms = (List) roomDao.getAllRooms();
        
        // Set the rooms attribute to be accessible in the JSP page
        request.setAttribute("rooms", rooms);

        // Forward the request to the rooms.jsp page
        RequestDispatcher dispatcher = request.getRequestDispatcher("/rooms.jsp");
        dispatcher.forward(request, response);
    }

}





