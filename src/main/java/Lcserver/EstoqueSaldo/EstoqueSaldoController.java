/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.EstoqueSaldo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Fabricio
 */
@RestController
public class EstoqueSaldoController {

    @Autowired
    private EstoqueSaldoRepository estoqueSaldoRepository;

    @GetMapping("/empresas/{idEmpresa}/produtos/{idProduto}/saldoLocal/")
    public List<EstoqueSaldo> listaEstoqueSaldo(@PathVariable("idEmpresa") Integer idEmpresa, @PathVariable("idProduto") Integer idProduto) {
        return estoqueSaldoRepository.getEstoqueSaldoListByEmpresaIdByProdutoId(idEmpresa, idProduto);
    }

    @GetMapping("/empresas/{idEmpresa}/produtos/{idProduto}/saldoProduto/")
    public double saldoProduto(@PathVariable("idEmpresa") Integer idEmpresa, @PathVariable("idProduto") Integer idProduto) {
        return estoqueSaldoRepository.getSaldoProdutoByEmpresaIdByProdutoId(idEmpresa, idProduto);
    }

}
