<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Find Location by Phone Number - AUCA Library</title>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background: linear-gradient(145deg, #0f2027, #2c5364, #203a43);
            color: white;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .location-container {
            background: rgba(255, 255, 255, 0.1);
            border-radius: 15px;
            padding: 30px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.3);
            max-width: 400px;
            width: 100%;
            text-align: center;
            backdrop-filter: blur(10px);
        }

        .location-container h2 {
            margin-bottom: 20px;
            font-size: 1.75rem;
            font-weight: 700;
            color: white;
        }

        .location-container p {
            color: #b0b3b5;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 15px;
            text-align: left;
        }

        .form-group label {
            font-weight: bold;
            color: #b0b3b5;
        }

        .form-group input {
            width: 100%;
            padding: 12px;
            border: 1.5px solid transparent;
            border-radius: 8px;
            background: rgba(255, 255, 255, 0.15);
            font-size: 16px;
            color: #fff;
            transition: all 0.3s ease;
            outline: none;
        }

        .form-group input:focus {
            border-color: #1a73e8;
            box-shadow: 0 0 0 4px rgba(26, 115, 232, 0.1);
        }

        .btn {
            background: #1a73e8;
            color: white;
            padding: 12px 30px;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
            width: 100%;
            margin-top: 10px;
            transition: background-color 0.3s ease;
        }

        .btn:hover {
            background: #1557b0;
        }

        .error-message {
            color: red;
            font-size: 14px;
            margin-top: 10px;
        }

        .result-message {
            color: #b0e57c;
            font-size: 16px;
            margin-top: 15px;
        }
    </style>
</head>
<body>

    <div class="location-container">
        <h2>Find Location by Phone Number</h2>
        <p>Enter your phone number to find the associated location.</p>

        <form action="findLocation" method="POST">
            <div class="form-group">
                <label for="phoneNumber">Phone Number:</label>
                <input type="text" id="phoneNumber" name="phoneNumber" required>
            </div>

            <button type="submit" class="btn">Find Location</button>

            <!-- Error or Result Message -->
            <div id="error-message" class="error-message">
                <c:if test="${not empty errorMessage}">
                    ${errorMessage}
                </c:if>
            </div>
            <div id="result-message" class="result-message">
                <c:if test="${not empty location}">
                    <p><strong>Location Name:</strong> ${location}</p>
                </c:if>
            </div>
        </form>

        <p class="back-link">
            <a href="home.html">Go back to home page</a>
        </p>
    </div>

</body>
</html>
