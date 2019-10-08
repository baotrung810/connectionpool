<%-- 
    Document   : Edit
    Created on : Apr 1, 2019, 4:14:40 PM
    Author     : Administrator
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Edit Book!</h1>
        <form action="BookController" method="post">
            <label>BookName:</label><br>
            <input type="text" name="name" required><br>
            <label>Author:</label></br>
            <input type="text" name="author" required><br>
            <label>Category Name:</label><br>
            <select name="categoryID">
                <c:forEach var="c" items="${categoryList}">
                    <option value="${c.id}">${c.name}</option>  
                </c:forEach>               
            </select><br>
            <label>Isbn:</label><br>
            <input type="text" name="isbn" required><br>
            <label>NumberOfPage:</label><br>
            <input type="text" name="number" required><br>
            <label>Price:</label><br>
            <input type="text" name="price" required><br>
            <input type="hidden" name="id" value="${book.id}">
            <input type="hidden" name="action" value="EDIT">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
