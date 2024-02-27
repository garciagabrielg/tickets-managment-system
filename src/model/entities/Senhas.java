package model.entities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class Senhas {
	
	private char letra;
	private Integer num;
	
	public Senhas() {}
	
	public Senhas(char letra, Integer num) {
		this.letra = letra;
		this.num = num;
	}

	public char getLetra() {
		return letra;
	}

	public void setLetra(char letra) {
		this.letra = letra;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
	public Queue<Senhas> gerarPreferenciais() {
		Queue<Senhas> senhasPreferenciais = new LinkedList<Senhas>();
		
		for (int i = 1; i <= 600; i++) {
			senhasPreferenciais.add(new Senhas('P', i));
		}
		return senhasPreferenciais;
	}
	
	public Queue<Senhas> gerarComuns(){
		Queue<Senhas> senhasComuns = new LinkedList<Senhas>();
		for (int i = 1; i<= 600; i++) {
			senhasComuns.add(new Senhas('C', i));
		}
		return senhasComuns;
	}

	@Override
	public int hashCode() {
		return Objects.hash(num);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Senhas other = (Senhas) obj;
		return Objects.equals(num, other.num);
	}

	@Override
	public String toString() {
		return String.format("%s%d", letra, num);
	}


}
