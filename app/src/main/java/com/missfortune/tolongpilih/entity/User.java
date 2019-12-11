package com.missfortune.tolongpilih.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    private String id;
    private String email;
    private String username;
    private String role;
    private String token;

    public User() {}

    public User(String id, String email, String username, String role, String token) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.role = role;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", this.id);
            jsonObject.put("email", this.email);
            jsonObject.put("username", this.username);
            jsonObject.put("role", this.role);
            jsonObject.put("token", this.token);

            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
