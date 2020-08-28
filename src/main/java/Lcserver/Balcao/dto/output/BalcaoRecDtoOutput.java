/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Balcao.dto.output;

import Lcserver.Balcao.BalcaoRec;
import Lcserver.Pagamento.dto.Output.PagamentoDtoOutput;

/**
 *
 * @author JORDAN QUEIROGA
 */
public class BalcaoRecDtoOutput {

    private Integer id;
    private PagamentoDtoOutput pagamento;
    private Double valor;
    private String numeroParcelas;

    @Deprecated
    public BalcaoRecDtoOutput() {
    }

    public BalcaoRecDtoOutput(BalcaoRec balcaoRec) {
        this.id = balcaoRec.getId();
        this.pagamento = new PagamentoDtoOutput(balcaoRec.getPagamento());
        this.valor = balcaoRec.getValor();
        this.numeroParcelas = balcaoRec.getNumeroParcelas();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PagamentoDtoOutput getPagamento() {
        return pagamento;
    }

    public void setPagamento(PagamentoDtoOutput pagamento) {
        this.pagamento = pagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(String numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

}
