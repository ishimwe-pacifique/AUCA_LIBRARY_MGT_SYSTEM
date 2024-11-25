<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forgot Password - AUCA Library</title>
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

        .forgot-password-container {
            background: rgba(255, 255, 255, 0.1);
            border-radius: 15px;
            padding: 30px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.3);
            max-width: 400px;
            width: 100%;
            text-align: center;
            backdrop-filter: blur(10px);
        }

        .forgot-password-container h2 {
            margin-bottom: 20px;
            font-size: 1.75rem;
            font-weight: 700;
            color: white;
        }

        .forgot-password-container p {
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

        .back-link {
            margin-top: 15px;
            display: block;
            color: #1a73e8;
        }

        .back-link a {
            text-decoration: none;
            color: #1a73e8;
        }

        .back-link a:hover {
            color: #1557b0;
        }
    </style>
</head>
<body>

    <div class="forgot-password-container">
        <h2>Forgot Password</h2>
        <p>Enter your username or email to receive a password reset link.</p>

        <form action="resetPasswordRequest" method="POST">
            <div class="form-group">
                <label for="emailOrUsername">Email or Username:</label>
                <input type="text" id="emailOrUsername" name="emailOrUsername" required>
            </div>
            <button type="submit" class="btn"><a href="reset-password.jsp">Send Reset Link</a></button>
        </form>

        <p class="back-link">
            Remember your password? <a href="login.html">Login here</a>
        </p>
    </div>

</body>
</html>
