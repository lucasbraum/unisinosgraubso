package br.unisinos.controllers;

import java.util.LinkedList;
import java.util.Queue;

import br.unisinos.core.Caixa;
import br.unisinos.models.ClienteNormal;
import br.unisinos.models.ClientePrioritario;
import br.unisinos.models.Computador;

public class Banco {
	
	private Queue<ClienteNormal> clientes = new LinkedList<ClienteNormal>();
	private Queue<ClientePrioritario> clientesPrioritarios = new LinkedList<ClientePrioritario>();
	private Computador computador = new Computador();
	
	/** 
	 * Adiciona um novo cliente para a fila comum
	 * @param ClienteNormal cliente
	 */
	public void addClienteComum(ClienteNormal cliente){
		try {
			clientes.add(cliente);
		} catch (IllegalStateException e) {
			System.out.println("Erro ao entrar na fila!");
		}
	}

	public synchronized ClienteNormal removeClienteComum(Queue<ClienteNormal> clientes) throws InterruptedException{
		ClienteNormal cliente = clientes.poll();
		notifyAll();
		return cliente;
	}

	/**
	 * Remove um cliente da fila comum
	 * @return ClienteNormal cliente
	 * @throws InterruptedException 
	 */
	public ClienteNormal removeClienteComum() throws InterruptedException{
		return removeClienteComum(this.clientes);
	}
	
	/**
	 * Adiciona um novo cliente para a fila prioritaria
	 * @param ClientePrioritario cliente
	 */
	public void addClientePrioritario(ClientePrioritario cliente){
		try {
			clientesPrioritarios.add(cliente);
		} catch (IllegalStateException e) {
			System.out.println("Erro ao entrar na fila!");
		}
	}
	
	public synchronized ClientePrioritario removeClientePrioritaria(Queue<ClientePrioritario> clientesPrioritarios) throws InterruptedException{
		ClientePrioritario clienteP = clientesPrioritarios.poll();
		notifyAll();
		return clienteP;
	}
	
	/**
	 * Remove um cliente da fila prioritaria
	 * @return ClientePrioritario cliente
	 * @throws InterruptedException 
	 */
	public ClientePrioritario removeClientePrioritaria() throws InterruptedException{
		return removeClientePrioritaria(this.clientesPrioritarios);
	}
	
	/**
	 * Verifica se a fila normal esta vazia
	 * @return boolean
	 */
	public boolean verificaClientesNormais(){
		return clientes.isEmpty();
	}

	/**
	 * Verifica se a fila prioritaria esta vazia
	 * @return boolean
	 */
	public boolean verificaClientesPrioritarios(){
		return clientesPrioritarios.isEmpty();
	}
	
	public void usaComputador(Caixa caixa){
		this.computador.processaAtendimento(caixa);
	}
}
