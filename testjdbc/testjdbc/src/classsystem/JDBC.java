package classsystem;
import java.sql.*;

public class JDBC {

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/management?useSSL=false&serverTimezone=UTC";
    private static String user = "root";
    private static String  pass = "123456";
    public static String getUrl(){
        return url;
    }
    public static String getUser(){
        return user;
    }
    public static String getPass(){
        return pass;
    }

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(ResultSet set, PreparedStatement preparedStatement, Connection connection){
        try {
            if(set!=null){
                set.close();
            }
            if(preparedStatement!=null){
                preparedStatement.close();
            }
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}