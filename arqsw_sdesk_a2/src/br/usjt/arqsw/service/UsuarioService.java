package br.usjt.arqsw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.usjt.arqsw.dao.UsuarioDAO;

@Service
public class UsuarioService {
	
	@Autowired 
	private UsuarioDAO dao;

}
