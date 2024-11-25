<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Borrow Book</title>


    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Roboto', sans-serif;
        }

        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            min-height: 100vh;
            background: linear-gradient(145deg, #1c2b32, #3c4b58);
            color: #fff;
            padding-top: 80px; /* Space for navbar */
        }

        /* Navbar Styling */
        .navbar {
            width: 100%;
            padding: 15px 20px;
            background: rgba(0, 0, 0, 0.2);
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
            display: flex;
            justify-content: space-between;
            align-items: center;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 1000;
            backdrop-filter: blur(10px);
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
            padding: 5px 10px;
            border-radius: 8px;
            transition: background-color 0.3s ease;
        }

        .navbar a:hover {
            background-color: #1557b0;
        }

      
        .container {
            background: rgba(0, 0, 0, 0.3);
            padding: 2.5rem;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
            backdrop-filter: blur(10px);
            max-width: 500px;
            width: 100%;
            animation: fadeIn 0.8s ease-in-out;
            margin-top: 120px;
        }

        h2 {
            text-align: center;
            font-size: 2rem;
            color: #fff;
            margin-bottom: 2rem;
        }

        .form-group {
            margin-bottom: 1.5rem;
            position: relative;
        }

        label {
            color: #b0b3b5;
            font-size: 0.875rem;
            font-weight: 500;
            margin-bottom: 0.5rem;
            display: block;
        }

        input[type="text"],
        input[type="date"],
        input[type="number"] {
            width: 100%;
            padding: 0.75rem;
            border: 1.5px solid transparent;
            border-radius: 8px;
            font-size: 1rem;
            color: #ffffff;
            background: rgba(255, 255, 255, 0.15);
            transition: all 0.3s ease;
            outline: none;
        }

        input:focus {
            border-color: #1a73e8;
            box-shadow: 0 0 0 4px rgba(26, 115, 232, 0.1);
        }

        .input-icon {
            position: absolute;
            right: 1rem;
            top: 1.25rem;
            color: #b0b3b5;
            transition: color 0.3s ease;
        }

        input:focus + .input-icon {
            color: #1a73e8;
        }

        button {
            width: 100%;
            padding: 0.875rem;
            background-color: #1a73e8;
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 1rem;
            font-weight: 500;
            cursor: pointer;
            transition: all 0.3s ease;
            display: flex;
            justify-content: center;
            align-items: center;
            gap: 8px;
            box-shadow: 0 4px 10px rgba(26, 115, 232, 0.4);
        }

        button:hover {
            background-color: #1557b0;
            box-shadow: 0 6px 12px rgba(26, 115, 232, 0.6);
        }

        .alert {
            background-color: #28a745;
            color: white;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 8px;
            font-size: 1rem;
            width: 100%;
            text-align: center;
        }

        .alert-error {
            background-color: #ff4444;
        }

        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(-30px);
            }

            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
    </style>
</head>
<body>

   
    <div class="navbar">
        <div class="title">AUCA Library Borrow Book of your wishes </div>
        <a href="studenthome.html">Home</a>
        <a href="login.html">Logout</a>
    </div>

 
    <div class="container">
        <h2><i class="fas fa-book"></i> Borrow Book</h2>

        
        <c:if test="${not empty message}">
            <div class="alert">${message}</div>
        </c:if>

        <form action="borrow-book" method="POST">
           
            <div class="form-group">
                <label for="borrowerName">Borrower's Name</label>
                <input type="text" id="borrowerName" name="borrowerName" required />
                <i class="fas fa-user input-icon"></i>
            </div>

          
            <div class="form-group">
                <label for="bookTitle">Book Title</label>
                <input type="text" id="bookTitle" name="bookTitle" required />
                <i class="fas fa-book-open input-icon"></i>
            </div>

           
            <div class="form-group">
                <label for="borrowDate">Borrow Date</label>
                <input type="date" id="borrowDate" name="borrowDate" required />
                <i class="fas fa-calendar-day input-icon"></i>
            </div>

           
            <div class="form-group">
                <label for="dueDate">Due Date</label>
                <input type="date" id="dueDate" name="dueDate" required />
                <i class="fas fa-calendar-alt input-icon"></i>
            </div>

            
            <div class="form-group">
                <label for="chargeFees">Charge Fees</label>
                <input type="number" id="chargeFees" name="chargeFees" required min="0" />
                <i class="fas fa-dollar-sign input-icon"></i>
            </div>

          
            <div>
                <button type="submit">
                    <i class="fas fa-check-circle"></i> Submit
                </button>
            </div>
        </form>
    </div>

</body>
</html>
