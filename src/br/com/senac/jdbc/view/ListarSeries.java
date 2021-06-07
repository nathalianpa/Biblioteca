/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.jdbc.view;

import br.com.senac.jdbc.dao.SerieDAO;
import br.com.senac.jdbc.modelo.Serie;
import static br.com.senac.jdbc.view.ListarFilmes.pesquisar;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nathalia
 */
public class ListarSeries extends JFrame {

    private JPanel painelFundo;
    private JPanel painelBotoes;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private JButton btInserir;
    private JButton btExcluir;
    private JButton btEditar;
    private DefaultTableModel modelo = new DefaultTableModel();

    public ListarSeries() {
        super("Listar Series");
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

        btInserir.addActionListener(new ListarSeries.BtInserirListener());
        btEditar.addActionListener(new ListarSeries.BtEditarListener());
        btExcluir.addActionListener(new ListarSeries.BtExcluirListener());
    }

    private void criaJTable() {
        tabela = new JTable(modelo);
        modelo.addColumn("Id");
        modelo.addColumn("Nome");
        modelo.addColumn("Categoria");
        modelo.addColumn("Sinopse");
        modelo.addColumn("Quantidade de Temporadas");
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
        SerieDAO dao = new SerieDAO();

        for (Serie serie : dao.getLista()) {
            modelo.addRow(new Object[]{serie.getId(), serie.getNome(),
                serie.getCategoria(), serie.getSinopse(),
                serie.getQuantidade_temporadas(), serie.getAssistido()});
        }
    }

    private class BtInserirListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            InserirSerie inserirSerie = new InserirSerie(modelo);
            inserirSerie.setVisible(true);
        }
    }

    private class BtEditarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = -1;
            linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada >= 0) {
                Long idSerie = (Long) tabela
                        .getValueAt(linhaSelecionada, 0);
                AtualizarSerie atualizarSerie
                        = new AtualizarSerie(modelo, idSerie, linhaSelecionada);
                atualizarSerie.setVisible(true);
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
                Long idSerie = (Long) tabela.getValueAt(linhaSelecionada, 0);
                SerieDAO dao = new SerieDAO();
                dao.remove(idSerie);
                modelo.removeRow(linhaSelecionada);
            } else {
                JOptionPane.showMessageDialog(null,
                        "É necessário selecionar uma linha.");
            }
        }
    }
}
