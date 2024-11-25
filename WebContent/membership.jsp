<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Membership Management</title>
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

        h1 {
            text-align: center;
            color: #ffffff;
            margin: 20px 0;
        }

        /* Form Styles */
        form {
            background-color: #ffffff;
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            color: #333;
        }

        form label {
            display: block;
            font-size: 14px;
            color: #004466;
            margin-top: 10px;
        }

        form input, form select, form button {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 10px;
            border: 1px solid #ccd1d9;
            border-radius: 4px;
            font-size: 16px;
        }

        form input:focus, form select:focus {
            outline: none;
            border-color: #006699;
            box-shadow: 0 0 4px rgba(0, 102, 153, 0.4);
        }

        form button {
            background-color: #006699;
            color: #ffffff;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        form button:hover {
            background-color: #004466;
        }

        /* Table Styles */
        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #ffffff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        table thead {
            background-color: #006699;
            color: #ffffff;
        }

        table th, table td {
            padding: 12px 15px;
            text-align: left;
            border: 1px solid #ddd;
        }

        table tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        table tbody tr:hover {
            background-color: #e8f4f8;
        }

        /* Icons and Details */
        .icon {
            margin-right: 10px;
            color: #006699;
        }
    </style>
</head>
<body>
<h1><i class="fas fa-users icon"></i>Membership Management</h1>

<!-- Form to add new membership -->
<form action="membership" method="post">
    <label for="membershipCode"><i class="fas fa-key icon"></i> Membership Code:</label>
    <input type="text" id="membershipCode" name="membershipCode" required>

    <label for="membershipType"><i class="fas fa-id-card-alt icon"></i> Membership Type:</label>
    <select id="membershipType" name="membershipType" required>
        <option value="Gold">Gold: 50 RWF/day, up to 5 books</option>
        <option value="Silver">Silver: 30 RWF/day, up to 3 books</option>
        <option value="Striver">Striver: 10 RWF/day, up to 2 books</option>
    </select>

    <label for="readerId"><i class="fas fa-user icon"></i> Reader ID:</label>
    <input type="text" id="readerId" name="readerId" required>

    <label for="expiryDate"><i class="fas fa-calendar-alt icon"></i> Expiry Date:</label>
    <input type="date" id="expiryDate" name="expiryDate" required>

    <button type="submit"><i class="fas fa-plus-circle icon"></i> Register Membership</button>
</form>

<!-- List of memberships -->
<table>
    <thead>
    <tr>
        <th><i class="fas fa-key icon"></i> Code</th>
        <th><i class="fas fa-id-card-alt icon"></i> Type</th>
        <th><i class="fas fa-check-circle icon"></i> Status</th>
        <th><i class="fas fa-calendar-alt icon"></i> Expiry</th>
        <th><i class="fas fa-user icon"></i> Reader</th>
        <th><i class="fas fa-clock icon"></i> Registration Date</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="membership" items="${memberships}">
        <tr>
            <td>${membership.membershipCode}</td>
            <td>${membership.membershipType}</td>
            <td>${membership.status}</td>
            <td>${membership.expiryDate}</td>
            <td>${membership.readerId}</td>
            <td>${membership.registrationDate}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
