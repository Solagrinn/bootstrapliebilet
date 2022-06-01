
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

    public String girisKontrol() throws ClassNotFoundException, SQLException {
        if (email != null) {
            return "/koltuksec.xhtml?faces-redirect=true";
        } else {
            return "/login.xhtml?faces-redirect=true";
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getKoltukno() {
        return koltukno;
    }
    
    public void setKoltukno(String koltukno) {
        this.koltukno = koltukno;
    }
    
    public String koltuklar(){
        
        return "<div class=\"container\">\n" +
"            <div class=\"screen\"></div>\n" +
"\n" +
                
"            <div class=\"row\">\n" +
"                <div class=\"konum\" style=\"background-color:#242333; margin-left:-2px; margin-bottom:10px;\">A</div>\n" +
"                <div class=\"konum\" style=\"background-color:#242333\">B</div>\n" +
"                <div class=\"konum\" style=\"background-color:#242333\">C</div>\n" +
"                <div class=\"konum\" style=\"background-color:#242333\">D</div>\n" +
"                <div class=\"konum\" style=\"background-color:#242333\">E</div>\n" +
"                <div class=\"konum\" style=\"background-color:#242333\">F</div>\n" +
"                <div class=\"konum\" style=\"background-color:#242333\">G</div>\n" +
"                <div class=\"konum\" style=\"background-color:#242333\">H</div>\n" +
"            </div>\n" +
"            <div class=\"row\">\n" +
"                <div class=\"seat A1\"></div>\n" +
"                <div class=\"seat B1\"></div>\n" +
"                <div class=\"seat C1\"></div>\n" +
"                <div class=\"seat D1\"></div>\n" +
"                <div class=\"seat E1\"></div>\n" +
"                <div class=\"seat F1\"></div>\n" +
"                <div class=\"seat G1\"></div>\n" +
"                <div class=\"seat H1\"></div>\n" +
"            </div>\n" +
"            <div class=\"row\">\n" +
"                <div class=\"seat A2\"></div>\n" +
"                <div class=\"seat B2\"></div>\n" +
"                <div class=\"seat C2\"></div>\n" +
"                <div class=\"seat D2 occupied\"></div>\n" +
"                <div class=\"seat E2 occupied\"></div>\n" +
"                <div class=\"seat F2\"></div>\n" +
"                <div class=\"seat G2\"></div>\n" +
"                <div class=\"seat H2\"></div>\n" +
"            </div>\n" +
"            <div class=\"row\">\n" +
"                <div class=\"seat A3\"></div>\n" +
"                <div class=\"seat B3\"></div>\n" +
"                <div class=\"seat C3\"></div>\n" +
"                <div class=\"seat D3\"></div>\n" +
"                <div class=\"seat E3\"></div>\n" +
"                <div class=\"seat F3\"></div>\n" +
"                <div class=\"seat G3 occupied\"></div>\n" +
"                <div class=\"seat H3 occupied\"></div>\n" +
"            </div>\n" +
"            <div class=\"row\">\n" +
"                <div class=\"seat A4\"></div>\n" +
"                <div class=\"seat B4\"></div>\n" +
"                <div class=\"seat C4\"></div>\n" +
"                <div class=\"seat D4\"></div>\n" +
"                <div class=\"seat E4\"></div>\n" +
"                <div class=\"seat F4\"></div>\n" +
"                <div class=\"seat G4\"></div>\n" +
"                <div class=\"seat H4\"></div>\n" +
"            </div>\n" +
"            <div class=\"row\">\n" +
"                <div class=\"seat A5\"></div>\n" +
"                <div class=\"seat B5\"></div>\n" +
"                <div class=\"seat C5\"></div>\n" +
"                <div class=\"seat D5 occupied\"></div>\n" +
"                <div class=\"seat E5 occupied\"></div>\n" +
"                <div class=\"seat F5\"></div>\n" +
"                <div class=\"seat G5\"></div>\n" +
"                <div class=\"seat H5\"></div>\n" +
"            </div>\n" +
"            <div class=\"row\">\n" +
"                <div class=\"seat A6\"></div>\n" +
"                <div class=\"seat B6\"></div>\n" +
"                <div class=\"seat C6\"></div>\n" +
"                <div class=\"seat D6\"></div>\n" +
"                <div class=\"seat E6 occupied\"></div>\n" +
"                <div class=\"seat F6 occupied\"></div>\n" +
"                <div class=\"seat G6 occupied\"></div>\n" +
"                <div class=\"seat H6\"></div>\n" +
"            </div>\n" +
"        </div>";
    }

}
