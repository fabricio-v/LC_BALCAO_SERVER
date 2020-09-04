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
public interface ConfigDao extends JpaRepository<Config, Integer> {

    @Query(name = "select * from config where id_empresa = ?1", nativeQuery = true)
    Config getConfigByIdEmpresa(Integer id_empresa);

}
