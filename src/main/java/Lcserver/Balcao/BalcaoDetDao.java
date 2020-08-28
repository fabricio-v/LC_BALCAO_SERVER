/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Balcao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Repository
public interface BalcaoDetDao extends JpaRepository<BalcaoDet, Integer> {
    
    @Transactional
    @Modifying
    @Query(value = "delete from BalcaoDet where id_balcao = ?1", nativeQuery = true)
    void deleteIdBalcao(Integer id);

  //  void deleteByIdBalcao(Integer idBalcao);
    
}
