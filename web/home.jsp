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
            ArrayList<Book> books = (ArrayList<Book>)session.getAttribute("books");
            if(books==null) response.sendRedirect("home");
        %>
        <form action="home" method="get">
            Search<br>
            <input type="text" name="search" value=""><button type="submit">Search</button><br><br>
            Category<br>
            <%
                for(Category i:(ArrayList<Category>)session.getAttribute("category")){
            %>
            <input type="checkbox" name="category" value="<%out.println(i.getId());%>"><%out.println(i.getCategory());%><br>
            <%
                }
            %>
            
            Company<br>
            <%
                for(Company i:(ArrayList<Company>)session.getAttribute("company")){
            %>
            <input type="checkbox" name="company" value="<%out.println(i.getId());%>"><%out.println(i.getCompanyName());%><br>
            <%
                }
            %>
            
            Cover<br>
            <%
                for(CoverType i:(ArrayList<CoverType>)session.getAttribute("coverType")){
            %>
            <input type="checkbox" name="coverType" value="<%out.println(i.getId());%>"><%out.println(i.getCover());%><br>
            <%
                }
            %>
            Sort<br>
            <input type="radio" name="sort" value="1">Giá tăng dần<br>
            <input type="radio" name="sort" value="2">Giá Giảm dần<br>
            
        </form><br>
        <%
                int numberPage;
                if(books.size()%5==0) numberPage=books.size()/5;
                else numberPage=books.size()/5+1;
                for(int i=1;i<=numberPage;i++){
                
                
            %>
            <a href="home.jsp?page=<%out.println(i);%>"><%out.println(i);%></a>&nbsp;
            <%}%>
        <br><br><table border="1">
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
                    int start,end,page1;
                    if(request.getParameter("page")==null) page1 = 1;
                    else{
                        try{
                            page1 = Integer.parseInt(request.getParameter("page"));
                        }catch(NumberFormatException e){
                            page1=1;
                        }
                    }
                    start=5*(page1-1);
                    if(page1>=numberPage ){
                        if(books.size()%5!=0){
                            end = start+books.size()%5;
                        }else{
                            end = start+5;
                        }
                    }
                    else{
                        end = start+5;
                    }
                    for(int i=start;i<end;i++){
                    Book book = books.get(i);
                %>
                <tr>
                    <td><a href="book?id=<%out.println(book.getId());%>"><%out.println(book.getName());%></a></td>
                    <td><img src="Img/<%out.println(book.getId());%>.webp" width="100" height="100"></td>
                    <td><%out.println(book.getPrice());%></td>
                    <%if(request.getSession().getAttribute("account")!=null){%>
                    <td>
                        <form action="addcart" type="get">
                            <input type="number" name="quantity" min="1" value="1"/>
                            <input type="checkbox" name="bookID" value="<%out.print(book.getId());%>" onchange="this.form.submit()"/>Add
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
