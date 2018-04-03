package br.usjt.arqsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.service.ChamadoService;
import br.usjt.arqsw.service.FilaService;
/**
 * 
 * @author pgsilva
 * Paulo Guilherme da Silva 816133977
 *
 */
@Controller
public class ManterChamadosController {
	@Autowired
	private FilaService filaService;

	@Autowired
	private ChamadoService chamadoService;

	/**
	 * 
	 * @return
	 */
	@RequestMapping("index")
	public String inicio() {
		return "index";
	}

	/**
	 * 
	 * @param model
	 *            Acesso à request http
	 * @return JSP de Listar Chamados
	 */
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
				model.addAttribute("filas", listarFilas());
				System.out.println("Deu erro " + result.toString());
				return "ChamadoListar";
			}
			fila = filaService.carregar(fila.getId());
			model.addAttribute("fila", fila);
			model.addAttribute("chamados", listarChamados(fila));
			return "ChamadoListarExibir";

		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/novo_chamado")
	public String novoChamado(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "NovoChamado";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@Transactional
	@RequestMapping("/criar_chamado")
	public String criarChamado(@Valid Chamado chamado, BindingResult result, Model model) {
		try {
			if (result.hasFieldErrors()) {
				model.addAttribute("filas", listarFilas());
				System.out.println("Deu erro " + result.toString());
				return "NovoChamado";
			}
			int numeroChamado = chamadoService.novoChamado(chamado);
			model.addAttribute("numeroChamado", numeroChamado);
			return "ChamadoSalvo";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/listar_filas_fechar")
	public String listarFilhasFechar(Model model) {
		try {
			model.addAttribute("filas", listarFilas());
			return "ListarFilas";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/listar_chamados_abertos")
	public String listarChamadosAbertos(@Valid Fila fila, BindingResult result, Model model) {
		try {
			fila = filaService.carregar(fila.getId());
			model.addAttribute("chamadosAbertos", listarChamadosAbertos(fila));
			model.addAttribute("fila", fila);
			return "ListarChamadosAbertos";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@Transactional
	@RequestMapping("/fechar_chamados")
	public String fecharChamado(HttpServletRequest request) {
		try {
			String[] chamados = request.getParameterValues("chamados");
			chamadoService.fecharChamados(chamados);
			return "ChamadoFechado";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	private List<Fila> listarFilas() throws IOException {
		return filaService.listarFilas();
	}

	private List<Chamado> listarChamados(Fila fila) throws IOException {
		return chamadoService.listarChamados(fila);
	}

	private List<Chamado> listarChamadosAbertos(Fila fila) throws IOException {
		return chamadoService.listarChamadosAbertos(fila);
}
}
