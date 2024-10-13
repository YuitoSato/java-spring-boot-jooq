package com.example.javaspringbootjooq.infrastructure;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderNameStyle;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.boot.autoconfigure.jooq.SpringTransactionProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class JooqConfig {

    private final DataSource dataSource;

    public JooqConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public DefaultConfiguration jooqConfiguration() {
        DefaultConfiguration jooqConfiguration = new DefaultConfiguration();

        // トランザクションプロバイダーをSpringのトランザクションマネージャに設定
        jooqConfiguration.set(new SpringTransactionProvider(transactionManager()));

        // DataSourceをTransactionAwareDataSourceProxyでラップして設定
        jooqConfiguration.set(new TransactionAwareDataSourceProxy(dataSource));

        // SQLの名前スタイル設定
        Settings settings = new Settings().withRenderNameStyle(RenderNameStyle.AS_IS);
        jooqConfiguration.setSQLDialect(SQLDialect.POSTGRES);
        jooqConfiguration.setSettings(settings);

        return jooqConfiguration;
    }

    @Bean
    public DSLContext dslContext() {
        return new DefaultDSLContext(jooqConfiguration());
    }
}
