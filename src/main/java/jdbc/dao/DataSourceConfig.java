package jdbc.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;
import java.util.regex.Matcher;

/**
 * @author thiago-amm
 * @version v1.0.0 05/11/2017
 * @since v1.0.0
 */
public class DataSourceConfig {
   
   private static final File FILE = new File(".");
   private static String exceptionMessage;
   
   public static final String REGEX_FILE_SEPARATOR = Matcher.quoteReplacement(File.separator);
   public static final String FILE_SEPARATOR = File.separator.replaceAll("\\\\", "/");
   public static final String PROJECT_DIR = FILE.getAbsolutePath().replace(".", "");
   public static final String PROJECT_DIR_DATABASE_PROPERTIES = PROJECT_DIR + "database.properties";
   
   public static final String BIN_DIR = PROJECT_DIR + "bin/";
   public static final String BUILD_DIR = PROJECT_DIR + "build/";
   public static final String DOC_DIR = PROJECT_DIR + "doc/";
   public static final String SRC_DIR = PROJECT_DIR + "src/";
   public static final String LIB_DIR = PROJECT_DIR + "lib/";
   
   public static final String SRC_MAIN_DIR = SRC_DIR + "main/";
   public static final String SRC_MAIN_JAVA_DIR = SRC_MAIN_DIR + "java/";
   public static final String SRC_MAIN_RESOURCES_DIR = SRC_MAIN_DIR + "resources/";
   public static final String SRC_MAIN_RESOURCES_DATABASE_PROPERTIES = SRC_MAIN_RESOURCES_DIR + "database.properties";
   public static final String SRC_MAIN_RESOURCES_SQL_DIR = SRC_MAIN_RESOURCES_DIR + "sql/";
   public static final String SRC_MAIN_RESOURCES_METAINF_DIR = SRC_MAIN_RESOURCES_DIR + "META-INF/";
   public static final String SRC_MAIN_WEBAPP_DIR = SRC_MAIN_DIR + "webapp/";
   public static final String SRC_MAIN_WEBAPP_METAINF_DIR = SRC_MAIN_WEBAPP_DIR + "META-INF/";
   public static final String SRC_MAIN_WEBAPP_WEBINF_DIR = SRC_MAIN_WEBAPP_DIR + "WEB-INF/";
   public static final String SRC_MAIN_WEBAPP_WEBINF__LIB_DIR = SRC_MAIN_WEBAPP_WEBINF_DIR + "lib/";
   public static final String SRC_MAIN_WEBAPP_WEBINF_DATABASE_PROPERTIES = SRC_MAIN_WEBAPP_WEBINF_DIR + "database.properties";
   
   public static final String SRC_TEST_DIR = SRC_MAIN_DIR.replace("main", "test");
   public static final String SRC_TEST_JAVA_DIR = SRC_MAIN_JAVA_DIR.replace("main", "test");
   public static final String SRC_TEST_RESOURCES_DIR = SRC_MAIN_RESOURCES_DIR.replace("main", "test");
   public static final String SRC_TEST_RESOURCES_DATABASE_PROPERTIES = SRC_TEST_RESOURCES_DIR + "database.properties";
   
   public static String DATABASE_PROPERTIES_PATH = null;
   public static boolean DATABASE_PROPERTIES_LOADED = false;
   public static FileInputStream DATABASE_PROPERTIES_FILE;
   public static Properties DATABASE_PROPERTIES;
   
   private Environment environment;
   private Engine engine;
   private Pool pool;
   private String host;
   private Integer port;
   private String database;
   private String user;
   private String password;
   private String charset;
   private String collate;
   private Boolean autoReconnect;
   private Boolean autoReconnectForPools;
   private Boolean autoCommit;
   private Boolean autoClose;
   private Boolean useSSL;
   private Boolean verifyServerCertificate;
   private Boolean logging;
   private String driver;
   private String url;
   
