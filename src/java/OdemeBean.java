
import java.awt.event.ActionEvent;
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

@ManagedBean(name = "OdemeBean")
@SessionScoped

public class OdemeBean {

    String film;
    String koltukno;

    String email;

    String customerid;
    String filmid;
    
    String[] dolukoltuklar;

    public String girisKontrol() throws ClassNotFoundException, SQLException {
        if (email != null) {
            return "/koltuksec.xhtml?faces-redirect=true";
        } else {
            return "/login.xhtml?faces-redirect=true";
        }
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public void setFilmid(String filmid) {
        this.filmid = filmid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public String getFilm() {
        return film;
    }

    public String getKoltukno() {
        return koltukno;
    }

    public void setKoltukno(String koltukno) {
        this.koltukno = koltukno;
    }

    public String koltuklar() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/EBiletDB", "admin", "admin");

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }

        try {
            PreparedStatement getEntry = connection.prepareStatement(
                    "SELECT * FROM SEATS"
                    + " WHERE FILM_ID ="+ filmid);

            ResultSet rs = getEntry.executeQuery();
            rs.next();

            String dolular = rs.getString(2);
            
            dolukoltuklar = new String[dolular.length()/2];
            
            
            for(int i=0; i<dolular.length(); i=i+2)
            {
               dolukoltuklar[i/2] = dolular.substring(i, i+2);
            }
            
            String koltuklar = "<div class=\"container\">\n"
                + "            <div class=\"screen\"></div>\n"
                + "\n"
                + "            <div class=\"row\">\n"
                + "                <div class=\"konum\" style=\"background-color:#242333; margin-left:-2px; margin-bottom:10px;\">A</div>\n"
                + "                <div class=\"konum\" style=\"background-color:#242333\">B</div>\n"
                + "                <div class=\"konum\" style=\"background-color:#242333\">C</div>\n"
                + "                <div class=\"konum\" style=\"background-color:#242333\">D</div>\n"
                + "                <div class=\"konum\" style=\"background-color:#242333\">E</div>\n"
                + "                <div class=\"konum\" style=\"background-color:#242333\">F</div>\n"
                + "                <div class=\"konum\" style=\"background-color:#242333\">G</div>\n"
                + "                <div class=\"konum\" style=\"background-color:#242333\">H</div>\n"
                + "            </div>\n"
                + "            <div class=\"row\">\n"
                + "                <div class=\"seat A1\"></div>\n"
                + "                <div class=\"seat B1\"></div>\n"
                + "                <div class=\"seat C1\"></div>\n"
                + "                <div class=\"seat D1\"></div>\n"
                + "                <div class=\"seat E1\"></div>\n"
                + "                <div class=\"seat F1\"></div>\n"
                + "                <div class=\"seat G1\"></div>\n"
                + "                <div class=\"seat H1\"></div>\n"
                + "            </div>\n"
                + "            <div class=\"row\">\n"
                + "                <div class=\"seat A2\"></div>\n"
                + "                <div class=\"seat B2\"></div>\n"
                + "                <div class=\"seat C2\"></div>\n"
                + "                <div class=\"seat D2\"></div>\n"
                + "                <div class=\"seat E2\"></div>\n"
                + "                <div class=\"seat F2\"></div>\n"
                + "                <div class=\"seat G2\"></div>\n"
                + "                <div class=\"seat H2\"></div>\n"
                + "            </div>\n"
                + "            <div class=\"row\">\n"
                + "                <div class=\"seat A3\"></div>\n"
                + "                <div class=\"seat B3\"></div>\n"
                + "                <div class=\"seat C3\"></div>\n"
                + "                <div class=\"seat D3\"></div>\n"
                + "                <div class=\"seat E3\"></div>\n"
                + "                <div class=\"seat F3\"></div>\n"
                + "                <div class=\"seat G3\"></div>\n"
                + "                <div class=\"seat H3\"></div>\n"
                + "            </div>\n"
                + "            <div class=\"row\">\n"
                + "                <div class=\"seat A4\"></div>\n"
                + "                <div class=\"seat B4\"></div>\n"
                + "                <div class=\"seat C4\"></div>\n"
                + "                <div class=\"seat D4\"></div>\n"
                + "                <div class=\"seat E4\"></div>\n"
                + "                <div class=\"seat F4\"></div>\n"
                + "                <div class=\"seat G4\"></div>\n"
                + "                <div class=\"seat H4\"></div>\n"
                + "            </div>\n"
                + "            <div class=\"row\">\n"
                + "                <div class=\"seat A5\"></div>\n"
                + "                <div class=\"seat B5\"></div>\n"
                + "                <div class=\"seat C5\"></div>\n"
                + "                <div class=\"seat D5\"></div>\n"
                + "                <div class=\"seat E5\"></div>\n"
                + "                <div class=\"seat F5\"></div>\n"
                + "                <div class=\"seat G5\"></div>\n"
                + "                <div class=\"seat H5\"></div>\n"
                + "            </div>\n"
                + "            <div class=\"row\">\n"
                + "                <div class=\"seat A6\"></div>\n"
                + "                <div class=\"seat B6\"></div>\n"
                + "                <div class=\"seat C6\"></div>\n"
                + "                <div class=\"seat D6\"></div>\n"
                + "                <div class=\"seat E6\"></div>\n"
                + "                <div class=\"seat F6\"></div>\n"
                + "                <div class=\"seat G6\"></div>\n"
                + "                <div class=\"seat H6\"></div>\n"
                + "            </div>\n"
                + "        </div>";
            
            
          for(int i=0; i<dolukoltuklar.length;i++)
            koltuklar = koltuklar.replaceAll(dolukoltuklar[i], dolukoltuklar[i] + " occupied");
            
            return koltuklar;
            
        } finally {
            connection.close();
        }
    }

    public String satinal() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        Connection connection = DriverManager.getConnection("jdbc:derby://localhost:1527/EBiletDB", "admin", "admin");

        if (connection == null) {
            throw new SQLException("Unable to connect to DataSource");
        }

        try {
            PreparedStatement addEntry
                    = connection.prepareStatement("INSERT INTO TRANSACTIONS"
                            + "(CUSTOMER_ID,FILM_ID,SEAT_NUMBER,PURCHASE_DATE)"
                            + "VALUES ( ?, ?, ?, ? )");

            addEntry.setInt(1, Integer.parseInt(customerid));
            addEntry.setInt(2, Integer.parseInt(filmid));
            addEntry.setString(3, koltukno);
            addEntry.setString(4, "2022-06-02");

            addEntry.executeUpdate();

            PreparedStatement updateEntry
                    = connection.prepareStatement("UPDATE SEATS SET OCCUPIED_SEATS = OCCUPIED_SEATS || '" + koltukno + "' WHERE FILM_ID=" + filmid);

            updateEntry.executeUpdate();

            return "/basarili.xhtml?faces-redirect=true";
        } finally {
            connection.close();
        }
    }

}
