package br.usjt.arqsw.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;

/**
 * 
 * @author 81612389 - William Morone Varga
 *
 */
@Repository
public class ChamadoDAO {
	@PersistenceContext
	EntityManager manager;
	
	// Não tem mais construtor, nem conn
	//private Connection conn;
	/*@Autowired
	public ChamadoDAO(DataSource dataSource) throws IOException{
		try{
			this.conn = dataSource.getConnection();
		} catch (SQLException e){
			throw new IOException(e);
		}
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Chamado> listarChamados(Fila fila) throws IOException {		
		String jpql = "select c from Chamado c where c.fila = :fila";
		
		Query query = manager.createQuery(jpql);
		query.setParameter("fila", fila);

		List<Chamado> result = query.getResultList();
		return result;
	}
	
	public int criarChamado(Chamado chamado) throws IOException {
		manager.persist(chamado);
		return chamado.getId();
	}
}
