package com.mvp.demo.domain.model;

import java.util.List;

public class ResultModel {

    private List<String> list;

    public ResultModel(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return this.list;
    }

}
