package br.usjt.arqsw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author pgsilva
 * Paulo Guilherme da Silva 816113977
 */
@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2666200939622264260L;
	
	@Id
	@GeneratedValue
	@Column(name="id_usuario")
	@NotNull
	private int id;
	
	@Column(name="username")
	private String username;
	
	@Column(name="pass")
	private String password;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Usuario [username=" + username + ", password=" + password + "]";
	}
	
	
	
	
}
