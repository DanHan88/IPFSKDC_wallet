/*
 * Decompiled with CFR 0.151.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.security.crypto.password.PasswordEncoder
 *  org.springframework.stereotype.Repository
 */
package com.ipfs.kdc.vo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository(value="loginVO")
public class LoginVO {
    @Autowired
    private PasswordEncoder pwEncoder;
    private String id;
    private String password;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

