package jdbc.dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.org.jext.Strings;

/**
 * Abstrai uma Entidade ou classe de objetos a ser persistida no banco de dados.
 * 
 * @author thiago-amm
 * @version v1.0.0 04/11/2017
 * @since v1.0.0
 * @param <T>
 */
public abstract class DAO<T extends Entity> {

	private static final String MESSAGE_DATASOURCE_NOT_FOUND = "datasource not found!";
	protected DataSource dataSource;
	protected Class<?> entityClass;
	protected boolean connected;
	protected Connection connection;
	protected List<Field> persistentFields;
	protected List<String> columns;
	protected List<Object> values;
	protected String tableName;
	protected static final List<? extends Entity> EMPTY_LIST = new ArrayList<>(0);
	protected static final String FORMAT_FIND_ALL = "SELECT * FROM %s";
	protected static final String FORMAT_FIND_BY_ID = "SELECT * FROM %s WHERE id = ?";
	protected static final String FORMAT_FIND_BY = "SELECT * FROM %s WHERE %s = ?";
	protected static final String FORMAT_FIND_LAST_ID = "SELECT id FROM %s ORDER BY id DESC LIMIT 1";
	protected static final String FORMAT_INSERT = "INSERT INTO %s (%s) VALUES (%s)";
	protected static final String FORMAT_UPDATE = "UPDATE %s SET %s WHERE %s";
	protected static final String FORMAT_DELETE = "DELETE FROM %s WHERE %s";

	public DAO(DataSource dataSource) {
		this.dataSource = dataSource;
		Type genericSuperClass = getClass().getGenericSuperclass();
		ParameterizedType parameterizedType = (ParameterizedType) genericSuperClass;
		Type typeArgument = parameterizedType.getActualTypeArguments()[0];
		entityClass = (Class<?>) typeArgument;
		Table table = entityClass.getAnnotation(Table.class);
		if (table != null) {
			tableName = table.name();
		} else {
			tableName = SqlHelper.getTableName(entityClass);
		}
		loadColumns();
	}

