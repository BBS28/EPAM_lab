package com.epam.shchehlov.bean;

import java.util.Arrays;

/**
 * Class for temporary storage SQL query and parameters.
 *
 * @author B.Shchehlov.
 */
public class SQLBean {

    private String sqlQuery;
    private Object[] values;

    public String getSqlQuery() {
        return sqlQuery;
    }

    public void setSqlQuery(String sqlQuery) {
        this.sqlQuery = sqlQuery;
    }

    public Object[] getValues() {
        return values;
    }

    public void setValues(Object[] values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return "SQLBean{" +
                "sqlQuery='" + sqlQuery + '\'' +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}
