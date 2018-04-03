package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;


/**
 * 
 * @author pg__s
 *	Paulo Guilherme da Silva 816113977
 */
@Repository
public class ChamadoDAO {
	@PersistenceContext
	EntityManager manager;
	
	
	public int inserirChamado(Chamado chamado) throws IOException {
		manager.persist(chamado);
		return chamado.getNumero();
	}
	
	public List<Chamado> listarChamadosAbertos(Fila fila) throws IOException{
		//conectei minha fila com a persistencia
		fila = manager.find(Fila.class, fila.getId());
		
		String jpql = "select c from Chamado c where c.fila = :fila and c.status = :status";
		
		Query query = manager.createQuery(jpql);
		query.setParameter("fila", fila);
		query.setParameter("status", Chamado.ABERTO);

		List<Chamado> result = query.getResultList();
		return result;
	}
	
	public List<Chamado> listarChamados(Fila fila) throws IOException{
		//conectei minha fila com a persistencia
				fila = manager.find(Fila.class, fila.getId());
				
				String jpql = "select c from Chamado c where c.fila = :fila";
				
				Query query = manager.createQuery(jpql);
				query.setParameter("fila", fila);

				@SuppressWarnings("unchecked")
				List<Chamado> result = query.getResultList();
				return result;
	}
	
	public void fecharChamado(Chamado chamado) throws IOException {
		manager.remove(chamado);
		manager.merge(chamado);

	}
	
	public Chamado carregar(int idChamado) throws IOException {
		return manager.find(Chamado.class, idChamado);
	}
	
	public void fecharChamados(ArrayList<Integer> lista) throws IOException{
		
			for(int id:lista){
				Chamado chamado = manager.find(Chamado.class, id);
				chamado.setDataFechamento(new java.util.Date());
				chamado.setStatus(Chamado.FECHADO);
				manager.merge(chamado);
			}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Chamado> listarChamados(){
		return manager.createQuery("select c from Chamado c").getResultList();
	}


}
