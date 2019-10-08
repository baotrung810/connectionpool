/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Entity.BookDetailEntity;
import Entity.BookEntity;
import Entity.CategoryEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public static List<BookEntity> EditBook (BookEntity book){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        String sqlString = "update book set name=? ,author=? ,categoryID=? where id= ? ";
        try{
            ps=connection.prepareStatement(sqlString);
            ps.setString(1,book.getName());
            ps.setString(2,book.getAuthor());
            ps.setInt(3,book.getCategory().getId());
            ps.setInt(4,book.getId());
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            DBUtil.closePrepareStatement(ps);
            pool.freeConnection(connection);
        }
        List<BookEntity> bookList =getBookList();
        return bookList;
    }

    public static List<BookEntity> SearchBook(String search) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlString = " select * from book where name regexp ? or author regexp ? or categoryID =? ";
        List<BookEntity> bookList = new ArrayList<>();
        try {
            ps = connection.prepareStatement(sqlString);
            ps.setString(1, search);
            ps.setString(2, search);
            ps.setInt(3,CategoryDAO.IdCategory(search));
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                int categoryID = rs.getInt("categoryid");
                BookEntity book = new BookEntity(id, name, author);
                CategoryEntity category = CategoryDAO.getCategoryID(categoryID);
                book.setCategory(category);
                BookDetailEntity bookDetail = BookDetailDAO.getBookDetailByID(id);
                book.setBookDetail(bookDetail);
                bookList.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePrepareStatement(ps);
            pool.freeConnection(connection);
        }
        return bookList;
    }

    public static boolean AddNewBook(BookEntity book) {
        boolean t = false;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String sqlString = "insert into Book(name,author,categoryID) values(?,?,?)";
        try {
            ps = connection.prepareStatement(sqlString);
            ps.setString(1, book.getName());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getCategory().getId());
            ps.executeUpdate();
            //get max id book
            int bookId = BookDAO.getMaxBookId();
            book.getBookDetail().setId(bookId);
            //save book detail
            t = BookDetailDAO.addNewBookDatail(book.getBookDetail());

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePrepareStatement(ps);
            pool.freeConnection(connection);
        }
        return t;
    }

    public static int getMaxBookId() {
        int maxId = 0;
        List<BookEntity> bookList = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlString = "Select max(id) as MaxID from book ";
        try {
            ps = connection.prepareStatement(sqlString);
            rs = ps.executeQuery();
            if (rs.next()) {
                maxId = rs.getInt("MaxID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        return maxId;
    }

    public static List<BookEntity> getBookList() {
        List<BookEntity> bookList = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlString = "Select * from book ";
        try {
            ps = connection.prepareStatement(sqlString);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                int categoryID = rs.getInt("categoryid");
                BookEntity book = new BookEntity(id, name, author);
                CategoryEntity category = CategoryDAO.getCategoryID(categoryID);
                book.setCategory(category);
                BookDetailEntity bookDetail = BookDetailDAO.getBookDetailByID(id);
                book.setBookDetail(bookDetail);
                bookList.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        return bookList;
    }

    public static List<BookEntity> deleteBookById(int id) {
        List<BookEntity> bookList = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String sqlString = "delete from book where id=?";
        //  ResultSet rs= null;
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sqlString);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closePrepareStatement(ps);
            pool.freeConnection(connection);
        }
        bookList = getBookList();
        return bookList;
    }

}
