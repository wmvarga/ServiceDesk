package br.usjt.arqsw.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Fila;
/**
 * 
 * @author 81612389 - William Morone Varga
 *
 */
@Repository
public class FilaDAO {
	@PersistenceContext
	EntityManager manager;
	
	// Não tem mais construtor, nem conn
	//private Connection conn;
	/*@Autowired
	public FilaDAO(DataSource dataSource) throws IOException{
		try{
			this.conn = dataSource.getConnection();
		} catch (SQLException e){
			throw new IOException(e);
		}
	}*/
	
	public Fila carregar(int id) throws IOException {
		return manager.find(Fila.class, id);	
	}

	@SuppressWarnings("unchecked")
	public List<Fila> listarFilas() throws IOException {		
		String jpql = "select f from Fila f";
		Query query = manager.createQuery(jpql);

		List<Fila> result = query.getResultList();
		return result;
	}

}
