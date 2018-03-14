package br.usjt.arqsw.entity;

import javax.validation.constraints.NotNull;
/**
 * 
 * @author 81612389 - William Morone Varga
 *
 */
public class Usuario {
	
	private String username;
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Usuario [username=" + username + ", password=" + password + "]";
	}

}
