package jdbc.dao.entity;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;

import jdbc.dao.DAO;
import jdbc.dao.DataSource;

/**
 * @author thiago-amm
 * @version v1.0.0 05/11/2017
 * @since v1.0.0
 */
public class PessoaDAO extends DAO<Pessoa> {
   
   private static final long serialVersionUID = 1L;
   
   public PessoaDAO() {
      super();
   }
   
   public PessoaDAO(DataSource ds) {
      super(ds);
   }
   
   @Override
   public void insert(Pessoa object) {
      if (dataSource != null) {
         columns = new ArrayList<>();
         values = new ArrayList<>();
         for (Field field : persistentFields) {
            columns.add(field.getName());
            try {
               Object value = field.get(object);
               if (value instanceof String) {
                  values.add("'" + field.get(object) + "'");
               } else {
                  values.add(field.get(object));
               }
            } catch (IllegalArgumentException e) {
               e.printStackTrace();
            } catch (IllegalAccessException e) {
               e.printStackTrace();
            }
         }
         try {
            connection = connect();
            sql = String.format(SQL_INSERT_FORMAT, table, sql(columns), sql(values));
            System.out.println(sql);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            close(preparedStatement);
            close(connection);
         }
      }
   }
   
}
