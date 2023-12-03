package jdbc.dao;

/**
 * @author thiago-amm
 * @version v1.0.0 05/11/2017
 * @since v1.0.0
 */
public class PessoaDAO extends DAO<Pessoa> {

	public PessoaDAO() {
		super();
	}

	public PessoaDAO(DataSource dataSource) {
		super(dataSource);
	}

}