	public DAO() {
		this(null);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public DataSource dataSource() {
		return getDataSource();
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DAO<T> dataSource(DataSource dataSource) {
		setDataSource(dataSource);
		return this;
	}

	protected void connect() throws IllegalStateException {
		if (dataSource != null) {
			connection = dataSource.getConnection();
			connected = true;
		} else {
			throw new IllegalStateException(MESSAGE_DATASOURCE_NOT_FOUND);
		}
	}

	protected void disconnect() throws IllegalStateException {
		DataSource.disconnect(connection);
		connected = false;
	}

	protected boolean connected() {
		return connected;
	}

	protected boolean disconnected() {
		return !connected();
	}

	protected void close(Object o) {
		if (o != null && o instanceof AutoCloseable) {
			try {
				((AutoCloseable) o).close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	protected void close(Object... objects) {
		if (objects != null) {
			for (Object o : objects) {
				close(o);
			}
		}
	}

	protected static String sql(List<?> list) {
		String sqlList = "";
		if (list != null && list.isEmpty() == false) {
			sqlList = list.toString();
			sqlList = sqlList.replace("[", "");
			sqlList = sqlList.replace("]", "");
		}
		return sqlList;
	}

	protected void loadColumns() {
		columns = new ArrayList<>();
		persistentFields = new ArrayList<>();
		Field[] fields = entityClass.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			if (field.getAnnotation(Transient.class) == null && field.getName().equals("serialVersionUID") == false) {
				columns.add(SqlHelper.getColumnName(field));
				persistentFields.add(field);

			}
		}
	}

	protected List<Object> getValues(T object) {
		List<Object> values = new ArrayList<>();
		for (Field field : persistentFields) {
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
		return values;
	}

	protected void loadValues(T object) {
		values = getValues(object);
	}

	public Long findLastId() {
		Long id = 0L;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = String.format(FORMAT_FIND_LAST_ID, tableName);
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				id = resultSet.getLong("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(resultSet, preparedStatement, connection);
		}
		return id;
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		List<T> list = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String sql = String.format(FORMAT_FIND_ALL, tableName);
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			Object object = null;
			String columnLabel = "";
			// percorre os registros retornados do banco de dados.
			while (resultSet.next()) {
				object = entityClass.getDeclaredConstructor().newInstance();
				Field idField = entityClass.getSuperclass().getDeclaredField("id");
				idField.setAccessible(true);
				idField.set(object, resultSet.getObject("id"));
				Field persistedField = entityClass.getSuperclass().getDeclaredField("persisted");
				persistedField.setAccessible(true);
				persistedField.set(object, true);
				// percorre cada atributo do objeto.
				for (Field persistentField : persistentFields) {
					columnLabel = SqlHelper.getColumnName(persistentField);
					persistentField.set(object, resultSet.getObject(columnLabel));
				}
				list.add((T) object);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public T findById(Long id) {
		Object object = null;
		if (id != null) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = dataSource.getConnection();
				String sql = String.format(FORMAT_FIND_BY_ID, tableName, id);
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setLong(1, id);
				resultSet = preparedStatement.executeQuery();
				String columnLabel = "";
				while (resultSet.next()) {
					object = entityClass.getDeclaredConstructor().newInstance();
					Field idField = entityClass.getSuperclass().getDeclaredField("id");
					idField.setAccessible(true);
					idField.set(object, resultSet.getObject("id"));
					Field persistedField = entityClass.getSuperclass().getDeclaredField("persisted");
					persistedField.setAccessible(true);
					persistedField.set(object, true);
					for (Field persistentField : persistentFields) {
						columnLabel = SqlHelper.getColumnName(persistentField);
						persistentField.set(object, resultSet.getObject(columnLabel));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} finally {
				close(resultSet, preparedStatement, connection);
			}
		}
		return (T) object;
	}

	@SuppressWarnings("unchecked")
	public List<T> findBy(String field, Object value) {
		List<T> list = (List<T>) EMPTY_LIST;
		field = field == null ? "" : field;
		if (field.isEmpty() == false) {
			list = new ArrayList<>();
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				connection = dataSource.getConnection();
				String sql = String.format(FORMAT_FIND_BY, tableName, field);
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setObject(1, value);
				resultSet = preparedStatement.executeQuery();
				String columnLabel = "";
				while (resultSet.next()) {
					Object object = entityClass.getDeclaredConstructor().newInstance();
					Field idField = entityClass.getSuperclass().getDeclaredField("id");
					idField.setAccessible(true);
					idField.set(object, resultSet.getObject("id"));
					Field persistedField = entityClass.getSuperclass().getDeclaredField("persisted");
					persistedField.setAccessible(true);
					persistedField.set(object, true);
					for (Field persistentField : persistentFields) {
						columnLabel = SqlHelper.getColumnName(persistentField);
						persistentField.set(object, resultSet.getObject(columnLabel));
					}
					list.add((T) object);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} finally {
				close(resultSet, preparedStatement, connection);
			}
		}
		return list;
	}

	public void insert(T entity) throws IllegalArgumentException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String[] valuesArray = Strings.array("?", columns.size());
		String values = String.join(", ", valuesArray);
		String columns = sql(this.columns);
		String sql = String.format(FORMAT_INSERT, tableName, columns, values);
		int position = 0;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (Field persistentField : persistentFields) {
				preparedStatement.setObject(++position, persistentField.get(entity));
			}

			preparedStatement.executeUpdate();
			entity.setId(findLastId());
			entity.setPersisted(true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement, connection);
		}
	}

	public void update(T entity) throws IllegalArgumentException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String[] columnsAndValues = new String[columns.size()];
		for (int i = 0; i < columns.size(); i++) {
			columnsAndValues[i] = String.format("%s = ?", columns.get(i));
		}
		String sql = String.format(FORMAT_UPDATE, tableName, String.join(", ", columnsAndValues), "id = ?");
		int position = 0;
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for (Field persistentField : persistentFields) {
				preparedStatement.setObject(++position, persistentField.get(entity));
			}
			preparedStatement.setObject(++position, entity.getId());
			preparedStatement.executeUpdate();
			entity.setId(findLastId());
			entity.setPersisted(true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement, connection);
		}
	}

	public void save(T entity) throws IllegalArgumentException {
		if (entity != null) {
			if (entity.getPersisted() == false) {
				this.insert(entity);
			} else {
				this.update(entity);
			}
		} else {
			throw new IllegalArgumentException(
					"ATENÇÃO: A entidade a ser persistida no banco de dados não pode ser nula!");
		}
	}

	public void delete(T entity) throws IllegalArgumentException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = String.format(FORMAT_DELETE, tableName, "id = ?");
		try {
			connection = dataSource.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setObject(1, entity.getId());
			preparedStatement.executeUpdate();
			entity.setPersisted(false);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(preparedStatement, connection);
		}
	}

}
