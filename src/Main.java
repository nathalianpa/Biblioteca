
import br.com.senac.jdbc.conexao.ConnectionFactory;
import br.com.senac.jdbc.dao.FilmeDAO;
import br.com.senac.jdbc.dao.SerieDAO;
import br.com.senac.jdbc.modelo.Filme;
import br.com.senac.jdbc.modelo.Serie;
import br.com.senac.jdbc.view.Filmes;
import br.com.senac.jdbc.view.TelaInicial;
import java.awt.Container;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Nathalia
 */
public class Main extends JFrame {

    /**
     * @param args the command line arguments
     */
    public Main() {
        super("Biblioteca");
        setSize(600,400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Filmes painel = new Filmes();
         
        Container con = getContentPane();
        con.add(painel);

        setVisible(true);
    }
    public static void main(String[] args) throws SQLException {
        new Main();

//        Serie serie = new Serie();
//        
//        serie.setNome("Anna with an A");
//        serie.setCategoria("Aventura");
//        serie.setSinopse("llorem ypsilon");
//        serie.setQuantidade_temporadas(3);
//        
//        SerieDAO dao = new SerieDAO();
//        
//        dao.adiciona(serie);
//        SerieDAO dao = new SerieDAO();
//
//        Serie serie = dao.busca(4L);
//
//        System.out.println("Nome: " + serie.getNome());
//        System.out.println("Categoria: " + serie.getCategoria());
//        System.out.println("Sinopse: " + serie.getSinopse());
//        System.out.println("Quantidade_temporadas: " + serie.getQuantidade_temporadas());

//        Serie serie = new Serie();
//        
//        serie.setId(2L);
//        filme.setNome("Wanda Vision");
//        filme.setCategoria("Aventura");
//        filme.setSinopse("llorem ypsilon");
//        filme.setQuantidade_temporadas(1);
//       SerieDAO dao = new SerieDAO();
//        
//        dao.remove(serie);
//        
//        System.out.println("Removido!");
//        Connection conexao = new ConnectionFactory().getConnection();
//        System.out.println("Conexao aberta.");
//        conexao.close();
    }
}
