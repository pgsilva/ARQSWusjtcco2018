package br.usjt.arqsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw.service.ChamadoService;
import br.usjt.arqsw.service.FilaService;

/**
 * 
 * @author pgsilva Paulo Guilherme da Silva 816113977
 *
 */
@Controller
public class ManterFilaController {


	

	@RequestMapping("/nova_fila")
	public String novoChamado(Model model) {
	
			return "NovaFila";

	}

	
}
