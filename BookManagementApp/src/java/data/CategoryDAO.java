/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Entity.CategoryEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    public static int IdCategory(String search){
        int id=0;
       ConnectionPool pool =ConnectionPool.getInstance();
       Connection connection= pool.getConnection();
       String sqlString = "select * from category where name regexp ?";
       PreparedStatement ps =null;
       ResultSet rs= null;
       try{
           ps = connection.prepareStatement(sqlString);
           ps.setString(1, search);
           rs= ps.executeQuery();
           if(rs.next()){
                id = rs.getInt("id");
           }
       }catch(SQLException e){
           System.out.println(e);
       }finally{
           DBUtil.closePrepareStatement(ps);
           pool.freeConnection(connection);
       }
       return id;
    }
    public static CategoryEntity getCategoryID(int id) {
        CategoryEntity category = null;
        //
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlString = "Select * from category where id=?";
        try {
            ps = connection.prepareStatement(sqlString);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                category = new CategoryEntity(id, name, description);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        return category;
    }

    public static List<CategoryEntity> getCategoryID() {
        List<CategoryEntity> categoryList = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sqlString = "Select * from category ";
        try {
            ps = connection.prepareStatement(sqlString);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
           CategoryEntity  category = new CategoryEntity(id, name, description);
            categoryList.add(category);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtil.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        return categoryList;
    }
}
