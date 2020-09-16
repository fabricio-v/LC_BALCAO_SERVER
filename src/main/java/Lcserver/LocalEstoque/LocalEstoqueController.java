/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.LocalEstoque;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Fabricio
 */
@RestController
public class LocalEstoqueController {

    @Autowired
    private LocalEstoqueRepository locaisEstoqueRepository;

    @GetMapping("/empresas/{idEmpresa}/locais/")
    public List<LocalEstoque> getLocalEstoqueListByIdEmpresa(@PathVariable("idEmpresa") Integer idEmpresa) {
        return locaisEstoqueRepository.getLocalEstoqueListByEmpresaId(idEmpresa);
    }

}
