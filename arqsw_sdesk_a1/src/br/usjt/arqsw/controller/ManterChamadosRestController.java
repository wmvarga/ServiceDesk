package br.usjt.arqsw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.service.ChamadoService;
import br.usjt.arqsw.service.FilaService;

/**
 * 
 * @author 81612389 - William Morone Varga
 *
 */
@RestController
public class ManterChamadosRestController {
	private ChamadoService chamadoService;
	private FilaService filaService;

	@Autowired
	public ManterChamadosRestController(ChamadoService chamadoService, FilaService filaService) {
		this.chamadoService = chamadoService;
		this.filaService = filaService;
	}

	@RequestMapping(method = RequestMethod.GET, value = "rest/chamados")
	public @ResponseBody List<Chamado> listarTodosChamados() {
		// trycatch
		try {
			return chamadoService.listarChamados();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "rest/chamados/{id}")
	public @ResponseBody List<Chamado> listarChamados(@PathVariable("id") Long id) {
		// trycatch
		try {
			Fila fila = filaService.carregar(id.intValue());
			return chamadoService.listarChamados(fila);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "rest/chamados/abertos/{id}")
	public @ResponseBody List<Chamado> listarChamadosAbertos(@PathVariable("id") Long id) {
		// trycatch
		try {
			Fila fila = filaService.carregar(id.intValue());
			return chamadoService.listarChamadosAbertos(fila);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "rest/chamado")
	public ResponseEntity<Chamado> criarChamado(@RequestBody Chamado chamado) {
		// ResponseEntity porque eu vou devolver um chamado.
		// RequestBody porque eu estou recebendo um chamado.
		
		// try
		try {
			int id = chamadoService.criarChamado(chamado);
			chamado.setId(id);
			return new ResponseEntity<Chamado>(chamado, HttpStatus.OK);
		} catch (IOException e) {
			return new ResponseEntity<Chamado>(chamado, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@Transactional
	@RequestMapping(method = RequestMethod.PUT, value = "rest/chamado")
	public void fecharChamados(@RequestBody List<Chamado> chamados) {
		
		try {
			ArrayList<Integer> listaIds = new ArrayList<>();
			for (Chamado chamado : chamados) {
				listaIds.add(chamado.getId());
			}
			chamadoService.fecharChamados(listaIds);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
