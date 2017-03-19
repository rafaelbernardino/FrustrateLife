package com.bernardino.frustratelife.persistence.table;

/**
 * Created by Rafael on 12/03/2017.
 */

public enum TbUser {
    TABLE_NAME("APP_USER"),
    COL_USER("USER"),
    COL_PASS("PASSWORD"),
    ;

    private String name;

    TbUser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
