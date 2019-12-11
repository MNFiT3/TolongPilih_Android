package com.missfortune.tolongpilih;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class HomeActivity extends AppCompatActivity {
    Session session;

    ListView groupListView;

    ArrayList<String> groupList;
    String token;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        session = new Session(getApplicationContext());

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        token = session.userToken();
        groupListView = findViewById(R.id.groupListView);
        groupList= new ArrayList<>();

        groupListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String data[] = groupListView.getItemAtPosition(position).toString().split("::");

                Intent intent = new Intent(getApplicationContext(), WheelActivity.class);
                intent.putExtra("groupId", data[0]);
                intent.putExtra("token", token);
                startActivity(intent);
            }
        });
        loadGroup();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void loadGroup(){
        JSONObject postData = new JSONObject();
        try {

            ServerHandler serverHandler = (ServerHandler) new ServerHandler().execute(Globals.API_ENDPOINT + Globals.LIST_GROUP, postData.toString(), token);
            JSONObject result = serverHandler.get();

            if(result.getInt("code") >= 300) {
                new Toast(this).makeText(this, result.getString("body"), Toast.LENGTH_LONG).show();
                return;
            }

            JSONArray array = new JSONArray(result.getString("body"));

            for(int i = 0; i < array.length(); i++){
                JSONObject jsonObject = (JSONObject) array.get(i);
                JSONObject groupObject = new JSONObject(jsonObject.getString("group"));
                String name = groupObject.getString("id") + "::" + groupObject.getString("name");
                groupList.add(name);
            }

            groupListView();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void groupListView() {
        ArrayAdapter<String> adapterSkill = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, groupList);
        groupListView.setAdapter(adapterSkill);
    }

    @Override
    public boolean onSupportNavigateUp(){
        new Toast(this).makeText(this, "Logged Out", Toast.LENGTH_LONG).show();
        session.setLoggedIn(false, "", "");
        startActivity(new Intent(this, LoginActivity.class));
        finish();
        return true;
    }

    public void createNewGroup(View v){
        startActivity(new Intent(this, NewGroupActivity.class));
        finish();
    }
}
