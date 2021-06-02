/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.jdbc.dao;

import br.com.senac.jdbc.conexao.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.com.senac.jdbc.conexao.ConnectionFactory;
import br.com.senac.jdbc.modelo.Serie;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nathalia
 */
public class SerieDAO {

    private Connection conexao;

    public SerieDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public void adiciona(Serie serie) {
        String sql = "insert into series "
                + "(nome, categoria, sinopse, quantidade_temporadas) "
                + "values (?, ?, ?, ?)";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, serie.getNome());
            stmt.setString(2, serie.getCategoria());
            stmt.setString(3, serie.getSinopse());
            stmt.setInt(4, serie.getQuantidade_temporadas());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Serie> getLista() {
        try {
            List<Serie> series = new ArrayList<Serie>();

            PreparedStatement stmt = conexao.prepareStatement(
                    "select * from series"
            );

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Serie serie = new Serie();

                serie.setId(rs.getLong("id"));
                serie.setNome(rs.getString("nome"));
                serie.setCategoria(rs.getString("categoria"));
                serie.setSinopse(rs.getString("sinopse"));
                serie.setQuantidade_temporadas(rs.getInt("quantidade_temporadas"));

                series.add(serie);
            }

            rs.close();
            stmt.close();

            return series;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(Serie serie) {
        String sql = "update series set nome=?, categoria=?, "
                + "sinopse=?, quantidade_temporadas=? where id=?";

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setString(1, serie.getNome());
            stmt.setString(2, serie.getCategoria());
            stmt.setString(3, serie.getSinopse());
            stmt.setInt(4, serie.getQuantidade_temporadas());
            stmt.setLong(5, serie.getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void remove(Serie serie) {
        try {
            PreparedStatement stmt = conexao.prepareStatement(
                    "delete from series where id=?"
            );

            stmt.setLong(1, serie.getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Serie busca(Long id) {
        String sql = "SELECT * FROM series where id=?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            rs.next();

            Serie serie = new Serie();

            serie.setId(rs.getLong("id"));
            serie.setNome(rs.getString("nome"));
            serie.setCategoria(rs.getString("categoria"));
            serie.setSinopse(rs.getString("sinopse"));
            serie.setQuantidade_temporadas(rs.getInt("Quantidade_temporadas"));

            rs.close();
            stmt.close();

            return serie;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
