package com.missfortune.tolongpilih;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

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
            postData.put("name", "21313");
            postData.put("address", "23123");
            postData.put("manufacturer", "233123");
            postData.put("location", "3213");
            postData.put("type", "421");
            postData.put("deviceID", "1231");

            ServerHandler serverHandler = (ServerHandler) new ServerHandler().execute("http://10.0.2.2:3001/api/tolongpilih/test", postData.toString());
            String temp = serverHandler.get();

            JSONObject result = new JSONObject(temp);
            String strPostData = result.getString("PostData");

            JSONObject data = new JSONObject(strPostData);
            String name = data.getString("name");

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
