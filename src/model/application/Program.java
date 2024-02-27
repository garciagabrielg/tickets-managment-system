package model.application;

import model.services.GerenciamentoDeSenhas;

public class Program {

	public static void main(String[] args) {
		GerenciamentoDeSenhas gds = new GerenciamentoDeSenhas();
		gds.gerarSenhas();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				TicketUI ui = new TicketUI();

			}
		});
	}
	

}
