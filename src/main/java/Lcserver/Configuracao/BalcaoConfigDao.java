/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Configuracao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Repository
public interface BalcaoConfigDao extends JpaRepository<BalcaoConfig, Integer> {

    @Query(value = "select * from balcaoconfig limit 1", nativeQuery = true)
    BalcaoConfig getBalcaoConfig();

}
