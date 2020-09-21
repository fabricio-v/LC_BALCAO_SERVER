/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Impressora;

import Lcserver.Auditoria.AuditoriaControle;
import Lcserver.Empresa.EmpresaService;
import Lcserver.Exception.ImpressaoErro;
import Lcserver.Rest.BalcaoService;
import Lcserver.TelaPrincipal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JORDAN QUEIROGA
 */
@RestController
@RequestMapping(value = "/impressoras",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ImpressoraRestControler {

    @Autowired
    private BalcaoImpressao balcaoImpressao;
    @Autowired
    private AuditoriaControle auditoriaControle;
    @Autowired
    private EmpresaService empresaService;

    @PostMapping
    @RequestMapping(value = "/empresas/{idEmpresa}/imprimir/balcoes/{id}")
    public Void imprimir(@PathVariable Integer idEmpresa, @RequestBody Impressora i, @PathVariable Integer id) {
        TelaPrincipal.TelaPrincipal.setLog("/imprimir");
        try {
            balcaoImpressao.imprimir(idEmpresa, id, i);
        } catch (Exception ex) {
            Logger.getLogger(BalcaoService.class.getName()).log(Level.SEVERE, null, ex);
            auditoriaControle.erro(ImpressoraRestControler.class, "imprimir", ex, empresaService.getEmpresaById(idEmpresa));
            TelaPrincipal.TelaPrincipal.setErro("Não foi Possível Imprimir no(a) " + i.getDescricao() + ", Porta: " + i.getPorta(), ex);
            throw new ImpressaoErro("Não foi Possível Imprimir no(a) " + i.getDescricao() + ", Porta: " + i.getPorta());
        }
        return null;

    }

    @PostMapping
    @RequestMapping(value = "/empresas/{idEmpresa}/imprimir/paginaTeste")
    public Void imprimirPaginaTeste(@RequestBody Impressora i, @PathVariable Integer idEmpresa) {
        TelaPrincipal.TelaPrincipal.setLog("/imprimirPaginaTeste");
        try {
            balcaoImpressao.imprimirPaginaTeste(i);
        } catch (Exception ex) {
            auditoriaControle.erro(ImpressoraRestControler.class, "imprimirPaginaTeste", ex, empresaService.getEmpresaById(idEmpresa));
            Logger.getLogger(BalcaoService.class.getName()).log(Level.SEVERE, null, ex);
            throw new ImpressaoErro("Não foi Possível Imprimir no(a) " + i.getDescricao() + ", Porta: " + i.getPorta());
        }
        return null;
    }

}
