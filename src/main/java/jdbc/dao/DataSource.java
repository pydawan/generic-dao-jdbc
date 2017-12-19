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
   
   private static final String MESSAGE_URL_NOT_FOUND = "url not found!";
   private static final String MESSAGE_USERNAME_NOT_FOUND = "username not found!";
   private static final String MESSAGE_PASSWORD_NOT_FOUND = "password not found!";
   private static final String MESSAGE_DRIVER_NOT_FOUND = "driver not found!";
   
   private String url;
   private String username;
   private String password;
   private String driver;
   private ConfigParser configParser;
   
   public static DataSource get() {
      return new DataSource();
   }
   
   public String getUrl() {
      return url;
   }
   
   public String url() {
      return getUrl();
   }
   
   public void setUrl(String url) {
      this.url = url;
   }
   
   public DataSource url(String url) {
      setUrl(url);
      return this;
   }
   
   public String getUsername() {
      return username;
   }
   
   public String username() {
      return getUsername();
   }
   
   public void setUsername(String username) {
      this.username = username;
   }
   
   public DataSource username(String username) {
      setUsername(username);
      return this;
   }
   
   public String getPassword() {
      return password;
   }
   
   public String password() {
      return getPassword();
   }
   
   public void setPassword(String password) {
      this.password = password;
   }
   
   public DataSource password(String password) {
      setPassword(password);
      return this;
   }
   
   public String getDriver() {
      return driver;
   }
   
   public String driver() {
      return getDriver();
   }
   
   public void setDriver(String driver) {
      this.driver = driver;
   }
   
   public DataSource driver(String driver) {
      setDriver(driver);
      return this;
   }
   
   public ConfigParser getConfigParser() {
      return configParser;
   }
   
   public ConfigParser configParser() {
      return getConfigParser();
   }
   
   public void setConfigParser(ConfigParser configParser) {
      this.configParser = configParser;
   }
   
   public DataSource configParser(ConfigParser configParser) {
      setConfigParser(configParser);
      return this;
   }
   
   public void load(String path, String section) {
      path = path == null ? "" : path;
      section = section == null ? "" : section;
      if (path.isEmpty() == false) {
         configParser = new ConfigParser();
         configParser.read(path);
         if (section.isEmpty() == true) {
            url = configParser.get("url").toString();
            username = configParser.get("username").toString();
            password = configParser.get("password").toString();
            driver = configParser.get("driver").toString();
         } else {
            url = configParser.get(section, "url").toString();
            username = configParser.get(section, "username").toString();
            password = configParser.get(section, "password").toString();
            driver = configParser.get(section, "driver").toString();
         }
      }
   }
   
   public void load(String path) {
      load(path, null);
   }
   
   public static DataSource get(String path, String section) {
      DataSource dataSource = new DataSource();
      dataSource.load(path, section);
      return dataSource;
   }
   
   public static DataSource get(String path) {
      return get(path, null);
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
   
   public Connection connection() {
      return getConnection();
   }
   
   private static void validateParams(String url, String username, String password, String driver) throws DAOException {
      url = url == null ? "" : url;
      username = username == null ? "" : username;
      password = password == null ? "" : password;
      driver = driver == null ? "" : driver;
      if (url.isEmpty()) {
         throw new DAOException(MESSAGE_URL_NOT_FOUND);
      }
      if (username.isEmpty()) {
         throw new DAOException(MESSAGE_USERNAME_NOT_FOUND);
      }
      if (password.isEmpty()) {
         throw new DAOException(MESSAGE_PASSWORD_NOT_FOUND);
      }
      if (driver.isEmpty()) {
         throw new DAOException(MESSAGE_DRIVER_NOT_FOUND);
      }
   }
   
   public static Connection connect(String url, String username, String password, String driver) throws DAOException {
      Connection connection = null;
      validateParams(url, username, password, driver);
      try {
         Class.forName(driver);
         connection = DriverManager.getConnection(url, username, password);
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return connection;
   }
   
   public static void disconnect(Connection connection) {
      if (connection != null) {
         try {
            connection.close();
//            connection = null;
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
