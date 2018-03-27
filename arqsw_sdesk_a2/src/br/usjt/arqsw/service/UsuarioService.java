package br.usjt.arqsw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqsw.dao.UsuarioDAO;
import br.usjt.arqsw.entity.Usuario;

/**
 * 
 * @author pg__s
 *Paulo Guilherme da Silva 816113977
 */
@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioDAO loginRepository;

	public boolean buscarLogin(Usuario usuario) throws Exception {
		return loginRepository.buscarLogin(usuario);
	}
}
