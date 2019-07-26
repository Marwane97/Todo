package com.example.todo;

import java.util.Date;

public class Model {
    private String title;
    private String today;
    private String detail;
    private int id;
    public Model(String title, String taday, String detail) {
        this.title = title;
        this.today = taday;
        this.detail = detail;
    }

    public Model() { }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
