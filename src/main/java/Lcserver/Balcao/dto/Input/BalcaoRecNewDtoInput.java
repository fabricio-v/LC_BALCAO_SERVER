/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Balcao.dto.Input;

import Lcserver.Balcao.Balcao;
import Lcserver.Balcao.BalcaoRec;
import Lcserver.Pagamento.Pagamento;
import Lcserver.Usuario.Usuario;

/**
 *
 * @author JORDAN QUEIROGA
 */
public class BalcaoRecNewDtoInput {

    private int idPagamento;
    private Double valor;
    private String numeroParcelas;

    public static BalcaoRec build(Usuario usuario, Balcao balcao, BalcaoRecNewDtoInput balcaoRecDtoInput) {
        BalcaoRec balcaoRec = new BalcaoRec();
        balcaoRec.setBalcao(balcao);
        balcaoRec.setIdEmpresa(balcao.getEmpresa().getId());
        balcaoRec.setIdUsuario(usuario.getId());
        balcaoRec.setNumeroParcelas(balcaoRecDtoInput.getNumeroParcelas());
        balcaoRec.setValor(balcaoRecDtoInput.getValor());
        balcaoRec.setPagamento(new Pagamento(balcaoRecDtoInput.getIdPagamento()));
        return balcaoRec;
    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
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
