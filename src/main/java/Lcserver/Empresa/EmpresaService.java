/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Empresa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Fabricio
 */
@Service
public class EmpresaService {

    @Autowired
    private EmpresaController empresaController;

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Empresa> getListEmpresas() {
        return empresaController.getEmpresas();
    }

    public Empresa getEmpresaById(Integer idEmpresa) {
        return empresaRepository.getEmpresaById(idEmpresa);
    }

}
