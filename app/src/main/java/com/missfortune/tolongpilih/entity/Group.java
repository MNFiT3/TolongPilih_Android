package com.missfortune.tolongpilih.entity;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", this.id);
            jsonObject.put("name", this.name);

            JSONArray memberArray = new JSONArray();
            this.members.forEach((n) -> memberArray.put(n));

            JSONArray itemArray = new JSONArray();
            this.items.forEach((n) -> itemArray.put(n));

            jsonObject.put("members", memberArray);
            jsonObject.put("items", itemArray);

            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
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
