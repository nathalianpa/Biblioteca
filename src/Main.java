
import br.com.senac.jdbc.conexao.ConnectionFactory;
import br.com.senac.jdbc.dao.FilmeDAO;
import br.com.senac.jdbc.dao.SerieDAO;
import br.com.senac.jdbc.modelo.Filme;
import br.com.senac.jdbc.modelo.Serie;
import java.sql.Connection;
import java.sql.SQLException;
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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
//        SerieDAO dao = new SerieDAO();
//        
//        List<Serie> series = dao.getLista();
//        
//        for(Serie serie : series) {
//            System.out.println("Nome: " + serie.getNome());
//            System.out.println("Categoria: " + serie.getCategoria());
//            System.out.println("Sinopse: " + serie.getSinopse());
//            System.out.println("Quantidade de Temporadas: " + serie.getQuantidade_temporadas());
//        }
        
        
        Serie serie = new Serie();
        
        serie.setId(2L);
//        filme.setNome("Wanda Vision");
//        filme.setCategoria("Aventura");
//        filme.setSinopse("llorem ypsilon");
//        filme.setQuantidade_temporadas(1);
        
       SerieDAO dao = new SerieDAO();
        
        dao.remove(serie);
        
        System.out.println("Removido!");
        
//        Connection conexao = new ConnectionFactory().getConnection();
//        System.out.println("Conexao aberta.");
//        conexao.close();
    }    
}
