package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.usjt.arqsw.dao.ChamadoDAO;
import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.mapeamento.Resultado;
import br.usjt.arqsw.mapeamento.ResultadoJSON;

/**
 * 
 * @author pg__s Paulo Guilherme da Silva 816113977
 */
@Service
public class ChamadoService {
	ChamadoDAO dao;

	@Autowired
	public ChamadoService(ChamadoDAO dao) {
		this.dao = dao;
	}

	public int novoChamado(Chamado chamado) throws IOException {
		chamado.setDataAbertura(new Date());
		chamado.setDataFechamento(null);
		chamado.setStatus(Chamado.ABERTO);
		return dao.inserirChamado(chamado);
	}

	public List<Chamado> listarChamados(Fila fila) throws IOException {
		return dao.listarChamados(fila);
	}

	public List<Chamado> listarChamados() throws IOException {

		return dao.listarChamados();
	}

	public List<Chamado> listarChamadosAbertos(Fila fila) throws IOException {
		return dao.listarChamadosAbertos(fila);
	}

	public void fecharChamados(String[] chamados) throws IOException {
		for (String idChamado : chamados) {
			Chamado chamado = dao.carregar(Integer.parseInt(idChamado));
			dao.fecharChamado(chamado);
		}
	}

	public Resultado carregaClinte(String url) {
		// RestTemplate restTemplate = new RestTemplate();
		// ResultadoJSON result = new ResultadoJSON();
		// result = restTemplate.getForObject(url, ResultadoJSON.class);
		// return result;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.add("user-agent",
					"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<Resultado> response = restTemplate.exchange(
					url, HttpMethod.GET, entity, Resultado.class);
			Resultado reqres = response.getBody();
			return reqres;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
