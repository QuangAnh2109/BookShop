<%-- 
    Document   : Cart
    Created on : Jul 10, 2024, 9:12:34 PM
    Author     : anh21
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.CartItem" %>
<%@ page import="model.Book" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <table border="1">
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Img</th>
                    <th>Date add to cart</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Change</th>
                </tr>
            </thead>
            <%
            
            Book book;
            for(CartItem item:(ArrayList<CartItem>)request.getAttribute("cart")){
                book = item.getBook();
            %>
            <tbody>
                <tr>
                    <td><a href="book?id=<%out.print(book.getId());%>"><%out.print(book.getName());%></a></td>
                    <th><img src="Img/<%out.println(book.getId());%>.webp" width="100" height="100"></th>
                    <th><%out.print(item.getDate());%></th>
                    <th><%out.print(item.getQuantity());%></th>
                    <th><%out.print(book.getPrice());%></th>
                    <th>
                        <a href="addcart?bookID=<%out.print(book.getId());%>&quantity=-1"><button>-</button></a>
                        <a href="addcart?bookID=<%out.print(book.getId());%>&quantity=1"><button>+</button></a>
                        <a href="removecart?bookID=<%out.print(book.getId());%>"><button>Remove</button></a>
                    </th>
                </tr>
            </tbody>
            <%
            }
            %>
            
        </table>
    </body>
</html>
