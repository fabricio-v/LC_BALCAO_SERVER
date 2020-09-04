/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Rest;

import Lcserver.Balcao.Balcao;
import Lcserver.Balcao.BalcaoControle;
import Lcserver.Categoria.CategoriaControle;
import Lcserver.Cliente.Cliente;
import Lcserver.Cliente.ClienteService;
import Lcserver.Configuracao.Config;
import Lcserver.Configuracao.ConfigControle;
import Lcserver.Mensagem.Mensagem;
import Lcserver.BalcaoMobile.BalcaoMobile;
import Lcserver.BalcaoMobile.BalcaoMobileControle;
import Lcserver.Configuracao.BalcaoConfig;
import Lcserver.Configuracao.BalcaoConfigDao;
import Lcserver.Empresa.Empresa;
import Lcserver.Exception.ImpressaoErro;
import Lcserver.Improssora.BalcaoImpressao;
import Lcserver.Improssora.Impressora;
import Lcserver.Produto.Produto;
import Lcserver.Produto.ProdutoControle;
import Lcserver.TelaPrincipal;
import Lcserver.Usuario.Usuario;
import Lcserver.Usuario.UsuarioControle;
import SessaoAberta.SessaoAberta;
import Util.Funcoes;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JORDAN QUEIROGA
 */
@RestController
public class BalcaoService {

    @Autowired
    UsuarioControle usuarioControle;
    @Autowired
    ProdutoControle produtoControle;
    @Autowired
    TelaPrincipal telaPrincipal;
    @Autowired
    CategoriaControle categoriaControle;
    @Autowired
    BalcaoMobileControle mobileControle;
    @Autowired
    ClienteService clienteControle;
    @Autowired
    BalcaoControle balcaoControle;
    @Autowired
    ConfigControle configControle;
    @Autowired
    BalcaoImpressao balcaoImpressao;
    @Autowired
    BalcaoConfigDao balcaoConfigDao;

