/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.EstadoCidade;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jordan LC Sistemas
 */
@Repository
public interface CidadesRepository extends JpaRepository<Cidades, Integer> {

    @Query("select c from Cidades c where c.iduf = ?1 order by c.nome")
    Optional<List<Cidades>> getCidadesByIdEstado(String idUf);

}
