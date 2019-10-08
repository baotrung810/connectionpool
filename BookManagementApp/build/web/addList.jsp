<%-- 
    Document   : addList
    Created on : Mar 29, 2019, 4:07:36 PM
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
        <form action="BookController" method="post">
            <lable>BookName(*):</lable><br>
            <input type="text" name="bookName" required><br>
            <lable>Author(*):</lable><br>
            <input type="text" name="author" required><br>
            <lable>ISBN(*):</lable><br>
            <input type="text" name="isbn" required><br>
            <lable>NumberOfPage(*):</lable><br>
            <input type="text" name="number" required><br>
            <label>CategoryID:</label><br>
            <select name="categoryID">
                <c:forEach var="category" items="${categoryList}">
                <option value="${category.id}">${category.name}</option>
                </c:forEach>         
            </select><br>
            <lable>Price(*):</lable><br>
            <input type="text" name="price" required=><br>
            <input type="hidden" name="action" value="save">
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
