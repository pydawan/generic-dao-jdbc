package jdbc.dao.entity;

import jdbc.dao.DAO;
import jdbc.dao.DataSource;

/**
 * @author thiago-amm
 * @version v1.0.0 05/11/2017
 * @since v1.0.0
 */
public class PessoaDAO extends DAO<Pessoa> {
   
   private static final long serialVersionUID = 1L;
   
   public PessoaDAO() {
      super();
   }
   
   public PessoaDAO(DataSource ds) {
      super(ds);
   }
   
}
