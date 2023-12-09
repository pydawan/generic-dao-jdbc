package jdbc.dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação que indica que um determinado atributo não será mapeado em uma
 * coluna de uma tabela no banco de dados.
 * 
 * @author thiago-amm
 * @version v1.0.0
 * @since v1.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Transient {

}
