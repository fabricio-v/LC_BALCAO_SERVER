/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.EstoqueSaldo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Fabricio
 */
@Service
public class EstoqueSaldoService {

    @Autowired
    private EstoqueSaldoRepository estoqueSaldoRepository;

    public List<EstoqueSaldo> findByIdEmpresaByIdProduto(Integer idEmpresa, Integer idProduto) {
        return estoqueSaldoRepository.getEstoqueSaldoListByEmpresaIdByProdutoId(idEmpresa, idProduto);
    }

    public double getSaldoProdutoByEmpresaIdByProdutoId(Integer idEmpresa, Integer idProduto) {
        return estoqueSaldoRepository.getSaldoProdutoByEmpresaIdByProdutoId(idEmpresa, idProduto);
    }

}
