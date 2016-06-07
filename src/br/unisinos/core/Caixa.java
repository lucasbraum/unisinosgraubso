package br.unisinos.core;

import java.util.concurrent.Semaphore;

import br.unisinos.controllers.Banco;

public abstract class Caixa extends Thread {

	protected String nome;
	protected Semaphore semaforo;
	protected Banco banco;
	
	public Caixa(String nome, Banco banco, Semaphore semaforo) {
		this.nome = nome;
		this.banco = banco;
		this.semaforo = semaforo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Semaphore getSemaforo() {
		return semaforo;
	}

	public void setSemaforo(Semaphore semaforo) {
		this.semaforo = semaforo;
	}

	public Banco getBanco() {
		return banco;
	}

	public void setBanco(Banco banco) {
		this.banco = banco;
	}

	public void atender(Cliente cliente) throws InterruptedException{
		cliente.setAtendido();
		System.out.println(cliente.toString());
	}
}
