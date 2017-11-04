package jdbc.dao.entity;

import org.junit.Test;

import jdbc.dao.DAO;

/**
 * @author thiago-amm
 * @version v1.0.0 04/11/2017
 * @since v1.0.0
 */
public class EntityTest {
   
   @Test
   public void test() {
      System.out.println(new Pessoa());
      System.out.println(Pessoa.of());
      DAO pessoaDAO = DAO.of(Pessoa.class);
      Pessoa pessoa = Pessoa.of();
      pessoaDAO.insert(pessoa);
   }
   
}
