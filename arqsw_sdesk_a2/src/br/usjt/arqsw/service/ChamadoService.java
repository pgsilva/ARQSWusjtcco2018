package br.usjt.arqsw.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.usjt.arqsw.dao.ChamadoDAO;
import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;
import br.usjt.arqsw.mapeamento.ResultadoJSON;
/**
 * 
 * @author pg__s
 *Paulo Guilherme da Silva 816113977
 */
@Service
public class ChamadoService {
	ChamadoDAO dao;
	
	@Autowired
	public ChamadoService(ChamadoDAO dao){
		this.dao = dao;
	}
	
	public int novoChamado(Chamado chamado) throws IOException{
		chamado.setDataAbertura(new Date());
		chamado.setDataFechamento(null);
		chamado.setStatus(Chamado.ABERTO);
		return dao.inserirChamado(chamado);
	}
	
	public List<Chamado> listarChamados(Fila fila) throws IOException{
		return dao.listarChamados(fila);
	}
	
	public List<Chamado> listarChamados() throws IOException{
		
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
	
	public ResultadoJSON carregaClinte(String url) {
		RestTemplate restTemplate = new RestTemplate();
		ResultadoJSON result = new ResultadoJSON();
		result = restTemplate.getForObject(url, ResultadoJSON.class);
		return result;
	}
	





}
