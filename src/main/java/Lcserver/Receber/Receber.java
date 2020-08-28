/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Receber;

import Lcserver.Pagamento.Pagamento;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Entity
@Table(name = "receber")

public class Receber implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_cliente")
    private int idCliente;
    @Column(name = "id_usuario")
    private int idUsuario;
    @Column(name = "id_empresa")
    private int idEmpresa;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pagamento", nullable = false)
    private Pagamento pagamento;
    @Column(name = "id_planocontas")
    private int idPlanocontas;
    @Column(name = "id_contamovimento")
    private int idContamovimento;
    @Column(name = "id_convenio")
    private Integer idConvenio;
    @Column(name = "tipo")
    private String tipo;
    @Column(name = "status")
    private String status;
    @Column(name = "documento")
    private String documento;
    @Column(name = "n_documento")
    private String nDocumento;
    @Column(name = "lancamento")
    @Temporal(TemporalType.DATE)
    private Date lancamento;
    @Column(name = "emissao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date emissao;
    @Column(name = "vencimento")
    @Temporal(TemporalType.DATE)
    private Date vencimento;
    @Column(name = "parcela")
    private String parcela;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_original")
    private Double valorOriginal;
    @Column(name = "valor_rec")
    private Double valorRec;
    @Column(name = "data_rec")
    @Temporal(TemporalType.DATE)
    private Date dataRec;
    @Column(name = "juros_rec")
    private Double jurosRec;
    @Column(name = "multa_rec")
    private Double multaRec;
    @Column(name = "valor_arec")
    private Double valorArec;
    @Column(name = "valor_desconto")
    private Double valorDesconto;
    @Column(name = "agencia")
    private String agencia;
    @Column(name = "conta")
    private String conta;
    @Column(name = "conciliado")
    private String conciliado;
    @Column(name = "numero_cheque")
    private String numeroCheque;
    @Column(name = "numero_boleto")
    private String numeroBoleto;
    @Lob
    @Column(name = "historico")
    private String historico;
    @Lob
    @Column(name = "obs")
    private String obs;

    public Receber() {
    }

    public Receber(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public String getnDocumento() {
        return nDocumento;
    }

    public void setnDocumento(String nDocumento) {
        this.nDocumento = nDocumento;
    }

    public int getIdPlanocontas() {
        return idPlanocontas;
    }

    public void setIdPlanocontas(int idPlanocontas) {
        this.idPlanocontas = idPlanocontas;
    }

    public int getIdContamovimento() {
        return idContamovimento;
    }

    public void setIdContamovimento(int idContamovimento) {
        this.idContamovimento = idContamovimento;
    }

    public Integer getIdConvenio() {
        return idConvenio;
    }

    public void setIdConvenio(Integer idConvenio) {
        this.idConvenio = idConvenio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNDocumento() {
        return nDocumento;
    }

    public void setNDocumento(String nDocumento) {
        this.nDocumento = nDocumento;
    }

    public Date getLancamento() {
        return lancamento;
    }

    public void setLancamento(Date lancamento) {
        this.lancamento = lancamento;
    }

    public Date getEmissao() {
        return emissao;
    }

    public void setEmissao(Date emissao) {
        this.emissao = emissao;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public Double getValorOriginal() {
        return valorOriginal;
    }

    public void setValorOriginal(Double valorOriginal) {
        this.valorOriginal = valorOriginal;
    }

    public Double getValorRec() {
        return valorRec;
    }

    public void setValorRec(Double valorRec) {
        this.valorRec = valorRec;
    }

    public Date getDataRec() {
        return dataRec;
    }

    public void setDataRec(Date dataRec) {
        this.dataRec = dataRec;
    }

    public Double getJurosRec() {
        return jurosRec;
    }

    public void setJurosRec(Double jurosRec) {
        this.jurosRec = jurosRec;
    }

    public Double getMultaRec() {
        return multaRec;
    }

    public void setMultaRec(Double multaRec) {
        this.multaRec = multaRec;
    }

    public Double getValorArec() {
        return valorArec;
    }

    public void setValorArec(Double valorArec) {
        this.valorArec = valorArec;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getConciliado() {
        return conciliado;
    }

    public void setConciliado(String conciliado) {
        this.conciliado = conciliado;
    }

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public String getNumeroBoleto() {
        return numeroBoleto;
    }

    public void setNumeroBoleto(String numeroBoleto) {
        this.numeroBoleto = numeroBoleto;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
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
        if (!(object instanceof Receber)) {
            return false;
        }
        Receber other = (Receber) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lcserver.Receber.Receber[ id=" + id + " ]";
    }

}
