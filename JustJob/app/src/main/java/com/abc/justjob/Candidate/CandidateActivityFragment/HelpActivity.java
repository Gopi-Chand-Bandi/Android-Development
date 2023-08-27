package com.abc.justjob.Candidate.CandidateActivityFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.ms.square.android.expandabletextview.ExpandableTextView;

public class HelpActivity extends AppCompatActivity {

    private ExpandableTextView s1,s2,s3,s4;

    private TextView textView1,textView2,textView3,textView4;
    private ImageView imageView1,imageView2,imageView3,imageView4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        textView1 = findViewById(R.id.expandable_text_help1);
        textView2 = findViewById(R.id.expandable_text_help2);
        textView3 = findViewById(R.id.expandable_text_help3);
        textView4 = findViewById(R.id.expandable_text_help4);

        imageView1 = findViewById(R.id.help_button1);
        imageView2 = findViewById(R.id.help_button2);
        imageView3 = findViewById(R.id.help_button3);
        imageView4 = findViewById(R.id.help_button4);

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView1.getVisibility() == View.GONE) {

                    textView1.setVisibility(View.VISIBLE);
                    imageView1.setImageResource(R.drawable.ic_arrow_up);

                } else {

                    textView1.setVisibility(View.GONE);
                    imageView1.setImageResource(R.drawable.ic_arrow_down);

                }
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView2.getVisibility() == View.GONE){

                    textView2.setVisibility(View.VISIBLE);
                    imageView2.setImageResource(R.drawable.ic_arrow_up);

                }else {

                    textView2.setVisibility(View.GONE);
                    imageView2.setImageResource(R.drawable.ic_arrow_down);

                }
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView3.getVisibility() == View.GONE) {

                    textView3.setVisibility(View.VISIBLE);
                    imageView3.setImageResource(R.drawable.ic_arrow_up);

                } else {

                    textView3.setVisibility(View.GONE);
                    imageView3.setImageResource(R.drawable.ic_arrow_down);

                }
            }
        });

        imageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView4.getVisibility() == View.GONE) {

                    textView4.setVisibility(View.VISIBLE);
                    imageView4.setImageResource(R.drawable.ic_arrow_up);

                } else {

                    textView4.setVisibility(View.GONE);
                    imageView4.setImageResource(R.drawable.ic_arrow_down);

                }
            }
        });

    }

}

