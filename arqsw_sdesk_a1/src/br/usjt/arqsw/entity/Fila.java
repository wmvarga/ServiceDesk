package br.usjt.arqsw.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * 
 * @author 81612389 - William Morone Varga
 *
 */
@Entity
public class Fila implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id_fila")
	@NotNull(message="A fila não pode ser vazia")
	@Min(value=1, message="A fila não pode ser vazia")
	private int id;
	
	@Column(name="nm_fila")
	@NotNull
	@Size(min=5, max=45, message="O nome da fila deve estar entre 5 e 45 caracteres.")
	private String nome;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	@Override
	public String toString() {
		return "Fila [id=" + id + ", nome=" + nome + "]";
	}
	
}
