package com.example.project1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class MainActivity3 extends AppCompatActivity {

    private Button hclBtn;
    private Button naohBtn;
    private Button hno3Btn;
    private Button nh4ohBtn;
    private Button caoh2Btn;
    private Button h2so4Btn;
    private Button resetBtn;
    private Button continueBtn;
    private Button sumbitBtn;
    private TextView resultTxt;
    private int score;
    private int selection;
    private boolean[] isSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        populate();
        isSelected = new boolean[6];
        hclBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selection < 3 && !isSelected[0]) {
                    isSelected[0] = true;
                    hclBtn.setClickable(false);
                    hclBtn.setBackgroundColor(Color.parseColor("#BA5C37"));
                    selection++;
                }
            }
        });

        h2so4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selection < 3 && !isSelected[1]) {
                    isSelected[1] = true;
                    h2so4Btn.setClickable(false);
                    h2so4Btn.setBackgroundColor(Color.parseColor("#BA5C37"));
                    selection++;
                }
            }
        });

        hno3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selection < 3 && !isSelected[2]) {
                    isSelected[2] = true;
                    hno3Btn.setClickable(false);
                    hno3Btn.setBackgroundColor(Color.parseColor("#BA5C37"));
                    selection++;
                }
            }
        });

        naohBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selection < 3 && !isSelected[3]) {
                    isSelected[3] = true;
                    naohBtn.setClickable(false);
                    naohBtn.setBackgroundColor(Color.parseColor("#BA5C37"));
                    selection++;
                }
            }
        });

        caoh2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selection < 3 && !isSelected[4]) {
                    isSelected[4] = true;
                    caoh2Btn.setClickable(false);
                    caoh2Btn.setBackgroundColor(Color.parseColor("#BA5C37"));
                    selection++;
                }
            }
        });

        nh4ohBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selection < 3 && !isSelected[5]) {
                    isSelected[5] = true;
                    nh4ohBtn.setClickable(false);
                    nh4ohBtn.setBackgroundColor(Color.parseColor("#BA5C37"));
                    selection++;
                }
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Arrays.fill(isSelected, false);
                        selection = 0;
                        resetButtonStates();
                    }
                });

            }
        });

        Intent intent = getIntent();

        sumbitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = 0;

                if (isSelected[0]) {
                    score++;
                    hclBtn.setBackgroundColor(Color.parseColor("#FF9BE49E"));
                }

                if (isSelected[1]) {
                    score++;
                    h2so4Btn.setBackgroundColor(Color.parseColor("#FF9BE49E"));
                }

                if (isSelected[2]) {
                    score++;
                    hno3Btn.setBackgroundColor(Color.parseColor("#FF9BE49E"));
                }

                if (isSelected[3]) {
                    naohBtn.setBackgroundColor(Color.parseColor("#FFED8D8D"));
                }

                if (isSelected[4]) {
                    caoh2Btn.setBackgroundColor(Color.parseColor("#FFED8D8D"));
                }

                if (isSelected[5]) {
                    nh4ohBtn.setBackgroundColor(Color.parseColor("#FFED8D8D"));
                }

                if (score == 3) {
                    resultTxt.setText("Congrats! You Got " + score + "/3");
                } else if (score > 0) {
                    resultTxt.setText("Partial score: " + score + "/3");
                } else {
                    resultTxt.setText("Oops! Maybe try again :(");
                }
                resultTxt.setVisibility(View.VISIBLE);
            }
        });




        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity3.this , TableOfContentsActivity.class);
                startActivity(intent2);
            }
        });

    }

    private void populate(){
        hclBtn = findViewById(R.id.hclBtn);
        naohBtn = findViewById(R.id.naohBtn);
        hno3Btn = findViewById(R.id.hno3Btn);
        nh4ohBtn = findViewById(R.id.nh4ohBtn);
        caoh2Btn = findViewById(R.id.caoh2Btn);
        h2so4Btn = findViewById(R.id.h2so4Btn);
        resetBtn = findViewById(R.id.resetBtn);
        sumbitBtn = findViewById(R.id.submitBtn);
        resultTxt = findViewById(R.id.resultTxt);
        continueBtn = findViewById(R.id.continueBtn);
    }

    private void resetButtonStates() {
        Button[] buttons = {hclBtn, hno3Btn, h2so4Btn, naohBtn, nh4ohBtn, caoh2Btn};
        for (Button btn : buttons) {
            btn.setClickable(true);
            btn.setBackgroundColor(Color.parseColor("#CC8467"));
        }
        resultTxt.setVisibility(View.INVISIBLE);
    }
    //meow
}