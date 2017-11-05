package jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author thiago-amm
 * @version v1.0.0 04/11/2017
 * @since v1.0.0
 */
public class DataSource {
   
   private DatabaseEngine engine;
   private Enviroment enviroment;
   private PoolEngine pool;
   private String prefix;
   private String host;
   private Integer port;
   private String user;
   private String password;
   private String database;
   private Boolean autoReconnect;
   private Boolean autoReconnectForPools;
   private Boolean autoCommit;
   private Boolean autoClose;
   private Boolean useSSL;
   private Boolean verifyServerCertificate;
   private Boolean logging;
   private String url;
   
   private String driver;
   
   public DatabaseEngine getEngine() {
      return engine;
   }
   
   public DatabaseEngine engine() {
      return getEngine();
   }
   
   public void setEngine(DatabaseEngine engine) {
      this.engine = engine == null ? DatabaseEngine.MYSQL : engine;
   }
   
   public DataSource engine(DatabaseEngine engine) {
      setEngine(engine);
      return this;
   }
   
   public Enviroment getEnviroment() {
      return enviroment;
   }
   
   public Enviroment enviroment() {
      return getEnviroment();
   }
   
   public void setEnviroment(Enviroment enviroment) {
      this.enviroment = enviroment == null ? Enviroment.DEVELOPMENT : enviroment;
   }
   
   public DataSource enviroment(Enviroment enviroment) {
      setEnviroment(enviroment);
      return this;
   }
   
   public PoolEngine getPool() {
      return pool;
   }
   
   public PoolEngine pool() {
      return getPool();
   }
   
   public void setPool(PoolEngine pool) {
      this.pool = pool == null ? PoolEngine.NONE : pool;
   }
   
   public DataSource pool(PoolEngine pool) {
      setPool(pool);
      return this;
   }
   
   public String getHost() {
      return host;
   }
   
   public String host() {
      return getHost();
   }
   
   public void setHost(String host) {
      host = host == null ? "localhost" : host;
      this.host = host;
   }
   
   public DataSource host(String host) {
      setHost(host);
      return this;
   }
   
   public Integer getPort() {
      return port;
   }
   
   public Integer port() {
      return getPort();
   }
   
   public void setPort(Integer port) {
      port = port == null ? 0 : port;
      if (engine.equals(DatabaseEngine.MYSQL)) {
         port = 3306;
      } else if (engine.equals(DatabaseEngine.POSTGRESQL)) {
         port = 5432;
      } else if (engine.equals(DatabaseEngine.ORACLE)) {
         port = 1521;
      } else {
         
      }
      this.port = port;
   }
   
   public DataSource port(Integer port) {
      setPort(port);
      return this;
   }
   
   public String getUser() {
      return user;
   }
   
   public String user() {
      return getUser();
   }
   
   public void setUser(String user) {
      user = user == null ? "" : user;
      if (user.isEmpty()) {
         if (engine.equals(DatabaseEngine.MYSQL)) {
            user = "root";
         } else if (engine.equals(DatabaseEngine.POSTGRESQL)) {
            user = "postgres";
         } else if (engine.equals(DatabaseEngine.ORACLE)) {
            user = "hr";
         } else if (engine.equals(DatabaseEngine.H2)) {
            user = "sa";
         } else {
            
         }
      }
      this.user = user;
   }
   
   public DataSource user(String user) {
      setUser(user);
      return this;
   }
   
   public String getPassword() {
      return password;
   }
   
   public String password() {
      return getPassword();
   }
   
   public void setPassword(String password) {
      password = password == null ? "" : password;
      if (password.isEmpty()) {
         if (!user.isEmpty()) {
            password = user;
         }
      }
      this.password = password;
   }
   
   public DataSource password(String password) {
      setPassword(password);
      return this;
   }
   
   public String getDatabase() {
      return database;
   }
   
   public String database() {
      return getDatabase();
   }
   
   public void setDatabase(String database) {
      database = database == null ? "" : database;
      if (database.isEmpty()) {
         if (!user.isEmpty()) {
            database = user;
         } else {
            if (engine.equals(DatabaseEngine.MYSQL)) {
               database = "mysql";
            } else if (engine.equals(DatabaseEngine.POSTGRESQL)) {
               database = "postgres";
            } else if (engine.equals(DatabaseEngine.ORACLE)) {
               database = "xe";
            } else if (engine.equals(DatabaseEngine.H2)) {
               database = "test";
            } else {
               
            }
         }
      }
      this.database = database;
   }
   
   public DataSource database(String database) {
      setDatabase(database);
      return this;
   }
   
   public Boolean getAutoReconnect() {
      return autoReconnect;
   }
   
   public Boolean autoReconnect() {
      return getAutoReconnect();
   }
   
   public void setAutoReconnect(Boolean autoReconnect) {
      this.autoReconnect = autoReconnect == null ? false : autoReconnect;
   }
   
   public DataSource autoReconnect(Boolean autoReconnect) {
      setAutoReconnect(autoReconnect);
      return this;
   }
   
