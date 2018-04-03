package br.usjt.arqsw.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.usjt.arqsw.entity.Usuario;
import br.usjt.arqsw.service.UsuarioService;

/**
 * 
 * @author pgsilva Paulo Guilherme da Silva 816113977
 *
 */
@Controller
public class ManterLoginController {

	@Autowired
	private UsuarioService loginService;
	private String isValidLogin;

	@RequestMapping("/fazer_login")
	public String login(@Valid Usuario login, BindingResult result, HttpSession session) {
		try {
			isValidLogin = loginService.buscarLogin(login);
			session.setAttribute("usuarioLogado", isValidLogin);
			return "index";

		} catch (Exception e) {
			isValidLogin = "Invalido";
			session.setAttribute("usuarioLogado", isValidLogin);
			return "Login";
		}
	}

	@RequestMapping("/login")
	public String login() {
		return "Login";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "Login";
	}

}
