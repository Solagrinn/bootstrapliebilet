
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

@ManagedBean(name = "IletisimBean")
@RequestScoped

public class IletisimBean {

    String adsoyad;
    String email;
    String telefon;
    String konu;
    String mesaj;

    public void setAdsoyad(String adsoyad) {
        this.adsoyad = adsoyad;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setKonu(String konu) {
        this.konu = konu;
    }

    public void setMesaj(String mesaj) {
        this.mesaj = mesaj;
    }

    public String getAdsoyad() {
        return adsoyad;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getKonu() {
        return konu;
    }

    public String getMesaj() {
        return mesaj;
    }

    public String formGonder() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/EBiletDB", "admin", "admin");

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }

        try {
            PreparedStatement addEntry
                    = connection.prepareStatement("INSERT INTO CONTACT_US"
                            + "(NAME,EMAIL,PHONE,SUBJECT,MESSAGE)"
                            + "VALUES ( ?, ?, ?, ?, ? )");

            addEntry.setString(1, getAdsoyad());
            addEntry.setString(2, getEmail());
            addEntry.setString(3, getTelefon());
            addEntry.setString(4, getKonu());
            addEntry.setString(5, getMesaj());

            addEntry.executeUpdate();
            return "#";
        } finally {
            connection.close();
        }

    }

}
