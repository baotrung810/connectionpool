package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
    public static void closePrepareStatement(PreparedStatement ps){
        try{
            ps.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    public static void closeResultSet(ResultSet rs){
        try{
            rs.close();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
}
