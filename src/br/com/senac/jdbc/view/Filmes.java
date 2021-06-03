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

/**
 *
 * @author Nathalia
 */
public class Filmes extends JPanel {

    public Filmes() {
        DefaultListModel listModel = new DefaultListModel();
        
        for(String filme :filmes()) {
            listModel.addElement(filme);
        }
        
        JList jListSeries = new JList(listModel);
        add(jListSeries);
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
