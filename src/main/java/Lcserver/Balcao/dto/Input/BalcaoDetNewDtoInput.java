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
    private double subTotal;
    private String tipoPreco;

    public static BalcaoDet build(Balcao balcao, BalcaoDetNewDtoInput balcaoDetDtoInput, int index) {
        BalcaoDet det = new BalcaoDet();
        det.setProduto(new Produto(balcaoDetDtoInput.getIdProduto()));
        det.setIdLote(0);
        det.setPreco(balcaoDetDtoInput.getPreco());
        det.setQuantidade(balcaoDetDtoInput.getQuantidade());
        det.setTotal(balcaoDetDtoInput.getTotal());
//        det.setDescTipo("Desconto");
        det.setDesconto(balcaoDetDtoInput.getDesconto());
        det.setDescForma(balcaoDetDtoInput.getDescForma());
        det.setValorDesconto(balcaoDetDtoInput.getValorDesconto());
        det.setSubTotal(balcaoDetDtoInput.getSubTotal());
        det.setTipoPreco(balcaoDetDtoInput.getTipoPreco());
        det.setBalcao(balcao);
        det.setItem(Funcoes.formatoCodigo3(index));
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
