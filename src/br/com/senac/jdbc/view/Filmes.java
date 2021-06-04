/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.jdbc.view;

import java.util.List;
import javax.swing.JList;
import javax.swing.JPanel;
import br.com.senac.jdbc.dao.FilmeDAO;
import br.com.senac.jdbc.modelo.Filme;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Nathalia
 */
public class Filmes extends JPanel {

    public Filmes() {
//        DefaultListModel listModel = new DefaultListModel();
//        
//        for(String filme :filmes()) {
//            listModel.addElement(filme);
//        }
//        
//        JList jListSeries = new JList(listModel);
//        add(jListSeries);

        String [] colunas = {"Nome", "Telefone", "Email"};
        
        Object [][] dados = {
            {"Ana Monteiro", "48 9923-7898", "ana.monteiro@gmail.com"},
            {"João da Silva", "48 8890-3345", "joaosilva@hotmail.com"},
            {"Pedro Cascaes", "48 9870-5634", "pedrinho@gmail.com"}
        };

//        Object[][] dados = new Object[][];
        
        JTable tabela = new JTable(dados, colunas);
        
        JScrollPane barraRolagem = new JScrollPane(tabela);
        
        add(barraRolagem);
    }
    
    public List<String> filmes() {
        List<String> result = new ArrayList<String>();
        FilmeDAO dao = new FilmeDAO();
        List<Filme> filmes = dao.getLista();
        
        for(Filme filme : filmes) {
            result.add(filme.getNome());
        }
        
        return result;
    }
}
