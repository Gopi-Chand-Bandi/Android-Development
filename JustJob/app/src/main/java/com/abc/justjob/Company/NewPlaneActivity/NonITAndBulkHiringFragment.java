package com.abc.justjob.Company.NewPlaneActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.abc.justjob.R;

public class NonITAndBulkHiringFragment extends Fragment {

    LinearLayout firstNonITExpandableView, secNonITExpandableView, thirdNonITExpandableView, fourthNonITExpandableView,
            fifthNonITExpandableView, sixthNonITExpandableView, seventhNonITExpandableView;

    ImageView firstNonITArrowBtn, secNonITArrowBtn, thirdNonITArrowBtn, fourthNonITArrowBtn, fifthNonITArrowBtn, sixthNonITArrowBtn, seventhNonITArrowBtn;

    CardView firstNonITPlanCardView, secondNonITPlanCardView, thirdNonITPlanCardView, fourthNonITPlanCardView,
            fifthNonITPlanCardView, sixthNonITPlanCardView, seventhNonITPlanCardView;


    public NonITAndBulkHiringFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_non_i_t_and_bulk_hiring, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firstNonITExpandableView = view.findViewById(R.id.firstPlanView);
        firstNonITArrowBtn = view.findViewById(R.id.firstArrowBtn);
        firstNonITPlanCardView = view.findViewById(R.id.first_plan);

        secNonITExpandableView = view.findViewById(R.id.secPlanView);
        secNonITArrowBtn = view.findViewById(R.id.secArrowBtn);
        secondNonITPlanCardView = view.findViewById(R.id.sec_plan);

        thirdNonITExpandableView = view.findViewById(R.id.thirdPlanView);
        thirdNonITArrowBtn = view.findViewById(R.id.thirdArrowBtn);
        thirdNonITPlanCardView = view.findViewById(R.id.third_plan);

        fourthNonITExpandableView = view.findViewById(R.id.fourthPlanView);
        fourthNonITArrowBtn = view.findViewById(R.id.fourthArrowBtn);
        fourthNonITPlanCardView = view.findViewById(R.id.fourth_plan);

        fifthNonITExpandableView = view.findViewById(R.id.fifthPlanView);
        fifthNonITArrowBtn = view.findViewById(R.id.fifthArrowBtn);
        fifthNonITPlanCardView = view.findViewById(R.id.fifth_plan);

        sixthNonITExpandableView = view.findViewById(R.id.sixthPlanView);
        sixthNonITArrowBtn = view.findViewById(R.id.sixthArrowBtn);
        sixthNonITPlanCardView = view.findViewById(R.id.sixth_plan);

        seventhNonITExpandableView = view.findViewById(R.id.seventhPlanView);
        seventhNonITArrowBtn = view.findViewById(R.id.seventhArrowBtn);
        seventhNonITPlanCardView = view.findViewById(R.id.seventh_plan);


        firstNonITArrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstNonITExpandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(firstNonITPlanCardView, new AutoTransition());
                    firstNonITExpandableView.setVisibility(View.VISIBLE);
                    firstNonITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(firstNonITPlanCardView, new AutoTransition());
                    firstNonITExpandableView.setVisibility(View.GONE);
                    firstNonITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        secNonITArrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (secNonITExpandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(secondNonITPlanCardView, new AutoTransition());
                    secNonITExpandableView.setVisibility(View.VISIBLE);
                    secNonITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(secondNonITPlanCardView, new AutoTransition());
                    secNonITExpandableView.setVisibility(View.GONE);
                    secNonITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        thirdNonITArrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thirdNonITExpandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(thirdNonITPlanCardView, new AutoTransition());
                    thirdNonITExpandableView.setVisibility(View.VISIBLE);
                    thirdNonITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(thirdNonITPlanCardView, new AutoTransition());
                    thirdNonITExpandableView.setVisibility(View.GONE);
                    thirdNonITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        fourthNonITArrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fourthNonITExpandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(fourthNonITPlanCardView, new AutoTransition());
                    fourthNonITExpandableView.setVisibility(View.VISIBLE);
                    fourthNonITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(fourthNonITPlanCardView, new AutoTransition());
                    fourthNonITExpandableView.setVisibility(View.GONE);
                    fourthNonITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        fifthNonITArrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fifthNonITExpandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(fifthNonITPlanCardView, new AutoTransition());
                    fifthNonITExpandableView.setVisibility(View.VISIBLE);
                    fifthNonITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(fifthNonITPlanCardView, new AutoTransition());
                    fifthNonITExpandableView.setVisibility(View.GONE);
                    fifthNonITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        sixthNonITArrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sixthNonITExpandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(sixthNonITPlanCardView, new AutoTransition());
                    sixthNonITExpandableView.setVisibility(View.VISIBLE);
                    sixthNonITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(sixthNonITPlanCardView, new AutoTransition());
                    sixthNonITExpandableView.setVisibility(View.GONE);
                    sixthNonITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        seventhNonITArrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seventhNonITExpandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(seventhNonITPlanCardView, new AutoTransition());
                    seventhNonITExpandableView.setVisibility(View.VISIBLE);
                    seventhNonITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(seventhNonITPlanCardView, new AutoTransition());
                    seventhNonITExpandableView.setVisibility(View.GONE);
                    seventhNonITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

    }
}