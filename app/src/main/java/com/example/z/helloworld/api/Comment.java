package com.example.z.helloworld.api;

import android.text.format.DateFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Z on 2016/12/15.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Comment implements Serializable {
    Integer Id;
    User author;
    String text;
    Date createDate;
    Date editDate;
    public Integer getId() {
        return Id;
    }
    public void setId(Integer id) {
        Id = id;
    }
    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getEditDate() {
        return editDate;
    }
    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }
    public String getStringCreateDate(){
        String s= DateFormat.format("yyyy-MM-dd hh-mm",createDate).toString();
        return s;
    }


}