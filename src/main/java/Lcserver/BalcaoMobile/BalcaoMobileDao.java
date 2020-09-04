/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.BalcaoMobile;

import java.util.List;
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
public interface BalcaoMobileDao extends JpaRepository<BalcaoMobile, Integer> {

    // @Query(value = "select * from mobile c where c.status = 'ONLINE'", nativeQuery = true)
    //Mobile getMobile(String imei);
    BalcaoMobile findByImeiLike(String imei);

    @Query(value = "select * from Balcaomobile b WHERE b.imei like ?1 and b.id_empresa = ?2", nativeQuery = true)
    BalcaoMobile findByImeiAndIdEmpresa(String imei, Integer idEmpresa);

    @Query(value = "select * from Balcaomobile b WHERE b.id_empresa like ?1", nativeQuery = true)
    public List<BalcaoMobile> findByIdEmpresaLike(String idEmpresa);

    @Query(value = "select count(id) from balcaomobile m where m.status = 'ATIVO'", nativeQuery = true)
    Integer getTotalMobileAtivo();

    @Modifying
    @Transactional
    @Query("update BalcaoMobile m set m.status = ?1 where m.id = ?2")
    void upStatus(String status, Integer idMobile);
}
