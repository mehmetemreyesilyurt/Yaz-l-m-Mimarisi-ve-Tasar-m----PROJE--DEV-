import javax.swing.*;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class OgretimUyesi extends Kullanici{

    public OgretimUyesi(String userName, String password) {
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
