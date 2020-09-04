package Lcserver;

import Lcserver.Auditoria.AuditoriaControle;
import Lcserver.Configuracao.BalcaoConfigDao;
import Lcserver.Empresa.EmpresaRepository;
import Lcserver.BalcaoMobile.BalcaoMobile;
import Lcserver.BalcaoMobile.BalcaoMobileControle;
import Lcserver.Configuracao.BalcaoConfig;
import Lcserver.Configuracao.ConfigDao;
import Lcserver.Empresa.Empresa;
import Lcserver.Empresa.EmpresaController;
import Lcserver.Mensagens.MsgTelaErro;
import Lcserver.SpringConfig.SpringConfig;
import SessaoAberta.SessaoAberta;
import Util.Funcoes;
import java.util.ArrayList;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@EnableAutoConfiguration(exclude = RepositoryRestMvcAutoConfiguration.class)
@Import({SpringConfig.class})
@SpringBootApplication
public class ApiwebApplication {

    public static AuditoriaControle auditoriaControle;

    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(ApiwebApplication.class).headless(false).run(args);
        TelaPrincipal tela = context.getBean(TelaPrincipal.class);

        try {
            BalcaoConfigDao gfDao = context.getBean(BalcaoConfigDao.class);
            tela.abriTela(tela);
            EmpresaController ec = context.getBean(EmpresaController.class);
            tela.atualizaEmpresas((ArrayList<Empresa>) ec.getEmpresas());
            BalcaoMobileControle mc = context.getBean(BalcaoMobileControle.class);
            tela.atualizaTabela((ArrayList<BalcaoMobile>) mc.getListMobile());
            SessaoAberta.setQntMobilePermitida(Funcoes.getMobilePermitido(tela.listEmpresa.get(0).getCnpj(), gfDao.getBalcaoConfigById(1)));
            SessaoAberta.setCnpj(tela.listEmpresa.get(0).getCnpj());
//            ConfigDao configDao = context.getBean(ConfigDao.class);
//            SessaoAberta.setQntMobilePermitida(Funcoes.getMobilePermitido(SessaoAberta.getCnpj(), gfDao.getBalcaoConfigById(Integer.SIZE)));
//            SessaoAberta.setConfig(configDao.findAll().get(0));
        } catch (Exception ex) {
            ex.printStackTrace();
            tela.setErro("main", ex);
            new MsgTelaErro(null, true, "Erro Inicializar", ex, ApiwebApplication.class, "main").setVisible(true);
        }
    }
}
