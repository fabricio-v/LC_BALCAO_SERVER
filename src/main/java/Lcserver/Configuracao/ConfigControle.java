/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Configuracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Service
public class ConfigControle {

    @Autowired
    ConfigDao configDao;

    public Config getConfigByIdEmpresa(Integer idEmpresa) {
        return configDao.getConfigByIdEmpresa(idEmpresa);
    }

}
