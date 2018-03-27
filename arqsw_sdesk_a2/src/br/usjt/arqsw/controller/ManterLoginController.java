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
 * @author pgsilva
 * Paulo Guilherme da Silva 816113977
 *
 */
@Controller
public class ManterLoginController {
	
	@Autowired
	private UsuarioService us;

	@RequestMapping("/fazer_login")
	public String login(@Valid Usuario login, BindingResult result, HttpSession session) {
		try {
			if (result.hasFieldErrors()) {
				System.out.println("Deu erro " + result.toString());
				return "Login";
			}
			boolean isValidLogin = us.buscarLogin(login);
			session.setAttribute("usuarioLogado", isValidLogin);
			if (!isValidLogin) {
				return "Login";
			}
			return "index";

		} catch (Exception e) {
			e.printStackTrace();
			return "Erro";
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
