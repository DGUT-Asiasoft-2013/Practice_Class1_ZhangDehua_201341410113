package com.example.z.helloworld.api;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Z on 2016/12/13.
 */

public class Article implements Serializable{
    Integer id;
    Date createDate;
    Date editDate;
    User author;
    String title;
    String text;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }



}