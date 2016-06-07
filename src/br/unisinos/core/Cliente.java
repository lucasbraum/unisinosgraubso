package br.unisinos.core;

public abstract class Cliente {

	protected long senha;
	protected char codigoPrioritario;
	protected String nome;
	protected boolean atendido;
	
	public Cliente(){
		
	}
	
	public Cliente(long senha, char codigoPrioritario, String nome){
		this.senha = senha;
		this.codigoPrioritario = codigoPrioritario;
		this.nome = nome;
		this.atendido = false;
	}
	
	public String toString(){
		return this.nome + " - " + this.codigoPrioritario + this.senha + " - " + this.atendidoToString();
	}
	
	public String atendidoToString(){
		if(this.atendido)
			return "ATENDIMENTO CONCLUIDO";
		else
			return "ATENDIMENTO PENDENTE";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public long getSenha() {
		return senha;
	}

	public void setSenha(long senha) {
		this.senha = senha;
	}

	public char getCodigoPrioritario() {
		return codigoPrioritario;
	}

	public void setCodigoPrioritario(char codigoPrioritario) {
		this.codigoPrioritario = codigoPrioritario;
	}

	public boolean isAtendido() {
		return atendido;
	}

	public void setAtendido() {
		this.atendido = true;
	}
}
