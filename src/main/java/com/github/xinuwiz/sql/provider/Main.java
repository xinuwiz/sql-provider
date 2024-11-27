package com.github.xinuwiz.sql.provider;

public class Main {

    public static void main(String[] args) {
        SessionFactory factory = SessionFactory.newDefault(null);
        Session session = factory.open();
        session.execute("");
    }
}
