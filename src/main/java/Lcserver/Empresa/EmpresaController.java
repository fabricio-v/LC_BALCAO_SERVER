/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Empresa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Fabricio
 */
@RestController
public class EmpresaController {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/empresas")
    public List<Empresa> getEmpresas() {
        return empresaRepository.findAll();
    }

    @GetMapping("/empresas/{idEmpresa}/logo")
    public byte[] exibirLogo(@PathVariable Integer idEmpresa) {
        return empresaService.getEmpresaById(idEmpresa).getLogo();
    }

}
