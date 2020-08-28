/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Usuario;

import Lcserver.Empresa.Empresa;
import Lcserver.Empresa.EmpresaRepository;
import Lcserver.Exception.CredencialInvalidaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Service
public class UsuarioControle {

    @Autowired
    UsuarioDao usuarioRepository;
    @Autowired
    EmpresaRepository empresaDao;

    public Usuario getUsuarioAtivo(int id) {
        Usuario u = usuarioRepository.getUsuarioAtivo(id);
        if (u != null && u.getEmpresa() == null) {
            u.setEmpresa(empresaDao.findAll().get(0));
        }
        return u;
    }

    @Deprecated
    public Usuario getUsuarioAtivoValida(int id, String senha) {
        Usuario u = usuarioRepository.getUsuarioAtivo(id, senha);
        if (u == null) {
           throw new CredencialInvalidaException("Login ou senha do usuário estão incorretos!");
        }
        if (u != null && u.getEmpresa() == null) {
            u.setEmpresa(empresaDao.findAll().get(0));
        }
        return u;
    }

    public Usuario getUsuarioAtivoValida(Usuario usuario) {
        Usuario u = usuarioRepository.getUsuarioAtivo(usuario.getId(), usuario.getSenha());
        if (u == null) {
           throw new CredencialInvalidaException("Login ou senha do usuário estão incorretos!");
        }
        if (u != null && u.getEmpresa() == null) {
            u.setEmpresa(empresaDao.findAll().get(0));
        }
        return u;
    }

}
