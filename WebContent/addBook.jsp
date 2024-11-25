<%@page import="controller.ShelfDao"%>
<%@page import="model.Shelf"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<% 
    // Redirect to assignBook servlet if shelves attribute is missing
    if (request.getAttribute("shelves") == null) {
        response.sendRedirect("assignBook");
        return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Book to Shelf</title>
    <link rel="icon" href="auca.jpg">
    <style>
        :root {
            --primary-color: #2563eb;
            --primary-hover: #1d4ed8;
            --background-color: #f8fafc;
            --card-background: #ffffff;
            --text-color: #1e293b;
            --border-color: #e2e8f0;
            --error-color: #ef4444;
            --success-color: #22c55e;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: system-ui, -apple-system, sans-serif;
            background-color: var(--background-color);
            color: var(--text-color);
            line-height: 1.5;
            min-height: 100vh;
            padding: 2rem 1rem;
              background-image: url(2.jpg);
        }

        .container {
            max-width: 32rem;
            margin: 0 auto;
        }

        .card {
            background-color: var(--card-background);
            border-radius: 1rem;
            box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 
                        0 2px 4px -1px rgba(0, 0, 0, 0.06);
            padding: 2rem;
        }

        .header {
            text-align: center;
            margin-bottom: 2rem;
        }

        .header h1 {
            color: var(--primary-color);
            font-size: 1.875rem;
            font-weight: 700;
            margin-bottom: 0.5rem;
        }

        .header p {
            color: #64748b;
            font-size: 0.875rem;
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        label {
            display: block;
            font-size: 0.875rem;
            font-weight: 500;
            margin-bottom: 0.5rem;
            color: #475569;
        }

        input, select {
            width: 100%;
            padding: 0.75rem 1rem;
            border: 1px solid var(--border-color);
            border-radius: 0.5rem;
            font-size: 1rem;
            transition: all 0.2s;
            background-color: #fff;
        }

        input:focus, select:focus {
            outline: none;
            border-color: var(--primary-color);
            box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.1);
        }

        .submit-btn {
            width: 100%;
            padding: 0.75rem 1.5rem;
            background-color: blue;
            color: white;
            border: none;
            border-radius: 0.5rem;
            font-size: 1rem;
            font-weight: 500;
            cursor: pointer;
            transition: background-color 0.2s;
        }

        .submit-btn:hover {
            background-color: var(--primary-hover);
        }

        .submit-btn:active {
            transform: scale(0.98);
        }

        .form-grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 1rem;
        }

        @media (max-width: 640px) {
            .form-grid {
                grid-template-columns: 1fr;
            }
        }

        .no-category-message {
            color: var(--error-color);
            font-size: 0.875rem;
            margin-top: 0.25rem;
        }

        /* Custom styles for date input */
        input[type="date"] {
            appearance: none;
            -webkit-appearance: none;
            padding-right: 0.5rem;
        }

        /* Error message styling */
        .error-message {
            color: var(--error-color);
            font-size: 0.875rem;
            margin-top: 0.25rem;
        }

        /* Success message styling */
        .success-message {
            color: var(--success-color);
            font-size: 0.875rem;
            margin-top: 0.25rem;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="card">
            <div class="header">
                <h1>Add Book to Shelf</h1>
                <p>Enter the book details to add it to your library</p>
            </div>

            <%-- Display error message if any --%>
            <% if (request.getAttribute("error") != null) { %>
                <div class="error-message">
                    <%= request.getAttribute("error") %>
                </div>
            <% } %>

            <%-- Display success message if any --%>
            <% if (request.getAttribute("success") != null) { %>
                <div class="success-message">
                    <%= request.getAttribute("success") %>
                </div>
            <% } %>

            <form action="assignBook" method="POST">
                <div class="form-group">
                    <label for="shelfCategory">Shelf Category</label>
                    <select name="shelfCategory" id="shelfCategory" required>
                        <%-- Check if shelves list exists and has items --%>
                        <% 
                            List<Shelf> shelves = (List<Shelf>) request.getAttribute("shelves");
                            if (shelves != null && !shelves.isEmpty()) {
                                for (Shelf shelf : shelves) {
                        %>
                            <option value="<%= shelf.getBookCategory() %>">
                                <%= shelf.getBookCategory() %>
                            </option>
                        <% 
                                }
                            } else { 
                        %>
                            <option value="">No categories available</option>
                        <% 
                            } 
                        %>
                    </select>
                </div>

                <div class="form-group">
                    <label for="title">Book Title</label>
                    <input type="text" id="title" name="title" required 
                           placeholder="Enter book title"
                           value="<%= request.getParameter("title") != null ? request.getParameter("title") : "" %>">
                </div>

                <div class="form-grid">
                    <div class="form-group">
                        <label for="isbnCode">ISBN Code</label>
                        <input type="text" id="isbnCode" name="isbnCode" required 
                               placeholder="Enter ISBN"
                               value="<%= request.getParameter("isbnCode") != null ? request.getParameter("isbnCode") : "" %>">
                    </div>

                    <div class="form-group">
                        <label for="edition">Edition</label>
                        <input type="number" id="edition" name="edition" required 
                               min="1" placeholder="Edition number"
                               value="<%= request.getParameter("edition") != null ? request.getParameter("edition") : "" %>">
                    </div>
                </div>

                <div class="form-grid">
                    <div class="form-group">
                        <label for="publicationYear">Publication Date</label>
                        <input type="date" id="publicationYear" name="publicationYear" required
                               value="<%= request.getParameter("publicationYear") != null ? request.getParameter("publicationYear") : "" %>">
                    </div>

                    <div class="form-group">
                        <label for="publisherName">Publisher</label>
                        <input type="text" id="publisherName" name="publisherName" required 
                               placeholder="Publisher name"
                               value="<%= request.getParameter("publisherName") != null ? request.getParameter("publisherName") : "" %>">
                    </div>
                </div>

                <button type="submit" class="submit-btn">Add Book to Shelf</button>
            </form>
        </div>
    </div>
</body>
</html>



