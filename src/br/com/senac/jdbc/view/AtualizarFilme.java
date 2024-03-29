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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nathalia
 */
public class AtualizarFilme extends JFrame {

    private DefaultTableModel modelo
            = new DefaultTableModel();
    private JPanel painelFundo;
    private JButton btSalvar;
    private JButton btLimpar;
    private JLabel lbNome;
    private JLabel lbCategoria;
    private JLabel lbSinopse;
    private JLabel lbDuracao;
    private JLabel lbId;
    private JTextField txNome;
    private JTextField txId;
    private JTextField txCategoria;
    private JTextField txSinopse;
    private JTextField txDuracao;
    private JCheckBox cbAssistido;
    Filme filme;
    private int linhaSelecionada;

    public AtualizarFilme(DefaultTableModel md, Long id, int linha) {
        super("Atualizar Filme");
        criaJanela();
        modelo = md;
        FilmeDAO dao = new FilmeDAO();
        filme = dao.busca(id);
        txId.setText(Long.toString(filme.getId()));
        txNome.setText(filme.getNome());
        txCategoria.setText(filme.getCategoria());
        txSinopse.setText(filme.getSinopse());
        txDuracao.setText(filme.getDuracao());
        cbAssistido.setSelected(filme.getAssistido());
        linhaSelecionada = linha;
    }

    public void criaJanela() {
        btSalvar = new JButton("Salvar");
        btLimpar = new JButton("Limpar");
        lbNome = new JLabel("         Nome.:   ");
        lbCategoria = new JLabel("         Categoria.:   ");
        lbSinopse = new JLabel("         Sinopse.:   ");
        lbDuracao = new JLabel("         Duracação.:   ");
        lbId = new JLabel("         Id.:   ");
        txNome = new JTextField();
        txCategoria = new JTextField();
        txSinopse = new JTextField();
        txDuracao = new JTextField();
        cbAssistido = new JCheckBox("Assistido");
        txId = new JTextField();
        txId.setEditable(false);

        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(7, 2, 2, 4));
        painelFundo.add(lbId);
        painelFundo.add(txId);
        painelFundo.add(lbNome);
        painelFundo.add(txNome);
        painelFundo.add(lbCategoria);
        painelFundo.add(txCategoria);
        painelFundo.add(lbSinopse);
        painelFundo.add(txSinopse);
        painelFundo.add(lbDuracao);
        painelFundo.add(txDuracao);
        painelFundo.add(new JLabel());
        painelFundo.add(cbAssistido);
        painelFundo.add(btLimpar);
        painelFundo.add(btSalvar);

        getContentPane().add(painelFundo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 250);
        setVisible(true);

        btSalvar.addActionListener(new AtualizarFilme.BtSalvarListener());
        btLimpar.addActionListener(new AtualizarFilme.BtLimparListener());
    }

    private class BtSalvarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Filme filme = new Filme();
            filme.setId(Long.parseLong(txId.getText()));
            filme.setNome(txNome.getText());
            filme.setCategoria(txCategoria.getText());
            filme.setSinopse(txSinopse.getText());
            filme.setDuracao(txDuracao.getText());
            filme.setAssistido(cbAssistido.isSelected());

            FilmeDAO dao = new FilmeDAO();
            dao.altera(filme);
            modelo.removeRow(linhaSelecionada);
            modelo.addRow(new Object[]{filme.getId(),
                filme.getNome(), filme.getCategoria(), filme.getSinopse(),
                filme.getDuracao(), filme.getAssistido()});
            setVisible(false);
        }
    }

    private class BtLimparListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            txNome.setText("");
            txCategoria.setText("");
            txSinopse.setText("");
            txDuracao.setText("");
            cbAssistido.setSelected(false);
        }
    }
}
