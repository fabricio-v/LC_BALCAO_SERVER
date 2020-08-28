/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Balcao;

import Lcserver.Cliente.Cliente;
import Lcserver.Empresa.Empresa;
import Lcserver.Usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "balcao")
public class Balcao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @NotNull
    @ManyToOne(optional = false, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_empresa", referencedColumnName = "id")
    private Empresa empresa;
    @JsonIgnoreProperties({"descontoPermitido", "descontoPermitidoCR", "obs", "permissoes", "empresa"})
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;
    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;
    @Column(name = "id_usuarioex")
    private Integer idUsuarioex;
    @Column(name = "id_balcaosb")
    private Integer idBalcaosb;
    @Column(name = "id_orcamento")
    private Integer idOrcamento;
    @Column(name = "data_hora")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")//, timezone = "GMT-3")
    private Date dataHora;
    @Column(name = "data_horaex")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")//, timezone = "GMT-3")
    private Date dataHoraex;
    @Column(name = "total")
    private Double total;
    @Column(name = "desc_tipo")
    private String descTipo;
    @Column(name = "desc_forma")
    private String descForma;
    @Column(name = "desconto")
    private Double desconto;
    @Column(name = "valor_desconto")
    private Double valorDesconto;
    @Column(name = "arredondamento")
    private Double arredondamento;
    @Column(name = "sub_total")
    private Double subTotal;
    @Column(name = "obs")
    private String obs;
    @Column(name = "status")
    private String status;

    @JsonIgnoreProperties({"balcao"})
    @OneToMany(mappedBy = "balcao", cascade = CascadeType.ALL)
    private List<BalcaoDet> balcaoDet;

    @JsonIgnoreProperties({"balcao"})
    @OneToMany(mappedBy = "balcao", cascade = CascadeType.ALL)
    private List<BalcaoRec> balcaoRec;
//    @JsonIgnoreProperties("balcao")
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_balcao", nullable = false)
//    private List<BalcaoDet> balcaoDet;

    public Balcao() {
        this.total = 0.0;
        this.descTipo = "Desconto";
        this.idBalcaosb = 0;
        this.idOrcamento = 0;
        this.desconto = 0.0;
        this.arredondamento = 0.0;
    }

    public Balcao(Integer id,
            Empresa empresa,
            Usuario usuario,
            Cliente cliente,
            Date dataHora,
            Double total,
            String descTipo,
            String descForma, Double desconto, Double valorDesconto, Double arredondamento, Double subTotal, String status, String obs) {
        this.id = id;
        this.empresa = empresa;
        this.usuario = usuario;
        this.cliente = cliente;

        this.dataHora = dataHora;
        this.total = total;
        this.descTipo = descTipo;
        this.descForma = descForma;
        this.desconto = desconto;
        this.valorDesconto = valorDesconto;
        this.arredondamento = arredondamento;
        this.subTotal = subTotal;
        this.status = status;
        this.obs = obs;
    }

    public Balcao(Integer id) {
        this.id = id;
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

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getIdUsuarioex() {
        return idUsuarioex;
    }

    public void setIdUsuarioex(Integer idUsuarioex) {
        this.idUsuarioex = idUsuarioex;
    }

    public Integer getIdBalcaosb() {
        return idBalcaosb;
    }

    public void setIdBalcaosb(Integer idBalcaosb) {
        this.idBalcaosb = idBalcaosb;
    }

    public Integer getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(Integer idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public Date getDataHoraex() {
        return dataHoraex;
    }

    public void setDataHoraex(Date dataHoraex) {
        this.dataHoraex = dataHoraex;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getDescTipo() {
        return descTipo;
    }

    public void setDescTipo(String descTipo) {
        this.descTipo = descTipo;
    }

    public String getDescForma() {
        return descForma;
    }

    public void setDescForma(String descForma) {
        this.descForma = descForma;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(Double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public Double getArredondamento() {
        return arredondamento;
    }

    public void setArredondamento(Double arredondamento) {
        this.arredondamento = arredondamento;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<BalcaoDet> getBalcaoDet() {
        return balcaoDet;
    }

    public void setBalcaodet(List<BalcaoDet> balcaoDet) {
        this.balcaoDet = balcaoDet;
    }

    public List<BalcaoRec> getBalcaoRec() {
        return balcaoRec;
    }

    public void setBalcaoRec(List<BalcaoRec> balcaoRec) {
        this.balcaoRec = balcaoRec;
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
        if (!(object instanceof Balcao)) {
            return false;
        }
        Balcao other = (Balcao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lcserver.Balcao.Balcao[ id=" + id + " ]";
    }

}
