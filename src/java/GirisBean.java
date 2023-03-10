
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;

@ManagedBean(name = "GirisBean")
@SessionScoped

public class GirisBean {

    String email;
    String sifre;
    String customerid;

    int girisbasarisiz = 0;

    public String girisYap() throws SQLException, ClassNotFoundException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/EBiletDB", "admin", "admin");

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }

        try {
            PreparedStatement getEntry = connection.prepareStatement(
                    "SELECT * FROM CUSTOMER "
                    + "WHERE USERNAME = ? AND PASSWORD = ?");

            getEntry.setString(1, getEmail());
            getEntry.setString(2, getSifre());

            ResultSet rs = getEntry.executeQuery();

            if (rs.next()) { // login başarılı
                customerid = rs.getString(1);
                return "index.xhtml?faces-redirect=true";
            } else {
                email = "";
                sifre = "";
                customerid = "";
                girisbasarisiz = 1;
                return "login.xhtml?faces-redirect=true";
            }

        } finally {
            connection.close();
        }
    }

    public String cikisYap() {
        email = "";
        sifre = "";
        customerid = "";

        return "index.xhtml";
    }

    public String getIsim() throws SQLException, ClassNotFoundException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/EBiletDB", "admin", "admin");

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }

        try {
            PreparedStatement getEntry = connection.prepareStatement(
                    "SELECT * FROM CUSTOMER "
                    + "WHERE USERNAME = ?");

            getEntry.setString(1, getEmail());

            ResultSet rs = getEntry.executeQuery();

            rs.next();
            return rs.getString(2) + " " + rs.getString(3);

        } finally {
            connection.close();
        }
    }

    public String getAlinanBiletler() throws SQLException, ClassNotFoundException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/EBiletDB", "admin", "admin");

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }

        try {
            PreparedStatement getEntry = connection.prepareStatement(
                    "SELECT * FROM FILM INNER JOIN (SELECT * FROM TRANSACTIONS WHERE CUSTOMER_ID = ?) AS TEMP on FILM.FILM_ID = TEMP.FILM_ID");

            getEntry.setString(1, customerid);

            ResultSet rs = getEntry.executeQuery();

            String alinanbiletler = "<br/>Alınan Biletler:<br/>";

            int i = 1;

            while (rs.next()) {
                alinanbiletler += i + "- Film: " + rs.getString(2) + " Koltuk No: " + rs.getString(7) + " Satın Alma Tarihi: " + rs.getString(8) + "<br/>";
                i++;
            }

            return alinanbiletler;

        } finally {
            connection.close();
        }
    }

    public String girisBasarili() {
        if (girisbasarisiz == 1) {
            girisbasarisiz = 0;
            return "Kullanıcı adınız veya şifreniz hatalı.";
        }

        return "Devam etmek için giriş yapmalısınız.";
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getEmail() {
        return email;
    }

    public String getSifre() {
        return sifre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String response() throws ClassNotFoundException, SQLException {
        if (email != null && sifre != null && !email.equals("")) {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/EBiletDB", "admin", "admin");

            if (connection == null) {
                throw new SQLException("Unable to connect to DataSource");
            }

            try {
                PreparedStatement getEntry = connection.prepareStatement(
                        "SELECT * FROM CUSTOMER "
                        + "WHERE USERNAME = ?");

                getEntry.setString(1, getEmail());

                ResultSet rs = getEntry.executeQuery();
                rs.next();

                return "<a href=\"/EBilet/faces/profil.xhtml\"><button class=\"form-inline my-2 my-lg-0 btn btn-primary\" type=\"button\" aria-expanded=\"false\" style=\"background-color:#2e4c6d\">" + rs.getString(2) + " " + rs.getString(3) + "</button></a>"
                        + "<a href=\"/EBilet/faces/cikis.xhtml\"><button class=\"fa fa-sign-out form-inline my-2 my-lg-0 btn btn-primary\" type=\"button\" aria-expanded=\"false\" style=\"background-color:red; height:42px; width:52px;\"></button></a>";

            } finally {
                connection.close();
            }

        } else {
            return "<a href=\"/EBilet/faces/login.xhtml\"><button class=\"form-inline my-2 my-lg-0 btn btn-primary\" type=\"button\" aria-expanded=\"false\" style=\"background-color:#2e4c6d\">Giriş Yap</button></a>";
        }
    }
}
