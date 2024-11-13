<%-- 
    Document   : editprofile
    Created on : Jul 16, 2024, 4:33:24 AM
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
        <jsp:include page="header.jsp"/>
        <h1>Edit profile</h1>
        <%
            Member account = (Member)session.getAttribute("account");
            if(account!=null){
        %>
        <form action="changeaccountprofile" method="post">
            Password<br>
            <input type="password" name="password" required value="<%out.println(account.getName());%>"><br>
            Full name<br>
            <input type="text" name="name" value="<%out.println(account.getName());%>" required><br>
            Address<br>
            <input type="text" name="address" value="<%out.println(account.getAddress());%>" required><br>
            Phone number<br>
            <input type="tel" name="phoneNumber" placeholder="123-45-678" pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}" required value="<%out.println(account.getPhoneNumber());%>"><br><br>
            <button type="submit">Save</button><br>
        </form>
        <%}%>
    </body>
</html>
