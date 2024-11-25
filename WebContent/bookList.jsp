<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Book" %>
<%@ page import="model.BookStatus" %>
<%@ page import="model.Shelf" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book List</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
    
        body {
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(145deg, #0f2027, #2c5364, #203a43);
            color: #ffffff;
            margin: 0;
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #ffffff;
            margin-bottom: 30px;
            font-size: 2rem;
        }

 
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 30px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #1a73e8;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #2c5364;
        }

    
        .button {
            padding: 10px 15px;
            background-color: #1a73e8;
            color: white;
            border: none;
            border-radius: 8px;
            text-decoration: none;
            font-size: 1rem;
            transition: background-color 0.3s;
            display: flex;
            align-items: center;
            gap: 8px;
        }

        .button:hover {
            background-color: #1557b0;
        }

        .button i {
            font-size: 1.2rem;
        }

       
        .action-btns {
            display: flex;
            justify-content: space-between;
            margin-bottom: 20px;
        }

        .action-btns a {
            padding: 8px 15px;
            background-color: #4CAF50;
            color: white;
            border-radius: 8px;
            text-decoration: none;
            font-size: 1rem;
            transition: background-color 0.3s ease;
            display: flex;
            align-items: center;
            gap: 8px;
        }

        .action-btns a:hover {
            background-color: #45a049;
        }

      
        .action-btns a i {
            font-size: 1.1rem;
        }
    </style>
</head>
<body>

    <h1>Book List</h1>

    
    <div class="action-btns">
        <a href="book-form.jsp" class="button"><i class="fas fa-plus"></i> Add New Book</a>
    </div>

   
    <table>
        <thead>
            <tr>
                <th>Book ID</th>
                <th>Title</th>
                <th>ISBN</th>
                <th>Edition</th>
                <th>Publisher</th>
                <th>Publication Year</th>
                <th>Status</th>
                <th>Shelf</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            
            <c:forEach var="book" items="${bookList}">
                <tr>
                    <td>${book.bookId}</td>
                    <td>${book.title}</td>
                    <td>${book.ISBNCode}</td>
                    <td>${book.edition}</td>
                    <td>${book.publisherName}</td>
                    <td>${book.publicationYear}</td>
                    <td>${book.status}</td>
                    <td>${book.shelf != null ? book.shelf.room.roomCode : 'N/A'}</td>
                    <td class="action-btns">
                        
                        <a href="editBook.jsp?bookId=${book.bookId}" class="button">
                            <i class="fas fa-edit"></i> Edit
                        </a>
                        
                      
                        <a href="deleteBook.jsp?bookId=${book.bookId}" class="button">
                            <i class="fas fa-trash-alt"></i> Delete
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</body>
</html>
