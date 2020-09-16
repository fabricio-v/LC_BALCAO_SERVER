/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.EstoqueSaldo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Fabricio
 */
@Repository
public interface EstoqueSaldoRepository extends JpaRepository<EstoqueSaldo, Integer> {

    @Query(value = "select * from estoquesaldo where id_empresa  = ?1 and id_produto = ?2", nativeQuery = true)
    List<EstoqueSaldo> getEstoqueSaldoListByEmpresaIdByProdutoId(Integer idEmpresa, Integer idProduto);

    @Query(value = "select sum(quantidade) from estoquesaldo where id_empresa  = ?1 and id_produto = ?2", nativeQuery = true)
    double getSaldoProdutoByEmpresaIdByProdutoId(Integer idEmpresa, Integer idProduto);

}
