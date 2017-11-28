package jdbc.dao;

import br.org.configparser.ConfigParser;

/**
 * @author thiago-amm
 * @version v1.0.0 27/11/2017
 * @since v1.0.0
 */
public class DatabaseProperties {
   
   public static DataSourceConfig get(String path) {
      path = path == null ? "" : path;
      DataSourceConfig config = null;
      if (path.isEmpty() == false) {
         config = new DataSourceConfig();
         ConfigParser configParser = new ConfigParser();
         configParser.read(path);
         if (configParser.hasSection("")) {
            String environment = ""; // default
            String engine = "" + configParser.get("engine");
            String pool = "" + configParser.get("pool");
            String host = "" + configParser.get("host");
            String port = "" + configParser.get("port");
            String database = "" + configParser.get("database");
            String user = "" + configParser.get("user");
            String password = "" + configParser.get("password");
            String charset = "" + configParser.get("charset");
            String collate = "" + configParser.get("collate");
            String autoReconnect = "" + configParser.get("autoReconnect");
            String autoReconnectForPools = "" + configParser.get("autoReconnectForPools");
            String autoCommit = "" + configParser.get("autoCommit");
            String autoClose = "" + configParser.get("autoClose");
            String useSSL = "" + configParser.get("useSSL");
            String verifyServerCertificate = "" + configParser.get("verifyServerCertificate");
            String logging = "" + configParser.get("logging");
            config.setEnvironment(environment);
            config.setEngine(engine);
            config.setPool(pool);
            config.setHost(host);
            config.setPort(port);
            config.setDatabase(database);
            config.setUser(user);
            config.setPassword(password);
            config.setCharset(charset);
            config.setCollate(collate);
            config.setAutoReconnect(autoReconnect);
            config.setAutoReconnectForPools(autoReconnectForPools);
            config.setAutoCommit(autoCommit);
            config.setAutoClose(autoClose);
            config.setUseSSL(useSSL);
            config.setVerifyServerCertificate(verifyServerCertificate);
            config.setLogging(logging);
         }
      }
      return config;
   }
   
   public static DataSourceConfig get() {
      return get("main:database.properties");
   }
   
   public static DataSourceConfig getDevelopment(String path) {
      path = path == null ? "" : path;
      DataSourceConfig config = null;
      if (path.isEmpty() == false) {
         config = new DataSourceConfig();
         ConfigParser configParser = new ConfigParser();
         configParser.read(path);
         if (configParser.hasSection("development")) {
            String environment = ""; // default
            String engine = "" + configParser.get("development", "engine");
            String pool = "" + configParser.get("development", "pool");
            String host = "" + configParser.get("development", "host");
            String port = "" + configParser.get("development", "port");
            String database = "" + configParser.get("development", "database");
            String user = "" + configParser.get("development", "user");
            String password = "" + configParser.get("development", "password");
            String charset = "" + configParser.get("development", "charset");
            String collate = "" + configParser.get("development", "collate");
            String autoReconnect = "" + configParser.get("development", "autoReconnect");
            String autoReconnectForPools = "" + configParser.get("development", "autoReconnectForPools");
            String autoCommit = "" + configParser.get("development", "autoCommit");
            String autoClose = "" + configParser.get("development", "autoClose");
            String useSSL = "" + configParser.get("development", "useSSL");
            String verifyServerCertificate = "" + configParser.get("development", "verifyServerCertificate");
            String logging = "" + configParser.get("development", "logging");
            config.setEnvironment(environment);
            config.setEngine(engine);
            config.setPool(pool);
            config.setHost(host);
            config.setPort(port);
            config.setDatabase(database);
            config.setUser(user);
            config.setPassword(password);
            config.setCharset(charset);
            config.setCollate(collate);
            config.setAutoReconnect(autoReconnect);
            config.setAutoReconnectForPools(autoReconnectForPools);
            config.setAutoCommit(autoCommit);
            config.setAutoClose(autoClose);
            config.setUseSSL(useSSL);
            config.setVerifyServerCertificate(verifyServerCertificate);
            config.setLogging(logging);
         }
      }
      return config;
   }
   
   public static DataSourceConfig getDevelopment() {
      return getDevelopment("main:database.properties");
   }
   
   public static DataSourceConfig development(String path) {
      return getDevelopment(path);
   }
   
   public static DataSourceConfig development() {
      return getDevelopment();
   }
   
   public static DataSourceConfig getTest(String path) {
      path = path == null ? "" : path;
      DataSourceConfig config = null;
      if (path.isEmpty() == false) {
         config = new DataSourceConfig();
         ConfigParser configParser = new ConfigParser();
         configParser.read(path);
         if (configParser.hasSection("test")) {
            String environment = ""; // default
            String engine = "" + configParser.get("test", "engine");
            String pool = "" + configParser.get("test", "pool");
            String host = "" + configParser.get("test", "host");
            String port = "" + configParser.get("test", "port");
            String database = "" + configParser.get("test", "database");
            String user = "" + configParser.get("test", "user");
            String password = "" + configParser.get("test", "password");
            String charset = "" + configParser.get("test", "charset");
            String collate = "" + configParser.get("test", "collate");
            String autoReconnect = "" + configParser.get("test", "autoReconnect");
            String autoReconnectForPools = "" + configParser.get("test", "autoReconnectForPools");
            String autoCommit = "" + configParser.get("test", "autoCommit");
            String autoClose = "" + configParser.get("test", "autoClose");
            String useSSL = "" + configParser.get("test", "useSSL");
            String verifyServerCertificate = "" + configParser.get("test", "verifyServerCertificate");
            String logging = "" + configParser.get("test", "logging");
            config.setEnvironment(environment);
            config.setEngine(engine);
            config.setPool(pool);
            config.setHost(host);
            config.setPort(port);
            config.setDatabase(database);
            config.setUser(user);
            config.setPassword(password);
            config.setCharset(charset);
            config.setCollate(collate);
            config.setAutoReconnect(autoReconnect);
            config.setAutoReconnectForPools(autoReconnectForPools);
            config.setAutoCommit(autoCommit);
            config.setAutoClose(autoClose);
            config.setUseSSL(useSSL);
            config.setVerifyServerCertificate(verifyServerCertificate);
            config.setLogging(logging);
         }
      }
      return config;
   }
   
