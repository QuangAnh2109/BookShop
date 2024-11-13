<%-- 
    Document   : login
    Created on : Jul 10, 2024, 4:31:13 PM
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
        <h2>Account Login</h2>
        <form action="login" method="post">
            Account name<br>
            <input type="text" name="name" required><br>
            Password<br>
            <input type="password" name="password" required><br><br>
            <button type="submit">Login</button>
            <%
                Integer loginStatus = (Integer)request.getAttribute("loginStatus");
                if(loginStatus!=null)out.println("Mật khẩu hoặc tài khoản không tồn tại");
            %>
        </form>
        <p>
            Create an account? <a href="register.jsp">Register</a>
        </p>
    </div>
    </body>
</html>
