/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Usuario;

import Lcserver.BalcaoMobile.BalcaoMobile;
import Lcserver.BalcaoMobile.BalcaoMobileControle;
import Lcserver.Configuracao.BalcaoConfig;
import Lcserver.Configuracao.BalcaoConfigDao;
import Lcserver.Empresa.Empresa;
import Lcserver.Empresa.EmpresaController;
import Lcserver.Empresa.EmpresaService;
import Lcserver.Exception.PermissaoInsuficienteException;
import Lcserver.Exception.NotFoundException;
import Lcserver.TelaPrincipal;
import Lcserver.Usuario.Usuario;
import Lcserver.Usuario.UsuarioControle;
import Lcserver.Usuario.dto.input.UsuarioDtoInput;
import SessaoAberta.SessaoAberta;
import Util.Funcoes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JORDAN QUEIROGA
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UsuarioRestControler {

    @Autowired
    private UsuarioControle usuarioControle;
    @Autowired
    private BalcaoMobileControle mobileControle;
    @Autowired
    private BalcaoConfigDao balcaoConfigDao;
    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/empresas/{idEmpresa}/usuarios/{id}/{imei}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario getUsuario(@PathVariable Integer idEmpresa, @PathVariable Integer id, @PathVariable String imei) {
        TelaPrincipal.TelaPrincipal.setLog("buscou usuario ID: " + id);
        Usuario u = usuarioControle.getUsuarioAtivo(id);
        if (u == null) {
            throw new NotFoundException("Usuário não Encontrado na Base de Dados!");
        }
//        BalcaoConfig balcaoConfig = balcaoConfigDao.getBalcaoConfigById(idEmpresa);
//        System.out.println("BALCAOCONFIG - SERIAL: " + balcaoConfig.getSerial());
//        System.out.println("CNPJ: " + SessaoAberta.getCnpj());
//        SessaoAberta.setQntMobilePermitida(Funcoes.getMobilePermitido(SessaoAberta.getCnpj(), balcaoConfig));
        BalcaoMobile mobile = mobileControle.cadastrarMobile(empresaService.getEmpresaById(idEmpresa), imei, u.getLogin());
        TelaPrincipal.TelaPrincipal.atualizaTabela(mobile.getEmpresa());

        if (!mobile.getStatus().equals("ATIVO")) {
            throw new PermissaoInsuficienteException();
        }
        return u;
    }

    @PostMapping("/empresas/{idEmpresa}/usuarios/autenticacao/{imei}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario autenticacao(@RequestBody UsuarioDtoInput usuarioDtoInput, @PathVariable String imei, @PathVariable Integer idEmpresa) {
        TelaPrincipal.TelaPrincipal.setLog("buscou usuario ID: " + usuarioDtoInput.getId());
        Usuario u = usuarioControle.getUsuarioAtivo(Integer.parseInt(usuarioDtoInput.getId()));
        if (u == null) {
            throw new NotFoundException("Usuário não encontrado na base de dados!");
        } else if (!u.getSenha().equals(usuarioDtoInput.getSenha())) {
            throw new PermissaoInsuficienteException("Senha inválida!");
        }
//        BalcaoConfig balcaoConfig = balcaoConfigDao.getBalcaoConfigById(idEmpresa);
//        System.out.println("BALCAOCONFIG: " + balcaoConfig.getId());
//        SessaoAberta.setQntMobilePermitida(Funcoes.getMobilePermitido(SessaoAberta.getCnpj(), balcaoConfig));
        BalcaoMobile mobile = mobileControle.cadastrarMobile(empresaService.getEmpresaById(idEmpresa), imei, u.getLogin());
        TelaPrincipal.TelaPrincipal.atualizaTabela(mobile.getEmpresa());

        if (!mobile.getStatus().equals("ATIVO")) {
            throw new PermissaoInsuficienteException();
        }
        return u;
    }
}
