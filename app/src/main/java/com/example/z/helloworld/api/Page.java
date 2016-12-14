package com.example.z.helloworld.api;

/**
 * Created by Z on 2016/12/13.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Page<T> {
    List<T> content;
    Integer number;
    public List<T> getContent() {
        return content;
    }
    public void setContent(List<T> content) {
        this.content = content;
    }
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }


}
