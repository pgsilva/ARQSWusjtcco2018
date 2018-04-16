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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.service.ChamadoService;
import br.usjt.arqsw.service.FilaService;

/**
 * 
 * @author pgsilva Paulo Guilherme da Silva 816113977
 *
 */
@Controller
public class ManterFilaController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private FilaService filaService;

	@Autowired
	private ChamadoService chamadoService;

	@RequestMapping("/form_criar_fila")
	public String formCriarFila() {
		return "NovaFila";
	}
	
	@Transactional
	@RequestMapping("/nova_fila")
	public String criarFila(@Valid Fila fila, BindingResult result, @RequestParam("file") MultipartFile file) {
		try {
			if (result.hasFieldErrors("nome")) {
				System.out.println("Deu erro " + result.toString());
				return "NovaFila";
			}
			filaService.criar(fila);
			filaService.gravarImagem(servletContext, fila, file);
			return "FilaCriada";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/listar_filas")
	public String listarFila(Model model) {
		try {
			model.addAttribute("filas", filaService.listarFilas());
			return "ListaFilas";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@Transactional
	@RequestMapping("/excluir_fila")
	public String excluirFila(Fila fila, Model model) {
		try {
			fila = filaService.carregar(fila.getId());
			List<Chamado> chamadosAbertosFila = chamadoService.listarChamadosAbertos(fila);
			model.addAttribute("filas", filaService.listarFilas());
			if (!chamadosAbertosFila.isEmpty()) {
				model.addAttribute("chamadosAbertosFila", chamadosAbertosFila);
				return "ListarFilas";
			}
			filaService.deletar(fila);
			return "FilaExcluida";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}

	@RequestMapping("/form_edita_fila")
	public String formEditaFila(Fila fila, Model model) {
		try {
			fila = filaService.carregar(fila.getId());
			model.addAttribute("fila", fila);
			return "EditaFila";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
	
	@Transactional
	@RequestMapping("/edita_fila")
	public String editaFila(Fila fila, Model model, @RequestParam("file")MultipartFile file) {
		try {
			Fila filaEditada = filaService.carregar(fila.getId());
			filaEditada.setNome(fila.getNome());
			filaEditada.setFigura(fila.getFigura());
			filaService.alterar(filaEditada);
			filaService.gravarImagem(servletContext, filaEditada, file);
			model.addAttribute("filas", filaService.listarFilas());
			return "ListaFilas";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}
}
