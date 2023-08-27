package com.abc.justjob.Candidate.CandidateActivityFragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.abc.justjob.NetworkError.NetworkChangeListener;
import com.abc.justjob.R;
import com.ms.square.android.expandabletextview.ExpandableTextView;

public class Interview_tips extends AppCompatActivity {

    private NetworkChangeListener networkChangeListener=new NetworkChangeListener();

    private ExpandableTextView qa1,qa2,qa3,qa4,qa5,qa6,qa7,qa8,qa9,qa10,qa11,qa12,qa13,qa14,qa15,qa16,qa17,qa18;

    private TextView textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,
                     textView10,textView11,textView12,textView13,textView14,textView15,textView16,textView17,textView18;
    private ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,
                      imageView10,imageView11,imageView12,imageView13,imageView14,imageView15,imageView16,imageView17,imageView18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interview_tips);

        textView1 = findViewById(R.id.expandable_text_tip1);
        textView2 = findViewById(R.id.expandable_text_tip2);
        textView3 = findViewById(R.id.expandable_text_tip3);
        textView4 = findViewById(R.id.expandable_text_tip4);
        textView5 = findViewById(R.id.expandable_text_tip5);
        textView6 = findViewById(R.id.expandable_text_tip6);
        textView7 = findViewById(R.id.expandable_text_tip7);
        textView8 = findViewById(R.id.expandable_text_tip8);
        textView9 = findViewById(R.id.expandable_text_tip9);
        textView10 = findViewById(R.id.expandable_text_tip10);
        textView11 = findViewById(R.id.expandable_text_tip11);
        textView12 = findViewById(R.id.expandable_text_tip12);
        textView13 = findViewById(R.id.expandable_text_tip13);
        textView14 = findViewById(R.id.expandable_text_tip14);
        textView15 = findViewById(R.id.expandable_text_tip15);
        textView16 = findViewById(R.id.expandable_text_tip16);
        textView17 = findViewById(R.id.expandable_text_tip17);
        textView18 = findViewById(R.id.expandable_text_tip18);

        imageView1 = findViewById(R.id.Interview_button1);
        imageView2 = findViewById(R.id.Interview_button2);
        imageView3 = findViewById(R.id.Interview_button3);
        imageView4 = findViewById(R.id.Interview_button4);
        imageView5 = findViewById(R.id.Interview_button5);
        imageView6 = findViewById(R.id.Interview_button6);
        imageView7 = findViewById(R.id.Interview_button7);
        imageView8 = findViewById(R.id.Interview_button8);
        imageView9 = findViewById(R.id.Interview_button9);
        imageView10 = findViewById(R.id.Interview_button10);
        imageView11 = findViewById(R.id.Interview_button11);
        imageView12 = findViewById(R.id.Interview_button12);
        imageView13 = findViewById(R.id.Interview_button13);
        imageView14 = findViewById(R.id.Interview_button14);
        imageView15 = findViewById(R.id.Interview_button15);
        imageView16 = findViewById(R.id.Interview_button16);
        imageView17 = findViewById(R.id.Interview_button17);
        imageView18 = findViewById(R.id.Interview_button18);


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

        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView5.getVisibility() == View.GONE) {

                    textView5.setVisibility(View.VISIBLE);
                    imageView5.setImageResource(R.drawable.ic_arrow_up);

                } else {

                    textView5.setVisibility(View.GONE);
                    imageView5.setImageResource(R.drawable.ic_arrow_down);

                }
            }
        });

        imageView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView6.getVisibility() == View.GONE) {

                    textView6.setVisibility(View.VISIBLE);
                    imageView6.setImageResource(R.drawable.ic_arrow_up);

                } else {

                    textView6.setVisibility(View.GONE);
                    imageView6.setImageResource(R.drawable.ic_arrow_down);

                }
            }
        });

        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView7.getVisibility() == View.GONE) {

                    textView7.setVisibility(View.VISIBLE);
                    imageView7.setImageResource(R.drawable.ic_arrow_up);

                } else {

                    textView7.setVisibility(View.GONE);
                    imageView7.setImageResource(R.drawable.ic_arrow_down);

                }
            }
        });

        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView8.getVisibility() == View.GONE) {

                    textView8.setVisibility(View.VISIBLE);
                    imageView8.setImageResource(R.drawable.ic_arrow_up);

                } else {

                    textView8.setVisibility(View.GONE);
                    imageView8.setImageResource(R.drawable.ic_arrow_down);

                }
            }
        });

        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView9.getVisibility() == View.GONE) {

                    textView9.setVisibility(View.VISIBLE);
                    imageView9.setImageResource(R.drawable.ic_arrow_up);

                } else {

                    textView9.setVisibility(View.GONE);
                    imageView9.setImageResource(R.drawable.ic_arrow_down);

                }
            }
        });

        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView10.getVisibility() == View.GONE) {

                    textView10.setVisibility(View.VISIBLE);
                    imageView10.setImageResource(R.drawable.ic_arrow_up);

                } else {

                    textView10.setVisibility(View.GONE);
                    imageView10.setImageResource(R.drawable.ic_arrow_down);

                }
            }
        });

        imageView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView11.getVisibility() == View.GONE){

                    textView11.setVisibility(View.VISIBLE);
                    imageView11.setImageResource(R.drawable.ic_arrow_up);

                }else {

                    textView11.setVisibility(View.GONE);
                    imageView11.setImageResource(R.drawable.ic_arrow_down);

                }
            }
        });

        imageView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView12.getVisibility() == View.GONE) {

                    textView12.setVisibility(View.VISIBLE);
                    imageView12.setImageResource(R.drawable.ic_arrow_up);

                } else {

                    textView12.setVisibility(View.GONE);
                    imageView12.setImageResource(R.drawable.ic_arrow_down);

                }
            }
        });

        imageView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView13.getVisibility() == View.GONE) {

                    textView13.setVisibility(View.VISIBLE);
                    imageView13.setImageResource(R.drawable.ic_arrow_up);

                } else {

                    textView13.setVisibility(View.GONE);
                    imageView13.setImageResource(R.drawable.ic_arrow_down);

                }
            }
        });

        imageView14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView14.getVisibility() == View.GONE) {

                    textView14.setVisibility(View.VISIBLE);
                    imageView14.setImageResource(R.drawable.ic_arrow_up);

                } else {

                    textView14.setVisibility(View.GONE);
                    imageView14.setImageResource(R.drawable.ic_arrow_down);

                }
            }
        });

        imageView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView15.getVisibility() == View.GONE) {

                    textView15.setVisibility(View.VISIBLE);
                    imageView15.setImageResource(R.drawable.ic_arrow_up);

                } else {

                    textView15.setVisibility(View.GONE);
                    imageView15.setImageResource(R.drawable.ic_arrow_down);

                }
            }
        });

        imageView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView16.getVisibility() == View.GONE) {

                    textView16.setVisibility(View.VISIBLE);
                    imageView16.setImageResource(R.drawable.ic_arrow_up);

                } else {

                    textView16.setVisibility(View.GONE);
                    imageView16.setImageResource(R.drawable.ic_arrow_down);

                }
            }
        });

        imageView17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView17.getVisibility() == View.GONE) {

                    textView17.setVisibility(View.VISIBLE);
                    imageView17.setImageResource(R.drawable.ic_arrow_up);

                } else {

                    textView17.setVisibility(View.GONE);
                    imageView17.setImageResource(R.drawable.ic_arrow_down);

                }
            }
        });

        imageView18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView18.getVisibility() == View.GONE) {

                    textView18.setVisibility(View.VISIBLE);
                    imageView18.setImageResource(R.drawable.ic_arrow_up);

                } else {

                    textView18.setVisibility(View.GONE);
                    imageView18.setImageResource(R.drawable.ic_arrow_down);

                }
            }
        });

        // sample code snippet to set the text content on the ExpandableTextView
