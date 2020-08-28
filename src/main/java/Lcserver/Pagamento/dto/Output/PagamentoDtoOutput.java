/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Pagamento.dto.Output;

import Lcserver.Pagamento.Pagamento;

/**
 *
 * @author JORDAN QUEIROGA
 */
public class PagamentoDtoOutput {

    private int id;
    private String nome;
    private String nMaxparcela;
    private String podeVercredito;

    /**
     * @deprecated
     */
    public PagamentoDtoOutput() {
    }

    public PagamentoDtoOutput(Pagamento pagamento) {
        this.id = pagamento.getId();
        this.nome = pagamento.getNome();
        this.nMaxparcela = pagamento.getNMaxparcela();
        this.podeVercredito = pagamento.getPodeVercredito();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getnMaxparcela() {
        return nMaxparcela;
    }

    public void setnMaxparcela(String nMaxparcela) {
        this.nMaxparcela = nMaxparcela;
    }

    public String getPodeVercredito() {
        return podeVercredito;
    }

    public void setPodeVercredito(String podeVercredito) {
        this.podeVercredito = podeVercredito;
    }

}
