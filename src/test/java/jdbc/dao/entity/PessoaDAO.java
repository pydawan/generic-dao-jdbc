package jdbc.dao.entity;

import java.sql.SQLException;

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
      values = getValues(object);
      try {
         connect();
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
