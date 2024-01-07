import javax.swing.*;

public class FrameFactory {
    public JFrame createFactory(String role, String k, String s){

        if(role.equalsIgnoreCase("admin")){
            return new MainFrame(k,s);
        }else if(role.equalsIgnoreCase("Personel")){
            return new PersonelFrame(k,s);
        }else if(role.equalsIgnoreCase("Öğrenci")){
            return new OgrenciFrame(k,s);
        }else if(role.equalsIgnoreCase("Öğretim Üyesi")){
            return new OgretimUyesiFrame(k,s);
        }
        return null;
    }
}
