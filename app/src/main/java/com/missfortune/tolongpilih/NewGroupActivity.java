package com.missfortune.tolongpilih;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.missfortune.tolongpilih.config.Globals;
import com.missfortune.tolongpilih.services.ServerHandler;
import com.missfortune.tolongpilih.services.Session;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class NewGroupActivity extends AppCompatActivity {
    Session session;

    EditText groupName;

    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_group_activity);

        session = new Session(getApplicationContext());

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        token = session.userToken();
    }

    public void createGroup(View v) {
        groupName = findViewById(R.id.txtGroupName);

        JSONObject postData = new JSONObject();
        try {
            postData.put("name", groupName.getText());

            //TODO: Input validation

            ServerHandler serverHandler = (ServerHandler) new ServerHandler().execute(Globals.API_ENDPOINT + Globals.CREATE_GROUP, postData.toString(), token);
            JSONObject result = serverHandler.get();

            if(result.getInt("code") >= 300) {
                new Toast(this).makeText(this, result.getString("body"), Toast.LENGTH_LONG).show();
                return;
            }

            new Toast(this).makeText(this, "Successfully Create Group", Toast.LENGTH_LONG).show();
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

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
