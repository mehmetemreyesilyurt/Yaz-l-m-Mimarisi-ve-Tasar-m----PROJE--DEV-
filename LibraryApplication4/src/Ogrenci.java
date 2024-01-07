import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Ogrenci extends Kullanici implements IOgrenci{

    public Ogrenci(String userName, String password) {
        super(userName, password);
    }

    @Override
    public void bilgileri_goster(String userName) {
        try{
            boolean isFound = false;
            Connection connection = ConnectSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQLTemplate.sqlSelect("details"));
            while(rs.next()){
                if(rs.getString("userName").equals(userName)){
                    isFound=true;
                    JOptionPane.showMessageDialog(null,"Adı = "+rs.getString("name")+"\nSoyadı = "+rs.getString("surname")
                            +"\nEmaili = "+rs.getString("email")+"\nYaşı = "+rs.getString("age")+"\nTelefon numarası = "+rs.getString("no"));
                }
            }
            if(!isFound){
                JOptionPane.showMessageDialog(null,"Herhangi bir kayıt bulunamadı.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void searchBookByName(String name) {
        try{
            boolean isFound = false;
            Connection connection = ConnectSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQLTemplate.sqlSelect("books"));
            while(rs.next()){
                if(rs.getString("bookName").equals(name) & rs.getString("bookExistNum").equals("1")){
                    isFound=true;
                    JOptionPane.showMessageDialog(null,"Kitabın adı = "+name+"\nYazarı = "+rs.getString("bookAuthor")
                            +"\nKonusu = "+rs.getString("bookTheme")+"\nDurumu = "+rs.getString("bookStatus"));
                }
            }
            if(!isFound){
                JOptionPane.showMessageDialog(null,"Herhangi bir kayıt bulunamadı.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void searchBookByAuthor(String author) {
        try{
            boolean isFound = false;
            String result ="";
            Connection connection = ConnectSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQLTemplate.sqlSelect("books"));
            while(rs.next()){
                if(rs.getString("bookAuthor").equals(author) & rs.getString("bookExistNum").equals("1")){
                    result += rs.getString("bookName")+"-"+rs.getString("bookAuthor")+"-"+rs.getString("bookTheme")+"-"+rs.getString("bookStatus")+"\n";

                    isFound=true;
                }
            }
            if(isFound){
                JOptionPane.showMessageDialog(null,result);
            }if(!isFound){
                JOptionPane.showMessageDialog(null,"Herhangi bir kayıt bulunamadı.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void searchBookByTheme(String theme) {
        try{
            boolean isFound = false;
            String result ="";
            Connection connection = ConnectSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQLTemplate.sqlSelect("books"));
            while(rs.next()){
                if(rs.getString("bookTheme").equals(theme) & rs.getString("bookExistNum").equals("1")){
                    result += rs.getString("bookName")+"-"+rs.getString("bookAuthor")+"-"+rs.getString("bookTheme")+"-"+rs.getString("bookStatus")+"\n";

                    isFound=true;
                }
            }
            if(isFound){
                JOptionPane.showMessageDialog(null,result);
            }if(!isFound){
                JOptionPane.showMessageDialog(null,"Herhangi bir kayıt bulunamadı.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void borrow(String userName,String name) {
        try {
            boolean isFound = false;
            String firstTakenBook = "";
            String lastTakenBook = "";
            Connection connection = ConnectSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQLTemplate.sqlSelect("users"));
            while (rs.next()) {
                if (rs.getString("username").equals(userName) & rs.getString("takenBook").equals("-") ) {
                    isFound = true;
                    firstTakenBook = rs.getString("takenBook");
                }
            }
            if (!isFound) {
                JOptionPane.showMessageDialog(null, "Aldığınız bir kitap bulunmaktadır.");
            } else {
                isFound = false;
                rs = statement.executeQuery(SQLTemplate.sqlSelect("books"));
                while (rs.next()) {
                    if (rs.getString("bookName").equals(name) & rs.getString("bookStatus").equals("Rafta") & rs.getString("bookExistNum").equals("1")) {
                        isFound = true;
                    }
                }
                if (!isFound) {
                    JOptionPane.showMessageDialog(null, "Almak istediğiniz kitap rafta bulunmamaktadır.");
                } else {
                    Statement userStatement = connection.createStatement();
                    String userSql = "update users set takenBook="+SQLTemplate.setValue(name)+" where username="+SQLTemplate.setValue(userName);
                    userStatement.executeUpdate(userSql);
                    lastTakenBook = name;

                    StateManagement sm = new StateManagement();
                    sm.Borrow();
                    sm.changeStatus(connection,name);

                    BorrowDatabase bd = new BorrowDatabase(name);
                    ShowBorrow sb = new ShowBorrow(userName);
                    bd.add(sb);
                    bd.borrowProcess(firstTakenBook,lastTakenBook);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void giveBack(String userName,String name) {
        try {
            String firstTakenBook = "";
            String lastTakenBook = "";
            boolean isFound = false;
            Connection connection = ConnectSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQLTemplate.sqlSelect("users"));
            while (rs.next()) {
                if (rs.getString("username").equals(userName) & rs.getString("takenBook").equals(name)) {
                    isFound = true;
                    firstTakenBook = rs.getString("takenBook");
                }
            }
            if (!isFound) {
                JOptionPane.showMessageDialog(null, "Girilen kitap sizde bulunamadı.");
            } else {

                Statement userStatement = connection.createStatement();
                String userSql = "update users set takenBook="+SQLTemplate.setValue("-")+" where username="+SQLTemplate.setValue(userName);
                userStatement.executeUpdate(userSql);
                lastTakenBook = "-";

                StateManagement sm = new StateManagement();
                sm.GiveBack();
                sm.changeStatus(connection,name);

                GiveBackDatabase gd = new GiveBackDatabase(name);
                ShowGiveBack sg = new ShowGiveBack(userName);
                gd.add(sg);
                gd.givebackProcess(firstTakenBook,lastTakenBook);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getTakenBook(String userName) {
        try{
            boolean isFound = false;
            Connection connection = ConnectSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQLTemplate.sqlSelect("users"));
            while(rs.next()){
                if(rs.getString("username").equals(userName) & !(rs.getString("takenBook").equals("-"))){
                    isFound = true;
                    JOptionPane.showMessageDialog(null,"Alınan kitap = "+rs.getString("takenBook"));
                }
            }if(!isFound){
                JOptionPane.showMessageDialog(null,"Alınan kitap bulunamadı.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public String getRole(String userName){
        try{
            Connection connection = ConnectSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQLTemplate.sqlSelect("users"));
            while(rs.next()){
                if(rs.getString("username").equals(userName)){
                    return rs.getString("userRole");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "null";
    }
}