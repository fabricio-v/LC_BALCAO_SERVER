/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.EstadoCidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jordan LC Sistemas
 */
@Repository
public interface EstadosRepository extends JpaRepository<Estados, Integer> {

    @Query("select e.nome from Estados e where e.uf =?1")
    String getNomeEstadoByUf(String uf);

}
