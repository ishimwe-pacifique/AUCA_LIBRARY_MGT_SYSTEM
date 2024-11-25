<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Book" %>
<%@ page import="java.util.List" %>


<%
    if (request.getAttribute("books") == null) {
        response.sendRedirect("borrowBook");  // Redirect to call doGet in the servlet
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Borrow a Book</title>
    <link rel="icon"href="auca.jpg">
    <style>
		/* General page styling */
body {
    font-family: Arial, sans-serif;
    background-color: #f3f4f6;
    color: #333;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    margin: 0;
    padding: 20px;
    background-image: url(3.jpg);
}

/* Page title styling */
h2 {
    color: #4a90e2;
    margin-bottom: 1rem;
}

/* Table styling */
table {
    width: 100%;
    max-width: 800px;
    border-collapse: collapse;
    margin-top: 20px;
    background-color: #ffffff;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    border-radius: 8px;
    overflow: hidden;
}

th, td {
    padding: 12px;
    text-align: left;
    border-bottom: 1px solid #dddddd;
}

th {
    background-color: #4a90e2;
    color: white;
    font-weight: bold;
}

td {
    color: #555;
}

tr:nth-child(even) {
    background-color: #f9f9f9;
}

/* Button styling */
button {
    padding: 8px 12px;
    background-color: #4a90e2;
    color: white;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 0.9rem;
    font-weight: bold;
    transition: background-color 0.3s ease;
}

button:hover {
    background-color: #357abd;
}

button:active {
    background-color: #2d6a9c;
}

/* Message styling */
p {
    margin-top: 1rem;
    font-size: 1rem;
}

p.success {
    color: green;
}

p.error {
    color: red;
}
		    
    </style>
</head>
<body>
    <h2>Available Books for Borrowing</h2>
    
    <!-- error or success message -->
    
	 <%
        String errorMessage = (String) request.getAttribute("errorMessage");
        String successMessage = (String) request.getAttribute("successMessage");
        if (errorMessage != null) {
    %>
        <p class="error-message"><%= errorMessage %></p>
    <%
        } else if (successMessage != null) {
    %>
        <p class="success-message"><%= successMessage %></p>
    <%
        }
    %>

    <table>
        <thead>
            <tr>
                <th>Title</th>
                <th>ISBN Code</th>
                <th>Edition</th>
                <th>Author</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                List<Book> books = (List<Book>) request.getAttribute("books");
                if (books != null && !books.isEmpty()) {
                    for (Book book : books) {
            %>
                        <tr>
                            <td><%= book.getTitle() %></td>
                            <td><%= book.getISBNCode() %></td>
                            <td><%= book.getEdition() %></td>
                            <td><%= book.getPublisherName() %></td>
                            <td>
                                <!-- Borrow form -->
                                <form action="borrowBook" method="POST" style="display:inline;">
                                    <input type="hidden" name="bookId" value="<%= book.getBookId() %>">
                                    <button type="submit">Borrow</button>
                                </form>
                            </td>
                        </tr>
            <%
                    }
                } else {
            %>
                <tr>
                    <td colspan="5">No books available for borrowing.</td>
                </tr>
            <%
                }
            %>
        </tbody>
    </table>

    <!-- Display success or error messages if set -->
   <%--  <%
        String successMessage = request.getParameter("success");
        String errorMessage = request.getParameter("error");
        if ("BookBorrowed".equals(successMessage)) {
    %>
        <p class="success">Book borrowed successfully!</p>
    <%
        } else if ("BookNotFound".equals(errorMessage)) {
    %>
        <p class="error">Book not found!</p>
    <%
        }
    %> --%>
</body>
</html>

