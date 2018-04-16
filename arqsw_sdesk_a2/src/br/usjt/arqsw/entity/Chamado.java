package br.usjt.arqsw.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 
 * @author pgsilva
 * Paulo Guilherme da Silva 816113977
 *
 */
@Entity
@Table(name="chamado")
public class Chamado implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7398606136502291339L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_chamado")
	private int numero;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name="dt_abertura")
	private Date dataAbertura;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name="dt_fechamento")
	private Date dataFechamento;
	
	@Column(name="status")
	private String status;
	
	@NotNull 
	@Size(max=100,min=10, message="O tamanho da descrição deve estar entre 10 e 100 caracteres")
	@Column(name="descricao")
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "id_fila")
	@NotNull
	private Fila fila;
	@JoinColumn(name = "id_rh")
	@NotNull
	private int rh;
	
	public static final String ABERTO = "aberto";
	public static final String FECHADO = "fechado";
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Date getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(Date dataAbertura) {
		this.dataAbertura = dataAbertura;
	}
	public Date getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(Date dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Fila getFila() {
		return fila;
	}
	public void setFila(Fila fila) {
		this.fila = fila;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public int getTempoDias(){
		//getTime e currentTimeMillis retornam o tempo em milisegundos
		//dividir por 1000 * 60 * 60 * 24 converte para dias
		int dias;
		if(dataFechamento == null){
			//considera o momento atual para calcular o tempo aberto
			dias =  (int)(System.currentTimeMillis() - dataAbertura.getTime())/(1000 * 60 * 60 * 24);
		} else {
			//considera a data de fechamento para calcular o tempo aberto
			dias = (int)(dataFechamento.getTime() - dataAbertura.getTime())/(1000 * 60 * 60 * 24);
		}
		return dias;
	}
	
	@Override
	public String toString() {
		return "Chamado [numero=" + numero + ", dataAbertura=" + dataAbertura
				+ ", dataFechamento=" + dataFechamento + ", status=" + status
				+ ", descricao=" + descricao + ", fila=" + fila + "]";
	}


}
