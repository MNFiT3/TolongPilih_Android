package com.missfortune.tolongpilih;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.missfortune.tolongpilih.config.Globals;

import org.json.JSONException;
import org.json.JSONObject;

public class WheelActivity extends AppCompatActivity {
    private static final String FILENAME = "wheel.html";
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wheel_activity);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView = findViewById(R.id.web_view);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        String groupId = bundle.getString("groupId");
        String token = bundle.getString("token");


        //https://gist.github.com/wesleyduff/403fc3a24f5a0f4508ef0e5f55a95ae9
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl("file:///android_asset/" + FILENAME);
        webView.setWebViewClient(new WebViewClient(){
            public void onPageFinished(WebView view, String url){
                //Passing data to JS function
                webView.loadUrl("javascript:onLoad('" + groupId + "', '" + token + ", '" + Globals.API_ENDPOINT + "')");
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
