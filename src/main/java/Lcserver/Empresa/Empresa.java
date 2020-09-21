/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Empresa;

import Lcserver.EstadoCidade.Cidades;
import Lcserver.EstadoCidade.Estados;
import java.io.Serializable;
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
import javax.swing.ImageIcon;
import javax.validation.constraints.Size;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    @Size(max = 60)
    @Column(name = "fantasia")
    private String fantasia;

    @Size(max = 60)
    @Column(name = "razao_social")
    private String razaoSocial;

    @Size(max = 18)
    @Column(name = "cnpj")
    private String cnpj;

    @Size(max = 15)
    @Column(name = "ie")
    private String ie;

    @Size(max = 15)
    @Column(name = "im")
    private String im;

    @Size(max = 80)
    @Column(name = "endereco")
    private String endereco;

    @Size(max = 10)
    @Column(name = "numero")
    private String numero;

    @Size(max = 13)
    @Column(name = "telefone")
    private String telefone;

    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="E-mail inválido")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 50)
    @Column(name = "email")
    private String email;

    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "juros_diario")
    private Double jurosDiario;

    @Column(name = "multa")
    private Double multa;

    @Size(max = 20)
    @Column(name = "bairro")
    private String bairro;

    @Size(max = 10)
    @Column(name = "cep")
    private String cep;

    @Size(max = 3)
    @Column(name = "crt")
    private String crt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_estados", nullable = false)
    private Estados estado;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cidades")
    private Cidades cidade;

    @Size(max = 2)
    @Column(name = "ativo")
    private String ativo;

    @Size(max = 8)
    @Column(name = "cnae")
    private String cnae;

    @Lob
    @Column(name = "logo")
    private byte[] logo;

    public Empresa() {
    }

    public Empresa(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getJurosDiario() {
        return jurosDiario;
    }

    public void setJurosDiario(Double jurosDiario) {
        this.jurosDiario = jurosDiario;
    }

    public Double getMulta() {
        return multa;
    }

    public void setMulta(Double multa) {
        this.multa = multa;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCrt() {
        return crt;
    }

    public void setCrt(String crt) {
        this.crt = crt;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public Cidades getCidade() {
        return cidade;
    }

    public void setCidade(Cidades cidade) {
        this.cidade = cidade;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
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
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Empresa.Empresa[ id=" + id + " ]";
    }

}
