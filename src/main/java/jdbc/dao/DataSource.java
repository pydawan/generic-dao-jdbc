package jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author thiago-amm
 * @version v1.0.0 15/11/2017
 * @since v1.0.0
 */
public class DataSource {
   
   private DataSourceConfig config;
   
   public DataSource(DataSourceConfig config) {
      this.config = config;
   }
   
   public DataSource() {
      this(null);
   }
   
   public DataSourceConfig getConfig() {
      return config;
   }
   
   public DataSourceConfig config() {
      return getConfig();
   }
   
   public void setConfig(DataSourceConfig config) {
      this.config = config;
   }
   
   public DataSource config(DataSourceConfig config) {
      setConfig(config);
      return this;
   }
   
   public static boolean isWindows() {
      boolean isWindows = false;
      if (System.getProperty("os.name").toLowerCase().contains("windows")) {
         isWindows = true;
      }
      return isWindows;
   }
   
   public static boolean isLinux() {
      boolean isWindows = false;
      if (System.getProperty("os.name").toLowerCase().contains("linux")) {
         isWindows = true;
      }
      return isWindows;
   }
   
   public static String normalizeFilePath(String path) {
      if (isWindows()) {
         path = path.replace("\\", "/");
         path = path.substring(0, 1).equals("/") ? path.substring(1) : path;
      }
      return path;
   }
   
   public Connection getConnection() {
      Connection connection = null;
      try {
         Class.forName(config.getDriver());
         connection = DriverManager.getConnection(config.getUrl());
      } catch (ClassNotFoundException | SQLException e) {
         e.printStackTrace();
      }
      return connection;
   }
   
   public Connection connection() {
      return getConnection();
   }
   
