<%-- 
    Document   : home
    Created on : Jun 11, 2024, 9:13:07 AM
    Author     : khanh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Book" %>
<%@ page import="model.Category" %>
<%@ page import="model.Company" %>
<%@ page import="model.CoverType" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <%
            ArrayList<Book> books = (ArrayList<Book>)request.getAttribute("books");
            if(books==null) response.sendRedirect("home");
        %>
        <form action="home" method="get">
            Search<br>
            <input type="text" name="search" value=""><button type="submit">Search</button><br><br>
            Category<br>
            <%
                for(Category i:(ArrayList<Category>)request.getAttribute("category")){
            %>
            <input type="checkbox" name="category" value="<%out.println(i.getId());%>"><%out.println(i.getCategory());%><br>
            <%
                }
            %>
            
            Company<br>
            <%
                for(Company i:(ArrayList<Company>)request.getAttribute("company")){
            %>
            <input type="checkbox" name="company" value="<%out.println(i.getId());%>"><%out.println(i.getCompanyName());%><br>
            <%
                }
            %>
            
            Cover<br>
            <%
                for(CoverType i:(ArrayList<CoverType>)request.getAttribute("coverType")){
            %>
            <input type="checkbox" name="coverType" value="<%out.println(i.getId());%>"><%out.println(i.getCover());%><br>
            <%
                }
            %>
        </form><br>
        <table border="1">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Img</th>
                    <th>Price</th>
                    <%if(request.getSession().getAttribute("account")!=null){%><th>Add to cart</th><%}%>
                </tr>
            </thead>
            <tbody>
                <%
                    for(Book i:books){
                %>
                <tr>
                    <td><a href="book?id=<%out.println(i.getId());%>"><%out.println(i.getName());%></a></td>
                    <td><img src="Img/<%out.println(i.getId());%>.webp" width="100" height="100"></td>
                    <td><%out.println(i.getPrice());%></td>
                    <%if(request.getSession().getAttribute("account")!=null){%>
                    <td>
                        <a href="addcart?id=<%out.println(i.getId());%>"></a>
                        <form action="addcart" type="get">
                            <input type="number" name="quantity" min="1" value="1"/>
                            <input type="checkbox" name="bookID" value="<%out.println(i.getId());%>" onchange="this.form.submit()"/>Add
                        </form>
                    </td>
                    <%}%>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

    </body>
</html>
