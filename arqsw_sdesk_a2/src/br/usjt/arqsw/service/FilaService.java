package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqsw.dao.FilaDAO;
import br.usjt.arqsw.entity.Fila;

/**
 * 
 * @author pg__s
 *Paulo Guilherme da Silva 816113977
 */

@Service
public class FilaService {

	@Autowired
	private FilaDAO dao;

	public List<Fila> listarFilas() throws IOException {
		return dao.listarFilas();
	}

	public Fila carregar(int id) throws IOException {
		return dao.carregar(id);
	}
	
	public int criar(Fila fila) throws IOException {
		return dao.criar(fila);
	}
	
	public void deletar(Fila fila) throws IOException {
		dao.deletar(fila);
	}
	
	public void alterar(Fila fila) throws IOException {
		dao.alterar(fila);
}
}
