<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Room Book Count</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha512-Fo3rlrZj/k7ujTnHg4f+2WYlGdzZZkVapbVnbfgiD3g1hK0XXsSEv2g61UkkbCWiRY2sqwe95EaFg1C8rFmeZw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        /* General Styles */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(145deg, #0f2027, #2c5364, #203a43);
            color: #333;
        }

        h1, h2 {
            text-align: center;
            color: #006699;
            margin-bottom: 20px;
        }

        /* Form Styles */
        form {
            background-color: #ffffff;
            max-width: 500px;
            margin: 20px auto;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        label {
            display: flex;
            align-items: center;
            justify-content: start;
            margin-bottom: 5px;
            font-size: 16px;
            color: #004466;
        }

        input, button {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            border: 1px solid #ccd1d9;
            border-radius: 4px;
            font-size: 16px;
        }

        input:focus {
            outline: none;
            border-color: #006699;
            box-shadow: 0 0 4px rgba(0, 102, 153, 0.4);
        }

        button {
            background-color: #006699;
            color: #ffffff;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #004466;
        }

        /* Icons and Fine Details */
        .icon {
            margin-right: 10px;
            color: #006699;
        }

        .details {
            background-color: #e8f4f8;
            max-width: 500px;
            margin: 20px auto;
            padding: 20px;
            border-radius: 8px;
            text-align: center;
            border: 1px solid #b3d9ea;
        }

        .details p {
            font-size: 18px;
            color: #006699;
        }

        /* Error Message */
        .error-message {
            color: red;
            text-align: center;
            margin-top: 20px;
            font-size: 16px;
        }
    </style>
</head>
<body>
<h1><i class="fas fa-book icon"></i>Room Book Count</h1>

<!-- Form to enter room code -->
<form action="getRoomBookCount" method="post">
    <label for="roomCode"><i class="fas fa-door-closed icon"></i>Room Code:</label>
    <input type="text" id="roomCode" name="roomCode" placeholder="Enter Room Code" required>

    <button type="submit"><i class="fas fa-search icon"></i>Get Book Count</button>
</form>

<!-- Display book count if available -->
<c:if test="${not empty bookCount}">
    <div class="details">
        <h2><i class="fas fa-info-circle icon"></i>Room Details</h2>
        <p><strong>Room Code:</strong> ${roomCode}</p>
        <p><strong>Number of Books:</strong> ${bookCount}</p>
    </div>
</c:if>

<!-- Display error message if room not found -->
<c:if test="${not empty errorMessage}">
    <div class="error-message">
        <i class="fas fa-exclamation-triangle"></i> ${errorMessage}
    </div>
</c:if>
</body>
</html>
