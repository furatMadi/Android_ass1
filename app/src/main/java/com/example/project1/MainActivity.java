package com.example.project1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    public static final String NAME = "NAME";
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private TextView mainTxt;
    private TextView warningTxt;
    private Button startBtn;
    private EditText nameEdtTxt;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mainTxt = findViewById(R.id.mainTxt);
        startBtn = findViewById(R.id.startBtn);
        warningTxt = findViewById(R.id.warningTxt);
        nameEdtTxt = findViewById(R.id.nameEdtTxt);

        setupSharedPrefs();

        startBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(nameEdtTxt.getText().toString().equals(""))
                    warningTxt.setText("Type your name first");
                else {
                    String name = nameEdtTxt.getText().toString();
                    editor.putString(NAME, name);
                    editor.commit();
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);

                }
            }
        });
    }
    private void setupSharedPrefs() {
        prefs= PreferenceManager.getDefaultSharedPreferences(this);
        editor = prefs.edit();
    }
    protected void onPause() {
        super.onPause();
        saveName();
    }
    @Override
    protected void onResume() {
        super.onResume();
        String name = prefs.getString(NAME, "");
        nameEdtTxt.setText(name);
    }
    protected void onDestroy() {
        super.onDestroy();
        resetName();
    }

    private void resetName() {
        editor.remove(NAME);
        editor.apply();

        nameEdtTxt.setText("");
    }
    private void saveName() {
        String name = nameEdtTxt.getText().toString();
        editor.putString(NAME, name);
        editor.apply();
    }

}