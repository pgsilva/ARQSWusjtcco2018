package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAO {
	
	private Connection conn;
	
	@Autowired
	public UsuarioDAO(DataSource dataSource) throws IOException {
		try{
			this.conn = dataSource.getConnection();
		}catch(SQLException e) {
			throw new IOException(e);
		}
		
	}

}
