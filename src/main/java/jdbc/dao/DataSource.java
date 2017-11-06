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
   
}
