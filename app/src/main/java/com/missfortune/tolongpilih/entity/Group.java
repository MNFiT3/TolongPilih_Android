package com.missfortune.tolongpilih.entity;

import java.util.ArrayList;

public class Group {
    private String id;
    private String name;
    private ArrayList<User> members;
    private ArrayList<String> items;

    public Group() {}

    public Group(String id, String name, ArrayList<User> members, ArrayList<String> items) {
        this.id = id;
        this.name = name;
        this.members = members;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }

    public ArrayList<String> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", members=" + members +
                ", items=" + items +
                '}';
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
    }
}
