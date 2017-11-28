package jdbc.dao.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import jdbc.dao.DAO;

/**
 * @author thiago-amm
 * @version v1.0.0 05/11/2017
 * @since v1.0.0
 */
public class PessoaDAO extends DAO<Pessoa> {
   
   private static final long serialVersionUID = 1L;

   @Override
   public void insert(Pessoa object) {
      if (dataSource != null) {
         Connection connection = null;
         PreparedStatement statement = null;
         try {
            connection = connect();
            statement = connection.prepareStatement("INSERT INTO (%s) VALUES (%s)");
            statement.executeUpdate();
         } catch (SQLException e) {
            e.printStackTrace();
         } finally {
            close(statement);
            close(connection);
         }
      }
   }

   @Override
   public void update(Pessoa object) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void save(Pessoa object) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void delete(Pessoa object) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public List<Pessoa> findAll() {
      // TODO Auto-generated method stub
      return null;
   }
   
}
