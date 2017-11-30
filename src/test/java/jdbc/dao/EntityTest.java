package jdbc.dao;

import org.junit.Test;

import jdbc.dao.DataSource;

/**
 * @author thiago-amm
 * @version v1.0.0 04/11/2017
 * @since v1.0.0
 */
public class EntityTest {
   
   @Test
   public void testDataSource() {
      DataSource testDS = DataSource.get("main:datasource.properties", "test");
      System.out.println(testDS);
   }
   
   @Test
   public void testDAO() {
      DataSource ds = DataSource.get("main:datasource.properties");
      PessoaDAO pessoaDAO = new PessoaDAO(ds);
      pessoaDAO.insert(Pessoa.create().nome("Thiago Alexandre").sobrenome("Martins Monteiro"));
   }
   
}
