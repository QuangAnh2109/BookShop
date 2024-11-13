<%-- 
    Document   : profile
    Created on : Jul 11, 2024, 10:54:53 AM
    Author     : khanh
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
        <h1>Profile</h1>
        <%
            Member account = (Member)session.getAttribute("account");
            if(account!=null){
        %>
        <p>Account name: <%out.println(account.getAccountName());%></p>
        <p>Name: <%out.println(account.getName());%></p>
        <p>Phone number: <%out.println(account.getPhoneNumber());%></p>
        <p>Address: <%out.println(account.getAddress());%></p>
        <p>Role: <%out.println(account.getRole());%></p>
        <h1><%
                Integer registerStatus = (Integer)request.getAttribute("registerStatus");
                if(registerStatus!=null){ 
                    if(registerStatus==0)out.println("Edit faild");
                    else out.println("Edit complete");
                }
                %></h1>
        <%}%>
        <a href="editprofile.jsp"><button>Edit</button></a>
    </body>
</html>
