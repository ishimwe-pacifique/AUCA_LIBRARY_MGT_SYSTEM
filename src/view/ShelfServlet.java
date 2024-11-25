package view;

import controller.ShelfDao;
import controller.RoomDao;
import model.Shelf;
import model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@WebServlet("/shelf")
public class ShelfServlet extends HttpServlet {

    private ShelfDao shelfDao;
    private RoomDao roomDao;

    @Override
    public void init() throws ServletException {
        shelfDao = new ShelfDao();
        roomDao = new RoomDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("create".equals(action)) {
               
                Shelf shelf = new Shelf();
                shelf.setShelfId(UUID.randomUUID()); 
                shelf.setAvailableStock(Integer.parseInt(request.getParameter("availableStock")));
                shelf.setBookCategory(request.getParameter("bookCategory"));
                shelf.setBorrowedNumber(Integer.parseInt(request.getParameter("borrowedNumber")));
                shelf.setInitialStock(Integer.parseInt(request.getParameter("initialStock")));

                UUID roomId = UUID.fromString(request.getParameter("roomId"));
                Room room = roomDao.getRoomById(roomId);
                if (room != null) {
                    shelf.setRoom(room);
                    String result = shelfDao.saveShelf(shelf); 
                    request.setAttribute("message", result); 
                } else {
                    request.setAttribute("message", "Room not found.");
                }
                request.getRequestDispatcher("/shelfList.jsp").forward(request, response);

            } else if ("update".equals(action)) {
                
                UUID shelfId = UUID.fromString(request.getParameter("id")); 
                Shelf shelf = shelfDao.getShelfById(shelfId);
                if (shelf != null) {
                    shelf.setAvailableStock(Integer.parseInt(request.getParameter("availableStock")));
                    shelf.setBookCategory(request.getParameter("bookCategory"));
                    shelf.setBorrowedNumber(Integer.parseInt(request.getParameter("borrowedNumber")));
                    shelf.setInitialStock(Integer.parseInt(request.getParameter("initialStock")));

                    // Update Room
                    UUID roomId = UUID.fromString(request.getParameter("roomId"));
                    Room room = roomDao.getRoomById(roomId);
                    if (room != null) {
                        shelf.setRoom(room);
                        String result = shelfDao.updateShelf(shelf); 
                        request.setAttribute("message", result); 
                    } else {
                        request.setAttribute("message", "Room not found.");
                    }
                }
                response.sendRedirect("shelf?action=list");

            } else if ("delete".equals(action)) {
                // Delete a Shelf
                UUID shelfId = UUID.fromString(request.getParameter("id")); 
                String result = shelfDao.deleteShelf(shelfId); 
                request.setAttribute("message", result); 
                response.sendRedirect("shelf?action=list");
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Invalid input: " + e.getMessage());
            request.getRequestDispatcher("/shelfForm.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        // Fetch all rooms for dropdown
        List<Room> rooms = roomDao.getAllRooms();
        request.setAttribute("rooms", rooms);

        try {
            if ("edit".equals(action)) {
                
                UUID shelfId = UUID.fromString(request.getParameter("id")); 
                Shelf shelf = shelfDao.getShelfById(shelfId); 
                if (shelf != null) {
                    request.setAttribute("shelf", shelf); 
                }
                request.getRequestDispatcher("/shelfForm.jsp").forward(request, response);

            } else if ("list".equals(action)) {

                List<Shelf> shelves = shelfDao.getAllShelves();
                request.setAttribute("shelves", shelves); 
                request.getRequestDispatcher("/shelfList.jsp").forward(request, response);

            } else {

                request.getRequestDispatcher("/shelfForm.jsp").forward(request, response);
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Invalid input: " + e.getMessage());
            request.getRequestDispatcher("/shelfForm.jsp").forward(request, response);
        }
    }
}