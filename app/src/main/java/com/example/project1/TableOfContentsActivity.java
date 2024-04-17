package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TableOfContentsActivity extends AppCompatActivity {
    private ListView contentsLstV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        contentsLstV = findViewById(R.id.contentsLstV);
        String [] contents = {"Introduction", "Theories Of Acids And Bases", "Uses Of Acids And Bases" , "Test Yourself"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contents);
        contentsLstV.setAdapter(adapter);

        contentsLstV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String clickedItem = (String) parent.getItemAtPosition(position);
                switch (clickedItem) {
                    case "Introduction":
                        Intent introIntent = new Intent(TableOfContentsActivity.this, IntroActivity.class);
                        startActivity(introIntent);
                        break;
                    case "Theories Of Acids And Bases":
                        Intent theoIntent = new Intent(TableOfContentsActivity.this, TheoryActivity.class);
                        startActivity(theoIntent);
                        break;
                    case "Uses Of Acids And Bases":
                        Intent usesIntent = new Intent(TableOfContentsActivity.this, UsesActivity.class);
                        startActivity(usesIntent);
                        break;
                    case "Test Yourself":
                        Intent testIntent = new Intent(TableOfContentsActivity.this, TestActivity.class);
                        startActivity(testIntent);
                        break;
                    default:
                        Intent defIntent = new Intent(TableOfContentsActivity.this , TableOfContentsActivity.class);
                        startActivity(defIntent);
                        break;
                }
            }
        });
    }
}