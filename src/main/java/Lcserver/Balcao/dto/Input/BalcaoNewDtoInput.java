/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Balcao.dto.Input;

import Lcserver.Balcao.Balcao;
import Lcserver.Balcao.BalcaoDao;
import Lcserver.Cliente.Cliente;
import Lcserver.Cliente.ClienteRepository;
import Lcserver.Empresa.Empresa;
import Lcserver.Exception.OperacaoInvalidaException;
import Lcserver.Exception.PermissaoInsuficienteException;

import Lcserver.Usuario.Usuario;
import Util.Funcoes;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JORDAN QUEIROGA
 */
public class BalcaoNewDtoInput {

    private Integer id;
    @NotNull(message = "Total não pode ser nulo!")
    private Double total;
    @NotNull(message = "Subtotal não pode ser nulo!")
    private Double subTotal;
    @NotNull(message = "Cliente não pode ser nulo!")
    private Integer idCliente;
    @NotNull(message = "Status não pode ser nulo!")
    private String status;
    private String obs;
    @NotNull(message = "Orçamento não pode ter lista de itens vazia!")
    private List<BalcaoDetNewDtoInput> balcaoDet;
    private List<BalcaoRecNewDtoInput> balcaoRec;

    public BalcaoNewDtoInput() {
    }

    public static Balcao build(Empresa e, Usuario u, BalcaoNewDtoInput balcaoDtoNovo, BalcaoDao balcaoDao) {
        Balcao balcao = new Balcao();
        balcao.setIdBalcaosb(0);
        balcao.setIdOrcamento(0);
        balcao.setDesconto(0.0);
        balcao.setArredondamento(0.000);
        balcao.setDataHora(balcaoDao.getDataServer());
        balcao.setDescTipo("Desconto");
        balcao.setDescForma("%");
        balcao.setValorDesconto(0.0);
        balcao.setEmpresa(new Empresa(1));
        balcao.setId(balcaoDtoNovo.getId());
        balcao.setTotal(balcaoDtoNovo.getTotal());
        balcao.setSubTotal(balcaoDtoNovo.getSubTotal());
        balcao.setEmpresa(new Empresa(e.getId()));
        balcao.setCliente(new Cliente(balcaoDtoNovo.getIdCliente()));
        balcao.setUsuario(new Usuario(u.getId()));
        balcao.setStatus(balcaoDtoNovo.getStatus());
        balcao.setObs(balcaoDtoNovo.getObs());

        AtomicInteger index = new AtomicInteger();
        balcao.setBalcaodet(balcaoDtoNovo.getBalcaoDet().stream()
                .map(det -> BalcaoDetNewDtoInput.build(balcao, det, index.getAndIncrement() + 1))
                .collect(Collectors.toList()));

        if (balcaoDtoNovo.getBalcaoRec() != null && balcao.getStatus().equals("PV")) {
            balcao.setBalcaoRec(balcaoDtoNovo.getBalcaoRec().stream()
                    .map(rec -> BalcaoRecNewDtoInput.build(u, balcao, rec)).collect(Collectors.toList()));
        }

        return balcao;
    }

    public boolean validate(Usuario usuario) {
        if (this.equals("OC") && !usuario.getPermissoes().contains("BALCAO_SALVAR_ATENDIMENTO")) {
            throw new PermissaoInsuficienteException(usuario.getLogin() + ", você não tem permissão para esta operação!");
        } else if (this.total <= 0) {
            throw new OperacaoInvalidaException("Operação Inválida! Total não pode ser menor ou igual a zero!");
        } else if (this.subTotal <= 0) {
            throw new OperacaoInvalidaException("Operação Inválida! Subtotal não pode ser menor ou igual a zero!");
        } else if (idCliente <= 0) {
            throw new OperacaoInvalidaException("Operação Inválida! Cliente com id inválido: " + this.idCliente);
        } else if (balcaoDet.isEmpty()) {
            throw new OperacaoInvalidaException("Operação Inválida! Orçamento não possui itens na lista!");
        }
        double totalDet = Funcoes.arredondarValor(this.balcaoDet.stream().mapToDouble(det -> {
            return det.getSubTotal();
        }).sum(), 3, true);
        if (totalDet != this.subTotal) {
            throw new OperacaoInvalidaException("Operação Inválida! Total dos itens é diferente do total do cabeçalho do balcão");
        }
        if (this.status.equals("PV")) {
//            if (this.balcaoRec==null || this.balcaoRec.isEmpty()) {
//                throw new OperacaoInvalidaException("Operação Inválida! Orçamento não possui a forma de pagamento!");
//            }
//            double totalRec = this.balcaoRec.stream().mapToDouble(rec -> rec.getValor()).sum();
//            if (totalRec <= totalDet) {
//                throw new OperacaoInvalidaException("Operação Inválida! Orçamento possui valor de forma de pagamento inferior que valor total!");
//            }
        }

        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public List<BalcaoDetNewDtoInput> getBalcaoDet() {
        return balcaoDet;
    }

    public void setBalcaoDet(List<BalcaoDetNewDtoInput> balcaoDet) {
        this.balcaoDet = balcaoDet;
    }

    public List<BalcaoRecNewDtoInput> getBalcaoRec() {
        return balcaoRec;
    }

    public void setBalcaoRec(List<BalcaoRecNewDtoInput> balcaoRec) {
        this.balcaoRec = balcaoRec;
    }

}