   public Boolean getAutoReconnectForPools() {
      return autoReconnectForPools;
   }
   
   public Boolean autoReconnectForPools() {
      return getAutoReconnectForPools();
   }
   
   public void setAutoReconnectForPools(Boolean autoReconnectForPools) {
      this.autoReconnectForPools = autoReconnectForPools == null ? false : autoReconnectForPools;
   }
   
   public DataSource autoReconnectForPools(Boolean autoReconnectForPools) {
      setAutoReconnectForPools(autoReconnectForPools);
      return this;
   }
   
   public Boolean getAutoCommit() {
      return autoCommit;
   }
   
   public Boolean autoCommit() {
      return getAutoCommit();
   }
   
   public void setAutoCommit(Boolean autoCommit) {
      this.autoCommit = autoCommit == null ? false : autoCommit;
   }
   
   public DataSource autoCommit(Boolean autoCommit) {
      setAutoCommit(autoCommit);
      return this;
   }
   
   public Boolean getAutoClose() {
      return autoClose;
   }
   
   public Boolean autoClose() {
      return getAutoClose();
   }
   
   public void setAutoClose(Boolean autoClose) {
      this.autoClose = autoClose == null ? false : autoClose;
   }
   
   public DataSource autoClose(Boolean autoClose) {
      setAutoClose(autoClose);
      return this;
   }
   
   public Boolean getUseSSL() {
      return useSSL;
   }
   
   public Boolean useSSL() {
      return getUseSSL();
   }
   
   public void setUseSSL(Boolean useSSL) {
      this.useSSL = useSSL == null ? false : useSSL;
   }
   
   public DataSource useSSL(Boolean useSSL) {
      setUseSSL(useSSL);
      return this;
   }
   
   public Boolean getVerifyServerCertificate() {
      return verifyServerCertificate;
   }
   
   public Boolean verifyServerCertificate() {
      return getVerifyServerCertificate();
   }
   
   public void setVerifyServerCertificate(Boolean verifyServerCertificate) {
      this.verifyServerCertificate = verifyServerCertificate == null ? false : verifyServerCertificate;
   }
   
   public DataSource verifyServerCertificate(Boolean verifyServerCertificate) {
      setVerifyServerCertificate(verifyServerCertificate);
      return this;
   }
   
   public Boolean getLogging() {
      return logging;
   }
   
   public Boolean logging() {
      return getLogging();
   }
   
   public void setLogging(Boolean logging) {
      this.logging = logging == null ? false : logging;
   }
   
   public DataSource logging(Boolean logging) {
      setLogging(logging);
      return this;
   }
   
   public String getURL() {
      if (logging) {
         prefix = "jdbc:log4jdbc";
         driver = "net.sf.log4jdbc.DriverSpy";
      } else {
         prefix = "jdbc";
      }
      if (engine.equals(DatabaseEngine.MYSQL)) {
         driver = "com.mysql.jdbc.Driver";
         url = String.format(
            "%s:%s://%s:%s/%s?user=%s&password=%s",
            prefix,
            engine,
            host,
            port,
            database,
            user,
            password
         );
      } else if (engine.equals(DatabaseEngine.POSTGRESQL)) {
         driver = "org.postgresql.Driver";
      } else if (engine.equals(DatabaseEngine.ORACLE)) {
         driver = "oracle.jdbc.driver.OracleDriver";
      } else if (engine.equals(DatabaseEngine.H2)) {
         driver = "org.h2.Driver";
      } else {
         
      }
      url += String.format("&verifyServerCertificate=%s", verifyServerCertificate);
      url += String.format("&useSSL=%s", useSSL);
      url += String.format("&autoReconnect=%s", autoReconnect);
      url += String.format("&autoReconnectForPools=%s", autoReconnectForPools);
      return url;
   }
   
   public String url() {
      return getURL();
   }
   
   public Connection getConnection() {
      Connection connection = null;
      try {
         Class.forName(driver);
         connection = DriverManager.getConnection(url);
      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      }
      return connection;
   }
   
   public Connection connection() {
      return getConnection();
   }
   