    @RequestMapping(method = RequestMethod.GET, value = "/empresas/{idEmpresa}/getUsuario/{id}/{imei}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> getUsuario(@PathVariable Empresa empresa, @PathVariable Integer id, @PathVariable String imei) {
        telaPrincipal.setLog("buscou usuario ID: " + id);
        Usuario u = usuarioControle.getUsuarioAtivo(id);
        if (u == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BalcaoConfig balcaoConfig = balcaoConfigDao.getBalcaoConfigById(empresa.getId());
        SessaoAberta.setQntMobilePermitida(Funcoes.getMobilePermitido(SessaoAberta.getCnpj(), balcaoConfig));
        BalcaoMobile mobile = mobileControle.cadastrarMobile(empresa, imei, u.getLogin());
        telaPrincipal.atualizaTabela();
        if (!mobile.getStatus().equals("ATIVO")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(u, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getProdutoDetalhado/{nome}/{descricao}/{cod}/{fabricante}/{referencia}/{imei}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Produto>> getProdutoDetalhado(@PathVariable String nome, @PathVariable String descricao, @PathVariable String cod, @PathVariable String fabricante, @PathVariable String referencia, @PathVariable String imei) {
        telaPrincipal.setLog("getProdutoDetalhado");
        BalcaoMobile mobile = mobileControle.validaAndroid(imei);
        telaPrincipal.atualizaTabela();
        if (!mobile.getStatus().equals("ATIVO")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            List<Produto> listProd = new ArrayList<>();
            listProd = produtoControle.getProdutoCodEanNomeDescRefFab(nome.trim(), descricao.trim(), cod.trim(), fabricante.trim(), referencia.trim());
            if (listProd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(listProd, HttpStatus.OK);
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getProdutoCod/{cod}/{imei}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produto> getProdutoCod(@PathVariable String cod, @PathVariable String imei) {
        telaPrincipal.setLog("getProdutoCod");
        BalcaoMobile mobile = mobileControle.validaAndroid(imei);
        telaPrincipal.atualizaTabela();
        if (!mobile.getStatus().equals("ATIVO")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            List<Produto> listProd = new ArrayList<>();
            listProd = produtoControle.getProdutoIdCodEan(cod);
            if (listProd.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(listProd.get(0), HttpStatus.OK);
            }
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = "/getListaCliente/{nome}/{imei}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cliente>> getListaCliente(@PathVariable String nome, @PathVariable String imei) {
        telaPrincipal.setLog("getListaCliente");
        ArrayList<Cliente> listCliente = (ArrayList<Cliente>) clienteControle.getClienteIdCpfCnpjRazaoNomeApelido(nome);
        if (listCliente.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            BalcaoMobile mobile = mobileControle.validaAndroid(imei);
            telaPrincipal.atualizaTabela();
            if (mobile.getStatus().equals("INATIVO")) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            } else {
                return new ResponseEntity<>(listCliente, HttpStatus.OK);
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getClienteCartao/{cartao}/{imei}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cliente> getClienteCartao(@PathVariable String cartao, @PathVariable String imei) {
        telaPrincipal.setLog("getClienteCartao");
        BalcaoMobile mobile = mobileControle.validaAndroid(imei);
        telaPrincipal.atualizaTabela();
        if (mobile.getStatus().equals("INATIVO")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            Cliente cliente = clienteControle.getClienteCartao(cartao);
            if (cliente == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(cliente, HttpStatus.OK);
            }
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/empresas/{idEmpresa}/pesquisaSimplesBalcaoCodCliente/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Balcao>> pesquisaSimplesBalcaoCodCliente(@PathVariable Integer idEmpresa, @PathVariable String nome) {
        telaPrincipal.setLog("/pesquisaSimplesBalcaoCodCliente: " + nome);
        List<Balcao> list = balcaoControle.getBalcaoListByIdByClienteNomeByCpfCnpjByRazaoAndPF(idEmpresa, nome);
        if (list == null || list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/empresas/{idEmpresa}/getBalcao/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Balcao> getBalcao(@PathVariable Integer idEmpresa, @PathVariable Integer id) {
        telaPrincipal.setLog("/getBalcao: " + id);
        Balcao balcao = balcaoControle.getBalcao(idEmpresa, id);
        if (balcao == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(balcao, HttpStatus.OK);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/salvar/{imei}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mensagem> salvar(@RequestBody Balcao balcao, @PathVariable String imei) {
        telaPrincipal.setLog("/salvar");
        BalcaoMobile mobile = mobileControle.validaAndroid(imei);
        telaPrincipal.atualizaTabela();
        if (mobile.getStatus().equals("INATIVO")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            balcao = balcaoControle.inserir(balcao);
            return new ResponseEntity<>(new Mensagem(String.valueOf(balcao.getId())), HttpStatus.OK);
        }
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/deletar/{id}/{imei}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Mensagem> delete(@PathVariable Integer id, @PathVariable String imei) {
//        telaPrincipal.setLog("/deletar OC: " + id);
//        BalcaoMobile mobile = mobileControle.validaAndroid(imei);
//        telaPrincipal.atualizaTabela();
//        if (mobile.getStatus().equals("INATIVO")) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        } else {
//            balcaoControle.delete(id);
//            return new ResponseEntity<>(new Mensagem("Excluiu"), HttpStatus.OK);
//        }
//    }
    @RequestMapping(method = RequestMethod.POST, value = "/imprimir/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mensagem> imprimir(@RequestBody Impressora i, @PathVariable Integer id) {
        telaPrincipal.setLog("/imprimir");
        try {
            balcaoImpressao.imprimir(id, i);
        } catch (Exception ex) {
            Logger.getLogger(BalcaoService.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(new Mensagem("Não foi Possível Imprimir no(a) " + i.getDescricao() + ", Porta: " + i.getPorta()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Mensagem(""), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/imprimirPaginaTeste", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Mensagem> imprimirPaginaTeste(@RequestBody Impressora i) {
        telaPrincipal.setLog("/imprimirPaginaTeste");
        try {
            balcaoImpressao.imprimirPaginaTeste(i);
        } catch (Exception ex) {
            Logger.getLogger(BalcaoService.class.getName()).log(Level.SEVERE, null, ex);
            //throw new ImpressaoErro("Não foi Possível Imprimir no(a) " + i.getDescricao() + ", Porta: " + i.getPorta(), ex);
            return new ResponseEntity<>(new Mensagem("Não foi Possível Imprimir no(a) " + i.getDescricao() + ", Porta: " + i.getPorta()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new Mensagem(""), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/empresas/{idEmpresa}/getConfig", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Config> getConfig(@PathVariable Integer idEmpresa) {
        telaPrincipal.setLog("/getConfig");
        SessaoAberta.setConfig(configControle.getConfigByIdEmpresa(idEmpresa));
        return new ResponseEntity<>(SessaoAberta.getConfig(), HttpStatus.OK);
    }
}
