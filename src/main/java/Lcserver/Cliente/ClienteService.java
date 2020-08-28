/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Cliente;

import Lcserver.Cliente.form.ClienteDebitoForm;
import Lcserver.Empresa.Empresa;
import Lcserver.Empresa.EmpresaRepository;
import SessaoAberta.SessaoAberta;
import Util.Funcoes;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteDao;
    @Autowired
    private EmpresaRepository empresaDao;

    public List<Cliente> getClienteIdCpfCnpjRazaoNomeApelido(String nome) {
        return clienteDao.getClienteDetalhado(nome, nome + "%");
    }

    public Cliente getClienteCartao(String nome) {
        return clienteDao.getClienteCartao(nome);
    }

    public double getDebitoCliente(int idCliente) {
        Double juros = empresaDao.findAll().get(0).getJurosDiario();
        double jurosAux = (juros == null ? 0 : juros);
        List<ClienteDebitoForm> listDebito = clienteDao.getDebitoCliente(idCliente);
        double debito = listDebito.stream().mapToDouble((d) -> {
            double vlrOriginal = d.getValorOrigiral() != null ? d.getValorOrigiral() : 0;
            if (d.getDias() < 0) {
                double desconto = Funcoes.acrescimo(vlrOriginal, jurosAux);
                return ((desconto * (d.getDias() * (-1))) + vlrOriginal);
            } else {
                return vlrOriginal;
            }
        }).sum();

        return debito;
    }

    public boolean isDevendo(int idCliente) {
        if (clienteDao.listaIdReceberAtrasos(idCliente) == null || clienteDao.listaIdReceberAtrasos(idCliente).isEmpty()) {
            return false;
        }
        return true;

    }

}
