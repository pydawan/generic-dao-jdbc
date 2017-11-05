package jdbc.dao;

/**
 * @author thiago-amm
 * @version v1.0.0 04/11/2017
 * @since v1.0.0
 */
public enum PoolEngine {
   
   NONE("None"),
   C3P0("C3P0"),
   HIKARI("Hikari");
   
   private final String value;
   
   private PoolEngine(String value) {
      this.value = value;
   }
   
   public String getValue() {
      return value;
   }
   
   public String value() {
      return getValue();
   }
   
}
