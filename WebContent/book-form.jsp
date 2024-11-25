<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Entry Form</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

      
        body {
            background: linear-gradient(145deg, #0f2027, #2c5364, #203a43);
            color: white;
        }

     
        .navbar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 1rem 2rem;
            background-color: rgba(0, 0, 0, 0.7);
            position: sticky;
            top: 0;
            z-index: 1000;
        }

        .navbar .logo {
            font-size: 1.5rem;
            font-weight: 700;
            color: white;
            display: flex;
            align-items: center;
        }

        .navbar .logo img {
            height: 40px;
            margin-right: 8px;
        }

        .navbar ul {
            list-style: none;
            display: flex;
            gap: 20px;
        }

        .navbar ul li {
            display: inline;
        }

        .navbar ul li a {
            color: white;
            text-decoration: none;
            font-weight: 500;
            font-size: 1rem;
            transition: color 0.3s ease;
        }

        .navbar ul li a:hover {
            color: #1a73e8;
        }

     
        .form-container {
            max-width: 500px;
            margin: 5rem auto;
            padding: 2rem;
            border: 1px solid #ddd;
            border-radius: 8px;
            background-color: rgba(255, 255, 255, 0.1);
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
            backdrop-filter: blur(10px);
        }

        h2 {
            color: white;
            text-align: center;
            margin-bottom: 1.5rem;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
            color: #b0b3b5;
        }

        input[type="text"],
        input[type="date"],
        input[type="number"],
        select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            border-radius: 4px;
            border: 1px solid #ddd;
            background-color: rgba(255, 255, 255, 0.15);
            color: white;
        }

        select {
            background-image: url("data:image/svg+xml,%3csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 24 24' fill='none' stroke='%23333333' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'%3e%3cpolyline points='6 9 12 15 18 9'%3e%3c/polyline%3e%3c/svg%3e");
            background-repeat: no-repeat;
            background-position: right 1rem center;
            background-size: 1em;
        }

        input:focus, select:focus {
            border-color: #1a73e8;
            box-shadow: 0 0 0 4px rgba(26, 115, 232, 0.1);
        }

        button {
            width: 100%;
            padding: 10px;
            background-color: #1a73e8;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: all 0.3s ease;
        }

        button:hover {
            background-color: #1557b0;
        }

       
        .success-message, .error-message {
            padding: 10px;
            font-weight: bold;
            border-radius: 5px;
            margin-bottom: 15px;
        }

        .success-message {
            background-color: #28a745;
            color: white;
        }

        .error-message {
            background-color: #dc3545;
            color: white;
        }

    </style>
</head>
<body>


<div class="navbar">
    <div class="logo">
        <img src="images/123.png" alt="AUCA Logo"> 
        <span>AUCA Library</span>
    </div>
    <ul>
        <li><a href="home.html">Home</a></li>
        <li><a href="login.html">Login</a></li>
        <li><a href="bookList.jsp">Book List</a></li>
        <li><a href="#">About</a></li>
        <li><a href="#">Contact</a></li>
    </ul>
</div>


<div class="form-container">
    <h2>Book Entry Form</h2>

  
    <% 
        String message = (String) request.getAttribute("message");
        if (message != null) {
            if (message.equals("Book saved successfully")) { 
    %>
                <div class="success-message">
                    <%= message %>
                </div>
    <% 
            } else {
    %>
                <div class="error-message">
                    <%= message %>
                </div>
    <% 
            }
        }
    %>

  
    <form action="new-book" method="post">
        <div class="form-group">
            <label for="bookTitle">Book Title:</label>
            <input type="text" id="bookTitle" name="bookTitle" required>
        </div>
        <div class="form-group">
            <label for="isbnCode">ISBN Code:</label>
            <input type="text" id="isbnCode" name="isbnCode" required>
        </div>
        <div class="form-group">
            <label for="publisherName">Publisher Name:</label>
            <input type="text" id="publisherName" name="publisherName" required>
        </div>
        <div class="form-group">
            <label for="edition">Edition:</label>
            <input type="text" id="edition" name="edition" required>
        </div>
        <div class="form-group">
            <label for="publication">Publication Date:</label>
            <input type="date" id="publication" name="publication_year" required>
        </div>
        <div class="form-group">
            <label for="StatusSelect">Status:</label>
            <select id="StatusSelect" name="StatusSelect" required>
                <option value="">Select Status</option>
                <option value="AVAILABLE">Available</option>
                <option value="BORROWED">Borrowed</option>
                <option value="RESERVED">Reserved</option> 
            </select>
        </div>
        <div class="form-group">
         <label for="ShelfSelect">Shelves:</label>
          <select id="ShelfSelect" name="ShelfSelect" required>
                <option value="">Select Shelf</option>
                <option value="SCIENCE">Science</option>
                <option value="HISTORY">History</option>
                <option value="LANGUAGE">Language</option> 
            </select>
        
        </div>
        <button type="submit">Add Book</button>
    </form>
</div>

</body>
</html>