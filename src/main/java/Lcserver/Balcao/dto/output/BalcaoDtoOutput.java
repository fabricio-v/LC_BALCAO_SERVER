/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Balcao.dto.output;

import Lcserver.Balcao.Balcao;
import Lcserver.Balcao.BalcaoDet;
import Lcserver.Cliente.ClienteService;
import Lcserver.Cliente.dto.output.ClienteDtoOutput;
import Lcserver.Empresa.dto.output.EmpresaDtoOutput;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author JORDAN QUEIROGA
 */
public class BalcaoDtoOutput {

    private Integer id;
    private EmpresaDtoOutput empresa;
    private String usuarioLogin;
    private ClienteDtoOutput cliente;
    private Date dataHora;
    private Double total;
    private Double valorDesconto;
    private Double subTotal;
    private String obs;
    private String status;
    private List<BalcaoDetDtoOutput> balcaoDet;
    private List<BalcaoRecDtoOutput> balcaoRec;

    public BalcaoDtoOutput(Balcao balcao, ClienteService clienteControle) {
        this.id = balcao.getId();
        this.empresa = new EmpresaDtoOutput(balcao.getEmpresa());
        this.usuarioLogin = balcao.getUsuario().getLogin();
        if (balcao.getCliente().getId() <= 2) {
            this.cliente = new ClienteDtoOutput(balcao.getCliente());
        } else {
            this.cliente = new ClienteDtoOutput(balcao.getCliente(), clienteControle.getDebitoCliente(balcao.getCliente().getId()), clienteControle.isDevendo(balcao.getCliente().getId()));
        }
        this.dataHora = balcao.getDataHora();
        this.total = balcao.getTotal();
        this.subTotal = balcao.getSubTotal();
        this.valorDesconto = balcao.getValorDesconto();
        this.obs = balcao.getObs();
        this.status = balcao.getStatus();
        this.balcaoDet = balcao.getBalcaoDet().stream().map(BalcaoDetDtoOutput::new).collect(Collectors.toList());
        this.balcaoRec = balcao.getBalcaoRec().stream().map(BalcaoRecDtoOutput::new).collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EmpresaDtoOutput getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDtoOutput empresa) {
        this.empresa = empresa;
    }

    public String getUsuarioLogin() {
        return usuarioLogin;
    }

    public void setUsuarioLogin(String usuarioLogin) {
        this.usuarioLogin = usuarioLogin;
    }

    public ClienteDtoOutput getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDtoOutput cliente) {
        this.cliente = cliente;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
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

    public List<BalcaoDetDtoOutput> getBalcaoDet() {
        return balcaoDet;
    }

    public void setBalcaoDet(List<BalcaoDetDtoOutput> balcaoDet) {
        this.balcaoDet = balcaoDet;
    }

    public List<BalcaoRecDtoOutput> getBalcaoRec() {
        return balcaoRec;
    }

    public void setBalcaoRec(List<BalcaoRecDtoOutput> balcaoRec) {
        this.balcaoRec = balcaoRec;
    }

}
