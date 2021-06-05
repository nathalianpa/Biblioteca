/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.jdbc.view;

import br.com.senac.jdbc.dao.SerieDAO;
import br.com.senac.jdbc.modelo.Serie;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nathalia
 */
public class InserirSerie extends JFrame {
    private DefaultTableModel modelo = new DefaultTableModel();
    private JPanel painelFundo;
    private JButton btSalvar;
    private JButton btLimpar;
    private JLabel lbNome;
    private JLabel lbCategoria;
    private JLabel lbSinopse;
    private JLabel lbQuantidade_temporadas;
    private JTextField txNome;
    private JTextField txCategoria;
    private JTextField txSinopse;
    private JTextField txQuantidade_temporadas;

    public InserirSerie(DefaultTableModel md) {
        super("Series");
        criaJanela();
        modelo = md;
    }
    
    public void criaJanela() {
        btSalvar = new JButton("Salvar");
        btLimpar = new JButton("Limpar");
        lbNome = new JLabel("         Nome.:   ");
        lbCategoria = new JLabel("         Categoria.:   ");
        lbSinopse = new JLabel("         Sinopse.:   ");
        lbQuantidade_temporadas = new JLabel("         Quantidade de Temporadas.:   ");
        txNome = new JTextField(10);
        txCategoria = new JTextField();
        txSinopse = new JTextField();
        txQuantidade_temporadas = new JTextField();
        
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(5, 2, 2, 4));
        painelFundo.add(lbNome);
        painelFundo.add(txNome);
        painelFundo.add(lbCategoria);
        painelFundo.add(txCategoria);
        painelFundo.add(lbSinopse);
        painelFundo.add(txSinopse);
        painelFundo.add(lbQuantidade_temporadas);
        painelFundo.add(txQuantidade_temporadas);
        painelFundo.add(btLimpar);
        painelFundo.add(btSalvar);
        
        getContentPane().add(painelFundo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setVisible(true);
        btSalvar.addActionListener(new InserirSerie.BtSalvarListener());
        btLimpar.addActionListener(new InserirSerie.BtLimparListener());
    }
    
    private class BtSalvarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Serie serie = new Serie();
            serie.setNome(txNome.getText());
            serie.setCategoria(txCategoria.getText());
            serie.setSinopse(txSinopse.getText());
            serie.setQuantidade_temporadas(Integer.parseInt(
                    txQuantidade_temporadas.getText())
            );

            SerieDAO dao = new SerieDAO();
            dao.adiciona(serie);
            ListarSeries.pesquisar(modelo);

            setVisible(false);
        }
    }
    
    private class BtLimparListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            txNome.setText("");
            txCategoria.setText("");
            txSinopse.setText("");
            txQuantidade_temporadas.setText("");
        }
    }
}
