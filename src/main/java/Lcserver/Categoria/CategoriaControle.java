/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Categoria;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Service
public class CategoriaControle {

    @Autowired
    CategoriaDao categoriaDao;

    public List<Categoria> getListaCategoria() {
        return categoriaDao.getListaCategoriaAtivo();
    }

}
