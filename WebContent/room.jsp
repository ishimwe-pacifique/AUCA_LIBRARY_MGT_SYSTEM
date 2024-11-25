<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.Room" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Rooms</title>
    <style>
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>

    <h1 style="text-align: center;">List of Rooms</h1>

    <table>
        <thead>
            <tr>
                <th>Room ID</th>
                <th>Room Code</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <!-- Loop through the rooms list and display each room -->
            <c:forEach var="room" items="${rooms}">
                <tr>
                    <td>${room.roomId}</td>
                    <td>${room.roomCode}</td>
                    <td>
                        <!-- Here you can add buttons for editing, deleting, etc -->
                        <a href="editRoom?roomId=${room.roomId}">Edit</a> |
                        <a href="deleteRoom?roomId=${room.roomId}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