   public static Connection connect(String... args) {
      Connection connection = null;
      if (args != null && args.length > 0) {
         DataSourceConfig config = new DataSourceConfig();
         for (int i = 0; i < args.length; i++) {
            String property = args[i] == null ? "" : args[i];
            property = args[i].replaceAll(" = ", "=");
            String key = property.split("=")[0];
            String value = property.split("=")[1];
            // Environment
            if (key.matches("^(env|environment)$")) {
               if (value.matches("^(dev|development)$")) {
                  config.setEnvironment(Environment.DEVELOPMENT);
               } else if (value.matches("^(test)$")) {
                  config.setEnvironment(Environment.TEST);
               } else if (value.matches("^(stage)$")) {
                  config.setEnvironment(Environment.STAGE);
               } else if (value.matches("^(prod|production)$")) {
                  config.setEnvironment(Environment.PRODUCTION);
               } else {
                  
               }
               continue;
            }
            // Engine
            if (key.matches("^(engine)$")) {
               if (value.matches("^(h2)$")) {
                  config.setEngine(Engine.H2);
               } else if (value.matches("^(sqlite)$")) {
                  config.setEngine(Engine.SQLITE);
               } else if (value.matches("^(mysql)$")) {
                  config.setEngine(Engine.MYSQL);
               } else if (value.matches("^(postgresql)$")) {
                  config.setEngine(Engine.POSTGRESQL);
               } else if (value.matches("^(oracle)$")) {
                  config.setEngine(Engine.ORACLE);
               } else {
                  
               }
               continue;
            }
            // Pool
            if (key.matches("^(pool)$")) {
               if (value.matches("^(none)$")) {
                  config.setPool(Pool.NONE);
               } else if (value.matches("^(c3p0)$")) {
                  config.setPool(Pool.C3P0);
               } else if (value.matches("^(hikaricp)$")) {
                  config.setPool(Pool.HIKARICP);
               } else {
                  
               }
               continue;
            }
            // Host
            if (key.matches("^(host)$")) {
               if (value.matches("^([a-zA-Z0-9.]+)$")) {
                  config.setHost(value);
               } else {
                  
               }
               continue;
            }
            // Port
            if (key.matches("^(port)$")) {
               if (value.matches("^(\\d+)$")) {
                  config.setPort(Integer.parseInt(value));
               } else {
                  
               }
               continue;
            }
            // Database
            if (key.matches("^(database)$")) {
               if (value.matches("^(\\w+)$")) {
                  config.setDatabase(value);
               } else {
                  
               }
               continue;
            }
            // User
            if (key.matches("^(user)$")) {
               if (value.matches("^(\\w+)$")) {
                  config.setUser(value);
               } else {
                  
               }
               continue;
            }
            // Password
            if (key.matches("^(password)$")) {
               if (value.matches("^([\\p{L}\\d])+$")) {
                  config.setPassword(value);
               } else {
                  
               }
               continue;
            }
            // Charset
            if (key.matches("^(charset)$")) {
               if (value.matches("^([\\w\\d]+)$")) {
                  config.setCharset(value.toLowerCase());
               } else {
                  
               }
               continue;
            }
            // Collate
            if (key.matches("^(collate)$")) {
               if (value.matches("^([\\w\\d]+)$")) {
                  config.setCollate(value);
               } else {
                  
               }
               continue;
            }
            // Auto reconnect
            if (key.matches("^(autoReconnect)$")) {
               if (value.matches("^(false|true)$")) {
                  config.setAutoReconnect(Boolean.parseBoolean(value));
               } else {
                  
               }
               continue;
            }
            // Auto reconnect for pools
            if (key.matches("^(autoReconnectForPools)$")) {
               if (value.matches("^(false|true)$")) {
                  config.setAutoReconnectForPools(Boolean.parseBoolean(value));
               } else {
                  
               }
               continue;
            }
            // Auto commit
            if (key.matches("^(autoCommit)$")) {
               if (value.matches("^(false|true)$")) {
                  config.setAutoCommit(Boolean.parseBoolean(value));
               } else {
                  
               }
               continue;
            }
            // Auto close
            if (key.matches("^(autoClose)$")) {
               if (value.matches("^(false|true)$")) {
                  config.setAutoClose(Boolean.parseBoolean(value));
               } else {
                  
               }
               continue;
            }
            // Use SSL
            if (key.matches("^(useSSL)$")) {
               if (value.matches("^(false|true)$")) {
                  config.setUseSSL(Boolean.parseBoolean(value));
               } else {
                  
               }
               continue;
            }
            // Verify Server Certificate
            if (key.matches("^(verifyServerCertificate)$")) {
               if (value.matches("^(false|true)$")) {
                  config.setVerifyServerCertificate(Boolean.parseBoolean(value));
               } else {
                  
               }
               continue;
            }
            // Logging
            if (key.matches("^(logging)$")) {
               if (value.matches("^(false|true)$")) {
                  config.setLogging(Boolean.parseBoolean(value));
               } else {
                  
               }
               continue;
            }
         }
         // System.out.println(config);
         try {
            Class.forName(config.getDriver());
            connection = DriverManager.getConnection(config.getUrl());
         } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
         }
      }
      return connection;
   }
   
   public static String environment(String environment) {
      return "environment=" + environment;
   }
   
   public static String engine(String engine) {
      return "engine=" + engine;
   }
   
   public static String pool(String pool) {
      return "pool=" + pool;
   }
   
   public static String host(String host) {
      return "host=" + host;
   }
   
   public static String port(String port) {
      return "port=" + port;
   }
   
   public static String port(int port) {
      return "port=" + port;
   }
   
   public static String database(String database) {
      return "database=" + database;
   }
   
   public static String user(String user) {
      return "user=" + user;
   }
   
   public static String password(String password) {
      return "password=" + password;
   }
   
   public static String charset(String charset) {
      return "charset=" + charset;
   }
   
   public static String collate(String collate) {
      return "collate=" + collate;
   }
   
   public static String autoReconnect(boolean autoReconnect) {
      return "autoReconnect=" + autoReconnect;
   }
   
   public static String autoReconnectForPools(boolean autoReconnectForPools) {
      return "autoReconnectForPools=" + autoReconnectForPools;
   }
   
   public static String autoCommit(boolean autoCommit) {
      return "autoCommit=" + autoCommit;
   }
   
   public static String autoClose(boolean autoClose) {
      return "autoClose=" + autoClose;
   }
   
   public static String useSSL(boolean useSSL) {
      return "useSSL=" + useSSL;
   }
   
   public static String verifyServerCertificate(boolean verifyServerCertificate) {
      return "verifyServerCertificate=" + verifyServerCertificate;
   }
   
   public static String logging(boolean logging) {
      return "logging=" + logging;
   }
   
}
