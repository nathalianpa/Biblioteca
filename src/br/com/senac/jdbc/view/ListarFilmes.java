/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.jdbc.view;

import br.com.senac.jdbc.dao.FilmeDAO;
import br.com.senac.jdbc.modelo.Filme;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nathalia
 */
public class ListarFilmes extends JFrame {

    private JPanel painelFundo;
    private JPanel painelBotoes;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private JButton btInserir;
    private JButton btExcluir;
    private JButton btEditar;
    private DefaultTableModel modelo = new DefaultTableModel();

    public ListarFilmes() {
        super("Listar Filmes");
        criaJTable();
        criaJanela();
    }

    public void criaJanela() {
        btInserir = new JButton("Inserir");
        btExcluir = new JButton("Excluir");
        btEditar = new JButton("Atualizar");
        painelBotoes = new JPanel();
        barraRolagem = new JScrollPane(tabela);
        painelFundo = new JPanel();
        painelFundo.setLayout(new BorderLayout());
        painelFundo.add(BorderLayout.CENTER, barraRolagem);
        painelBotoes.add(btInserir);
        painelBotoes.add(btEditar);
        painelBotoes.add(btExcluir);
        painelFundo.add(BorderLayout.SOUTH, painelBotoes);

        getContentPane().add(painelFundo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 320);
        setVisible(true);

        btInserir.addActionListener(new BtInserirListener());
        btEditar.addActionListener(new BtEditarListener());
        btExcluir.addActionListener(new BtExcluirListener());
    }

    private void criaJTable() {
        tabela = new JTable(modelo);
        modelo.addColumn("Id");
        modelo.addColumn("Nome");
        modelo.addColumn("Categoria");
        modelo.addColumn("Sinopse");
        modelo.addColumn("Duracao");
        modelo.addColumn("Assistido");
        tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(180);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(50);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(50);
        pesquisar(modelo);
    }

    public static void pesquisar(DefaultTableModel modelo) {
        modelo.setNumRows(0);
        FilmeDAO dao = new FilmeDAO();

        for (Filme filme : dao.getLista()) {
            modelo.addRow(new Object[]{filme.getId(), filme.getNome(),
                filme.getCategoria(), filme.getSinopse(), filme.getDuracao(),
                filme.getAssistido()});
        }
    }

    private class BtInserirListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            InserirFilme inserirFilme = new InserirFilme(modelo);
            inserirFilme.setVisible(true);
        }
    }

    private class BtEditarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = -1;
            linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada >= 0) {
                Long idFilme = (Long) tabela
                        .getValueAt(linhaSelecionada, 0);
                AtualizarFilme atualizarFilme
                        = new AtualizarFilme(modelo, idFilme, linhaSelecionada);
                atualizarFilme.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null,
                        "É necessário selecionar uma linha.");
            }
        }
    }

    private class BtExcluirListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = -1;
            linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada >= 0) {
                Long idFilme = (Long) tabela.getValueAt(linhaSelecionada, 0);
                FilmeDAO dao = new FilmeDAO();
                dao.remove(idFilme);
                modelo.removeRow(linhaSelecionada);
            } else {
                JOptionPane.showMessageDialog(null,
                        "É necessário selecionar uma linha.");
            }
        }
    }
}
