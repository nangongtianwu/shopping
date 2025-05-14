package com.longyi.shopping.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GetPath {
    @Autowired
    private ApplicationContext applicationContext;
    public String getPath() throws IOException {
        ResourceLoader resourceLoader = applicationContext;
        Resource resource = resourceLoader.getResource("classpath:");
//        System.out.println(resource.getFile().getAbsolutePath());
        return  resource.getFile().getParentFile().getParentFile().getAbsolutePath();
    }
    public String getProjectPath() throws IOException {
        return getPath()+"\\src\\main\\resources\\static";
    }
}
