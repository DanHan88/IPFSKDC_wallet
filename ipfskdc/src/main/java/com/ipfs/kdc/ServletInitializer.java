/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  org.springframework.boot.builder.SpringApplicationBuilder
 *  org.springframework.boot.web.servlet.support.SpringBootServletInitializer
 */
package com.ipfs.kdc;

import com.ipfs.kdc.IpfskdcApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class ServletInitializer
extends SpringBootServletInitializer {
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(new Class[]{IpfskdcApplication.class});
    }
}

