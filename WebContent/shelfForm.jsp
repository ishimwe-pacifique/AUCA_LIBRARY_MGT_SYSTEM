<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Shelf" %>
<%@ page import="model.Room" %>
<%@ page import="controller.RoomDao" %>

<%
    RoomDao roomDao = new RoomDao();
    List<Room> rooms = roomDao.getAllRooms(); 
    Shelf shelf = (Shelf) request.getAttribute("shelf"); 
    boolean isUpdate = (shelf != null); 
    request.setAttribute("rooms", rooms); 
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${isUpdate ? "Update Shelf" : "Create New Shelf"}</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: 'Arial', sans-serif;
        }

        body {
            background: linear-gradient(145deg, #0f2027, #203a43, #2c5364);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: white;
            padding: 20px;
            flex-direction: column;
        }

      
        .navbar {
            width: 100%;
            padding: 15px 20px;
            background: linear-gradient(145deg, #002d52, #1a4d74);
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
            display: flex;
            justify-content: space-between;
            align-items: center;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 1000;
            backdrop-filter: blur(10px);
            transition: all 0.3s ease;
        }

        .navbar .title {
            font-size: 1.5rem;
            font-weight: 700;
            color: #ffffff;
            flex-grow: 1;
            text-align: center;
        }

        .navbar a {
            color: #ffffff;
            text-decoration: none;
            font-size: 1rem;
            font-weight: 500;
            padding: 8px 16px;
            border-radius: 8px;
            transition: background-color 0.3s ease, transform 0.3s ease;
        }

        .navbar a:hover {
            background-color: #1557b0;
            transform: scale(1.1);
        }

        .navbar a.active {
            background-color: #1a73e8;
            font-weight: 600;
        }

      
        .form-container {
            background-color: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(8px);
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.2);
            max-width: 600px;
            width: 100%;
            animation: fadeIn 0.8s ease-in-out;
            display: flex;
            flex-direction: column;
            margin-top: 80px;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #ffffff;
            font-size: 1.75rem;
            font-weight: bold;
        }

        form {
            width: 100%;
        }

        .form-group {
            margin-bottom: 20px;
            position: relative;
        }

        label {
            font-size: 1rem;
            margin-bottom: 5px;
            display: block;
            color: #fff;
        }

        input, select, button {
            width: 100%;
            padding: 14px;
            margin: 10px 0;
            border-radius: 8px;
            font-size: 1rem;
            color: #333;
            background-color: #f4f4f4;
            border: 1px solid #ccc;
            transition: border-color 0.3s ease, background-color 0.3s ease;
        }

        input:focus, select:focus {
            border-color: #1a73e8;
            background-color: #fff;
            outline: none;
        }

        button {
            background-color: #1a73e8;
            color: white;
            font-weight: bold;
            border: none;
            cursor: pointer;
            padding: 14px;
            font-size: 1.1rem;
            transition: background-color 0.3s ease;
            border-radius: 8px;
        }

        button:hover {
            background-color: #1557b0;
        }

        .icon-container {
            position: absolute;
            right: 15px;
            top: 50%;
            transform: translateY(-50%);
            color: #1a73e8;
        }

        .icon-container i {
            font-size: 1.4rem;
        }

        .form-group input, .form-group select {
            padding-right: 40px;
        }

        .error-message {
            color: #ff4f4f;
            font-size: 0.875rem;
            margin-top: 10px;
            display: none;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-20px); }
            to { opacity: 1; transform: translateY(0); }
        }

        .tooltip {
            position: absolute;
            background-color: #333;
            color: white;
            border-radius: 5px;
            padding: 5px;
            font-size: 0.9rem;
            top: -25px;
            left: 10px;
            visibility: hidden;
        }

        .form-group:hover .tooltip {
            visibility: visible;
        }

    </style>
</head>
<body>

    
    <div class="navbar">
        <div class="title">AUCA Library Shelf Management</div>
        <a href="home.html">Home</a>
        <a href="shelfForm.jsp">Manage Shelves</a>
        <a href="login.html">Logout</a>
    </div>

    
    <div class="form-container">
        <h2>${isUpdate ? "Update Shelf" : "Create New Shelf"}</h2>

        <form action="shelf" method="post">
            <input type="hidden" name="action" value="${isUpdate ? 'update' : 'create'}">
            <input type="hidden" name="id" value="${isUpdate ? shelf.shelfId : ''}">

            <div class="form-group">
                <label for="availableStock">Available Stock:</label>
                <input type="number" id="availableStock" name="availableStock" value="${isUpdate ? shelf.availableStock : ''}" required>
                <div class="icon-container"><i class="fas fa-cogs"></i></div>
                <div class="tooltip">Stock available in the shelf</div>
            </div>

            <div class="form-group">
                <label for="bookCategory">Book Category:</label>
                <input type="text" id="bookCategory" name="bookCategory" value="${isUpdate ? shelf.bookCategory : ''}" required>
                <div class="icon-container"><i class="fas fa-book"></i></div>
                <div class="tooltip">Category of books on the shelf</div>
            </div>

            <div class="form-group">
                <label for="borrowedNumber">Borrowed Number:</label>
                <input type="number" id="borrowedNumber" name="borrowedNumber" value="${isUpdate ? shelf.borrowedNumber : ''}" required>
                <div class="icon-container"><i class="fas fa-users"></i></div>
                <div class="tooltip">Number of books already borrowed</div>
            </div>

            <div class="form-group">
                <label for="initialStock">Initial Stock:</label>
                <input type="number" id="initialStock" name="initialStock" value="${isUpdate ? shelf.initialStock : ''}" required>
                <div class="icon-container"><i class="fas fa-box-open"></i></div>
                <div class="tooltip">Initial stock available when the shelf was created</div>
            </div>

            <div class="form-group">
                <label for="roomId">Room Code:</label>
                <select id="roomId" name="roomId" required>
                 <option value="" disabled ${isUpdate ? '' : 'selected'}>Select Room</option>
                    <c:forEach var="room" items="${rooms}">
                 <option value="${room.roomId}" ${isUpdate && shelf.room != null && shelf.room.roomId == room.roomId ? 'selected' : ''}>
                            ${room.roomCode}
                        </option>
                  </c:forEach>
                </select>
                <div class="icon-container"><i class="fas fa-door-open"></i></div>
                <div class="tooltip">Select the room this shelf belongs to</div>
            </div>

            <button type="submit">${isUpdate ? "Update Shelf" : "Create Shelf"}</button>
        </form>

        <div id="error-message" class="error-message">Please fill in all fields!</div>
    </div>

</body>
</html>
