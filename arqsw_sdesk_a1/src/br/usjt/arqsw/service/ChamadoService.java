package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.usjt.arqsw.dao.ChamadoDAO;
import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;

/**
 * 
 * @author 81612389 - William Morone Varga
 *
 */
@Service
public class ChamadoService {

	private ChamadoDAO dao;

	@Autowired
	public ChamadoService(ChamadoDAO dao) {
		this.dao = dao;
	}

	// listar de uma fila
	public List<Chamado> listarChamados(Fila fila) throws IOException {
		return dao.listarChamados(fila);
	}

	// listar todos
	public List<Chamado> listarChamados() throws IOException {
		return dao.listarChamados();
	}

	public List<Chamado> listarChamadosAbertos(Fila fila) throws IOException {
		return dao.listaChamadosAbertos(fila);
	}

	public int criarChamado(Chamado chamado) throws IOException {
		chamado.setStatus(Chamado.ABERTO);
		chamado.setDt_abertura(new Date());
		return dao.criarChamado(chamado);
	}

	public void fecharChamados(ArrayList<Integer> listaIds) throws IOException {
		dao.fecharChamados(listaIds);
	}

	//api
	/*private ResultadoReqres buscaReqres(Chamado chamado) {
		RestTemplate restTemplate = new RestTemplate();
		ResultadoReqres resultado = restTemplate.getForObject("https://reqres.in/api/users?per_page=15&page=1", ResultadoReqres.class);
		return resultado;
	}*/
	
	

}
