package br.usjt.arqsw.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import br.usjt.arqsw.dao.FilaDAO;
import br.usjt.arqsw.entity.Fila;

/**
 * 
 * @author 81612389 - William Morone Varga
 *
 */
@Service
public class FilaService {
	private FilaDAO dao;
	
	@Autowired
	public FilaService(FilaDAO dao) {
		this.dao = dao;
	}
	
	public int criar(Fila fila) throws IOException {
		return dao.criar(fila);
	}
	
	public void alterar(Fila fila) throws IOException {
		dao.alterar(fila);
	}
	
	public Fila carregar(int id) throws IOException{
		// TODO Auto-generated method stub
		return dao.carregar(id);
	}
	
	public List<Fila> listarFilas() throws IOException{
		return dao.listarFilas();
	}
	
	public void excluir(Fila fila) throws IOException {
		dao.excluir(fila);
	}
	
	public void gravarImagem(ServletContext servletContext, Fila fila, MultipartFile file) throws IOException {
		if (!file.isEmpty()) {
			BufferedImage src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
			String path = servletContext.getRealPath(servletContext.getContextPath());
			path = path.substring(0, path.lastIndexOf('\\'));
			String nomeArquivo = "img_" + fila.getNome() + ".jpg";
			fila.setImagem(nomeArquivo);
			alterar(fila);
			File destination = new File(path + File.separatorChar + "img" + File.separatorChar + nomeArquivo);
			if (destination.exists()) {
				destination.delete();
			}
			ImageIO.write(src, "jpg", destination);
		}
	}
	
	public void deletarImagem(ServletContext servletContext, Fila fila) throws IOException {
		String path = servletContext.getRealPath(servletContext.getContextPath());
		path = path.substring(0, path.lastIndexOf('\\'));
		File destination = new File(path + File.separatorChar + "img" + File.separatorChar + fila.getImagem());
		if (destination.exists()) {
			destination.delete();
		}
	}
	
}
