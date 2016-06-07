package br.unisinos.models;

import br.unisinos.core.Caixa;

public class Computador {
	
	public Computador(){
	}
	
	public void processaAtendimento(Caixa caixa){
		try {
			caixa.sleep((long) (Math.random() * 2000));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
