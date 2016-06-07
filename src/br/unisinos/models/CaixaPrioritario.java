package br.unisinos.models;

import java.util.concurrent.Semaphore;

import br.unisinos.controllers.Banco;
import br.unisinos.core.Caixa;
import br.unisinos.core.Cliente;

public class CaixaPrioritario extends Caixa {
	
	int iCont = 0;

	public CaixaPrioritario(String nome, Banco banco, Semaphore semaforo) {
		super(nome, banco, semaforo);
	}
	
	public void run() {
		while (!banco.verificaClientesPrioritarios() || !banco.verificaClientesNormais()) {
			// Caixa prioritario primeiro atende todos os clientes da fila prioritaria
			while(!banco.verificaClientesPrioritarios())
				try {
					atender(banco.removeClientePrioritaria());
				} catch (InterruptedException e) {
					iCont++;
				}
			
			// Apos atender todos clientes da fila prioritaria, o caixa prioritario atende os clientes da fila normal
			if(!banco.verificaClientesNormais())
				try {
					atender(banco.removeClienteComum());
				} catch (Exception e) {
					iCont++;
				}
		}
		System.out.println("Excessoes no caixa prioritario: " + iCont);
	}
	
	@Override
	public void atender(Cliente cliente) throws InterruptedException {
		try {
	        this.semaforo.acquire();
	        this.banco.usaComputador(this);
	        // Altera o status do cliente para atendido
	        cliente.setAtendido();
	        // Mostra o cliente no console
	        System.out.println(cliente.toString() + " - Caixa Prioritario " + this.nome + "\n");
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    } finally {
	        this.semaforo.release();
	        sleep(100);
	    }
	}

}
