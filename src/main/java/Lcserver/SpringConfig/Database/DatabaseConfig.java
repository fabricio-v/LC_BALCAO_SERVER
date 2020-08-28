/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lcserver.SpringConfig.Database;

import SessaoAberta.SessaoAberta;
import Util.Funcoes;
import javax.sql.DataSource;
import static org.apache.tomcat.jni.User.username;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 *
 * @author Fabricio
 */
@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() throws Exception {
        Funcoes.getRedeTxt();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://" + SessaoAberta.getDatabase().getIp() + "/" + SessaoAberta.getDatabase().getDatabase());
        dataSource.setUrl("jdbc:mysql://" + SessaoAberta.getDatabase().getIp() + "/" + SessaoAberta.getDatabase().getDatabase());
        dataSource.setUsername(SessaoAberta.getDatabase().getUsuario());
        dataSource.setPassword(SessaoAberta.getDatabase().getSenha());
        return dataSource;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.MYSQL);

        //adapter.setShowSql(true);
        adapter.setGenerateDdl(false);
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL5Dialect");
        adapter.setPrepareConnection(true);
        return adapter;
    }

}
