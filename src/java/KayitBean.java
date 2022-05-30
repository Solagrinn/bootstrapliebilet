
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

@ManagedBean(name = "KayitBean")
@RequestScoped

public class KayitBean {

    String ad;
    String soyad;
    String email;
    String sifre;

    public String kayitEt() throws SQLException, ClassNotFoundException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/EBiletDB", "admin", "admin");

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }

        try {
            PreparedStatement addEntry
                    = connection.prepareStatement("INSERT INTO CUSTOMER"
                            + "(NAME,SURNAME,USERNAME,PASSWORD)"
                            + "VALUES ( ?, ?, ?, ? )");

            addEntry.setString(1, getAd());
            addEntry.setString(2, getSoyad());
            addEntry.setString(3, getEmail());
            addEntry.setString(4, getSifre());

            addEntry.executeUpdate(); 
            return "sss.xhtml";
        }
        finally {
            connection.close();
        } 
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public String getEmail() {
        return email;
    }

    public String getSifre() {
        return sifre;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
}
