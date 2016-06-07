package br.unisinos.models;

import br.unisinos.core.Cliente;

public class ClientePrioritario extends Cliente {
	
	public ClientePrioritario(long senha, String nome){
		this.senha = senha;
		this.codigoPrioritario = 'P';
		this.nome = nome;
		this.atendido = false;
	}

}
