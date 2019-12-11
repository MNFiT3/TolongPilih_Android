package com.missfortune.tolongpilih;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.missfortune.tolongpilih.config.Globals;
import com.missfortune.tolongpilih.services.ServerHandler;
import com.missfortune.tolongpilih.services.Session;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class WheelActivity extends AppCompatActivity {
    private static final String FILENAME = "wheel.html";
    WebView webView;
    Button addItem, addMember, leaveGroup;
    String token, groupId;
    Session session;
    JSONObject dataToWeb;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dataToWeb = new JSONObject();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wheel_activity);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView = findViewById(R.id.web_view);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        try{
            groupId = bundle.getString("groupId");
            token = bundle.getString("token");
        }catch (Exception e){
            startActivity(new Intent(this, HomeActivity.class));
        }

        loadItems();

        //https://gist.github.com/wesleyduff/403fc3a24f5a0f4508ef0e5f55a95ae9
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl("file:///android_asset/" + FILENAME);
        webView.setWebViewClient(new WebViewClient(){
            public void onPageFinished(WebView view, String url){
                //Passing data to JS function
                webView.loadUrl("javascript:onLoad('" + dataToWeb.toString()  + "')");
            }
        });

        addItem = (Button)findViewById(R.id.addItem);
        addMember = (Button)findViewById(R.id.addMember);
        leaveGroup = (Button)findViewById(R.id.leaveGroup);

    }

    private void loadItems() {
        JSONObject postData = new JSONObject();
        try {
            postData.put("groupId", groupId);

            ServerHandler serverHandler = (ServerHandler) new ServerHandler().execute(Globals.API_ENDPOINT + Globals.LIST_ITEM, postData.toString(), token);
            JSONObject result = serverHandler.get();

            if(result.getInt("code") >= 300) {
                new Toast(this).makeText(this, result.getString("body"), Toast.LENGTH_LONG).show();
                return;
            }

            if(result.getInt("code") == 401) {
                new Toast(this).makeText(this, "Session Expired", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                return;
            }

            JSONObject jsonObject = new JSONObject(result.getString("body"));
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            JSONObject obj1 = new JSONObject();
            JSONArray arr1 = new JSONArray();
            try {
                for (int i = 0; i < jsonArray.length(); i++){
                    String temp = jsonArray.get(i).toString();
                    obj1.put("label", temp);
                    obj1.put("value", 1);
                    arr1.put(obj1);
                }

                dataToWeb.put("list", arr1);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void item (View v){

        Intent intent = new Intent(getApplicationContext(), ItemActivity.class);
        intent.putExtra("groupId", groupId);
        intent.putExtra("token", token);
        startActivity(intent);
    }

    public void member (View v){

        Intent intent = new Intent(getApplicationContext(), GroupActivity.class);
        intent.putExtra("groupId", groupId);
        intent.putExtra("token", token);
        startActivity(intent);
    }

    public void leave (View v){

        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
        intent.putExtra("groupId", groupId);
        intent.putExtra("token", token);
        startActivity(intent);
    }
}
