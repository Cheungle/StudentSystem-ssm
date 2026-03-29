package org.entity;

import lombok.Data;

@Data
public class admin {
    private int id;
    private String password;
    private String username;
    private String role;

    public admin(int id, String password) {
        super();
        this.id = id;
        this.password = password;
    }

    public admin() {

    }
}
