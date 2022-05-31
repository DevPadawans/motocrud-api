package com.devpadawans.motocrudapi.config.querydsl;

import com.devpadawans.motocrudapi.model.Membro;
import org.bitbucket.gt_tech.spring.data.querydsl.value.operators.experimental.QuerydslHttpRequestContextAwareServletFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.devpadawans.motocrudapi.commons.utils.PathUtils.*;
import static org.springframework.core.Ordered.LOWEST_PRECEDENCE;

@Configuration
@Order(LOWEST_PRECEDENCE)
public class QueryDslValueOperatorsConfig {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Bean
    public FilterRegistrationBean<Filter> querydslHttpRequestContextAwareServletFilter() {
        String[] urlPatterns = new String[]{
                SLASH + joinStringURL(RESOURCE_MEMBROS, "*"),
        };

        var bean = new FilterRegistrationBean<>();
        bean.setFilter(new QuerydslHttpRequestContextAwareServletFilter(querydslHttpRequestContextAwareServletFilterMappings()));
        bean.setAsyncSupported(false);
        bean.setEnabled(true);
        bean.setName("querydslHttpRequestContextAwareServletFilter");
        bean.setUrlPatterns(Arrays.asList(urlPatterns));
        bean.setOrder(LOWEST_PRECEDENCE);
        return bean;
    }

    private Map<String, Class<?>> querydslHttpRequestContextAwareServletFilterMappings() {
        Map<String, Class<?>> mappings = new HashMap<>();
        mappings.put(joinStringURL(this.contextPath, RESOURCE_MEMBROS), Membro.class);
        return mappings;
    }

}