//        qa1 = (ExpandableTextView) findViewById(R.id.expand_tv_qa1);
//        qa2 = (ExpandableTextView) findViewById(R.id.expand_tv_qa2);
//        qa3 = (ExpandableTextView) findViewById(R.id.expand_tv_qa3);
//        qa4 = (ExpandableTextView) findViewById(R.id.expand_tv_qa4);
//        qa5 = (ExpandableTextView) findViewById(R.id.expand_tv_qa5);
//        qa6 = (ExpandableTextView) findViewById(R.id.expand_tv_qa6);
//        qa7 = (ExpandableTextView) findViewById(R.id.expand_tv_qa7);
//        qa8 = (ExpandableTextView) findViewById(R.id.expand_tv_qa8);
//        qa9 = (ExpandableTextView) findViewById(R.id.expand_tv_qa9);
//        qa10 = (ExpandableTextView) findViewById(R.id.expand_tv_qa10);
//        qa11 = (ExpandableTextView) findViewById(R.id.expand_tv_qa11);
//        qa12 = (ExpandableTextView) findViewById(R.id.expand_tv_qa12);
//        qa13 = (ExpandableTextView) findViewById(R.id.expand_tv_qa13);
//        qa14 = (ExpandableTextView) findViewById(R.id.expand_tv_qa14);
//        qa15 = (ExpandableTextView) findViewById(R.id.expand_tv_qa15);
//        qa16 = (ExpandableTextView) findViewById(R.id.expand_tv_qa16);
//        qa17 = (ExpandableTextView) findViewById(R.id.expand_tv_qa17);
//        qa18 = (ExpandableTextView) findViewById(R.id.expand_tv_qa18);

        // IMPORTANT - call setText on the ExpandableTextView to set the text content to display
//        qa1.setText(getString(R.string.tip_qa1));
//        qa2.setText(getString(R.string.tip_qa2));
//        qa3.setText(getString(R.string.tip_qa3));
//        qa4.setText(getString(R.string.tip_qa4));
//        qa5.setText(getString(R.string.tip_qa5));
//        qa6.setText(getString(R.string.tip_qa6));
//
//        qa7.setText(getString(R.string.tip_qa7));
//        qa8.setText(getString(R.string.tip_qa8));
//        qa9.setText(getString(R.string.tip_qa9));
//        qa10.setText(getString(R.string.tip_qa10));
//        qa11.setText(getString(R.string.tip_qa11));
//        qa12.setText(getString(R.string.tip_qa12));
//
//        qa13.setText(getString(R.string.tip_qa13));
//        qa14.setText(getString(R.string.tip_qa14));
//        qa15.setText(getString(R.string.tip_qa15));
//        qa16.setText(getString(R.string.tip_qa16));
//        qa17.setText(getString(R.string.tip_qa17));
//        qa18.setText(getString(R.string.tip_qa18));
    }

    @Override
    protected void onStart() {
        IntentFilter filter=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener,filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }
}