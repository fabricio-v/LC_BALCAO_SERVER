/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Balcao;

import Lcserver.Produto.Produto;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Entity
@Table(name = "balcaodet")
public class BalcaoDet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "id_balcao")
    private Balcao balcao;
    @Size(max = 4)
    @Column(name = "item")
    private String item;
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_produto", referencedColumnName = "id")
    private Produto produto;
    @Column(name = "id_lote")
    private Integer idLote;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "preco")
    private Double preco;
//    @Size(max = 10)
//    @Column(name = "desc_tipo")
//    private String descTipo;
    @Size(max = 1)
    @Column(name = "desc_forma")
    private String descForma;
    @Column(name = "total")
    private Double total;
    @Column(name = "valor_desconto")
    private Double valorDesconto;
    @Column(name = "sub_total")
    private Double subTotal;
    @Column(name = "desconto")
    private Double desconto;
    @Column(name = "quantidade")
    private Double quantidade;
    @Size(max = 1)
    @Column(name = "tipo_preco")
    private String tipoPreco;
    @Size(max = 1)
    @Column(name = "acre_forma")
    private String acreForma;
    @Column(name = "acrescimo")
    private Double acrescimo;
    @Column(name = "valor_acrescimo")
    private Double valorAcrescimo;

    public BalcaoDet() {
        this.idLote = 0;
    }

    public BalcaoDet(Integer id) {
        this.id = id;
        this.idLote = 0;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Balcao getBalcao() {
        return balcao;
    }

    public void setBalcao(Balcao balcao) {
        this.balcao = balcao;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
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

//    public String getDescTipo() {
//        return descTipo;
//    }
//
//    public void setDescTipo(String descTipo) {
//        this.descTipo = descTipo;
//    }
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

    public String getAcreForma() {
        return acreForma;
    }

    public void setAcreForma(String acreForma) {
        this.acreForma = acreForma;
    }

    public Double getAcrescimo() {
        return acrescimo;
    }

    public void setAcrescimo(Double acrescimo) {
        this.acrescimo = acrescimo;
    }

    public Double getValorAcrescimo() {
        return valorAcrescimo;
    }

    public void setValorAcrescimo(Double valorAcrescimo) {
        this.valorAcrescimo = valorAcrescimo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BalcaoDet)) {
            return false;
        }
        BalcaoDet other = (BalcaoDet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lcserver.Balcao.Balcaodet[ id=" + id + " ]";
    }

}
