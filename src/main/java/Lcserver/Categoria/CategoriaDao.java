/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Categoria;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Repository
public interface CategoriaDao extends JpaRepository<Categoria, Integer> {

    @Query(value = "select * from Categoria where ativo = '1' order by nome", nativeQuery = true)
    List<Categoria> getListaCategoriaAtivo();

}
