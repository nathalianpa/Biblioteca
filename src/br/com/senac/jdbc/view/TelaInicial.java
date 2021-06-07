/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.jdbc.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Nathalia
 */
public class TelaInicial extends JFrame {

    private JPanel painelFundo;
    private JButton btFilmes;
    private JButton btSeries;

    public TelaInicial() {
        super("Biblioteca");
        criaJanela();
    }

    public void criaJanela() {
        btFilmes = new JButton("Filmes");
        btSeries = new JButton("Series");

        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(2, 1, 2, 4));
        painelFundo.add(btFilmes);
        painelFundo.add(btSeries);

        getContentPane().add(painelFundo);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 320);
        setVisible(true);
        btFilmes.addActionListener(new BtFilmesListener());
        btSeries.addActionListener(new BtSeriesListener());

    }

    private class BtFilmesListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ListarFilmes listarFilmes = new ListarFilmes();
            listarFilmes.setVisible(true);
        }
    }

    private class BtSeriesListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            ListarSeries listarSeries = new ListarSeries();
            listarSeries.setVisible(true);
        }
    }

    public static void main(String[] args) {
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.setVisible(true);
    }
}
