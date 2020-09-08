/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Produto;

import Lcserver.BalcaoMobile.BalcaoMobile;
import Lcserver.BalcaoMobile.BalcaoMobileControle;
import Lcserver.Exception.PermissaoInsuficienteException;
import Lcserver.Exception.NotFoundException;
import Lcserver.TelaPrincipal;
import Util.Funcoes;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author JORDAN QUEIROGA
 */
//@ComponentScan()
@RestController
@RequestMapping(value = "/produtos",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ProdutoRestControler {

    @Autowired
    private ProdutoControle produtoControle;

    @Autowired
    private BalcaoMobileControle mobileControle;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Produto getProduto(@PathVariable Integer id) {
        TelaPrincipal.TelaPrincipal.setLog("getProduto");
        Produto produto = produtoControle.findById(id);
        if (produto == null) {
            throw new NotFoundException("Nenhum Produto Encontrado!");
        }
        return produto;
    }

    @GetMapping("empresas/{idEmpresa}/{nome}/{descricao}/{cod}/{fabricante}/{referencia}/{imei}")
    @ResponseStatus(HttpStatus.OK)
    public List<Produto> getProdutoByDetalhado(@PathVariable Integer idEmpresa, @PathVariable String nome, @PathVariable String descricao, @PathVariable String cod, @PathVariable String fabricante, @PathVariable String referencia, @PathVariable String imei) {
        TelaPrincipal.TelaPrincipal.setLog("getProdutoByDetalhado");
        BalcaoMobile mobile = mobileControle.validaAndroid(imei, idEmpresa);
        TelaPrincipal.TelaPrincipal.atualizaTabela(mobile.getEmpresa());
        if (!mobile.getStatus().equals("ATIVO")) {
            throw new PermissaoInsuficienteException("Usuário Inativo! Ative-o no Servidor!");
        }
        List<Produto> listProd = produtoControle.getProdutoCodEanNomeDescRefFab(nome.trim(), descricao.trim(), cod.trim(), fabricante.trim(), referencia.trim());
        if (listProd == null || listProd.size() == 0) {
            throw new NotFoundException("Nenhum Produto Encontrado na Base de Dados!");
        }
        return listProd;
    }

    @GetMapping("/empresas/{idEmpresa}/{cod}/{imei}")
    @ResponseStatus(HttpStatus.OK)
    public Produto getProdutoCod(@PathVariable Integer idEmpresa, @PathVariable String cod, @PathVariable String imei) {
        TelaPrincipal.TelaPrincipal.setLog("getProdutoCod");
        BalcaoMobile mobile = mobileControle.validaAndroid(imei, idEmpresa);
        TelaPrincipal.TelaPrincipal.atualizaTabela(mobile.getEmpresa());
        if (!mobile.getStatus().equals("ATIVO")) {
            throw new PermissaoInsuficienteException("Usuário Inativo! Ative-o no Servidor!");
        }
        return produtoControle.getProdutoIdCodEan2(cod).get(0);
    }

    @GetMapping("/isPromocao/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Boolean getProdutoCod(@PathVariable Integer id) {
        TelaPrincipal.TelaPrincipal.setLog("/isPromocao/" + id);
        return  produtoControle.verificaPromocao(id);
    }

}
