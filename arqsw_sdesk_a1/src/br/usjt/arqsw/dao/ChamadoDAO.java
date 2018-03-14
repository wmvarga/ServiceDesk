package br.usjt.arqsw.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.usjt.arqsw.entity.Chamado;
import br.usjt.arqsw.entity.Fila;

/**
 * 
 * @author 81612389 - William Morone Varga
 *
 */
@Repository
public class ChamadoDAO {
	private Connection conn;
	
	@Autowired
	public ChamadoDAO(DataSource dataSource) throws IOException{
		try{
			this.conn = dataSource.getConnection();
		} catch (SQLException e){
			throw new IOException(e);
		}
	}
	
	public ArrayList<Chamado> listarChamados(Fila fila) throws IOException {
		String query = "select id_chamado, descricao, status, dt_abertura, dt_fechamento, id_fila from chamado where id_fila = ?";
		ArrayList<Chamado> lista = new ArrayList<>();
		try(PreparedStatement pst = conn.prepareStatement(query);){
			pst.setInt(1, fila.getId());
			try(ResultSet rs = pst.executeQuery();){
				
				while(rs.next()) {
					Chamado chamado = new Chamado();
					chamado.setId(rs.getInt("id_chamado"));
					chamado.setDescricao(rs.getString("descricao"));
					chamado.setStatus(rs.getString("status"));
					chamado.setDt_abertura(rs.getDate("dt_abertura"));
					chamado.setDt_fechamento(rs.getDate("dt_fechamento"));
					lista.add(chamado);
				}
			}
			
		} catch (SQLException e) {
			throw new IOException(e);
		}
		return lista;
	}
	
	public int criarChamado(Chamado chamado) throws IOException {
		Date dataAbertura = new Date(chamado.getDt_abertura().getTime());
		
		String sqlInsert = "INSERT INTO chamado(descricao, status, dt_abertura, id_fila) VALUES (?, ?, ?, ?)";
		try(PreparedStatement pst = conn.prepareStatement(sqlInsert);){
			pst.setString(1, chamado.getDescricao());
			pst.setString(2, chamado.getStatus());
			pst.setDate(3, dataAbertura);
			pst.setInt(4, chamado.getFila().getId());
			
			pst.execute();
			
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery); ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					chamado.setId(rs.getInt(1));
				}
			}
			
		} catch (SQLException e) {
			throw new IOException(e);
		}
		return chamado.getId();
	}
}
