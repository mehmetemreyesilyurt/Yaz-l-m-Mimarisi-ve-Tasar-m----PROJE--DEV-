import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {


        String kullaniciAdi = JOptionPane.showInputDialog("Kullanıcı adını giriniz = ");
        String sifre = JOptionPane.showInputDialog("Şifreyi giriniz = ");

        Hesaplar h = new Hesaplar(kullaniciAdi, sifre);
        if (h.login(kullaniciAdi, sifre)) {
            FrameFactory frameFactory = new FrameFactory();
            if (kullaniciAdi.equals("admin")) {
                frameFactory.createFactory("admin",kullaniciAdi,sifre);
            } else {
                frameFactory.createFactory(h.getRole(kullaniciAdi),kullaniciAdi,sifre);
            }
        }
    }
}