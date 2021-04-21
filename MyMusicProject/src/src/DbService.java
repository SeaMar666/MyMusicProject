package src;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbService {

    private static String url = "jdbc:mysql://localhost:3306/users?autoReconnect=true&useSSL=false";;
    private static String user = "root";
    private static String password ="12345678";


    public static Connection connect() {
        Connection connection = null;

        MysqlDataSource dataSource = new MysqlDataSource();

        dataSource.setUrl(url);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        try {
            connection= dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger("Get connection : " + DbService.class.getName()).log(Level.SEVERE,null,e);
        }

        return connection;

    }

}