package com.example.luckynumberapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class SecondActivity extends AppCompatActivity {


    TextView tvAnnounce,tvLuckyNum;
    Button btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvAnnounce = findViewById(R.id.tv_announce);
        tvLuckyNum = findViewById(R.id.tv_lucky_num);
        btnShare = findViewById(R.id.btn_share);

        // Receiving the data from from main Activity
        Intent i = getIntent();
        String userName = i.getStringExtra("name");


        // Generating random numbers
        int random_num = generateRandom();
        tvLuckyNum.setText(""+random_num);

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData(userName,random_num);
            }
        });

    }

    public  int generateRandom(){
        Random random = new Random();
        int upper_limit = 1000;
        int randomNumberGenerated = random.nextInt(upper_limit);

        return randomNumberGenerated;
    }

    public void shareData(String userName,int randomNum){

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");


        i.putExtra(Intent.EXTRA_SUBJECT,userName+" got lucky Today");
        i.putExtra(Intent.EXTRA_TEXT,"His Lucky Number is: "+randomNum);
        startActivity(Intent.createChooser(i,"Choose a Platform"));
    }
}