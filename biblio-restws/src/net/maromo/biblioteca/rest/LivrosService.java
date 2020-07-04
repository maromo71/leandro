package net.maromo.biblioteca.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.maromo.biblioteca.dao.LivroDAO;
import net.maromo.biblioteca.model.Livro;


@Path("/livros")
public class LivrosService {
	
	private static final String CHARSET_UTF8 = ";charset=utf-8";

	private LivroDAO livroDAO;
	
	@PostConstruct
	private void init() {
		livroDAO = new LivroDAO();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Livro> listarLivros() {
		List<Livro> lista = null;
		try {
			lista = livroDAO.listLivros();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Livro buscarPorId(@PathParam("id") int idLivro) {
		Livro livro = null;
		try {
			livro = livroDAO.searchLivroById(idLivro);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return livro;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String adicionarLivro(Livro livro) {
		String msg = "";
		System.out.println(livro.getTitulo());
		try {
			int idGerado = livroDAO.addLivro(livro);
			msg = "Livro adicionado com sucesso, id gerado: " + idGerado;
		} catch (Exception e) {
			msg = "Erro ao adicionar o livro!";
			e.printStackTrace();
		}
		return msg;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarLivro(Livro livro, @PathParam("id") int idLivro) {
		String msg = "";
		System.out.println(livro.getTitulo());
		try {
			livroDAO.editLivro(livro, idLivro);
			msg = "Livro editado com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao editar o livro!";
			e.printStackTrace();
		}
		return msg;
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String removerLivro(@PathParam("id") int idLivro) {
		String msg = "";
		try {
			livroDAO.delLivro(idLivro);
			msg = "Livro removido com sucesso!";
		} catch (Exception e) {
			msg = "Erro ao remover o livro!";
			e.printStackTrace();
		}
		return msg;
	}
}
