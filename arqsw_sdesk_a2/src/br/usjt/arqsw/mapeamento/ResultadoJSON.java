package br.usjt.arqsw.mapeamento;

import java.util.Arrays;

public class ResultadoJSON {
	
	private Resultado[] results;
	private String status;
	
	
	public Resultado[] getResults() {
		return results;
	}
	public void setResults(Resultado[] results) {
		this.results = results;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ResultadoJSON [results=" + Arrays.toString(results) + ", status=" + status + "]";
	}
	
	
	
	

}
