<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Return Book</title>
    <style>
        /* General styles */
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
        }

        /* Form styles */
        form {
            background-color: #ffffff;
            max-width: 500px;
            margin: 20px auto;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        label {
            margin-bottom: 5px;
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

        /* Fine details */
        .fine-details {
            background-color: #e8f4f8;
            max-width: 500px;
            margin: 20px auto;
            padding: 20px;
            border-radius: 8px;
            text-align: center;
            border: 1px solid #b3d9ea;
        }

        .fine-details p {
            font-size: 18px;
            color: #006699;
        }
    </style>
</head>
<body>
<h1>Return Book</h1>

<form action="calculateFine" method="post">
    <label for="borrowerName">Borrower Name:</label>
    <input type="text" id="borrowerName" name="borrowerName" required>

    <label for="returnDate">Return Date:</label>
    <input type="date" id="returnDate" name="returnDate" required>

    <label for="dueDate">Due Date:</label>
    <input type="date" id="dueDate" name="dueDate" required>

    <button type="submit">Calculate Fine</button>
</form>

<c:if test="${not empty fine}">
    <div class="fine-details">
        <h2>Fine Details</h2>
        <p>Borrower: ${borrowerName}</p>
        <p>Fine Amount: ${fine} RWF</p>
    </div>
</c:if>

</body>
</html>
