package net.maromo.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.maromo.biblioteca.config.Conexao;
import net.maromo.biblioteca.model.Livro;

public class LivroDAO {

	public List<Livro> listLivros() throws Exception {
		List<Livro> lista = new ArrayList<>();
		Connection conexao = Conexao.getConnection();
		String sql = "SELECT * FROM TB_LIVROS";

		PreparedStatement statement = conexao.prepareStatement(sql);
		ResultSet rs = statement.executeQuery();
		while (rs.next()) {
			Livro livro = new Livro();
			livro.setIdLivro(rs.getInt("IDLIVRO"));
			livro.setTitulo(rs.getString("TITULO"));
			livro.setAutor(rs.getString("AUTOR"));
			livro.setAno(rs.getInt("ANO"));
			livro.setEditora(rs.getString("EDITORA"));
			livro.setIsbn(rs.getString("ISBN"));
			lista.add(livro);
		}
		return lista;
	}
	
	public Livro searchLivroById(int idLivro) throws Exception {
		Livro livro = null;

		Connection conexao = Conexao.getConnection();

		String sql = "SELECT * FROM TB_LIVROS WHERE IDLIVRO = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, idLivro);
		ResultSet rs = statement.executeQuery();

		if (rs.next()) {
			livro = new Livro();
			livro.setIdLivro(rs.getInt("IDLIVRO"));
			livro.setTitulo(rs.getString("TITULO"));
			livro.setAutor(rs.getString("AUTOR"));
			livro.setAno(rs.getInt("ANO"));
			livro.setEditora(rs.getString("EDITORA"));
			livro.setIsbn(rs.getString("ISBN"));
		}
		return livro;
	}
	
	public int addLivro(Livro livro) throws Exception {
		int idGeradoMySQL = 0;
		Connection conexao = Conexao.getConnection();

		String sql = "INSERT INTO TB_LIVROS(TITULO, AUTOR, ANO, EDITORA, ISBN) "
				+ "VALUES(?,?,?,?,?)";

		PreparedStatement statement = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, livro.getTitulo());
		statement.setString(2, livro.getAutor());
		statement.setInt(3,  livro.getAno());
		statement.setString(4, livro.getEditora());
		statement.setString(5, livro.getIsbn());
		statement.execute();
		
		ResultSet rs = statement.getGeneratedKeys();
		if (rs.next()) {
			idGeradoMySQL = rs.getInt(1);
		}
		
		return idGeradoMySQL;
	}
	
	public void editLivro(Livro livro, int idLivro) throws Exception {
		Connection conexao = Conexao.getConnection();

		String sql = "UPDATE TB_LIVROS SET TITULO = ?, "
				+ "AUTOR = ?, "
				+ "ANO = ?, "
				+ "EDITORA = ?, "
				+ "ISBN = ? " 
				+ " WHERE IDLIVRO = ?";

		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, livro.getTitulo());
		statement.setString(2, livro.getAutor());
		statement.setInt(3, livro.getAno());
		statement.setString(4, livro.getEditora());
		statement.setString(5, livro.getIsbn());
		statement.setInt(6, idLivro);
		statement.execute();
	}
	
	public void delLivro(int idLivro) throws Exception {
		Connection conexao = Conexao.getConnection();
		String sql = "DELETE FROM TB_LIVROS WHERE IDLIVRO = ?";
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setInt(1, idLivro);
		statement.execute();
	}
}
