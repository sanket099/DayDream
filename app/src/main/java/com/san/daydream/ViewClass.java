package com.san.daydream;

import java.io.InputStream;

public class ViewClass {

    private int id;
    private InputStream img;
    private String name;

    private boolean isSelected;


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public ViewClass(int id, InputStream img, String name, Boolean isSelected) {
        this.id = id;
        this.img = img;
        this.name = name;
        this.isSelected = isSelected;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public InputStream getImg() {
        return img;
    }

    public void setImg(InputStream img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
