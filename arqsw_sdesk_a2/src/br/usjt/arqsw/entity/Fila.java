package br.usjt.arqsw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * 
 * @author pgsilva
 * Paulo Guilherme da Silva 816113977
 */
@Entity
@Table(name="fila")
public class Fila implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	@Column(name="id_fila")
	@NotNull(message="A fila não pode ser vazia")
	@Min(value=1, message="A fila não pode ser vazia")
	private int id;
	
	@Column(name="nm_fila")
	@NotNull
	@Size(min=5, max=45, message="O nome da fila deve estar entre 5 e 45 caracteres.")
	private String nome;
	
	
	@Size(max = 70)
	@Column(name="nm_figura")
	private String figura;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "Fila [id=" + id + ", figura=" + figura + ", nome=" + nome + "]";
	}
	public String getFigura() {
		return figura;
	}
	public void setFigura(String figura) {
		this.figura = figura;
	}
	
	
}
