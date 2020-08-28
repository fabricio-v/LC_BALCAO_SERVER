/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Balcao.dto.output;

import Lcserver.Balcao.BalcaoDet;
import Lcserver.Produto.ProdutoDtoOutput;

/**
 *
 * @author JORDAN QUEIROGA
 */
public class BalcaoDetDtoOutput {

    private Integer id;
    private String item;
    private ProdutoDtoOutput produto;
    private Integer idLote;
    private Double preco;
    private String descForma;
    private Double total;
    private Double valorDesconto;
    private Double subTotal;
    private Double desconto;
    private Double quantidade;
    private String tipoPreco;

    public BalcaoDetDtoOutput(BalcaoDet balcaoDet) {
        this.id = balcaoDet.getId();
        this.item = balcaoDet.getItem();
        this.produto = new ProdutoDtoOutput(balcaoDet.getProduto());
        this.idLote = balcaoDet.getIdLote();
        this.preco = balcaoDet.getPreco();
        this.descForma = balcaoDet.getDescForma();
        this.total = balcaoDet.getTotal();
        this.valorDesconto = balcaoDet.getValorDesconto();
        this.subTotal = balcaoDet.getSubTotal();
        this.desconto = balcaoDet.getDesconto();
        this.quantidade = balcaoDet.getQuantidade();
        this.tipoPreco = balcaoDet.getTipoPreco();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public ProdutoDtoOutput getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDtoOutput produto) {
        this.produto = produto;
    }

    public Integer getIdLote() {
        return idLote;
    }

    public void setIdLote(Integer idLote) {
        this.idLote = idLote;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescForma() {
        return descForma;
    }

    public void setDescForma(String descForma) {
        this.descForma = descForma;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public String getTipoPreco() {
        return tipoPreco;
    }

    public void setTipoPreco(String tipoPreco) {
        this.tipoPreco = tipoPreco;
    }
    
    
    
    

}
