import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Status {
    void changeStatus(Connection connection,String name) throws SQLException;

}
