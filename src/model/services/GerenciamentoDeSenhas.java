package model.services;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import model.entities.Senhas;
import model.entities.SenhasException;

public class GerenciamentoDeSenhas {

	private Senhas preferenciaisEntidades;
	private Senhas comunsEntidades;
	private Stack<Senhas> senhasChamadas;
	private Queue<Senhas> preferenciais;
	private Queue<Senhas> comuns;
	private boolean ultimaPreferencial = false;

	public GerenciamentoDeSenhas() {
		this.senhasChamadas = new Stack<Senhas>();
		this.preferenciais = new LinkedList<Senhas>();
		this.comuns = new LinkedList<Senhas>();
		this.preferenciaisEntidades = new Senhas();
		this.comunsEntidades = new Senhas();
	}

	public GerenciamentoDeSenhas(Senhas preferenciais, Senhas comuns, boolean ultimaPreferencial) {
		super();
		this.preferenciaisEntidades = preferenciais;
		this.comunsEntidades = comuns;
		this.ultimaPreferencial = ultimaPreferencial;
	}

	public Stack<Senhas> getSenhasChamadas() {
		return senhasChamadas;
	}

	public void setSenhasChamadas(Stack<Senhas> senhasChamadas) {
		this.senhasChamadas = senhasChamadas;
	}

	public Queue<Senhas> getPreferenciais() {
		return preferenciais;
	}

	public Queue<Senhas> getComuns() {
		return comuns;
	}

	public void setPreferenciais(Senhas preferenciais) {
		this.preferenciaisEntidades = preferenciais;
	}

	public void setComuns(Senhas comuns) {
		this.comunsEntidades = comuns;
	}

	public boolean isUltimaPreferencial() {
		return ultimaPreferencial;
	}

	public void setUltimaPreferencial(boolean ultimaPreferencial) {
		this.ultimaPreferencial = ultimaPreferencial;
	}

	public void gerarSenhas() {
		try {
			if (preferenciais.isEmpty() && comuns.isEmpty()) {
				preferenciais.addAll(preferenciaisEntidades.gerarPreferenciais());
				comuns.addAll(comunsEntidades.gerarComuns());
			} else {
				System.out.println("Senhas cheias");
			}
		} catch (RuntimeException e) {
			throw new SenhasException(e.getMessage());
		}

	}

	public void chamarProximo() {
		try {
			if (!preferenciais.isEmpty() && !comuns.isEmpty()) {
				if (!ultimaPreferencial) {
					senhasChamadas.push(preferenciais.poll());
					ultimaPreferencial = true;
				} else {
					senhasChamadas.push(comuns.poll());
					ultimaPreferencial = false;
				}
			} else {
				gerarSenhas();
			}

		} catch (RuntimeException e) {
			throw new SenhasException(e.getMessage());
		}
	}

	public void chamarApenasComum() {
		try {
			if (!comuns.isEmpty()) {
				senhasChamadas.push(comuns.poll());
			} else {
				System.out.println("Não existem senhas geradas");
			}
		} catch (RuntimeException e) {
			throw new SenhasException(e.getMessage());
		}
	}

	public void chamarApenasPreferencial() {
		try {
			if (!preferenciais.isEmpty()) {
				senhasChamadas.push(preferenciais.poll());
			} else {
				System.out.println("Não existem senhas geradas");
			}
		} catch (RuntimeException e) {
			throw new SenhasException(e.getMessage());
		}
	}

	public void voltarSenhasChamadas() {
		try {
			if (!senhasChamadas.isEmpty()) {
				senhasChamadas.pop();
			} else {
				System.out.println("Não existem senhas chamadas.");
			}
		} catch (RuntimeException e) {
			throw new SenhasException(e.getMessage());
		}
	}

	public void repetirUltimaSenha() {
		try {
			if (!senhasChamadas.isEmpty()) {
				senhasChamadas.peek();
			} else {
				System.out.println("Não existem senhas chamadas.");
			}
		} catch (RuntimeException e) {
			throw new SenhasException(e.getMessage());
		}
	}
}
