
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

@ManagedBean(name = "Sifre")
@RequestScoped

public class Sifre {

    String yenisifre;
    String customerid;

    public String getYenisifre() {
        return yenisifre;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setYenisifre(String yenisifre) {
        this.yenisifre = yenisifre;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String sifreDegistir() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/EBiletDB", "admin", "admin");

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }

        try {
            PreparedStatement updateEntry
                    = connection.prepareStatement("UPDATE CUSTOMER SET PASSWORD ='" + yenisifre + "' WHERE CUSTOMER_ID=" + customerid);

            updateEntry.executeUpdate();

            return "/profil.xhtml?faces-redirect=true";
        } finally {
            connection.close();
        }
    }

}
