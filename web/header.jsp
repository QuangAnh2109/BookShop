<%-- 
    Document   : header
    Created on : Jul 10, 2024, 8:57:04 PM
    Author     : anh21
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Member"%>

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
                Member account = (Member)session.getAttribute("account");
                if(account==null){
            %>
                <a href="login">Login</a>&nbsp;&nbsp;&nbsp;
                <a href="register">Register</a>
            <%
                }else{
            %>
                <a href="accountprofile">Profile</a>&nbsp;&nbsp;&nbsp;
                <a href="cart">Cart</a>&nbsp;&nbsp;&nbsp;
                <a href="order">Order</a>&nbsp;&nbsp;&nbsp;
                <a href="logout">Logout</a>&nbsp;&nbsp;&nbsp;
                <%if(account.getRole()==0){%>
                    <a href="statistical">Statistical</a>&nbsp;&nbsp;&nbsp;
                    <a href="allaccount">Show all account</a>&nbsp;&nbsp;&nbsp;
                <%}%>
            <%
                }
            %>
        </h1>
    </body>
</html>
