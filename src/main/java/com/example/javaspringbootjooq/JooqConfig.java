package com.example.javaspringbootjooq;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.RenderNameStyle;
import org.jooq.conf.Settings;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class JooqConfig {

    @Bean
    public DSLContext dsl(DataSource dataSource) {
        Settings settings = new Settings()
            .withRenderNameStyle(RenderNameStyle.AS_IS);
        return new DefaultDSLContext(dataSource, SQLDialect.POSTGRES, settings);
    }
}
