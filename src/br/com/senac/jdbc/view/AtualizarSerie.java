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
public class AtualizarSerie extends JFrame {

    private DefaultTableModel modelo
            = new DefaultTableModel();
    private JPanel painelFundo;
    private JButton btSalvar;
    private JButton btLimpar;
    private JLabel lbNome;
    private JLabel lbCategoria;
    private JLabel lbSinopse;
    private JLabel lbQuantidade_temporadas;
    private JLabel lbId;
    private JTextField txNome;
    private JTextField txId;
    private JTextField txCategoria;
    private JTextField txSinopse;
    private JTextField txQuantidade_temporadas;
    Serie serie;
    private int linhaSelecionada;

    public AtualizarSerie(DefaultTableModel md,
            Long id, int linha) {
        super("Series");
        criaJanela();
        modelo = md;
        SerieDAO dao = new SerieDAO();
        serie = dao.busca(id);
        txId.setText(Long.toString(serie.getId()));
        txNome.setText(serie.getNome());
        txCategoria.setText(serie.getCategoria());
        txSinopse.setText(serie.getSinopse());
        txQuantidade_temporadas.setText(
                Integer.toString(serie.getQuantidade_temporadas())
        );
        linhaSelecionada = linha;
    }

    public void criaJanela() {
        btSalvar = new JButton("Salvar");
        btLimpar = new JButton("Limpar");
        lbNome = new JLabel("         Nome.:   ");
        lbCategoria = new JLabel("         Categoria.:   ");
        lbSinopse = new JLabel("         Sinopse.:   ");
        lbQuantidade_temporadas = new JLabel("         Quantidade de Temporadas.:   ");
        lbId = new JLabel("         Id.:   ");
        txNome = new JTextField();
        txCategoria = new JTextField();
        txSinopse = new JTextField();
        txQuantidade_temporadas = new JTextField();
        txId = new JTextField();
        txId.setEditable(false);

        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(6, 2, 2, 4));
        painelFundo.add(lbId);
        painelFundo.add(txId);
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

        btSalvar.addActionListener(new AtualizarSerie.BtSalvarListener());
        btLimpar.addActionListener(new AtualizarSerie.BtLimparListener());
    }

    private class BtSalvarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Serie serie = new Serie();
            serie.setId(Long.parseLong(txId.getText()));
            serie.setNome(txNome.getText());
            serie.setCategoria(txCategoria.getText());
            serie.setSinopse(txSinopse.getText());
            serie.setQuantidade_temporadas(Integer.parseInt(
                    txQuantidade_temporadas.getText())
            );

            SerieDAO dao = new SerieDAO();
            dao.altera(serie);
            modelo.removeRow(linhaSelecionada);
            modelo.addRow(new Object[]{serie.getId(),
                serie.getNome(), serie.getCategoria(), serie.getSinopse(),
                serie.getQuantidade_temporadas()});
            setVisible(false);
        }
    }

    private class BtLimparListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            txNome.setText("");
            txCategoria.setText("");
            txSinopse.setText("");
            txQuantidade_temporadas.setText("");
        }
    }
}
