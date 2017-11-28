package jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.org.configparser.ConfigParser;

/**
 * @author thiago-amm
 * @version v1.0.0 15/11/2017
 * @since v1.0.0
 */
public class DataSource {
   
   private String url;
   private String username;
   private String password;
   private String driver;
   private ConfigParser configParser;
   
   public String getUrl() {
      return url;
   }
   
   public void setUrl(String url) {
      this.url = url;
   }
   
   public String getUsername() {
      return username;
   }
   
   public void setUsername(String username) {
      this.username = username;
   }
   
   public String getPassword() {
      return password;
   }
   
   public void setPassword(String password) {
      this.password = password;
   }
   
   public String getDriver() {
      return driver;
   }
   
   public void setDriver(String driver) {
      this.driver = driver;
   }
   
   public ConfigParser getConfigParser() {
      return configParser;
   }
   
   public void setConfigParser(ConfigParser configParser) {
      this.configParser = configParser;
   }
   
   public void load(String path, String environment) {
      path = path == null ? "" : path;
      environment = environment == null ? "" : environment;
      if (path.isEmpty() == false) {
         configParser = new ConfigParser();
         configParser.read(path);
         if (environment.isEmpty() == true) {
            url = configParser.get("url").toString();
            username = configParser.get("username").toString();
            password = configParser.get("password").toString();
            driver = configParser.get("driver").toString();
         } else {
            url = configParser.get(environment, "url").toString();
            username = configParser.get(environment, "username").toString();
            password = configParser.get(environment, "password").toString();
            driver = configParser.get(environment, "driver").toString();
         }
      }
   }
   
   public void load(String path) {
      load(path, null);
   }
   
   public Connection getConnection() {
      Connection connection = null;
      try {
         // carrega o driver na memória.
         Class.forName(driver);
         connection = DriverManager.getConnection(url, username, password);
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return connection;
   }
   
   public Connection connect(String url, String username, String password, String driver) {
      Connection connection = null;
      url = url == null ? "" : url;
      username = username == null ? "" : username;
      password = password == null ? "" : password;
      driver = driver == null ? "" : driver;
      if (url.isEmpty() == false && username.isEmpty() == false && password.isEmpty() == false && driver.isEmpty() == false) {
         try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
         } catch (ClassNotFoundException e) {
            e.printStackTrace();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return connection;
   }
   
   public void disconnect(Connection connection) {
      if (connection != null) {
         try {
            connection.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }
   
   @Override
   public String toString() {
      return "DataSource(url=" + url + ", username=" + username + ", password=" + password + ", driver=" + driver + ")";
   }
   
}
