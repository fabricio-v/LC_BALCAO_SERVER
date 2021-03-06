/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Auditoria;

import Lcserver.BalcaoMobile.BalcaoMobile;
import Lcserver.BalcaoMobile.BalcaoMobileControle;
import Lcserver.Empresa.EmpresaService;
import Lcserver.Exception.NotFoundException;
import Lcserver.TelaPrincipal;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class AuditoriaRestControle {

    @Autowired
    private AuditoriaControle auditoriaControle;
    @Autowired
    private BalcaoMobileControle mobileControle;
    @Autowired
    private EmpresaService empresaService;

    @PostMapping("/auditorias")
    @ResponseStatus(HttpStatus.OK)
    public Void exportarAlditoria(@RequestBody ArrayList<Auditoria> list) {
        for (Auditoria a : list) {
            a.setId(null);
        }
        TelaPrincipal.TelaPrincipal.setLog("Importando Auditoria...");
        auditoriaControle.saveAll(list);
        return null;
    }

    @PostMapping(value = "/empresas/{idEmpresa}/auditorias/log/{imei}")
    @ResponseStatus(HttpStatus.OK)
    public Void setAuditoriaLog(@RequestBody ArrayList<Auditoria> list, @PathVariable String imei, @PathVariable Integer idEmpresa) {
        BalcaoMobile mobile = mobileControle.getMobile(imei, idEmpresa);
        TelaPrincipal.TelaPrincipal.setLogAuditoria(mobile, list, empresaService.getEmpresaById(idEmpresa));
        return null;

    }

}
