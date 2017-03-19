package com.bernardino.frustratelife.persistence.table;

/**
 * Created by Rafael on 14/03/2017.
 */

public enum TbFrustration {
    TABLE_NAME("FRUSTRATION"),
    COL_ID("ID_FRUSTRATION"),
    COL_TITLE("TITLE"),
    COL_DESCRIPTION("DESCRIPTION"),
    COL_WHAT_TO_DO("WHAT_TO_DO"),
    ;

    private String name;


    TbFrustration(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
