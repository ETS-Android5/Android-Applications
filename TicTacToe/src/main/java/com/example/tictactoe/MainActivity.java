package com.example.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements onClick {

    Button user, ai, exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.button1);
        ai = findViewById(R.id.button2);
        exit = findViewById(R.id.button3);
    }


    @Override
    public void onCLick(View v) {
        int mode = 0;

        if (user.isPressed()) {
            mode = 1;
        } else if (ai.isPressed()) {
            mode = 2;
        } else if (exit.isPressed()) {
            finish();
            System.exit(0);
        }

        Intent intent = new Intent(this, Field.class);
        intent.putExtra("mode", mode);
        startActivity(intent);
    }
}