   public static Connection connect(String url) {
      Connection connection = null;
      url = url == null ? "" : url;
      if (!url.isEmpty()) {
         try {
            String driver = "";
            if (url.startsWith("jdbc:mysql")) {
               driver = DatabaseEngine.MYSQL.getDriver();
            } else if (url.startsWith("jdbc:postgresql")) {
               driver = DatabaseEngine.POSTGRESQL.getDriver();
            } else if (url.startsWith("jdbc:oracle")) {
               driver = DatabaseEngine.ORACLE.getDriver();
            } else {
               
            }
            try {
               Class.forName(driver);
            } catch (ClassNotFoundException e) {
               e.printStackTrace();
            }
            connection = DriverManager.getConnection(url);
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
      return connection;
   }
   
   public static Connection connect(String... args) {
      Connection connection = null;
      if (args != null && args.length > 0) {
         String engine = "";
         String driver = "";
         String host = "";
         Integer port = 0;
         String database = "";
         String user = "";
         String password = "";
         Boolean autoReconnect = false;
         Boolean autoReconnectForPools = false;
         Boolean autoCommit = false;
         Boolean autoClose = false;
         Boolean useSSL = false;
         Boolean verifyServerCertificate = false;
         Boolean logging = false;
         String url = "";
         for (int i = 0; i < args.length; i++) {
            args[i] = args[i].trim();
            args[i] = args[i].toLowerCase();
            args[i] = args[i].replace(" = ", "=");
            // engine
            if (args[i].matches("^engine=.*$")) {
               if (args[i].equals("engine=mysql")) {
                  engine = "mysql";
                  driver = "com.mysql.jdbc.Driver";
                  port = 3306;
                  database = "mysql";
                  user = "root";
                  password = "mysql";
                  url = "jdbc:mysql://%s:%s/%s?user=%s&password=%s";
               } else if (args[i].equals("engine=postgresql")) {
                  engine = "postgresql";
                  driver = "org.postgresql.Driver";
                  port = 5432;
                  database = "postgres";
                  user = "postgres";
                  password = "postgres";
                  url = "jdbc:postgresql://%s:%s/%s";
               } else if (args[i].equals("engine=oracle")) {
                  engine = "oracle";
                  driver = "oracle.jdbc.driver.OracleDriver";
                  port = 1521;
                  database = "xe";
                  user = "hr";
                  password = "hr";
                  url = "jdbc:oracle:thin:@%s:%s:%s";
               } else if (args[i].equals("engine=sqlite")) {
                  engine = "sqlite";
                  driver = "org.sqlite.JDBC";
               } else if (args[i].equals("engine=h2")) {
                  engine = "h2";
                  driver = "org.h2.Driver";
                  database = "test";
                  user = "sa";
                  password = "1";
                  url = "jdbc:%s:~/%s";
               } else {
                  
               }
            }
            // host
            if (args[i].matches("^host=.*$")) {
               if (args[i].split("=").length > 1) {
                  host = args[i].split("=")[1];
                  host = host == "null" || host == "" ? "localhost" : host;
               }
            }
            // port
            if (args[i].matches("^port=\\d+$")) {
               port = Integer.parseInt(args[i].split("=")[1]);
            }
            // database
            if (args[i].matches("^database=.*$")) {
               if (args[i].split("=").length > 1) {
                  database = args[i].split("=")[1];
               }
            }
            // user
            if (args[i].matches("^user=.*$")) {
               if (args[i].split("=").length > 1) {
                  user = args[i].split("=")[1];
               }
            }
            // password
            if (args[i].matches("^password=.*$")) {
               if (args[i].split("=").length > 1) {
                  password = args[i].split("=")[1];
               }
            }
            // autoReconnect
            if (args[i].matches("^autoReconnect=(false|true)$")) {
               autoReconnect = Boolean.parseBoolean(args[i].split("=")[1]);
               url += String.format("&autoReconnect=%s", autoReconnect);
            }
            // autoReconnectForPools
            if (args[i].matches("^autoReconnectForPools=(false|true)$")) {
               autoReconnectForPools = Boolean.parseBoolean(args[i].split("=")[1]);
               url += String.format("&autoReconnectForPools=%s", autoReconnectForPools);
            }
            // autoCommit
            if (args[i].matches("^autoCommit=(false|true)$")) {
               autoCommit = Boolean.parseBoolean(args[i].split("=")[1]);
            }
            // autoClose
            if (args[i].matches("^autoClose=(false|true)$")) {
               autoClose = Boolean.parseBoolean(args[i].split("=")[1]);
            }
            // useSSL
            if (args[i].matches("^useSSL=(false|true)$")) {
               useSSL = Boolean.parseBoolean(args[i].split("=")[1]);
               url += String.format("&useSSL=%s", useSSL);
            }
            // verifyServerCertificate
            if (args[i].matches("^verifyServerCertificate=(false|true)$")) {
               verifyServerCertificate = Boolean.parseBoolean(args[i].split("=")[1]);
               url += String.format("&verifyServerCertificate=%s", verifyServerCertificate);
            }
            // logging
            if (args[i].matches("^logging=(false|true)$")) {
               logging = Boolean.parseBoolean(args[i].split("=")[1]);
//               prefix = "jdbc:log4jdbc";
               driver = "net.sf.log4jdbc.DriverSpy";
            }
            if (!user.isEmpty() && password.isEmpty()) {
               password = user;
            }
            if (!user.isEmpty() && database.isEmpty()) {
               database = user;
            }
         }
         connection = DataSource.connect(url);
      }
      return connection;
   }
   
   public static String engine(String engine) {
      engine = engine == null ? "" : engine;
      if (!engine.matches("(mysql|postgresql|oracle|h2|sqlite)")) {
         engine = "mysql";
      }
      return engine;
   }
   
}
