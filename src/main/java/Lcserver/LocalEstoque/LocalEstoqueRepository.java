/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.LocalEstoque;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Fabricio
 */
@Repository
public interface LocalEstoqueRepository extends JpaRepository<LocalEstoque, Integer> {

    @Query(name = "select * from localestoque l inner join empresa e on l.id_empresa = e.id where e.id = ?1", nativeQuery = true)
    List<LocalEstoque> getLocalEstoqueListByEmpresaId(Integer idEmpresa);

}
