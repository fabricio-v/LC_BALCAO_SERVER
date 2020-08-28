/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Produto;

import Lcserver.Fabricante.Fabricante;
import Lcserver.Unidade.Unidade;
import java.util.Date;

/**
 *
 * @author JORDAN QUEIROGA
 */
public class ProdutoDtoOutput {

    private Integer id;
    private String codigo;
    private String referencia;
    private String codigoBarras;
    private String nome;
    private String descricao;
    private Fabricante fabricante;
    private Unidade unidade;
    private String podeDesconto;
    private String podeFracionado;
    private Double precoVenda;
    private Double descontoMax;
    private Double precoVenda2;
    private Double qtdMinimapv2;
    private Double descontoMax2;
    private Double precoVenda3;
    private Double qtdMinimapv3;
    private Double descontoMax3;
    private Double precoVenda4;
    private Double qtdMinimapv4;
    private Double descontoMax4;
    private Double precoPromocao;
    private Date dataPromocao;
    private Double estoque;

    public ProdutoDtoOutput(Produto produto) {
        this.id = produto.getId();
        this.codigo = produto.getCodigo();
        this.referencia = produto.getReferencia();
        this.codigoBarras = produto.getCodigoBarras();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.fabricante = new Fabricante(produto.getFabricante().getId(), produto.getNome());
        this.unidade = new Unidade(produto.getUnidade().getId(), produto.getUnidade().getNome());
        this.podeDesconto = produto.getPodeDesconto();
        this.podeFracionado = produto.getPodeFracionado();
        this.precoVenda = produto.getPrecoVenda();
        this.descontoMax = produto.getDescontoMax();
        this.precoVenda2 = produto.getPrecoVenda2();
        this.qtdMinimapv2 = produto.getQtdMinimapv2();
        this.descontoMax2 = produto.getDescontoMax2();
        this.precoVenda3 = produto.getPrecoVenda3();
        this.qtdMinimapv3 = produto.getQtdMinimapv3();
        this.descontoMax3 = produto.getDescontoMax3();
        this.precoVenda4 = produto.getPrecoVenda4();
        this.qtdMinimapv4 = produto.getQtdMinimapv4();
        this.descontoMax4 = produto.getDescontoMax4();
        this.precoPromocao = produto.getPrecoPromocao();
        this.dataPromocao = produto.getDataPromocao();
        this.estoque = produto.getEstoque();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Fabricante getFabricante() {
        return fabricante;
    }

    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public String getPodeDesconto() {
        return podeDesconto;
    }

    public void setPodeDesconto(String podeDesconto) {
        this.podeDesconto = podeDesconto;
    }

    public String getPodeFracionado() {
        return podeFracionado;
    }

    public void setPodeFracionado(String podeFracionado) {
        this.podeFracionado = podeFracionado;
    }

    public Double getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(Double precoVenda) {
        this.precoVenda = precoVenda;
    }

    public Double getDescontoMax() {
        return descontoMax;
    }

    public void setDescontoMax(Double descontoMax) {
        this.descontoMax = descontoMax;
    }

    public Double getPrecoVenda2() {
        return precoVenda2;
    }

    public void setPrecoVenda2(Double precoVenda2) {
        this.precoVenda2 = precoVenda2;
    }

    public Double getQtdMinimapv2() {
        return qtdMinimapv2;
    }

    public void setQtdMinimapv2(Double qtdMinimapv2) {
        this.qtdMinimapv2 = qtdMinimapv2;
    }

    public Double getDescontoMax2() {
        return descontoMax2;
    }

    public void setDescontoMax2(Double descontoMax2) {
        this.descontoMax2 = descontoMax2;
    }

    public Double getPrecoVenda3() {
        return precoVenda3;
    }

    public void setPrecoVenda3(Double precoVenda3) {
        this.precoVenda3 = precoVenda3;
    }

    public Double getQtdMinimapv3() {
        return qtdMinimapv3;
    }

    public void setQtdMinimapv3(Double qtdMinimapv3) {
        this.qtdMinimapv3 = qtdMinimapv3;
    }

    public Double getDescontoMax3() {
        return descontoMax3;
    }

    public void setDescontoMax3(Double descontoMax3) {
        this.descontoMax3 = descontoMax3;
    }

    public Double getPrecoVenda4() {
        return precoVenda4;
    }

    public void setPrecoVenda4(Double precoVenda4) {
        this.precoVenda4 = precoVenda4;
    }

    public Double getQtdMinimapv4() {
        return qtdMinimapv4;
    }

    public void setQtdMinimapv4(Double qtdMinimapv4) {
        this.qtdMinimapv4 = qtdMinimapv4;
    }

    public Double getDescontoMax4() {
        return descontoMax4;
    }

    public void setDescontoMax4(Double descontoMax4) {
        this.descontoMax4 = descontoMax4;
    }

    public Double getPrecoPromocao() {
        return precoPromocao;
    }

    public void setPrecoPromocao(Double precoPromocao) {
        this.precoPromocao = precoPromocao;
    }

    public Date getDataPromocao() {
        return dataPromocao;
    }

    public void setDataPromocao(Date dataPromocao) {
        this.dataPromocao = dataPromocao;
    }

    public Double getEstoque() {
        return estoque;
    }

    public void setEstoque(Double estoque) {
        this.estoque = estoque;
    }

    
    
}
