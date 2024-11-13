<%-- 
    Document   : book
    Created on : Jul 11, 2024, 8:41:16 AM
    Author     : anh21
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Book" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <%
            Book book = (Book)request.getAttribute("book");
            if(book==null) response.sendRedirect("home");
        %>
        <img src="Img/<%out.println(book.getId());%>.webp" width="300" height="300"><br><br>
        Book name:<%out.println(book.getName());%><br>
        Size:<%out.println(book.getSize());%><br>
        Author Name:<%out.println(book.getAuthorName());%><br>
        Number Pages:<%out.println(book.getNumberPages());%><br>
        Publishing Year:<%out.println(book.getPublishingYear());%><br>
        Price:<%out.println(book.getPrice());%><br>
        Weight:<%out.println(book.getWeight());%><br>
        Cover:<%out.println(book.getType().getCover());%><br>
        Category:<%out.println(book.getCategory().getCategory());%><br>
        Company<%out.println(book.getCompany().getCompanyName());%><br>
        Introduce:<%out.println(book.getIntroduce());%><br>
    </body>
</html>
