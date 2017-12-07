package jdbc.dao;

import java.util.Date;
import java.util.List;

import org.junit.Test;

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
      
      Pessoa pessoa = Pessoa.create().nome("Thiago Alexandre").sobrenome("Martins Monteiro").dataNascimento(new Date());
      System.out.println(pessoa);
      
      pessoaDAO.insert(pessoa);
      System.out.println(pessoa);
      
      pessoaDAO.update(pessoa.nome("Lorenzo").sobrenome("Roncolato Louly Monteiro"));
      pessoaDAO.delete(pessoa);
      
      pessoaDAO.findAll().forEach(System.out::println);
      pessoaDAO.findById(pessoaDAO.findLastId());
      pessoaDAO.findBy("nome", "Thiago Alexandre Martins Monteiro");
      
      System.out.println("-----------------------------------------");
      List<Pessoa> pessoas = pessoaDAO.findBy("nome", "Thiago Alexandre");
      System.out.println(pessoas.size());
   }
   
}
