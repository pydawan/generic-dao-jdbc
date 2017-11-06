package jdbc.dao.entity;

import jdbc.dao.DAO;

/**
 * @author thiago-amm
 * @version v1.0.0 05/11/2017
 * @since v1.0.0
 */
public class PessoaDAO extends DAO<Pessoa> {
   
   private static final long serialVersionUID = 1L;

   @Override
   public void insert(Pessoa object) {
      System.out.printf("INSERT INTO %s", object);
   }
   
   
}
