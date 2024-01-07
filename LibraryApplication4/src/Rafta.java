import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Rafta implements Status{

    @Override
    public void changeStatus(Connection connection,String name) throws SQLException {
        Statement bookStatement = connection.createStatement();
        String bookSql = "update books set bookStatus='Alındı' where bookName='" + name + "'";
        bookStatement.executeUpdate(bookSql);
    }
}