package br.unisinos.models;

import br.unisinos.core.Cliente;

public class ClienteNormal extends Cliente {

	public ClienteNormal(long senha, String nome){
		this.senha = senha;
		this.codigoPrioritario = 'N';
		this.nome = nome;
		this.atendido = false;
	}
}
