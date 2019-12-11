package com.missfortune.tolongpilih;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Uncomment one code below to jump to an activity

        startActivity(new Intent(this, LoginActivity.class)); //LoginActivity
        startActivity(new Intent(this, WheelActivity.class)); //WheelActivity
        //startActivity(new Intent(this, ItemActivity.class)); //ItemActivity
        //startActivity(new Intent(this, GroupActivity.class)); //GroupActivity
        //startActivity(new Intent(this, HomeActivity.class)); //HomeActivity

        finish();
    }
}