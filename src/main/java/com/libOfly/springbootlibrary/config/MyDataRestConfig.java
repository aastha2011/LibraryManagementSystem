package com.libOfly.springbootlibrary.config;

import com.libOfly.springbootlibrary.entity.Book;
import org.aspectj.apache.bcel.Repository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {


    private String theAllowedOrigins = "http://localhost:3000";

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors){

        HttpMethod[] theUnsupportedActions = {HttpMethod.POST, HttpMethod.PATCH, HttpMethod.DELETE, HttpMethod.PUT};

        config.exposeIdsFor(Book.class);

        disableHttpMethods(Book.class, config, theUnsupportedActions);

        /* Configure Cors Mapping */
        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigins);

    }

    private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions){

        config.getExposureConfiguration().forDomainType(theClass).withItemExposure((metadata, httpMethods)-> httpMethods.disable(theUnsupportedActions)).withCollectionExposure((metadata,httpMethods)-> httpMethods.disable(theUnsupportedActions));

    }
}


//the motive to create this class to make our file read only