   public static DataSourceConfig getTest() {
      return getTest("main:database.properties");
   }
   
   public static DataSourceConfig test(String path) {
      return getTest(path);
   }
   
   public static DataSourceConfig test() {
      return getTest();
   }
   
   public static DataSourceConfig getStage(String path) {
      path = path == null ? "" : path;
      DataSourceConfig config = null;
      if (path.isEmpty() == false) {
         config = new DataSourceConfig();
         ConfigParser configParser = new ConfigParser();
         if (configParser.hasSection("stage")) {
            String environment = ""; // default
            String engine = "" + configParser.get("stage", "engine");
            String pool = "" + configParser.get("stage", "pool");
            String host = "" + configParser.get("stage", "host");
            String port = "" + configParser.get("stage", "port");
            String database = "" + configParser.get("stage", "database");
            String user = "" + configParser.get("stage", "user");
            String password = "" + configParser.get("stage", "password");
            String charset = "" + configParser.get("stage", "charset");
            String collate = "" + configParser.get("stage", "collate");
            String autoReconnect = "" + configParser.get("stage", "autoReconnect");
            String autoReconnectForPools = "" + configParser.get("stage", "autoReconnectForPools");
            String autoCommit = "" + configParser.get("stage", "autoCommit");
            String autoClose = "" + configParser.get("stage", "autoClose");
            String useSSL = "" + configParser.get("stage", "useSSL");
            String verifyServerCertificate = "" + configParser.get("stage", "verifyServerCertificate");
            String logging = "" + configParser.get("stage", "logging");
            config.setEnvironment(environment);
            config.setEngine(engine);
            config.setPool(pool);
            config.setHost(host);
            config.setPort(port);
            config.setDatabase(database);
            config.setUser(user);
            config.setPassword(password);
            config.setCharset(charset);
            config.setCollate(collate);
            config.setAutoReconnect(autoReconnect);
            config.setAutoReconnectForPools(autoReconnectForPools);
            config.setAutoCommit(autoCommit);
            config.setAutoClose(autoClose);
            config.setUseSSL(useSSL);
            config.setVerifyServerCertificate(verifyServerCertificate);
            config.setLogging(logging);
         }
      }
      return config;
   }
   
   public static DataSourceConfig getStage() {
      return getStage("main:database.properties");
   }
   
   public static DataSourceConfig stage(String path) {
      return getStage(path);
   }
   
   public static DataSourceConfig stage() {
      return getStage();
   }
   
   public static DataSourceConfig getProduction(String path) {
      path = path == null ? "" : path;
      DataSourceConfig config = null;
      if (path.isEmpty() == false) {
         config = new DataSourceConfig();
         ConfigParser configParser = new ConfigParser();
         configParser.read(path);
         if (configParser.hasSection("production")) {
            String environment = ""; // default
            String engine = "" + configParser.get("production", "engine");
            String pool = "" + configParser.get("production", "pool");
            String host = "" + configParser.get("production", "host");
            String port = "" + configParser.get("production", "port");
            String database = "" + configParser.get("production", "database");
            String user = "" + configParser.get("production", "user");
            String password = "" + configParser.get("production", "password");
            String charset = "" + configParser.get("production", "charset");
            String collate = "" + configParser.get("production", "collate");
            String autoReconnect = "" + configParser.get("production", "autoReconnect");
            String autoReconnectForPools = "" + configParser.get("production", "autoReconnectForPools");
            String autoCommit = "" + configParser.get("production", "autoCommit");
            String autoClose = "" + configParser.get("production", "autoClose");
            String useSSL = "" + configParser.get("production", "useSSL");
            String verifyServerCertificate = "" + configParser.get("production", "verifyServerCertificate");
            String logging = "" + configParser.get("production", "logging");
            config.setEnvironment(environment);
            config.setEngine(engine);
            config.setPool(pool);
            config.setHost(host);
            config.setPort(port);
            config.setDatabase(database);
            config.setUser(user);
            config.setPassword(password);
            config.setCharset(charset);
            config.setCollate(collate);
            config.setAutoReconnect(autoReconnect);
            config.setAutoReconnectForPools(autoReconnectForPools);
            config.setAutoCommit(autoCommit);
            config.setAutoClose(autoClose);
            config.setUseSSL(useSSL);
            config.setVerifyServerCertificate(verifyServerCertificate);
            config.setLogging(logging);
         }
      }
      return config;
   }
   
   public static DataSourceConfig getProduction() {
      return getProduction("main:database.properties");
   }
   
   public static DataSourceConfig production(String path) {
      return getProduction(path);
   }
   
   public static DataSourceConfig production() {
      return getProduction();
   }
   
}
