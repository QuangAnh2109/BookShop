<%-- 
    Document   : register
    Created on : Jul 10, 2024, 4:31:33 PM
    Author     : anh21
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <div>
        <h2>Account Register</h2>
        <form action="register" method="get">
            Account name<br>
            <input type="text" name="accountName" required><br>
            Password<br>
            <input type="password" name="password" required><br>
            Full name<br>
            <input type="text" name="name" required><br>
            Address<br>
            <input type="text" name="address" required><br>
            Phone number<br>
            <input type="tel" name="phoneNumber" placeholder="123-45-678" pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}" required><br><br>
            <button type="submit">Register</button><br>
            <%
                Integer registerStatus = (Integer)request.getAttribute("registerStatus");
                out.println(registerStatus);
                if(registerStatus!=null){ 
                    if(registerStatus==0)out.println("Account name already exist");
                    else out.println("Register complete");
                }
            %>
        </form>
        <p>
            Have account? <a href="login.jsp">Login</a>
        </p>
    </div>
    </body>
</html>
