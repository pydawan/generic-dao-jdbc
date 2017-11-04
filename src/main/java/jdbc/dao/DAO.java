package jdbc.dao;

/**
 * Fábrica de objetos de acesso a dados (DAO Factory).
 * 
 * @author thiago-amm
 * @version v1.0.0 04/11/2017
 * @since v1.0.0
 */
public class DAO {
   
   @SuppressWarnings("unused")
   private Class<? extends Entity<?>> entity;
   
   public <T extends Entity<?>> DAO(Class<T> entity) {
      this.entity = entity;
   }
   
   public static <T extends Entity<?>> DAO of(Class<T> entity) {
      return new DAO(entity);
   }
   
   public <T extends Entity<?>> void insert(T object) {
      System.out.printf("INSERT %s", object);
   }
   
//   public static <T extends Entity<?>> void insert(T o) {
//      System.out.printf("INSERT %s\n", o);
//   }
   
}
