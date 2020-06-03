package com.example.eripolinema;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

        // untuk mendapatkan data dari activity sebelumnya, yaitu activity login.
        Bundle extras = getIntent().getExtras();
        if (extras != null)
            resultNama = extras.getString("result_nama");
        txtResultNama.setText(resultNama);
    }

    private void initComponents() {
        txtResultNama = (TextView) findViewById(R.id.txtResultNama);
    }

    TextView txtResultNama;
    String resultNama;
}