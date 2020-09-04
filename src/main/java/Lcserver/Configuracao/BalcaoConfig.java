/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Configuracao;

import Lcserver.Empresa.Empresa;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Entity
@Table(name = "balcaoconfig")
public class BalcaoConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

//    @Basic(optional = false)
//    @NotNull
//    @Column(name = "id_empresa")
//    private Integer idEmpresa;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_empresa", referencedColumnName = "id", nullable = false)
    private Empresa empresa;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "serial")
    private String serial;

    public BalcaoConfig() {
    }

    public BalcaoConfig(Integer id) {
        this.id = id;
    }

    public BalcaoConfig(Integer id, Empresa empresa, String serial) {
        this.id = id;
        this.empresa = empresa;
        this.serial = serial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

//    public Integer getIdEmpresa() {
//        return idEmpresa;
//    }
//
//    public void setIdEmpresa(Integer idEmpresa) {
//        this.idEmpresa = idEmpresa;
//    }
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
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
        if (!(object instanceof BalcaoConfig)) {
            return false;
        }
        BalcaoConfig other = (BalcaoConfig) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lcserver.Gourmet.Gourmetconfig[ id=" + id + " ]";
    }

}
