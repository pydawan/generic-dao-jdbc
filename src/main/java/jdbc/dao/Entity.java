package jdbc.dao;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Representa uma entidade ou objeto capaz de ser mapeado em um registro de uma tabela.
 * 
 * @author thiago-amm
 * @version v1.0.0 04/12/2017
 * @since v1.0.0
 */
public abstract class Entity implements Comparable<Entity>, Serializable {
   
   private static final long serialVersionUID = 1L;
   protected Long id;
   protected Boolean persisted;
   
   public Entity() {
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
   
   @Override
   public boolean equals(Object obj) {
      if (obj == null) return false;
      if (this == obj) return true;
      if (this.getClass() != obj.getClass()) return false;
      Entity other = (Entity) obj;
      if (this.persisted && !other.persisted()) return false;
      if (!this.persisted() && other.persisted) return false;
      if (this.id != other.id) return false;
      return true;
   }
   
   @Override
   public int compareTo(Entity other) {
      if (this.id < other.id) {
         return -1;
      }
      if (this.id > other.id) {
         return 1;
      }
      return 0;
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
   
   public Entity id(Long id) {
      setId(id);
      return this;
   }
   
   public void setId(Integer id) {
      this.id = id == null ? 0l : (long) id;
   }
   
   public Entity id(Integer id) {
      setId(id);
      return this;
   }
   
   public Boolean getPersisted() {
      return persisted == null ? false : persisted;
   }
   
   public Boolean persisted() {
      return getPersisted();
   }
   
   public void setPersisted(Boolean persisted) {
      persisted = persisted == null ? false : true;
      this.persisted = persisted;
   }
   
   public Entity persisted(Boolean persisted) {
      setPersisted(persisted);
      return this;
   }
   
   @Override
   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append(String.format("%s(", this.getClass().getSimpleName()));
      for (Field field : this.getClass().getDeclaredFields()) {
         field.setAccessible(true);
         try {
            if (field.getName().equals("serialVersionUID") == false) {
               sb.append(String.format("%s=%s, ", field.getName(), field.get(this)));
            }
         } catch (IllegalArgumentException e) {
            e.printStackTrace();
         } catch (IllegalAccessException e) {
            e.printStackTrace();
         }
      }
      sb.replace(sb.lastIndexOf(", "), sb.length(), "");
      sb.append(")");
      return sb.toString();
   }
   
}
