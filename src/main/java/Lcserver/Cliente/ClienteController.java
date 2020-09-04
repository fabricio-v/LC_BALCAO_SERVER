/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Cliente;

import Lcserver.BalcaoMobile.BalcaoMobile;
import Lcserver.BalcaoMobile.BalcaoMobileControle;
import Lcserver.Cliente.dto.input.ClienteNovoDtoInput;
import Lcserver.Cliente.dto.output.ClienteDtoOutput;
import Lcserver.Exception.PermissaoInsuficienteException;
import Lcserver.Exception.NotFoundException;
import Lcserver.TelaPrincipal;
import Lcserver.Usuario.UsuarioDao;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JORDAN QUEIROGA
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ClienteController {

    @Autowired
    private BalcaoMobileControle mobileControle;
    @Autowired
    private ClienteService clienteControle;

    @Autowired
    private ClienteRepository clienteDao;

    @Autowired
    private UsuarioDao usuarioDao;

    @Transactional(rollbackFor = {Throwable.class, Exception.class})
    @PostMapping("/empresas/{idEmpresa}/clientes/salvar")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDtoOutput salvar(@PathVariable Integer idEmpresa, @Valid @RequestBody ClienteNovoDtoInput clienteDtoInput, @RequestHeader String imei) {
        TelaPrincipal.TelaPrincipal.setLogAndValidaImei("/clientes/salvar", imei, idEmpresa);
        clienteDtoInput.isValid(usuarioDao, clienteDao);
        Cliente cliente = clienteDao.save(clienteDtoInput.buildCliente(clienteDao));
        return new ClienteDtoOutput(cliente);
    }

    @GetMapping("/empresas/{idEmpresa}/clientes/{nome}/{imei}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDtoOutput> getClienteByNome(@PathVariable Integer idEmpresa, @PathVariable String nome, @PathVariable String imei) {
        TelaPrincipal.TelaPrincipal.setLog("getClienteByNome");
        BalcaoMobile mobile = mobileControle.validaAndroid(imei, idEmpresa);
        TelaPrincipal.TelaPrincipal.atualizaTabela();
        if (!mobile.getStatus().equals("ATIVO")) {
            throw new PermissaoInsuficienteException("Usuário inativo! Ative-o no servidor!");
        }
        List<ClienteDtoOutput> listCliente = clienteControle.getClienteIdCpfCnpjRazaoNomeApelido(nome).stream()
                .map((c) -> new ClienteDtoOutput(c,
                clienteControle.getDebitoCliente(c.getId()),
                clienteControle.isDevendo(c.getId())))
                .collect(Collectors.toList());
        if (listCliente == null) {
            throw new NotFoundException("Cliente não encontrado na base de dados!");
        }
        return listCliente;
    }

    @GetMapping("/empresas/{idEmpresa}/clientes/cartao/{cartao}/{imei}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente getClienteCartao(@PathVariable Integer idEmpresa, @PathVariable String cartao, @PathVariable String imei) {
        TelaPrincipal.TelaPrincipal.setLog("getClienteCartao");
        BalcaoMobile mobile = mobileControle.validaAndroid(imei, idEmpresa);
        TelaPrincipal.TelaPrincipal.atualizaTabela();
        if (!mobile.getStatus().equals("ATIVO")) {
            throw new PermissaoInsuficienteException("Usuário inativo! Ative-o no servidor!");
        }
        Cliente cliente = clienteControle.getClienteCartao(cartao);
        if (cliente == null) {
            throw new NotFoundException("Cliente não encontrado na base de dados!");
        }
        return cliente;
    }

}
