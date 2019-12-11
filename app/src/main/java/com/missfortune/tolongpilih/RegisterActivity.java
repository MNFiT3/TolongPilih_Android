package com.missfortune.tolongpilih;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.missfortune.tolongpilih.config.Globals;
import com.missfortune.tolongpilih.services.ServerHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class RegisterActivity extends AppCompatActivity {
    ServerHandler serverHandler;
    EditText email, username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
    }

    public void registerUser(View v) {
        serverHandler = new ServerHandler();

        email = findViewById(R.id.registerEmail);
        username = findViewById(R.id.registerUsername);
        password = findViewById(R.id.registerPassword);

        JSONObject postData = new JSONObject();
        try {
            postData.put("email", email.getText());
            postData.put("username", username.getText());
            postData.put("password", password.getText());

            ServerHandler serverHandler = (ServerHandler) new ServerHandler().execute(Globals.API_ENDPOINT + Globals.TEST, postData.toString());
//            String result = serverHandler.get();
//
//            if(!result.equalsIgnoreCase("success")){
//                new Toast(this).makeText(this, "Error Occurred", Toast.LENGTH_LONG).show();
//                return;
//            }
//
//            new Toast(this).makeText(this, result, Toast.LENGTH_LONG).show();
//            startActivity(new Intent(this, LoginActivity.class));
//            finish();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void home (View v){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}
