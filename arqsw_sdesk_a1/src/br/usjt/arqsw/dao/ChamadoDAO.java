package br.usjt.arqsw.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
	
	@SuppressWarnings("unchecked")
	public List<Chamado> listarChamados() throws IOException {		
		String jpql = "select c from Chamado c";
		
		Query query = manager.createQuery(jpql);
		return query.getResultList();
		// return manager.createQuery("select c from Chamado c").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Chamado> listaChamadosAbertos(Fila fila) throws IOException {
		String jpql = "select c from Chamado c where c.fila = :fila and c.status = :status";
		Query query = manager.createQuery(jpql);
		query.setParameter("fila", fila);
		query.setParameter("status", Chamado.ABERTO);
		return query.getResultList();
	}
	
	public int criarChamado(Chamado chamado) throws IOException {
		manager.persist(chamado);
		return chamado.getId();
	}

	public void fecharChamados(ArrayList<Integer> listaIds) throws IOException {
		for (int id : listaIds) {
			Chamado chamado = manager.find(Chamado.class, id);
			chamado.setDt_fechamento(new Date());
			chamado.setStatus(Chamado.FECHADO);
			manager.merge(chamado);
		}
	}
}