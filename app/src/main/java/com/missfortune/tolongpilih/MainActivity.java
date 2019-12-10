package com.missfortune.tolongpilih;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.missfortune.tolongpilih.config.Globals;
import com.missfortune.tolongpilih.services.ServerHandler;
import com.missfortune.tolongpilih.services.Session;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    Session session;
    ServerHandler serverHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serverHandler = new ServerHandler();
        session = new Session(getApplicationContext());


        JSONObject postData = new JSONObject();
        try {
            postData.put("username", "21313");
            postData.put("email", "23123");
            postData.put("password", "233123");

            ServerHandler serverHandler = (ServerHandler) new ServerHandler().execute(Globals.API_ENDPOINT + "/tolongpilih/test", postData.toString());
            String temp = serverHandler.get();

            JSONObject result = new JSONObject(temp);
            String strPostData = result.getString("PostData");

            JSONObject data = new JSONObject(strPostData);
            String name = data.getString("username");

            TextView txt01 = findViewById(R.id.txt01);
            txt01.setText(name);

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
