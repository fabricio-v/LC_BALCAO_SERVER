/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Pagamento;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Entity
@Table(name = "pagamento")
@NamedQueries({
    @NamedQuery(name = "Pagamento.findAll", query = "SELECT p FROM Pagamento p")})
public class Pagamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_contamovimento")
    private Integer idContamovimento;
    @Size(max = 3)
    @Column(name = "tipo_fpag")
    private String tipoFpag;
    @Size(max = 45)
    @Column(name = "nome")
    private String nome;
    @Size(max = 1)
    @Column(name = "pode_parcelar")
    private String podeParcelar;
    @Size(max = 3)
    @Column(name = "n_maxparcela")
    private String nMaxparcela;
    @Size(max = 3)
    @Column(name = "dias")
    private String dias;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "retencao")
    private Double retencao;
    @Column(name = "comissao_percentual")
    private Double comissaoPercentual;
    @Size(max = 1)
    @Column(name = "pode_vercredito")
    private String podeVercredito;
    @Size(max = 1)
    @Column(name = "pode_caixa")
    private String podeCaixa;
    @Size(max = 1)
    @Column(name = "pode_tef")
    private String podeTef;
    @Size(max = 1)
    @Column(name = "pode_rpt")
    private String podeRpt;
    @Size(max = 1)
    @Column(name = "pode_rectit")
    private String podeRectit;
    @Size(max = 1)
    @Column(name = "pode_pagtit")
    private String podePagtit;
    @Size(max = 2)
    @Column(name = "cartao_tpintegra")
    private String cartaoTpintegra;
    @Size(max = 1)
    @Column(name = "ativo")
    private String ativo;

    public Pagamento() {
    }

    public Pagamento(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdContamovimento() {
        return idContamovimento;
    }

    public void setIdContamovimento(Integer idContamovimento) {
        this.idContamovimento = idContamovimento;
    }

    public String getTipoFpag() {
        return tipoFpag;
    }

    public void setTipoFpag(String tipoFpag) {
        this.tipoFpag = tipoFpag;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPodeParcelar() {
        return podeParcelar;
    }

    public void setPodeParcelar(String podeParcelar) {
        this.podeParcelar = podeParcelar;
    }

    public String getNMaxparcela() {
        return nMaxparcela;
    }

    public void setNMaxparcela(String nMaxparcela) {
        this.nMaxparcela = nMaxparcela;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public Double getRetencao() {
        return retencao;
    }

    public void setRetencao(Double retencao) {
        this.retencao = retencao;
    }

    public Double getComissaoPercentual() {
        return comissaoPercentual;
    }

    public void setComissaoPercentual(Double comissaoPercentual) {
        this.comissaoPercentual = comissaoPercentual;
    }

    public String getPodeVercredito() {
        return podeVercredito;
    }

    public void setPodeVercredito(String podeVercredito) {
        this.podeVercredito = podeVercredito;
    }

    public String getPodeCaixa() {
        return podeCaixa;
    }

    public void setPodeCaixa(String podeCaixa) {
        this.podeCaixa = podeCaixa;
    }

    public String getPodeTef() {
        return podeTef;
    }

    public void setPodeTef(String podeTef) {
        this.podeTef = podeTef;
    }

    public String getPodeRpt() {
        return podeRpt;
    }

    public void setPodeRpt(String podeRpt) {
        this.podeRpt = podeRpt;
    }

    public String getPodeRectit() {
        return podeRectit;
    }

    public void setPodeRectit(String podeRectit) {
        this.podeRectit = podeRectit;
    }

    public String getPodePagtit() {
        return podePagtit;
    }

    public void setPodePagtit(String podePagtit) {
        this.podePagtit = podePagtit;
    }

    public String getCartaoTpintegra() {
        return cartaoTpintegra;
    }

    public void setCartaoTpintegra(String cartaoTpintegra) {
        this.cartaoTpintegra = cartaoTpintegra;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
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
        if (!(object instanceof Pagamento)) {
            return false;
        }
        Pagamento other = (Pagamento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lcserver.Pagamento.Pagamento[ id=" + id + " ]";
    }
    
}
