package br.unisinos.run;

import java.util.concurrent.Semaphore;

import br.unisinos.controllers.Banco;
import br.unisinos.core.Caixa;
import br.unisinos.models.CaixaNormal;
import br.unisinos.models.CaixaPrioritario;
import br.unisinos.models.ClienteNormal;
import br.unisinos.models.ClientePrioritario;

public class Run {

	public static void main(String[] args) {

		Banco banco = new Banco();
		// semaforo para controlar o acesso ao computador
		// é possível que dois caixas utilizem o computador ao mesmo tempo
		Semaphore semaforo = new Semaphore(2);
		Caixa cxPrioritarioThread = new CaixaPrioritario("Thread 1", banco, semaforo);
		Caixa cxNormalThread	  = new CaixaNormal("Thread 2", banco, semaforo);
		Caixa cxNormalThread2	  = new CaixaNormal("Thread 3", banco, semaforo);
		
		for(int i=1; i<=80; i++){
			ClienteNormal c = new ClienteNormal(i, "Cliente Normal");
			banco.addClienteComum(c);
		}
		
		for(int i=1; i<=20; i++){
			ClientePrioritario c = new ClientePrioritario(i, "Cliente Prioritario");
			banco.addClientePrioritario(c);
		}
		
		cxPrioritarioThread.start();
		cxNormalThread.start();
		cxNormalThread2.start();
	}
}
