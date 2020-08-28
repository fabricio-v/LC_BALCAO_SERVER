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
import Lcserver.Exception.Erro;
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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
@RequestMapping(value = "/clientes",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
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

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Erro handleValidationError(MethodArgumentNotValidException exception) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        String message = "";
        for (int i = 0; i < fieldErrors.size(); i++) {
            message += fieldErrors.get(i).getDefaultMessage() + "\n";
        }

        return Erro.builder()
                .addDetalhe(message)
                .addErroMensagem("")
                .addStatus(HttpStatus.BAD_REQUEST)
                .addErroCause("Dados inválidos")
                .addHttpMethod("")
                .addPath("")
                .build();

    }

    @Transactional(rollbackFor = {Throwable.class, Exception.class})
    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public ClienteDtoOutput salvar(@Valid @RequestBody ClienteNovoDtoInput clienteDtoInput, @RequestHeader String imei) {
        TelaPrincipal.TelaPrincipal.setLogAndValidaImei("/clientes/salvar", imei);
        clienteDtoInput.isValid(usuarioDao, clienteDao);
        Cliente cliente = clienteDao.save(clienteDtoInput.buildCliente(clienteDao));
        return new ClienteDtoOutput(cliente);
    }

    @GetMapping("/{nome}/{imei}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDtoOutput> getClienteByNome(@PathVariable String nome, @PathVariable String imei) {
        TelaPrincipal.TelaPrincipal.setLog("getClienteByNome");
        BalcaoMobile mobile = mobileControle.validaAndroid(imei);
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

    @GetMapping("/cartao/{cartao}/{imei}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente getClienteCartao(@PathVariable String cartao, @PathVariable String imei) {
        TelaPrincipal.TelaPrincipal.setLog("getClienteCartao");
        BalcaoMobile mobile = mobileControle.validaAndroid(imei);
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
