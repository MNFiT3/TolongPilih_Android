package com.missfortune.tolongpilih;

import android.content.Intent;
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

public class ItemActivity extends AppCompatActivity {
    Session session;
    EditText itemName;
    ListView itemListView;

    String groupId, token;
    ArrayList<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_activity);



        itemListView = findViewById(R.id.itemListItem);
        itemList = new ArrayList<>();

        session = new Session(getApplicationContext());

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;

        groupId = bundle.getString("groupId");
        token = session.userToken();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        itemListView();
    }

    private void itemListView() {
        ArrayAdapter<String> adapterSkill = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, itemList);
        itemListView.setAdapter(adapterSkill);
    }

    public void addItem(View v){
        itemName = findViewById(R.id.txtNewItem);

        JSONObject postData = new JSONObject();
        try {
            postData.put("groupId", groupId);
            postData.put("item", itemName.getText());

            //TODO: Input validation

            ServerHandler serverHandler = (ServerHandler) new ServerHandler().execute(Globals.API_ENDPOINT + Globals.ADD_ITEM, postData.toString(), token);
            JSONObject result = serverHandler.get();

            if(result.getInt("code") >= 300) {
                new Toast(this).makeText(this, result.getString("body"), Toast.LENGTH_LONG).show();
                return;
            }

            new Toast(this).makeText(this, "Successfully add item", Toast.LENGTH_LONG).show();
            itemList.add(itemName.getText().toString());
            itemListView();

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
