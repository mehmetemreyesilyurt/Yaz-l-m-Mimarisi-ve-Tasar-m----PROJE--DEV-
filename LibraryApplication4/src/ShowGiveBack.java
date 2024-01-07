import javax.swing.*;

public class ShowGiveBack implements  Observer{
    private String userName;

    public ShowGiveBack(String userName){
        this.userName=userName;
    }
    @Override
    public void update(Observable observable) {
        GiveBackDatabase giveBackDatabase = (GiveBackDatabase) observable;
        JOptionPane.showMessageDialog(null,userName+" adlı kullanıcı "+ giveBackDatabase.getBook() +" adlı kitabı iade etti.");
    }
}
