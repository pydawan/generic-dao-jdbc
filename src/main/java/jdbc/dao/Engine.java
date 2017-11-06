package jdbc.dao;

/**
 * @author thiago-amm
 * @version v1.0.0 04/11/2017
 * @since v1.0.0
 */
public enum Engine {
   
   MYSQL(
      "MySQL", 
      "com.mysql.jdbc.Driver", 
      "localhost", 
      "root", 
      3306, 
      "mysql", 
//      "jdbc:mysql://%s:%s/%s?user=%s&password=%s&verifyServerCertificate=%s&useSSL=%s&autoReconnect=%s&autoReconnectForPools=%s"
      "jdbc:mysql://%s:%s/%s?user=%s&password=%s&verifyServerCertificate=%s&useSSL=%s"
   ),
   POSTGRESQL("PostgreSQL", "org.postgresql.Driver", "localhost", "postgres", 5432, "postgres", "jdbc:postgresql://%s:%s/%s"),
   ORACLE("Oracle", "oracle.jdbc.driver.OracleDriver", "localhost", "hr", 1521, "xe", "jdbc:oracle:thin:@%s:%s:%s"),
   SQLITE("SQLite", "org.sqlite.JDBC", "localhost", "", 0, "", ""),
   H2("H2", "org.h2.Driver", "localhost", "sa", 0, "test", "jdbc:%s:~/%s");

   private final String value;
   private final String driver;
   private final String host;
   private final String user;
   private final Integer port;
   private final String database;
   private final String url;
   
   private Engine(String value, String driver, String host, String user, Integer port, String database, String url) {
      this.value = value;
      this.driver = driver;
      this.host = host;
      this.user = user;
      this.port = port;
      this.database = database;
      this.url = url;
   }
   
   public String getValue() {
      return value;
   }
   
   public String value() {
      return value;
   }
   
   public String getDriver() {
      return driver;
   }
   
   public String driver() {
      return driver;
   }
   
   public String getHost() {
      return host;
   }
   
   public String host() {
      return host;
   }
   
   public String getUser() {
      return user;
   }
   
   public String user() {
      return user;
   }
   
   public Integer getPort() {
      return port;
   }
   
   public Integer port() {
      return port;
   }
   
   public String getDatabase() {
      return database;
   }
   
   public String database() {
      return database;
   }
   
   public String getUrl() {
      return url;
   }
   
   public String url() {
      return url;
   }
   
   @Override
   public String toString() {
      return super.toString().toLowerCase();
   }
   
}