   /**
    * Carrega o arquivo de propriedades database.properties.
    */
   public static void load() {
      try {
         DATABASE_PROPERTIES_PATH = DataSourceConfig.class.getClassLoader().getResource("database.properties").getPath();
         DATABASE_PROPERTIES_PATH = URLDecoder.decode(DATABASE_PROPERTIES_PATH, System.getProperty("file.encoding"));
         File databaseProperties = new File(DATABASE_PROPERTIES_PATH);
         if (databaseProperties.exists()) {
            DATABASE_PROPERTIES_FILE = new FileInputStream(databaseProperties);
         } else {
            exceptionMessage = "ATENÇÃO: Não foi possível encontrar o arquivo de configurações database.properties!";
            throw new IllegalStateException(exceptionMessage);
         }
         DATABASE_PROPERTIES = new Properties();
         DATABASE_PROPERTIES.load(DATABASE_PROPERTIES_FILE);
         for (Object o : DATABASE_PROPERTIES.keySet()) {
            String key = o.toString().toLowerCase().trim();
            String value = DATABASE_PROPERTIES.getProperty(key);
            value = value == null ? "" : value.trim().toLowerCase();
            System.out.println(value);
         }
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public DataSourceConfig() {
      environment = Environment.DEVELOPMENT;
      engine = Engine.MYSQL;
      pool = Pool.HIKARICP;
      host = "localhost";
      port = 3306;
      database = "";
      user = "";
      password = "";
      charset = "uf8";
      collate = "utf8_general_ci";
      autoReconnect = false;
      autoReconnectForPools = false;
      autoCommit = false;
      autoClose = false;
      useSSL = false;
      verifyServerCertificate = false;
      logging = false;
      driver = Engine.MYSQL.getDriver();
      url = Engine.MYSQL.getUrl();
   }
   
   public static DataSourceConfig create() {
      return new DataSourceConfig();
   }
   
   public Environment getEnviroment() {
      return environment;
   }
   
   public Environment enviroment() {
      return getEnviroment();
   }
   
   public void setEnvironment(Environment environment) {
      this.environment = environment == null ? Environment.DEVELOPMENT : environment;
   }
   
   public DataSourceConfig environment(Environment environment) {
      setEnvironment(environment);
      return this;
   }
   
   public Engine getEngine() {
      return engine;
   }
   
   public Engine engine() {
      return getEngine();
   }
   
   public void setEngine(Engine engine) {
      this.engine = engine == null ? Engine.MYSQL : engine;
      switch (engine.toString()) {
         case "mysql":
            url = Engine.MYSQL.getUrl();
         break;
         case "postgresql":
            url = Engine.POSTGRESQL.getUrl();
         break;
         case "oracle":
            url = Engine.ORACLE.getUrl();
         break;
      }
   }
   
   public DataSourceConfig engine(Engine engine) {
      setEngine(engine);
      return this;
   }
   
   public Pool getPool() {
      return pool;
   }
   
   public Pool pool() {
      return getPool();
   }
   
   public void setPool(Pool pool) {
      this.pool = pool == null ? Pool.NONE : pool;
   }
   
   public DataSourceConfig pool(Pool pool) {
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
      this.host = host == null ? "localhost" : host;
   }
   
   public DataSourceConfig host(String host) {
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
      this.port = port == null ? 3306 : port;
   }
   
   public DataSourceConfig port(Integer port) {
      setPort(port);
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
            this.database = user;
         } else {
            switch (engine.toString()) {
               case "mysql":
                  database = "mysql";
                  break;
               case "postgresql":
                  database = "postgres";
                  break;
               case "oracle":
                  database = "xe";
                  break;
            }
         }
      }
      this.database = database;
   }
   
   public DataSourceConfig database(String database) {
      setDatabase(database);
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
         switch (engine.toString()) {
            case "mysql":
               user = "root";
               break;
            case "postgresql":
               user = "postgres";
               break;
            case "oracle":
               user = "hr";
               break;
         }
      }
      if (database.isEmpty()) {
         database = user;
      }
      if (password.isEmpty()) {
         password = user;
      }
      this.user = user;
   }
   
   public DataSourceConfig user(String user) {
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
      this.password = password == null ? "" : password;
      if (password.isEmpty()) {
         if (!user.isEmpty()) {
            password = user;
         }
      }
      this.password = password;
   }
   
   public DataSourceConfig password(String password) {
      setPassword(password);
      return this;
   }
   
   public String getCharset() {
      return charset;
   }
   
   public String charset() {
      return getCharset();
   }
   
   public void setCharset(String charset) {
      this.charset = charset == null ? "utf8" : charset;
   }
   
   public DataSourceConfig charset(String charset) {
      setCharset(charset);
      return this;
   }
   
   public String getCollate() {
      return collate;
   }
   
   public String collate() {
      return getCollate();
   }
   
   public void setCollate(String collate) {
      this.collate = collate == null ? "utf8_general_ci" : collate;
   }
   
   public DataSourceConfig collate(String collate) {
      setCollate(collate);
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
   
   public DataSourceConfig autoReconnect(Boolean autoReconnect) {
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
   
   public DataSourceConfig autoReconnectForPools(Boolean autoReconnectForPools) {
      setAutoReconnect(autoReconnectForPools);
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
   
   public DataSourceConfig autoCommit(Boolean autoCommit) {
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
   
   public DataSourceConfig autoClose(Boolean autoClose) {
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
   
   public DataSourceConfig useSSL(Boolean useSSL) {
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
   
   public DataSourceConfig verifyServerCertificate(Boolean verifyServerCertificate) {
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
   
   public DataSourceConfig logging(Boolean logging) {
      setLogging(logging);
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
   
   public DataSourceConfig driver(String driver) {
      this.driver = driver == null ? Engine.MYSQL.getDriver() : driver;
      return this;
   }
   
   public String getUrl() {
      String url = "";
      if (engine.equals(Engine.MYSQL)) {
         url = String.format(this.url, host, port, database, user, password, verifyServerCertificate, useSSL);
      } else {
         url = String.format(this.url, this.host, this.port, this.database);
      }
      this.url = url;
      return url;
   }
   
   @Override
   public String toString() {
      return "DataSourceConfig(enviroment=" + environment + ", engine=" + engine + ", pool=" + pool + ", host=" + host + ", port=" + port +
         ", database=" + database + ", user=" + user + ", password=" + password + ", charset=" + charset + ", collate=" + collate +
         ", autoReconnect=" + autoReconnect + ", autoReconnectForPools=" + autoReconnectForPools + ", autoCommit=" + autoCommit +
         ", autoClose=" + autoClose + ", useSSL=" + useSSL + ", verifyServerCertificate=" + verifyServerCertificate + ", logging=" +
         logging + ", driver=" + driver + ", url=" + url + ")";
   }
   
}
