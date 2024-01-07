import java.sql.Connection;
import java.sql.SQLException;

public class StateManagement {

    private Status status;

    public void changeStatus(Connection connection,String name) throws SQLException {
        status.changeStatus(connection,name);
    }

    public void Borrow(){
        status = new Rafta();
    }
    public void GiveBack() {
        status = new Alındı();
    }
}