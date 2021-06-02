package br.com.senac.jdbc.dao;

import br.com.senac.jdbc.conexao.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.senac.jdbc.conexao.ConnectionFactory;
import br.com.senac.jdbc.modelo.Filme;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nathalia
 */
public class FilmeDAO {

    private Connection conexao;

    public FilmeDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public void adiciona(Filme filme) {
        String sql = "insert into filmes "
                + "(nome, categoria, sinopse, duracao) "
                + "values (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, filme.getNome());
            stmt.setString(2, filme.getCategoria());
            stmt.setString(3, filme.getSinopse());
            stmt.setString(4, filme.getDuracao());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Filme> getLista() {
        try {
            List<Filme> filmes = new ArrayList<Filme>();

            PreparedStatement stmt = conexao.prepareStatement(
                    "select * from filmes"
            );

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Filme filme = new Filme();

                filme.setId(rs.getLong("id"));
                filme.setNome(rs.getString("nome"));
                filme.setCategoria(rs.getString("categoria"));
                filme.setSinopse(rs.getString("sinopse"));
                filme.setDuracao(rs.getString("duracao"));

                filmes.add(filme);
            }

            rs.close();
            stmt.close();

            return filmes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(Filme filme) {
        String sql = "update filmes set nome=?, categoria=?, "
                + "sinopse=?, duracao=? where id=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, filme.getNome());
            stmt.setString(2, filme.getCategoria());
            stmt.setString(3, filme.getSinopse());
            stmt.setString(4, filme.getDuracao());
            stmt.setLong(5, filme.getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Filme filme) {
        try {
            PreparedStatement stmt = conexao.prepareStatement(
                    "delete from filmes where id=?"
            );

            stmt.setLong(1, filme.getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Filme busca(Long id) {
        String sql = "SELECT * FROM filmes where id=?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            rs.next();

            Filme filme = new Filme();

            filme.setId(rs.getLong("id"));
            filme.setNome(rs.getString("nome"));
            filme.setCategoria(rs.getString("categoria"));
            filme.setSinopse(rs.getString("sinopse"));
            filme.setDuracao(rs.getString("duracao"));

            rs.close();
            stmt.close();

            return filme;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
