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

            //TODO: Input validation

            ServerHandler serverHandler = (ServerHandler) new ServerHandler().execute(Globals.API_ENDPOINT + Globals.REGISTER_USER, postData.toString());
            JSONObject result = serverHandler.get();

            if(result.getInt("code") >= 300) {
                new Toast(this).makeText(this, result.getString("body"), Toast.LENGTH_LONG).show();
                return;
            }

            new Toast(this).makeText(this, "Successfully register", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, LoginActivity.class));
            finish();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void home (View v){
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

}
