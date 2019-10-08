/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Entity.BookDetailEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDetailDAO {
    public static boolean EditBookDetailEntity(BookDetailEntity bookDetailEntity){
        boolean t= false;       
         ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;       
        String sqlString = "update bookdetails set isbn=?, numberOfPage=?, price=? where id= ? ";
        try{
            ps=connection.prepareStatement(sqlString);
            ps.setString(1,bookDetailEntity.getIsbn());
            int i = bookDetailEntity.getId();
            ps.setInt(2,bookDetailEntity.getNumberOfPage());
            ps.setDouble(3,bookDetailEntity.getPrice());
            ps.setInt(4,bookDetailEntity.getId());
            ps.executeUpdate();
            t= true;
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            DBUtil.closePrepareStatement(ps);
            pool.freeConnection(connection);
        }
        return t;
    }
   public static boolean addNewBookDatail(BookDetailEntity bookDetail){
       boolean t=false;
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String sqlString= "insert into bookdetails values(?,?,?,?)";
        try{
            ps=connection.prepareStatement(sqlString);
            ps.setInt(1,bookDetail.getId());
            ps.setString(2,bookDetail.getIsbn());
            ps.setInt(3, bookDetail.getNumberOfPage());
            ps.setDouble(4,bookDetail.getPrice());
            ps.executeUpdate();
       
            t=true;
        }catch(SQLException e){
            System.out.println(e);
        }finally{
            DBUtil.closePrepareStatement(ps);
            pool.freeConnection(connection);
        }
        return t;
   }
    public static BookDetailEntity getBookDetailByID(int id){
        BookDetailEntity bookDetail = null;
        //
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlString = "Select * from bookdetails where id=? ";
        try {
            ps = connection.prepareStatement(sqlString);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("isbn");
                int numberOfPage = rs.getInt("numberOfPage");
                double price = rs.getDouble("price");
                bookDetail=new BookDetailEntity(id, name, numberOfPage, price);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        return bookDetail;
    }
   public static BookDetailEntity deleteBookDetailByID(int id){
       ConnectionPool pool=ConnectionPool.getInstance();
       Connection connection =pool.getConnection();
       String sqlString= "delete from bookdetails where id=?";
       PreparedStatement ps =null;
       try{
           ps= connection.prepareStatement(sqlString);
           ps.setInt(1, id);
           ps.executeUpdate();
       }catch(SQLException e){
           System.out.println(e);
       }finally{
           DBUtil.closePrepareStatement(ps);
           pool.freeConnection(connection);
       }
       BookDetailEntity bookDetail = getBookDetailByID(id);
       return bookDetail;
   }
}
