package com.jwzhang.model.user;

public class User {
    private final String name;
    private final String account;

    public String getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    public User(String name, String account) {
        this.name = name;
        this.account = account;
    }
}
