package jdbc.dao.entity;

import org.junit.Test;

import jdbc.dao.DataSource;
import jdbc.dao.DataSourceConfig;
import jdbc.dao.Engine;
import jdbc.dao.Enviroment;

/**
 * @author thiago-amm
 * @version v1.0.0 04/11/2017
 * @since v1.0.0
 */
public class EntityTest {
   
   @Test
   public void test() {
      DataSourceConfig config = new DataSourceConfig();
      config.setEnviroment(Enviroment.DEVELOPMENT);
      config.setEngine(Engine.MYSQL);
      config.setUser("siav");
      DataSource ds = new DataSource(config);
      ds.getConnection();
      PessoaDAO dao1 = new PessoaDAO();
      Pessoa pessoa = new Pessoa();
      dao1.insert(pessoa);
      PessoaDAO dao2 = new PessoaDAO();
      System.out.println(dao1.equals(dao2));
   }
   
}
