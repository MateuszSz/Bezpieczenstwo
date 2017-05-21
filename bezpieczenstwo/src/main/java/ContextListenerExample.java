import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class ContextListenerExample implements ServletContextListener {
    public void contextInitialized(ServletContextEvent e){
//        System.setProperty("javax.net.ssl.keyStore","F:/newcerts/keystore.keystore");
//        System.setProperty("javax.net.ssl.keyStorePassword","123456");
//        System.setProperty("javax.net.ssl.trustStore","F:/newcerts/truststore.keystore");
//        System.setProperty("javax.net.ssl.trustStorePassword","123456");
//        System.setProperty("javax.net.debug","all");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    public static void main(String [] args) throws SQLException, ClassNotFoundException {
    List<String> stringList = new ArrayList<String>();
    String lol = "rekrutacja";
    stringList.add(lol);
    lol = "esdd";
    lol = lol + " ";
    }

}