package jdbc.dao.entity;

import jdbc.dao.Entity;

/**
 * @author thiago-amm
 * @version v1.0.0 04/11/2017
 * @since v1.0.0
 */
public class Pessoa extends Entity<Pessoa> {
   
   private static final long serialVersionUID = 1L;
   
   public static Pessoa of() {
      return new Pessoa();
   }
   
}
