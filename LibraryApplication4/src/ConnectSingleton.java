import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectSingleton {
    private static ConnectSingleton instance;

    private ConnectSingleton(){
        ////
    }
    public static ConnectSingleton getInstance(){
        if(instance==null){
            instance = new ConnectSingleton();
        }
        return instance;
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/veritabani","root", "");
    }
}
