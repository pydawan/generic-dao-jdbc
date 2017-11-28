package jdbc.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;

/**
 * Abstrai uma Entidade ou classe de objetos a ser persistida no banco de dados.
 * 
 * @author thiago-amm
 * @version v1.0.0 04/11/2017
 * @since v1.0.0
 * @param <T>
 */
public abstract class DAO<T> implements Comparable<DAO<T>>, Serializable {
   
   private static final long serialVersionUID = 1L;
   protected Connection connection;
   protected Class<?> entityClass;
   protected Long id;
   protected Boolean persisted;
   
   public DAO() {
      Type genericSuperClass = getClass().getGenericSuperclass();
      ParameterizedType parameterizedType = (ParameterizedType) genericSuperClass;
      Type typeArgument = parameterizedType.getActualTypeArguments()[0];
      entityClass = (Class<?>) typeArgument;
      id = 0l;
      persisted = false; 
   }
   
   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }
   
   @SuppressWarnings("unchecked")
   @Override
   public boolean equals(Object obj) {
      if (obj == null) return false;
      if (this == obj) return true;
      if (this.getClass() != obj.getClass()) return false;
      DAO<T> other = (DAO<T>) obj;
      if (this.persisted && !other.persisted()) return false;
      if (!this.persisted() && other.persisted) return false;
      if (this.id != other.id) return false;
      return true;
   }
   
   @Override
   public int compareTo(DAO<T> other) {
      if (this.id < other.id) {
         return -1;
      }
      if (this.id > other.id) {
         return 1;
      }
      return 0;
   }
   
   public Connection getConnection() {
      return connection;
   }
   
   public void setConnection(Connection connection) {
      this.connection = connection;
   }
   
   public Long getId() {
      return id;
   }
   
   public Long id() {
      return getId();
   }
   
   public void setId(Long id) {
      this.id = id == null ? 0l : id;
   }
   
   public DAO<T> id(Long id) {
      setId(id);
      return this;
   }
   
   public void setId(Integer id) {
      this.id = id == null ? 0l : (long) id;
   }
   
   public DAO<T> id(Integer id) {
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
      persisted = persisted == null ? false : true;
      this.persisted = persisted;
   }
   
   public DAO<T> persisted(Boolean persisted) {
      setPersisted(persisted);
      return this;
   }
   
   public abstract void insert(T object);
   
}
