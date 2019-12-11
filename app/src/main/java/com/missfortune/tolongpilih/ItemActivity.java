package com.missfortune.tolongpilih;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_activity);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ListView itemListView = findViewById(R.id.itemListItem);

        final ArrayList<String> itemList = new ArrayList<>();

        for(int i = 0; i < 20; i++){
            itemList.add("List - " + i);
        }
        ArrayAdapter<String> adapterSkill = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, itemList);
        itemListView.setAdapter(adapterSkill);

    }
}
