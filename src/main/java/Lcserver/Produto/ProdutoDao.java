/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Produto;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Repository
public interface ProdutoDao extends JpaRepository<Produto, Integer> {

    //@Query("select p from Produto p where (p.id = ?1 or p.codigo = ?1 or p.codigoBarras = '?1') and p.ativo='1'")  limit 1 
    @Query(value = "select *, (select sum(quantidade) as quantidade from estoquesaldo where id_empresa = ?2 and id_produto = p.id) as estoque from Produto p where (p.id = ?1 or p.codigo = ?1 or p.codigo_barras = ?1 or p.referencia = ?1) and p.ativo='1' order by p.nome limit 50", nativeQuery = true)
    List<Produto> getProdutoByIdByCodBarrasByCod(String id, Integer idEmpresa);

    @Query(value = "select *, (select sum(quantidade) as quantidade from estoquesaldo where id_empresa = ?6 and id_produto = p.id) as estoque from Produto p inner join Fabricante f on p.id_fabricante = f.id where p.nome like ?1 and p.descricao like ?2 and p.codigo like ?3 and f.nome like ?4 and p.referencia like ?5 and p.ativo='1' order by p.nome limit 50", nativeQuery = true)
    List<Produto> getListaProdutoAtivo(String nome, String descricao, String codigo, String fabricante, String referencia, Integer idEmpresa);

    @Query(value = "SELECT datediff(data_promocao,now()) FROM produto p where id = ?1", nativeQuery = true)
    int verificaPromocao(int id);
}
