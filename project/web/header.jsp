<%-- 
    Document   : header
    Created on : Jul 10, 2024, 8:57:04 PM
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
        <h1>
            <a href="home">Home</a>&nbsp;&nbsp;&nbsp;
            <%
                if(session.getAttribute("account")==null){
            %>
                <a href="login">Login</a>&nbsp;&nbsp;&nbsp;
                <a href="register">Register</a>
            <%
                }else{
            %>
                <a href="accountprofile">Profile</a>&nbsp;&nbsp;&nbsp;
                <a href="cart">Cart</a>&nbsp;&nbsp;&nbsp;
                <a href="logout">Logout</a>
            <%
                }
            %>
        </h1>
    </body>
</html>
