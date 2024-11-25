<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Membership Approval</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        button {
            margin-right: 5px;
            padding: 5px 10px;
        }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(145deg, #0f2027, #2c5364, #203a43);
            color: #333;
        }
    </style>
</head>
<body>
    <h1>Membership Approval</h1>

    <c:choose>
        <c:when test="${empty memberships}">
            <p>No pending memberships at the moment.</p>
        </c:when>
        <c:otherwise>
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Membership Code</th>
                        <th>Type</th>
                        <th>Reader ID</th>
                        <th>Registration Date</th>
                        <th>Expiry Date</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="membership" items="${memberships}">
                        <tr>
                            <td>${membership.membershipId}</td>
                            <td>${membership.membershipCode}</td>
                            <td>${membership.membershipType}</td>
                            <td>${membership.readerId}</td>
                            <td>${membership.registrationDate}</td>
                            <td>${membership.expiryDate}</td>
                            <td>${membership.status}</td>
                            <td>
                                <form method="post" action="MembershipApprovalServlet">
                                    <input type="hidden" name="membershipId" value="${membership.membershipId}">
                                    <button type="submit" name="action" value="approve">Approve</button>
                                    <button type="submit" name="action" value="reject">Reject</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

   
</body>
</html>
