<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Librarian Membership Management</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f4f4f4;
        }
        .btn {
            padding: 5px 10px;
            text-decoration: none;
            color: white;
            border-radius: 3px;
        }
        .btn-approve {
            background-color: green;
        }
        .btn-reject {
            background-color: red;
        }
    </style>
</head>
<body>
    <h1>Manage Memberships</h1>
    <table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Code</th>
        <th>Type</th>
        <th>Status</th>
        <th>Expiry</th>
        <th>Reader</th>
        <th>Registration Date</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="membership" items="${memberships}">
        <tr>
            <td>${membership.membershipId}</td>
            <td>${membership.membershipCode}</td>
            <td>${membership.membershipType}</td>
            <td>${membership.status}</td>
            <td>${membership.expiryDate}</td>
            <td>${membership.readerId}</td>
            <td>${membership.registrationDate}</td>
            <td>
                <!-- Approve Button -->
                <form action="membershipAction" method="post" style="display:inline;">
                    <input type="hidden" name="membershipId" value="${membership.membershipId}">
                    <input type="hidden" name="action" value="approve">
                    <button type="submit">Approve</button>
                </form>
                <!-- Reject Button -->
                <form action="membershipAction" method="post" style="display:inline;">
                    <input type="hidden" name="membershipId" value="${membership.membershipId}">
                    <input type="hidden" name="action" value="reject">
                    <button type="submit" style="background-color: red; color: white;">Reject</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
