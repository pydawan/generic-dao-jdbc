package jdbc.dao;

/**
 * @author thiago-amm
 * @version v1.0.0
 * @since v1.0.0
 */
public class DataSourceException extends RuntimeException {
   
   private static final long serialVersionUID = 1L;
   
   public DataSourceException() {
   }
   
   public DataSourceException(String message) {
      super(message);
   }
   
   public DataSourceException(Throwable cause) {
      super(cause);
   }
   
   public DataSourceException(String message, Throwable cause) {
      super(message, cause);
   }
   
}
