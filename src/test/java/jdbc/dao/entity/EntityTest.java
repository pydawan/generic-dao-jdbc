package jdbc.dao.entity;

import org.junit.Test;

import jdbc.dao.DataSource;

/**
 * @author thiago-amm
 * @version v1.0.0 04/11/2017
 * @since v1.0.0
 */
public class EntityTest {
   
   @Test
   public void test() {
      DataSource dataSource = new DataSource();
      dataSource.load("main:datasource.properties");
      PessoaDAO pessoaDAO = new PessoaDAO();
      pessoaDAO.setDataSource(dataSource);
      pessoaDAO.insert(new Pessoa());
      DataSource testDS = DataSource.get("main:datasource.properties", "test");
      System.out.println(dataSource);
      System.out.println(testDS);
   }
   
}
