/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Balcao.dto.Input;

import Lcserver.Balcao.Balcao;
import Lcserver.Balcao.BalcaoDet;
import Lcserver.Produto.Produto;
import Util.Funcoes;

/**
 *
 * @author JORDAN QUEIROGA
 */
public class BalcaoDetNewDtoInput {

    private int idProduto;
    private double preco;
    private double quantidade;
    private double total;
    private double desconto;
    private String descForma;
    private double valorDesconto;
    private double acrescimo;
    private String acreForma;
    private double valorAcrescimo;
    private double subTotal;
    private String tipoPreco;

    public static BalcaoDet build(Balcao balcao, BalcaoDetNewDtoInput balcaoDetDtoInput, int index) {
        BalcaoDet det = new BalcaoDet();
        det.setProduto(new Produto(balcaoDetDtoInput.getIdProduto()));
        det.setIdLote(0);
        det.setPreco(balcaoDetDtoInput.getPreco());
        det.setQuantidade(balcaoDetDtoInput.getQuantidade());
        det.setTotal(balcaoDetDtoInput.getTotal());
        det.setDesconto(balcaoDetDtoInput.getDesconto());
        det.setDescForma(balcaoDetDtoInput.getDescForma());
        det.setValorDesconto(balcaoDetDtoInput.getValorDesconto());
        det.setAcrescimo(balcaoDetDtoInput.getAcrescimo());
        det.setAcreForma(balcaoDetDtoInput.getAcreForma());
        det.setValorAcrescimo(balcaoDetDtoInput.getValorAcrescimo());
        det.setSubTotal(balcaoDetDtoInput.getSubTotal());
        det.setTipoPreco(balcaoDetDtoInput.getTipoPreco());
        det.setBalcao(balcao);
        det.setItem(Funcoes.formatoCodigo3(index));
        System.out.println("valor acrescimo: " + det.getValorAcrescimo());
        return det;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public String getDescForma() {
        return descForma;
    }

    public void setDescForma(String descForma) {
        this.descForma = descForma;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public double getAcrescimo() {
        return acrescimo;
    }

    public void setAcrescimo(double acrescimo) {
        this.acrescimo = acrescimo;
    }

    public String getAcreForma() {
        return acreForma;
    }

    public void setAcreForma(String acreForma) {
        this.acreForma = acreForma;
    }

    public double getValorAcrescimo() {
        return valorAcrescimo;
    }

    public void setValorAcrescimo(double valorAcrescimo) {
        this.valorAcrescimo = valorAcrescimo;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public String getTipoPreco() {
        return tipoPreco;
    }

    public void setTipoPreco(String tipoPreco) {
        this.tipoPreco = tipoPreco;
    }

}
