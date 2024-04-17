package com.example.project1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TestActivity extends AppCompatActivity {
    private Button resultBtn;
    private Button backBtn;
    private Button reset2Btn;
    private RadioButton trueRBtn;
    private RadioButton falseRBtn;
    private RadioGroup rg1;
    private Spinner phSpn;
    private RadioButton arhRBtn;
    private RadioButton bronRBtn;
    private RadioButton lewisRBtn;
    private RadioGroup rg2;
    private EditText ansEdtTxt;
    private CheckBox vinChk;
    private CheckBox soapChk;
    private CheckBox batteriesChk;
    private CheckBox softChk;
    private ImageView tick1;
    private ImageView tick2;
    private ImageView tick3;
    private ImageView tick4;
    private ImageView tick5;
    private ImageView false1;
    private ImageView false2;
    private ImageView false3;
    private ImageView false4;
    private ImageView false5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_test);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        populate();

        String [] phItems = {"0-7" , "0-6" , "7-14" , "8-14"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this , android.R.layout.simple_spinner_item , phItems);
        phSpn.setAdapter(adapter);

        resultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(trueRBtn.isChecked()){
                    tick1.setVisibility(View.VISIBLE);
                }
                if(falseRBtn.isChecked()){
                    false1.setVisibility(View.VISIBLE);
                }
                if(phSpn.getSelectedItem().equals("8-14")){
                    tick2.setVisibility(View.VISIBLE);
                }
                if(!phSpn.getSelectedItem().equals("8-14")){
                    false2.setVisibility(View.VISIBLE);
                }
                if(lewisRBtn.isChecked()){
                    tick3.setVisibility(View.VISIBLE);
                }
                if(!lewisRBtn.isChecked()){
                    false3.setVisibility(View.VISIBLE);
                }
                if(ansEdtTxt.getText().toString().compareToIgnoreCase("na+")==0){
                    tick4.setVisibility(View.VISIBLE);
                }
                if(ansEdtTxt.getText().toString().compareToIgnoreCase("na+")!=0){
                    false4.setVisibility(View.VISIBLE);
                }
                if(vinChk.isChecked() && batteriesChk.isChecked() && softChk.isChecked() && !soapChk.isChecked()){
                    tick5.setVisibility(View.VISIBLE);
                }
                if(!vinChk.isChecked() || !batteriesChk.isChecked() || !softChk.isChecked() || soapChk.isChecked()){
                    false5.setVisibility(View.VISIBLE);
                }

            }
        });

        reset2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tick1.setVisibility(View.INVISIBLE);
                tick2.setVisibility(View.INVISIBLE);
                tick3.setVisibility(View.INVISIBLE);
                tick4.setVisibility(View.INVISIBLE);
                tick5.setVisibility(View.INVISIBLE);
                false1.setVisibility(View.INVISIBLE);
                false2.setVisibility(View.INVISIBLE);
                false3.setVisibility(View.INVISIBLE);
                false4.setVisibility(View.INVISIBLE);
                false5.setVisibility(View.INVISIBLE);
                trueRBtn.setChecked(false);
                falseRBtn.setChecked(false);
                arhRBtn.setChecked(false);
                bronRBtn.setChecked(false);
                lewisRBtn.setChecked(false);
                phSpn.setSelection(0);
                ansEdtTxt.setText("");
                vinChk.setChecked(false);
                softChk.setChecked(false);
                soapChk.setChecked(false);
                batteriesChk.setChecked(false);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this , TableOfContentsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void populate(){
        resultBtn = findViewById(R.id.resultBtn);
        backBtn = findViewById(R.id.backBtn);
        reset2Btn = findViewById(R.id.reset2Btn);
        trueRBtn = findViewById(R.id.trueRBtn);
        falseRBtn = findViewById(R.id.falseRBtn);
        rg1 = findViewById(R.id.rg1);
        arhRBtn = findViewById(R.id.arhRBtn);
        bronRBtn = findViewById(R.id.bronRBtn);
        lewisRBtn = findViewById(R.id.lewisRBtn);
        rg2 = findViewById(R.id.rg2);
        phSpn = findViewById(R.id.phSpn);
        ansEdtTxt = findViewById(R.id.ansEdtTxt);
        vinChk = findViewById(R.id.vinChk);
        soapChk = findViewById(R.id.soapChk);
        batteriesChk = findViewById(R.id.batteriesChk);
        softChk = findViewById(R.id.softChk);
        tick1 = findViewById(R.id.tick1);
        tick2 = findViewById(R.id.tick2);
        tick3 = findViewById(R.id.tick3);
        tick4 = findViewById(R.id.tick4);
        tick5 = findViewById(R.id.tick5);
        false1 = findViewById(R.id.false1);
        false2 = findViewById(R.id.false2);
        false3 = findViewById(R.id.false3);
        false4 = findViewById(R.id.false4);
        false5 = findViewById(R.id.false5);
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveQuizState();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreQuizState();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        resetUIComponents();
        clearPreferences();
    }

    private void saveQuizState() {
        SharedPreferences prefs = getSharedPreferences("QuizPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putBoolean("TrueRBtnSelected", trueRBtn.isChecked());
        editor.putBoolean("FalseRBtnSelected", falseRBtn.isChecked());

        int selectedRg2Id = rg2.getCheckedRadioButtonId();
        editor.putInt("SelectedTheoryId", selectedRg2Id);

        editor.putInt("SelectedPhItemPosition", phSpn.getSelectedItemPosition());

        editor.putString("AnswerText", ansEdtTxt.getText().toString());

        editor.putBoolean("VinChkSelected", vinChk.isChecked());
        editor.putBoolean("SoapChkSelected", soapChk.isChecked());
        editor.putBoolean("BatteriesChkSelected", batteriesChk.isChecked());
        editor.putBoolean("SoftChkSelected", softChk.isChecked());

        editor.apply();
    }
    private void restoreQuizState() {
        SharedPreferences prefs = getSharedPreferences("QuizPrefs", MODE_PRIVATE);

        trueRBtn.setChecked(prefs.getBoolean("TrueRBtnSelected", false));
        falseRBtn.setChecked(prefs.getBoolean("FalseRBtnSelected", false));

        int savedRadioIndex = prefs.getInt("SelectedTheoryId", -1);
        if (savedRadioIndex != -1) {
            RadioButton savedBtn = (RadioButton) rg2.findViewById(savedRadioIndex);
            savedBtn.setChecked(true);
        }

        int spinnerPosition = prefs.getInt("SelectedPhItemPosition", 0); // Default to first item
        phSpn.setSelection(spinnerPosition);

        ansEdtTxt.setText(prefs.getString("AnswerText", ""));

        vinChk.setChecked(prefs.getBoolean("VinChkSelected", false));
        soapChk.setChecked(prefs.getBoolean("SoapChkSelected", false));
        batteriesChk.setChecked(prefs.getBoolean("BatteriesChkSelected", false));
        softChk.setChecked(prefs.getBoolean("SoftChkSelected", false));
    }
    private void resetUIComponents() {
        trueRBtn.setChecked(false);
        falseRBtn.setChecked(false);
        rg1.clearCheck();
        arhRBtn.setChecked(false);
        bronRBtn.setChecked(false);
        lewisRBtn.setChecked(false);
        rg2.clearCheck();
        phSpn.setSelection(0);
        ansEdtTxt.setText("");
        vinChk.setChecked(false);
        soapChk.setChecked(false);
        batteriesChk.setChecked(false);
        softChk.setChecked(false);
    }

    private void clearPreferences() {
        SharedPreferences prefs = getSharedPreferences("QuizPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

}