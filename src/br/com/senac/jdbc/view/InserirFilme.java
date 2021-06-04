/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.jdbc.view;

import br.com.senac.jdbc.dao.FilmeDAO;
import br.com.senac.jdbc.modelo.Filme;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nathalia
 */
public class InserirFilme extends JFrame {

    private DefaultTableModel modelo = new DefaultTableModel();
    private JPanel painelFundo;
    private JButton btSalvar;
    private JButton btLimpar;
    private JLabel lbNome;
    private JLabel lbCategoria;
    private JLabel lbSinopse;
    private JTextField txNome;
    private JTextField txCategoria;
    private JTextField txSinopse;

    public InserirFilme(DefaultTableModel md) {
        super("Filmes");
        criaJanela();
        modelo = md;
    }

    public void criaJanela() {
        btSalvar = new JButton("Salvar");
        btLimpar = new JButton("Limpar");
        lbNome = new JLabel("         Nome.:   ");
        lbCategoria = new JLabel("         Categoria.:   ");
        lbSinopse = new JLabel("         Sinopse.:   ");
        txNome = new JTextField(10);
        txCategoria = new JTextField();
        txSinopse = new JTextField();

        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(4, 2, 2, 4));
        painelFundo.add(lbNome);
        painelFundo.add(txNome);
        painelFundo.add(lbCategoria);
        painelFundo.add(txCategoria);
        painelFundo.add(lbSinopse);
        painelFundo.add(txSinopse);
        painelFundo.add(btLimpar);
        painelFundo.add(btSalvar);

        getContentPane().add(painelFundo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setVisible(true);
        btSalvar.addActionListener(new BtSalvarListener());
        btLimpar.addActionListener(new BtLimparListener());
    }

    private class BtSalvarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Filme filme = new Filme();
            filme.setNome(txNome.getText());
            filme.setCategoria(txCategoria.getText());
            filme.setSinopse(txSinopse.getText());

            FilmeDAO dao = new FilmeDAO();
            dao.adiciona(filme);
            ListarFilmes.pesquisar(modelo);

            setVisible(false);
        }
    }

    private class BtLimparListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            txNome.setText("");
            txCategoria.setText("");
            txSinopse.setText("");
        }
    }
}
