/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  org.springframework.boot.SpringApplication
 *  org.springframework.boot.autoconfigure.SpringBootApplication
 */
package com.ipfs.kdc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.ipfs.kdc"})
public class IpfskdcApplication {
    public static void main(String[] args) {
        SpringApplication.run(IpfskdcApplication.class, (String[])args);
    }
}

