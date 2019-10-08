<%-- 
    Document   : bookList
    Created on : Mar 27, 2019, 8:19:00 PM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
          <h1>List of book</h1>
          
          <h2><a href="BookController?action=addnew">ADD NEW BOOK</a></h2>
          <form action="BookController" method="post">
              <input type="text" name="search" placeholder="name or email" required>
              <input type="hidden" name="action"  value="search">
              <input type="submit" value="Search">
          </form>
        <table>
            <tr>
                <th>Book ID</th>
                <th>Book Name </th>
                <th>Author</th>
                <th>Category Name</th>
                <th>Isbn</th>
                <th>NumberOfPage</th>
                <th>Price</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="b" items="${bookList}">
                <tr>
                    <td>${b.id}</td>
                    <td>${b.name}</td>
                    <td>${b.author}</td>
                    <td>${b.category.name}</td>
                    <td>${b.bookDetail.isbn}</td>
                    <td>${b.bookDetail.numberOfPage}</td>
                    <td>${b.bookDetail.price}</td>
                    <td><a href="BookController?action=edit&amp;id=${b.id}">Edit</a></td>
                    <td>
                        <form action="BookController" method="post">
                            <input type="hidden" name="id" value="${b.id}">
                            <input type="hidden" name="action" value="delete">                            
                            <input type="submit" value="Delete">
                                   
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
          <form action="BookController" method="post">
              <input type="hidden" name="action" value="add">
              <input type="submit" value="ADD">
          </form>
    </body>
</html>
