package com.missfortune.tolongpilih;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.missfortune.tolongpilih.config.Globals;
import com.missfortune.tolongpilih.services.ServerHandler;
import com.missfortune.tolongpilih.services.Session;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {
    Session session;
    ServerHandler serverHandler;
    EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        session = new Session(getApplicationContext());
        serverHandler = new ServerHandler();

        if(session.isLoggedIn()){
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }

    public void loginUser(View v) {


        email = findViewById(R.id.loginEmail);
        password = findViewById(R.id.loginPassword);

        JSONObject postData = new JSONObject();
        try {
            postData.put("email", email.getText());
            postData.put("password", password.getText());

            //TODO: Input validation

            ServerHandler serverHandler = (ServerHandler) new ServerHandler().execute(Globals.API_ENDPOINT + Globals.LOGIN, postData.toString(), "");
            JSONObject result = serverHandler.get();

            if(result.getInt("code") >= 300) {
                new Toast(this).makeText(this, result.getString("body"), Toast.LENGTH_LONG).show();
                return;
            }

            new Toast(this).makeText(this, "Successfully logged in", Toast.LENGTH_LONG).show();

            JSONObject data = new JSONObject(result.getString("body"));
            session.setLoggedIn(true, data.getString("email"), data.getString("token"));
            startActivity(new Intent(this, HomeActivity.class));
            finish();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void register(View v){
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }
}
