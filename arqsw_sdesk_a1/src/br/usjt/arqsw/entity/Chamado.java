package br.usjt.arqsw.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author 81612389 - William Morone Varga
 *
 */
@Entity
public class Chamado implements Serializable {

	public static final String ABERTO = "aberto";
	public static final String FECHADO = "fechado";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_chamado")
	@NotNull(message="O chamado n�o existe")
	private int id;
	
	@NotNull
	@Size(min=5, max=50, message="A descri��o do chamado deve estar entre 5 e 50 caracteres.")
	private String descricao;
	@NotNull
	private String status;
	@NotNull
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date dt_abertura;
	@JsonFormat(pattern="dd-MM-yyyy")
	private Date dt_fechamento;
	
	@NotNull(message="O chamado deve pertencer a uma fila")
	@ManyToOne
	@JoinColumn(name="id_fila")
	private Fila fila;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getDt_abertura() {
		return dt_abertura;
	}
	public void setDt_abertura(Date dt_abertura) {
		this.dt_abertura = dt_abertura;
	}
	public Date getDt_fechamento() {
		return dt_fechamento;
	}
	public void setDt_fechamento(Date dt_fechamento) {
		this.dt_fechamento = dt_fechamento;
	}
	public Fila getFila() {
		return fila;
	}
	public void setFila(Fila fila) {
		this.fila = fila;
	}
	
	public int getTempoDias(){
		//M�todo do Bonato
		//getTime e currentTimeMillis retornam o tempo em milisegundos
		//dividir por 1000 * 60 * 60 * 24 converte para dias
		int dias;
		if(dt_fechamento == null){
			//considera o momento atual para calcular o tempo aberto
			dias = (int)(System.currentTimeMillis() - dt_abertura.getTime())/(1000 * 60 * 60 * 24);
		} else {
			//considera a data de fechamento para calcular o tempo aberto
			dias = (int)(dt_fechamento.getTime() - dt_abertura.getTime())/(1000 * 60 * 60 * 24);
		}
		return dias;
	}
	
	@Override
	public String toString() {
		return "Chamado [id=" + id + ", descricao=" + descricao + ", status=" + status + ", dt_abertura=" + dt_abertura
				+ ", dt_fechamento=" + dt_fechamento + ", fila=" + fila + "]";
	}	
	
	
	
}
