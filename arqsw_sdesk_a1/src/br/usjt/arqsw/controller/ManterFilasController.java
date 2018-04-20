package br.usjt.arqsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.service.ChamadoService;
import br.usjt.arqsw.service.FilaService;

/**
 * 
 * @author 81612389 - William Morone Varga
 *
 */
@Transactional
@Controller
public class ManterFilasController {
	private FilaService filaService;
	private ChamadoService chamadoService;

	@Autowired
	private ServletContext servletContext;

	@Autowired
	public ManterFilasController(FilaService filaService, ChamadoService chamadoService) {
		this.filaService = filaService;
		this.chamadoService = chamadoService;
	}

	@RequestMapping("/fila_criar")
	public String filaCriar() {
		return "FilaCriar";
	}

	@RequestMapping(value = "/inserir_fila", headers = "content-type=multipart/form-data", method = RequestMethod.POST)
	public String inserirFila(@Valid Fila fila, BindingResult result, Model model,
			@RequestParam("file") MultipartFile file) {
		try {
			if (result.hasErrors()) {
				return "FilaCriar";
			}

			filaService.criar(fila);
			filaService.gravarImagem(servletContext, fila, file);

			return "redirect:listar_filas";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping(value = "/atualizar_fila", headers = "content-type=multipart/form-data", method = RequestMethod.POST)
	public String atualizarFila(Fila fila, BindingResult result, Model model,
			@RequestParam("file") MultipartFile file) {
		try {
			if (result.hasErrors()) {
				return "FilaAlterar";
			}

			filaService.alterar(fila);
			filaService.gravarImagem(servletContext, fila, file);

			return "redirect:listar_filas";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/alterar_fila{id}")
	public String alterarFila(@RequestParam("id") int idFila, Model model) {
		try {
			Fila fila = filaService.carregar(idFila);
			model.addAttribute("fila", fila);
			return "FilaAlterar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/listar_filas")
	public String listarFilas(Model model) {
		try {
			model.addAttribute("filas", filaService.listarFilas());
			return "FilaListar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@RequestMapping("/mostrar_fila{id}")
	public String mostrarFila(@RequestParam("id") int idFila, Model model) {
		try {
			Fila fila = filaService.carregar(idFila);
			model.addAttribute("fila", fila);
			return "FilaMostrar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@RequestMapping("/excluir_fila{id}")
	public String excluirFila(@RequestParam("id") int idFila, Model model) {
		try {
			Fila fila = filaService.carregar(idFila);
			List<Chamado> chamados = chamadoService.listarChamados(fila);
			
			if (chamados.isEmpty()) {
				filaService.deletarImagem(servletContext, fila);
				filaService.excluir(fila);
			}
			
			return "redirect:listar_filas";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
}
