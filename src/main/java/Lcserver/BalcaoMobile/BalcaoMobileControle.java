/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.BalcaoMobile;

import Lcserver.Configuracao.BalcaoConfig;
import Lcserver.Configuracao.BalcaoConfigDao;
import Lcserver.Empresa.Empresa;
import Lcserver.Exception.PermissaoInsuficienteException;
import Lcserver.Exception.NotFoundException;
import SessaoAberta.SessaoAberta;
import Util.Funcoes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author JORDAN QUEIROGA
 */
@Service
public class BalcaoMobileControle {

    @Autowired
    private BalcaoMobileDao mobileDao;
    @Autowired
    private BalcaoConfigDao balcaoConfigDao;

    public BalcaoMobile validaAndroid(String imei, Integer idEmpresa) {

        BalcaoMobile mobile = mobileDao.findByImeiAndIdEmpresa(imei, idEmpresa);
        if (mobile == null) {
            throw new PermissaoInsuficienteException("Dispositivo não encontrado na base de dados, cadastre-o no servidor!");
        }
        if (mobile.getStatus().equals("ATIVO")) {
            int numOnline = mobileDao.getTotalMobileAtivo(mobile.getEmpresa().getId());
            if (mobile.getStatus().equals("ATIVO")) {
                if (SessaoAberta.getQntMobilePermitida() == 0 || numOnline > SessaoAberta.getQntMobilePermitida()) {
                    mobile.setStatus("INATIVO");
                    mobileDao.upStatus(mobile.getStatus(), mobile.getId());
                }
            }
        } else if (!mobile.getStatus().equals("INATIVO")) {
            mobile.setStatus("INATIVO");
            mobileDao.upStatus(mobile.getStatus(), mobile.getId());
        }
        return mobile;
    }

    public BalcaoMobile cadastrarMobile(Empresa empresa, String imei, String usuario) {
//        SessaoAberta.setQntMobilePermitida(Funcoes.getMobilePermitido(SessaoAberta.getCnpj(), balcaoConfigDao.getBalcaoConfigById(empresa.getId())));
        BalcaoMobile mobile = mobileDao.findByImeiAndIdEmpresa(imei, empresa.getId());
        if (mobile == null) {
            mobile = new BalcaoMobile();
            mobile.setEmpresa(empresa);
            mobile.setImei(imei);
            mobile.setUsuario(usuario);
            mobile.setStatus("INATIVO");
        } else if (!mobile.getStatus().equals("INATIVO")) {
            int numAtivo = mobileDao.getTotalMobileAtivo(empresa.getId());
//            SessaoAberta.setQntMobilePermitida(Funcoes.getMobilePermitido(SessaoAberta.getCnpj(), balcaoConfigDao.getBalcaoConfigById(empresa.getId())));
            System.out.println("---------------------------------------");
            System.out.println("Empresa: " + empresa.getId());
            System.out.println(numAtivo);
            System.out.println(SessaoAberta.getQntMobilePermitida());
            System.out.println("---------------------------------------");
            if (numAtivo > SessaoAberta.getQntMobilePermitida()) {
//                mobile.setStatus("INATIVO");
            }
        }
        mobile.setUsuario(usuario);
        mobileDao.save(mobile);
        return mobile;
    }

    public void cadastrarMobile(BalcaoMobile mobile) {
        mobileDao.save(mobile);
    }

    public void removerMobile(BalcaoMobile balcaoMobile) {
        mobileDao.delete(balcaoMobile);
    }

    public List<BalcaoMobile> getListMobile() {
        return mobileDao.findAll();
    }

    public List<BalcaoMobile> getListMobileByIdEmpresa(String idEmpresa) {
        return mobileDao.findByIdEmpresaLike(idEmpresa);
    }

    public BalcaoMobile getMobile(String imei) {
        BalcaoMobile balcaoMobile = mobileDao.findByImeiLike(imei);
        if (balcaoMobile == null) {
            throw new NotFoundException("Dispositivo Não Encontrado na Base de Dados!");
        }
        return balcaoMobile;
    }

    public int getTotalMobileAtivo(Integer idEmpresa) {
        return mobileDao.getTotalMobileAtivo(idEmpresa);
    }

}
