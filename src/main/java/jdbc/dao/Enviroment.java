package jdbc.dao;

/**
 * @author thiago-amm
 * @version v1.0.0 04/11/2017
 * @since v1.0.0
 */
public enum Enviroment {
   
   DEVELOPMENT("Development"),
   TEST("Test"),
   STAGE("Stage"),
   PRODUCTION("Production");
   
   private final String value;
   
   private Enviroment(String value) {
      this.value = value;
   }
   
   public String getValue() {
      return value;
   }
   
   public String value() {
      return getValue();
   }
   
}
