package com.example.MobileApplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openMainActivity();
                Toast.makeText(Activity2.this, "First Page", Toast.LENGTH_SHORT).show();
            }

        });
    }
        public void openMainActivity(){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

}

