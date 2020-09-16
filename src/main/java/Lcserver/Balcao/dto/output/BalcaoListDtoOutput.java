/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Balcao.dto.output;

import Lcserver.Balcao.Balcao;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.temporal.Temporal;
import java.util.Date;

/**
 *
 * @author Jordan LC Sistemas
 */
public class BalcaoListDtoOutput implements Serializable {

    private Integer id;
    private String usuario;
    private String nomeCliente;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")//, timezone = "GMT-3")
    private Date dataHora;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")//, timezone = "GMT-3")
    private Double total;
    private String descTipo;
    private String descForma;
    private Double desconto;
    private Double valorDesconto;
    private Double arredondamento;
    private Double subTotal;
    private String obs;
    private String status;

    public BalcaoListDtoOutput(Balcao b) {
        this.id = b.getId();
        this.usuario = b.getUsuario().getLogin();
        this.nomeCliente = b.getCliente().getNome();
        this.dataHora = b.getDataHora();
        this.total = b.getTotal();
        this.descTipo = b.getDescTipo();
        this.descForma = b.getDescForma();
        this.desconto = b.getDesconto();
        this.valorDesconto = b.getValorDesconto();
        this.arredondamento = b.getArredondamento();
        this.subTotal = b.getSubTotal();
        this.obs = b.getObs();
        this.status = b.getStatus();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
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

}
