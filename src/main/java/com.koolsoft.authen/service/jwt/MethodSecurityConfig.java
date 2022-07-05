package com.koolsoft.authen.service.jwt;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    private final ApplicationContext context;

    @Autowired
    public MethodSecurityConfig(ApplicationContext context) {
        super();
        this.context = context;
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        var handler = new DefaultMethodSecurityExpressionHandler();
        handler.setApplicationContext(context);
        return handler;
    }

}
