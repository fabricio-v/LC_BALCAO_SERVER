/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Usuario;

import Lcserver.Empresa.Empresa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 15)
    @Column(name = "login")
    private String login;
    @Size(max = 10)
    @Column(name = "senha")
    private String senha;
    @Size(max = 80)
    @Column(name = "nome")
    private String nome;
    @Column(name = "desconto_permitido", scale = 12, precision = 3)
    private Double descontoPermitido;
    @Column(name = "desconto_permitidocr", scale = 12, precision = 3)
    private Double descontoPermitidoCR;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "obs")
    private String obs;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "permissoes")
    private String permissoes;
    @Size(max = 10)
    @Column(name = "status")
    private String status;
    @JsonIgnoreProperties("permissoes")
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_funcao", referencedColumnName = "id")
    private Funcao funcao;
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(optional = true)
    @JoinColumn(name = "id_empresa", referencedColumnName = "id")
    private Empresa empresa;
    @Size(max = 3)
    @Column(name = "pode_fp")
    private String podeFp;
    @Size(max = 12)
    @Column(name = "senha_fp")
    private String senhaFp;

    public Usuario() {
        super();
    }

  
    public Usuario(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getDescontoPermitido() {
        return descontoPermitido;
    }

    public void setDescontoPermitido(Double descontoPermitido) {
        this.descontoPermitido = descontoPermitido;
    }

    public Double getDescontoPermitidoCR() {
        return descontoPermitidoCR;
    }

    public void setDescontoPermitidoCR(Double descontoPermitidoCR) {
        this.descontoPermitidoCR = descontoPermitidoCR;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(String permissoes) {
        this.permissoes = permissoes;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getPodeFp() {
        return podeFp;
    }

    public void setPodeFp(String podeFp) {
        this.podeFp = podeFp;
    }

    public String getSenhaFp() {
        return senhaFp;
    }

    public void setSenhaFp(String senhaFp) {
        this.senhaFp = senhaFp;
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario.Usuario[ id=" + id + " ]";
    }

}
