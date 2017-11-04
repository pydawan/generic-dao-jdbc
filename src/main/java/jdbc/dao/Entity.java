package jdbc.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Abstrai uma Entidade ou classe de objetos a ser persistida no banco de dados.
 * 
 * @author thiago-amm
 * @version v1.0.0 04/11/2017
 * @since v1.0.0
 * @param <T>
 */
public abstract class Entity<T> implements Comparable<Entity<T>>, Serializable {

   private static final long serialVersionUID = 1L;
   protected Class<?> entityClass;
   protected Long id;
   protected Boolean persisted;
   
   public Entity() {
      Type gsc = getClass().getGenericSuperclass();
      ParameterizedType param = (ParameterizedType) gsc;
      Type arg = param.getActualTypeArguments()[0];
      entityClass = (Class<?>) arg;
      System.out.println("Generic Super Class: " + gsc);
      System.out.println("Type Argument: " + arg);
      System.out.println("Entity Class: " + entityClass);
   }
   
   @Override
   public int compareTo(Entity<T> other) {
      if (this.id < other.id) {
         return -1;
      }
      if (this.id > other.id) {
         return 1;
      }
      return 0;
   }
   
   public Class<?> getEntityClass() {
      return entityClass;
   }
   
   public Class<?> entityClass() {
      return getEntityClass();
   }
   
   public Long getId() {
      return id;
   }
   
   public Long id() {
      return getId();
   }
   
   public void setId(Long id) {
      this.id = id;
   }
   
   public Entity<T> id(Long id) {
      setId(id);
      return this;
   }
   
   public Boolean getPersisted() {
      return persisted;
   }
   
   public Boolean persisted() {
      return getPersisted();
   }
   
   public void setPersisted(Boolean persisted) {
      this.persisted = persisted;
   }
   
   public Entity<T> persisted(Boolean persisted) {
      setPersisted(persisted);
      return this;
   }
   
}
