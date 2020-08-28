/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.EstadoCidade;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Entity
@Table(name = "estados")
public class Estados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 2)
    @Column(name = "iduf")
    private String iduf;
    @Size(max = 2)
    @Column(name = "uf")
    private String uf;
    @Size(max = 45)
    @Column(name = "nome")
    private String nome;
    @Size(max = 10)
    @Column(name = "icmscompra")
    private String icmscompra;
    @Size(max = 10)
    @Column(name = "icmsvenda")
    private String icmsvenda;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "aliquota_interestadual")
    private Double aliquotaInterestadual;
    @Column(name = "aliquota_fcp")
    private Double aliquotaFcp;

    public Estados() {
    }

    public Estados(Integer id) {
        this.id = id;
    }

    public Estados(Integer id, String uf, String nome) {
        this.id = id;
        this.uf = uf;
        this.nome = nome;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIduf() {
        return iduf;
    }

    public void setIduf(String iduf) {
        this.iduf = iduf;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIcmscompra() {
        return icmscompra;
    }

    public void setIcmscompra(String icmscompra) {
        this.icmscompra = icmscompra;
    }

    public String getIcmsvenda() {
        return icmsvenda;
    }

    public void setIcmsvenda(String icmsvenda) {
        this.icmsvenda = icmsvenda;
    }

    public Double getAliquotaInterestadual() {
        return aliquotaInterestadual;
    }

    public void setAliquotaInterestadual(Double aliquotaInterestadual) {
        this.aliquotaInterestadual = aliquotaInterestadual;
    }

    public Double getAliquotaFcp() {
        return aliquotaFcp;
    }

    public void setAliquotaFcp(Double aliquotaFcp) {
        this.aliquotaFcp = aliquotaFcp;
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
        if (!(object instanceof Estados)) {
            return false;
        }
        Estados other = (Estados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lcserver.EstadoCidade.Estados[ id=" + id + " ]";
    }
    
}
