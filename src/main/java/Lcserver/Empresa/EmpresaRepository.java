/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Empresa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    @Query(name = "select * from empresa where id = ?1", nativeQuery = true)
    Empresa getEmpresaById(Integer idEmpresa);

}
