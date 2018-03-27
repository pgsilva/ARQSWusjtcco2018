package br.usjt.arqsw.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	
	private FilaService fs;
	private ChamadoService cs;

	@Autowired
	public ManterChamadosController(ChamadoService cs, FilaService fs ) {
		this.fs = fs;
		this.cs = cs;
	}

	
	@RequestMapping("index")
	public String inicio() {
		return "index";
	}

	private List<Fila> listarFilas() throws IOException{
			return fs.listarFilas();
	}
	
	/**
	 * 
	 * @param model Acesso à request http
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
				//return "redirect:listar_filas_exibir";
			}
			fila = fs.carregar(fila.getId());
			model.addAttribute("fila", fila);

			// TODO Código para carregar os chamados
			List<Chamado> chamados = cs.listarChamados(fila);
			model.addAttribute("chamados", chamados);
			
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

	@RequestMapping("/criar_chamado")
	public String criarChamado(@Valid Chamado chamado, BindingResult result, Model model) {
		try {
			if (result.hasFieldErrors()) {
				model.addAttribute("filas", listarFilas());
				System.out.println("Deu erro " + result.toString());
				return "NovoChamado";
			}
			int numeroChamado = cs.novoChamado(chamado);
			model.addAttribute("numeroChamado", numeroChamado);
			return "ChamadoSalvo";
		} catch (IOException e) {
			e.printStackTrace();
			return "Erro";
		}
	}


	private List<Chamado> listarChamados(Fila fila) throws IOException {
		return cs.listarChamados(fila);
	}

}
