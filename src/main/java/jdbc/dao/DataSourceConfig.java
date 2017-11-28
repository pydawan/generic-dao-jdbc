package jdbc.dao;

import br.org.configparser.ConfigParser;

/**
 * @author thiago-amm
 * @version v1.0.0 05/11/2017
 * @since v1.0.0
 */
public class DataSourceConfig {
   
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
   
   public DataSourceConfig load(String filePath) {
      DataSourceConfig dataSourceConfig = null;
      filePath = filePath == null ? "" : filePath;
      if (filePath.isEmpty() == false) {
         try {
            ConfigParser configParser = new ConfigParser();
            configParser.read(filePath);
            System.out.println("==> database.properties loading (start)");
            System.out.println("==> database.properties loading (end)");
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      return dataSourceConfig;
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
   
   public void setEnvironment(String environment) {
      environment = environment == null ? "" : environment;
      switch (environment) {
         case "development":
         case "dev":
            this.environment = Environment.DEVELOPMENT;
         break;
         case "test":
            this.environment = Environment.TEST;
         break;
         case "stage":
            this.environment = Environment.STAGE;
         break;
         case "production":
            this.environment = Environment.PRODUCTION;
         break;
         default:
            this.environment = Environment.DEVELOPMENT;
      }
   }
   
   public DataSourceConfig environment(String environment) {
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
   
   public void setEngine(String engine) {
      engine = engine == null ? "mysql" : engine;
      switch (engine) {
         case "mysql":
            this.engine = Engine.MYSQL;
            url = Engine.MYSQL.getUrl();
         break;
         case "postgresql":
            this.engine = Engine.POSTGRESQL;
            url = Engine.POSTGRESQL.getUrl();
         break;
         case "oracle":
            this.engine = Engine.ORACLE;
            url = Engine.ORACLE.getUrl();
         break;
      }
   }
   
   public DataSourceConfig engine(String engine) {
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
   
   public void setPool(String pool) {
      pool = pool == null ? "" : pool;
      switch (pool) {
         case "c3p0":
            this.pool = Pool.C3P0;
         break;
         case "hikaricp":
            this.pool = Pool.HIKARICP;
         break;
         default:
            this.pool = Pool.NONE;
      }
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
   
   public void setPort(String port) {
      port = port == null ? "3306" : port;
      this.port = Integer.parseInt(port);
   }
   
   public DataSourceConfig port(String port) {
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
   
   public void setAutoReconnect(String autoReconnect) {
      autoReconnect = autoReconnect == null ? "false" : autoReconnect;
      this.autoReconnect = Boolean.parseBoolean(autoReconnect);
   }
   
   public DataSourceConfig autoReconnect(String autoReconnect) {
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
   
   public void setAutoReconnectForPools(String autoReconnectForPools) {
      autoReconnectForPools = autoReconnectForPools == null ? "false" : autoReconnectForPools;
      this.autoReconnectForPools = Boolean.parseBoolean(autoReconnectForPools);
   }
   
   public DataSourceConfig autoReconnectForPools(String autoReconnectForPools) {
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
   
   public DataSourceConfig autoCommit(Boolean autoCommit) {
      setAutoCommit(autoCommit);
      return this;
   }
   
   public void setAutoCommit(String autoCommit) {
      autoCommit = autoCommit == null ? "false" : autoCommit;
      this.autoCommit = Boolean.parseBoolean(autoCommit);
   }
   
   public DataSourceConfig autoCommit(String autoCommit) {
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
   
   public void setAutoClose(String autoClose) {
      autoClose = autoClose == null ? "false" : autoClose;
      this.autoClose = Boolean.parseBoolean(autoClose);
   }
   
   public DataSourceConfig autoClose(String autoClose) {
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
   
   public void setUseSSL(String useSSL) {
      useSSL = useSSL == null ? "false" : useSSL;
      this.useSSL = Boolean.parseBoolean(useSSL);
   }
   
   public DataSourceConfig useSSL(String useSSL) {
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
   
   public void setVerifyServerCertificate(String verifyServerCertificate) {
      verifyServerCertificate = verifyServerCertificate == null ? "false" : verifyServerCertificate;
      this.verifyServerCertificate = Boolean.parseBoolean(verifyServerCertificate);
   }
   
   public DataSourceConfig verifyServerCertificate(String verifyServerCertificate) {
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
   
   public void setLogging(String logging) {
      logging = logging == null ? "false" : logging;
      this.logging = Boolean.parseBoolean(logging);
   }
   
   public DataSourceConfig logging(String logging) {
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
