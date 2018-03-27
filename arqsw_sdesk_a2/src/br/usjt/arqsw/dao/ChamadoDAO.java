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

	@SuppressWarnings("unchecked")
	public List<Chamado> listarChamados(Fila fila) throws IOException {
		fila = manager.find(Fila.class, fila.getId());
		Query query = manager.createQuery("select c from Chamado c where c.idFila = :fila");
		query.setParameter("fila", fila);
		return (List<Chamado>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public int novoChamado(Chamado chamado) throws IOException {
		manager.persist(chamado);
		Query query = manager.createQuery("select c from Chamado c");
		List<Chamado> chamados = (List<Chamado>) query.getResultList();
		return chamados.get(chamados.size() - 1).getNumero();
	}
	/*private Connection conn;

	
	@Autowired
	public ChamadoDAO(DataSource dataSource ) throws IOException{
		try {
			this.conn = dataSource.getConnection();
		}catch(SQLException e) {
			throw new IOException(e);
		}
	}
	
	public ArrayList<Chamado> listarChamados(Fila fila) throws IOException{
		ArrayList<Chamado> lista = new ArrayList<>();
		String query = "select c.id_chamado, c.descricao, c.dt_abertura, f.nm_fila "+
				"from chamado c, fila f where c.id_fila = f.id_fila and c.id_fila=?";
		
		try(PreparedStatement pst = conn.prepareStatement(query);){
			pst.setInt(1, fila.getId());
			
			try(ResultSet rs = pst.executeQuery();){
				while(rs.next()){
					Chamado chamado = new Chamado();
					chamado.setNumero(rs.getInt("id_chamado"));
					chamado.setDescricao(rs.getString("descricao"));
					chamado.setDataAbertura(rs.getDate("dt_abertura"));
					fila.setNome(rs.getString("nm_fila"));
					chamado.setFila(fila);
					lista.add(chamado);
				}
			} catch(SQLException e){
				e.printStackTrace();
				throw new IOException(e);
			}
		} catch(SQLException e){
			e.printStackTrace();
			throw new IOException(e);
		}
		return lista;
	}   ANTES DO REFATORAMENTO PRO JPA*/
	


	
}
