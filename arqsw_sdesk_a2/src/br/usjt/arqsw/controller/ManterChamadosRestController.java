package br.usjt.arqsw.controller;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
 * @author pg__s
 *Paulo Guilherme da Silva 816113977
 */

@RestController
public class ManterChamadosRestController {

	private ChamadoService cs;
	private FilaService fs;

	@Autowired
	public ManterChamadosRestController(ChamadoService cs, FilaService fs) {
		this.cs = cs;
		this.fs = fs;
	}

	@RequestMapping(method = RequestMethod.GET, value = "rest/chamados")
	public @ResponseBody List<Chamado> listarChamados() {
		try {
			return cs.listarChamados();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "rest/chamados/{id}")
	public @ResponseBody List<Chamado> listarChamados(@PathVariable("id") Long id){
		
		List<Chamado> chamados = null;
		try {
			
			Fila fila = fs.carregar(id.intValue());
			chamados = cs.listarChamados(fila);
			
		}catch(IOException e ) {
			e.printStackTrace();
		}
		return chamados;
		
	}

	@Transactional
	@RequestMapping(method = RequestMethod.POST, value = "rest/chamado")
	public ResponseEntity<Chamado> inserirChamado(@RequestBody Chamado chamado) throws IOException {
		try {
			int id = cs.novoChamado(chamado);
			chamado.setNumero(id);
			return new ResponseEntity<>(chamado, HttpStatus.OK);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(chamado, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
}
