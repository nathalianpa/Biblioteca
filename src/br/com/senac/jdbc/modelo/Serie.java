package br.com.senac.jdbc.modelo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nathalia
 */
public class Serie extends Biblioteca {
    private int quantidade_temporadas;

    /**
     * @return the quantidade_temporadas
     */
    public int getQuantidade_temporadas() {
        return quantidade_temporadas;
    }

    /**
     * @param quantidade_temporadas the quantidade_temporadas to set
     */
    public void setQuantidade_temporadas(int quantidade_temporadas) {
        this.quantidade_temporadas = quantidade_temporadas;
    }
}
