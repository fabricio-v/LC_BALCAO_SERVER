/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.Auditoria;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Service
public class AuditoriaControle {

    @Autowired
    private AuditoriaDao auditoriaDao;

    public void erro(Class qualClasse, String qualMetodo, Exception ex) {
        String descricao = ex.getMessage();
        for (StackTraceElement teste : ex.getStackTrace()) {
            descricao += teste.toString() + "\n";
        }
        Auditoria auditoria = new Auditoria();
        auditoria.setDataHora(auditoriaDao.getDataServer());
        auditoria.setOperacao("ERRO");
        auditoria.setLocal(qualClasse.getName());
        auditoria.setDescricao(descricao);
        auditoria.setMetodo(qualMetodo);
        auditoriaDao.save(auditoria);
    }

    public List<Auditoria> saveAll(List<Auditoria> listaAuditoria) {
        return auditoriaDao.saveAll(listaAuditoria);
    }

}
