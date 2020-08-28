/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Repository
public interface UsuarioDao extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u where u.status = 'ADMITIDO' and u.id = ?1")
    Usuario getUsuarioAtivo(Integer id);
       
    @Query("select u from Usuario u where u.status = 'ADMITIDO' and u.id = ?1 and u.senha = ?2")
    Usuario getUsuarioAtivo(Integer id, String senha);
}
