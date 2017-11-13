package jdbc.dao.entity;

import static jdbc.dao.DataSource.autoClose;
import static jdbc.dao.DataSource.autoCommit;
import static jdbc.dao.DataSource.autoReconnect;
import static jdbc.dao.DataSource.autoReconnectForPools;
import static jdbc.dao.DataSource.charset;
import static jdbc.dao.DataSource.collate;
import static jdbc.dao.DataSource.database;
import static jdbc.dao.DataSource.engine;
import static jdbc.dao.DataSource.environment;
import static jdbc.dao.DataSource.host;
import static jdbc.dao.DataSource.logging;
import static jdbc.dao.DataSource.password;
import static jdbc.dao.DataSource.pool;
import static jdbc.dao.DataSource.port;
import static jdbc.dao.DataSource.useSSL;
import static jdbc.dao.DataSource.user;
import static jdbc.dao.DataSource.verifyServerCertificate;

import org.junit.Test;

import br.org.configparser.ConfigParser;
import jdbc.dao.DataSource;
import jdbc.dao.DataSourceConfig;
import jdbc.dao.Engine;
import jdbc.dao.Environment;

/**
 * @author thiago-amm
 * @version v1.0.0 04/11/2017
 * @since v1.0.0
 */
public class EntityTest {
   
   @Test
   public void test() {
      DataSourceConfig config = new DataSourceConfig();
      config.setEnvironment(Environment.DEVELOPMENT);
      config.setEngine(Engine.MYSQL);
      config.setUser("siav");
      DataSource ds = new DataSource(config);
      ds.getConnection();
      PessoaDAO dao1 = new PessoaDAO();
      Pessoa pessoa = new Pessoa();
      dao1.insert(pessoa);
      PessoaDAO dao2 = new PessoaDAO();
      System.out.println(dao1.equals(dao2));
      DataSource.connect(
         "environment=development", 
         "engine=mysql", 
         "pool=hikaricp", 
         "host=localhost", 
         "port=3306", 
         "database=cadservidor", 
         "user=cadservidor", 
         "password=cadservidor",
         "charset=utf8",
         "collate=utf8_general_ci",
         "autoReconnect=true",
         "autoReconnectForPools=true",
         "autoCommit=true",
         "autoClose=true",
         "useSSL=true",
         "verifyServerCertificate=true",
         "logging=true"
      );
      DataSource.connect(
         environment("production"), 
         engine("mysql"), 
         pool("hikaricp"), 
         host("localhost"), 
         port(3306), 
         database("cadservidor"), 
         user("cadservidor"), 
         password("cadservidor"),
         charset("utf8"),
         collate("utf8_general_ci"),
         autoReconnect(true),
         autoReconnectForPools(true),
         autoCommit(true),
         autoClose(true),
         useSSL(true),
         verifyServerCertificate(true),
         logging(true)
      );
      ConfigParser cfg = new ConfigParser();
      cfg.read("main:database.properties");
      cfg.sections().forEach(section -> cfg.items(section).forEach(System.out::println));
   }
   
}
