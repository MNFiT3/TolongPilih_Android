package com.missfortune.tolongpilih;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GroupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.group_activity);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ListView itemListView = findViewById(R.id.itemListUser);

        final ArrayList<String> userList = new ArrayList<>();

        for(int i = 0; i < 20; i++){
            userList.add("List - " + i);
        }
        ArrayAdapter<String> adapterSkill = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, userList);
        itemListView.setAdapter(adapterSkill);

    }
}
