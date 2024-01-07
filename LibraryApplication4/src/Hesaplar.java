import javax.swing.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Hesaplar{

    String userName;
    String password;
    public Hesaplar(String userName,String password){
        this.userName=userName;
        this.password=password;
    }

    public void addUser(String userName,String password,String role){
        try{
            boolean isFound = false;
            Connection connection = ConnectSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQLTemplate.sqlSelect("users"));
            while(rs.next()){
                if(rs.getString("username").equals(userName) & rs.getString("existNum").equals("1")){
                    isFound=true;
                }
            }
            if(!isFound){
                Connection userConnection = ConnectSingleton.getInstance().getConnection();
                Statement userStatement = userConnection.createStatement();
                String sql = "insert into users(username,password,userRole,takenBook,existNum) values ("+SQLTemplate.setValue(userName)+","+SQLTemplate.setValue(password)+","+SQLTemplate.setValue(role)+",'-','1')";
                userStatement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,"Kullanıcı başarıyla eklendi.");
            }else {
                JOptionPane.showMessageDialog(null, "Kullanıcı zaten mevcut.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addUserDetails(String userName,String name,String surname,String email,String age,String no){
        try{
            boolean isFound = false;
            Connection connection = ConnectSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQLTemplate.sqlSelect("details"));
            while(rs.next()){
                if(rs.getString("userName").equals(userName)){
                    isFound=true;
                }
            }if(!isFound){
                isFound = false;
                Connection boolConnection = ConnectSingleton.getInstance().getConnection();
                Statement boolStatement = boolConnection.createStatement();
                ResultSet boolrs = boolStatement.executeQuery(SQLTemplate.sqlSelect("users"));
                while(boolrs.next()){
                    if(boolrs.getString("username").equals(userName) & boolrs.getString("existNum").equals("1")){
                        isFound=true;
                    }
                }if(isFound){
                    Connection userConnection = ConnectSingleton.getInstance().getConnection();
                    Statement userStatement = userConnection.createStatement();
                    String sql = "insert into details(userName,name,surname,email,age,no) values ("+SQLTemplate.setValue(userName)+","+SQLTemplate.setValue(name)+","+SQLTemplate.setValue(surname)+","+SQLTemplate.setValue(email)+","+SQLTemplate.setValue(age)+","+SQLTemplate.setValue(no)+")";
                    userStatement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null,"Kullanıcı detayları başarıyla eklendi.");
                }else{
                    JOptionPane.showMessageDialog(null, "Kullanıcı bulunamadı.");
                }
            }else {
                JOptionPane.showMessageDialog(null, "Kullanıcı detayları eklenemedi.");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteUser(String userName){
        try{
            boolean isFound = false;
            Connection connection = ConnectSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQLTemplate.sqlSelect("users"));
            while(rs.next()){
                if(rs.getString("username").equals(userName) & rs.getString("existNum").equals("1")){
                    isFound=true;
                }
            }
            if(isFound){
                Connection userConnection = ConnectSingleton.getInstance().getConnection();
                Statement userStatement = userConnection.createStatement();
                String sql = "update users set existNum="+SQLTemplate.setValue("0")+" where username="+SQLTemplate.setValue(userName);
                userStatement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,"Kullanıcı başarıyla silindi.");
            }else{
                JOptionPane.showMessageDialog(null,"Kullanıcı bulunamadı.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateUser(String userName,String newName,String newPassword,String newRole){
        try{
            boolean isFound = false;
            Connection connection = ConnectSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQLTemplate.sqlSelect("users"));
            while(rs.next()){
                if(rs.getString("username").equals(userName) & rs.getString("existNum").equals("1")){
                    isFound=true;
                }
            }
            if(isFound){
                Connection userConnection = ConnectSingleton.getInstance().getConnection();
                Statement userStatement = userConnection.createStatement();
                String sql = "update users set username="+SQLTemplate.setValue(newName)+",password="+SQLTemplate.setValue(newPassword)+",userRole="+SQLTemplate.setValue(newRole)+" where username="+SQLTemplate.setValue(userName);
                userStatement.executeUpdate(sql);
                JOptionPane.showMessageDialog(null,"Kullanıcı başarıyla güncellendi.");
            }else{
                JOptionPane.showMessageDialog(null,"Kullanıcı bulunamadı.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public boolean login(String userName,String password) throws IOException {
        try{
            boolean isLogin = false;
            boolean isUser = false;

            Connection connection = ConnectSingleton.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SQLTemplate.sqlSelect("users"));
            while(rs.next()){
                if(rs.getString("username").equals(userName) & rs.getString("existNum").equals("1")){
                    if(rs.getString("password").equals(password)){
                        isLogin=true;
                        isUser=true;
                        JOptionPane.showMessageDialog(null,userName+" adlı kullanıcı giriş yaptı.");
                    }else{
                        JOptionPane.showMessageDialog(null,"Kullanıcı adı veya şifre yanlış. Giriş yapılamadı.");
                        isUser=true;
                    }
                }
            }if(!isUser){
                JOptionPane.showMessageDialog(null,"Kullanıcı bulunamadı.");
            }
            return isLogin;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
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
