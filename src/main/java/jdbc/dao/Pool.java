package jdbc.dao;

/**
 * @author thiago-amm
 * @version v1.0.0 04/11/2017
 * @since v1.0.0
 */
public enum Pool {
   
   NONE(""),
   C3P0("c3p0"),
   HIKARICP("hikaricp");
   
   private final String value;
   
   private Pool(String value) {
      this.value = value;
   }
   
   public String getValue() {
      return value;
   }
   
   public String value() {
      return getValue();
   }
   
   @Override
   public String toString() {
      return super.toString().toLowerCase();
   }
   
}
