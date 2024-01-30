package com.hr.pereless.model;

public class MenuModel {
    String menu_name;
    int menu_img;
    String checkstaus;

    public MenuModel(int menu_img, String menu_name, String checkstaus) {
        this.menu_name = menu_name;
        this.menu_img = menu_img;
        this.checkstaus = checkstaus;
    }

    public String getMenu_name() {
        return menu_name;
    }

    public int getMenu_img() {
        return menu_img;
    }

    public String getCheckstaus() {
        return checkstaus;
    }
}
