import javax.swing.*;

public class ShowBorrow implements Observer{
    private String userName;

    public ShowBorrow(String userName){
        this.userName=userName;
    }
    @Override
    public void update(Observable observable) {
        BorrowDatabase borrowDatabase = (BorrowDatabase) observable;
        JOptionPane.showMessageDialog(null,userName+" adlı kullanıcı "+ borrowDatabase.getBook() +" adlı kitabı ödünç aldı.");
    }
}
