package br.unisinos.models;

import java.util.concurrent.Semaphore;

import br.unisinos.controllers.Banco;
import br.unisinos.core.Caixa;
import br.unisinos.core.Cliente;

public class CaixaNormal extends Caixa {

	int iCont = 0;
	
	public CaixaNormal(String nome, Banco banco, Semaphore semaforo) {
		super(nome, banco, semaforo);
	}

	public void run() {
		// Atende somente clientes da fila normal
		while(!banco.verificaClientesNormais())
			try { 
				atender(banco.removeClienteComum());
			} catch (Exception e) {
				iCont++;
			}
		System.out.println("Excessoes no caixa normal: " + iCont);
	}
	
	@Override
	public void atender(Cliente cliente) throws InterruptedException {
		try {
	        this.semaforo.acquire();
	        // processa o atendimento no computador
	        this.banco.usaComputador(this);
	        // Altera o status do cliente para atendido
	        cliente.setAtendido();
	        // Mostra o cliente no console
	        System.out.println(cliente.toString() + " - Caixa Normal " + this.nome + "\n");
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    } finally {
	        this.semaforo.release();
	        sleep(100);
	    }
	}

}
