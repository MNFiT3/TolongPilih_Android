package com.missfortune.tolongpilih;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.missfortune.tolongpilih.config.Globals;
import com.missfortune.tolongpilih.services.ServerHandler;
import com.missfortune.tolongpilih.services.Session;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class GroupActivity extends AppCompatActivity {
    Session session;
    EditText userEmail;
    ListView userListView;

    String groupId, token;
    ArrayList<String> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_activity);

        session = new Session(getApplicationContext());

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        groupId = bundle.getString("groupId");
        token = session.userToken();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        userListView = findViewById(R.id.itemListUser);
        userList= new ArrayList<>();

    }

    public void addUser(View v){

        userEmail = findViewById(R.id.newUserEmail);

        JSONObject postData = new JSONObject();
        try {
            postData.put("groupId", groupId);
            postData.put("email", userEmail.getText());

            //TODO: Input validation

            ServerHandler serverHandler = (ServerHandler) new ServerHandler().execute(Globals.API_ENDPOINT + Globals.INVITE_USER, postData.toString(), token);
            JSONObject result = serverHandler.get();

            if(result.getInt("code") >= 300) {
                new Toast(this).makeText(this, result.getString("body"), Toast.LENGTH_LONG).show();
                return;
            }

            new Toast(this).makeText(this, "Successfully add item", Toast.LENGTH_LONG).show();
            userList.add(userEmail.getText().toString());
            userListView();

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void userListView() {
        ArrayAdapter<String> adapterSkill = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, userList);
        userListView.setAdapter(adapterSkill);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
