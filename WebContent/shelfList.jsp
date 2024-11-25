<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Shelf" %>
<%@ page import="model.Room" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shelf List</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
<body>

<h2>Shelf List</h2>

<!-- Displaying a message, if any --> 
<c:if test="${not empty message}">
    <div style="color: green; font-weight: bold;">
        ${message}
    </div>
</c:if>

<!-- Button to create a new shelf -->
<a href="shelfForm.jsp?action=create">
    <button>Create New Shelf</button>
</a>

<!-- Shelf List Table -->
<table>
    <thead>
        <tr>
            <th>Shelf ID</th>
            <th>Book Category</th>
            <th>Available Stock</th>
            <th>Borrowed Number</th>
            <th>Initial Stock</th>
            <th>Room</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:choose>
            <c:when test="${not empty shelves}">
                <c:forEach var="shelf" items="${shelves}">
                    <tr>
                        <td>${shelf.shelfId}</td>
                        <td>${shelf.bookCategory}</td>
                        <td>${shelf.availableStock}</td>
                        <td>${shelf.borrowedNumber}</td>
                        <td>${shelf.initialStock}</td>
                        <!-- Display room code or 'N/A' if room is null -->
                        <td>${shelf.room != null ? shelf.room.roomCode : 'N/A'}</td>
                        <td>
                            <!-- Edit and Delete links with confirmation on delete -->
                            <a href="shelf?action=edit&id=${shelf.shelfId}">Edit</a> | 
                            <a href="shelf?action=delete&id=${shelf.shelfId}" 
                               onclick="return confirm('Are you sure you want to delete this shelf?');">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="7">No shelves available.</td>
                </tr>
            </c:otherwise>
        </c:choose>
    </tbody>
</table>

</body>
</html>
