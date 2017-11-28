package jdbc.dao.entity;

import org.junit.Test;

import br.org.configparser.ConfigParser;

public class ConfigParserTest {
   
   @Test
   public void test() {
      ConfigParser configParser = new ConfigParser();
      configParser.read("main:datasource.properties");
      configParser.sections().forEach(section -> configParser.items(section).forEach(System.out::println));
   }
   
}
