package jdbc.dao;

/**
 * @author thiago-amm
 * @version v1.0.0
 * @since v1.0.0
 */
public class DAOException extends Exception {

	private static final long serialVersionUID = 1L;

	public DAOException() {
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

}
