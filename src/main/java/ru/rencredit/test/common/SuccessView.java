package ru.rencredit.test.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SuccessView {
    @JsonIgnore
    public static final SuccessView SUCCESS = new SuccessView("success");

    private String result;

    private SuccessView(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }


}
