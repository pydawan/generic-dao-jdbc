package jdbc.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author thiago-amm
 * @version v1.0.0
 * @since v1.0.0
 */
public interface Factory<T extends Entity> {
   
   @SuppressWarnings("unchecked")
   default T newInstance() {
      Type genericSuperClass = getClass().getGenericSuperclass();
      ParameterizedType parameterizedType = (ParameterizedType) genericSuperClass;
      Type typeArgument = parameterizedType.getActualTypeArguments()[0];
      Class<?> clazz = (Class<?>) typeArgument;
      T instance = null;
      try {
         instance = (T) clazz.newInstance();
      } catch (InstantiationException e) {
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         e.printStackTrace();
      }
      return instance;
   }
   
   static <T extends Entity> T newInstance(Class<T> clazz) {
      T instance = null;
      if (clazz != null) {
         try {
            instance = clazz.newInstance();
         } catch (InstantiationException e) {
            e.printStackTrace();
         } catch (IllegalAccessException e) {
            e.printStackTrace();
         }
      }
      return instance;
   }
   
}
