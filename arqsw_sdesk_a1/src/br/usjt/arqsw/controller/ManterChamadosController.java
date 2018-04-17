package br.usjt.arqsw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
public class ManterChamadosController {
	private FilaService filaService;
	private ChamadoService chamadoService;

	@Autowired
	public ManterChamadosController(FilaService filaService, ChamadoService chamadoService) {
		this.filaService = filaService;
		this.chamadoService = chamadoService;
	}
	
	@RequestMapping("index")
	public String inicio() {
		return "index";
	}

	private List<Fila> listarFilas() throws IOException {
		return filaService.listarFilas();
	}
	@RequestMapping("/listar_filas_exibir")
	public String listarFilasExibir(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "ChamadoListar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/listar_chamados_exibir")
	public String listarChamadosExibir(@Valid Fila fila, BindingResult result, Model model) {
		try {
			if (result.hasFieldErrors("id")) {
				// model.addAttribute("filas", listarFilas());
				System.out.println("Deu erro " + result.toString());
				// return "ChamadoListar";
				return "redirect:listar_filas_exibir";
			}
			fila = filaService.carregar(fila.getId());
			model.addAttribute("fila", fila);
			model.addAttribute("chamados", chamadoService.listarChamados(fila));

			return "ChamadoListarExibir";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/fechar_chamados_exibir")
	public String fecharChamadosExibir(@Valid Fila fila, BindingResult result, Model model) {
		try {
			if (result.hasFieldErrors("id")) {
				// model.addAttribute("filas", listarFilas());
				System.out.println("Deu erro " + result.toString());
				// return "ChamadoListar";
				return "redirect:listar_filas_fechar_chamados";
			}
			fila = filaService.carregar(fila.getId());
			model.addAttribute("fila", fila);
			model.addAttribute("chamados", chamadoService.listarChamadosAbertos(fila));

			return "ChamadoFecharListar";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/criar_novo_chamado")
	public String criarNovoChamado(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "ChamadoCriar";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/listar_filas_fechar_chamados")
	public String fecharChamados(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "ChamadoFecharSelecionarFila";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/salvar_chamado")
	public String listarChamadosExibir(@Valid Chamado chamado, BindingResult result, Model model) {
		try {
			if (result.hasFieldErrors("descricao")) {
				System.out.println("Deu erro " + result.toString());
				return "redirect:criar_novo_chamado";
			}

			int id = chamadoService.criarChamado(chamado);
			model.addAttribute("id_chamado", id);
			return "ChamadoSalvo";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/fechar_chamados")
	public String fecharChamados(@RequestParam Map<String, String> params, Model model) {

		try {

			Set<String> chaves = params.keySet();
			Iterator<String> i = chaves.iterator();

			// Vai guardar os ids dos chamados a serem fechados (são as chaves
			// do map)
			ArrayList<Integer> listaIds = new ArrayList<>();

			while (i.hasNext()) {
				String chave = i.next();
				// Pega o valor daquela chave
				String valor = params.get(chave);
				if (valor.equals("on")) {
					listaIds.add(Integer.parseInt(chave));
				}
			}

			if (listaIds.size() > 0) {
				chamadoService.fecharChamados(listaIds);
			}
			
			model.addAttribute("ids", listaIds);

			return "ChamadoFechado";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}

	}
}
