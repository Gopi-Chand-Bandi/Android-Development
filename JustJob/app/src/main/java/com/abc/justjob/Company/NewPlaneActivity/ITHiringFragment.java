package com.abc.justjob.Company.NewPlaneActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.abc.justjob.Company.CompanyActivitys.CmpPayActivity;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ITHiringFragment extends Fragment {

    LinearLayout firstITExpandableView, secITExpandableView, thirdITExpandableView, fourthITExpandableView, fifthITExpandableView,
            sixthITExpandableView, seventhITExpandableView;

    ImageView firstITArrowBtn, secITArrowBtn, thirdITArrowBtn, fourthITArrowBtn, fifthITArrowBtn, sixthITArrowBtn, seventhITArrowBtn;

    CardView firstITPlanCardView, secondITPlanCardView, thirdITPlanCardView, fourthITPlanCardView, fifthITPlanCardView,
            sixthITPlanCardView, seventhITPlanCardView;

    private AppCompatButton planeBtn1,planeBtn2,planeBtn3,planeBtn4,planeBtn5,planeBtn6,planeBtn7;
    private String cmpRegId;

    public ITHiringFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_i_t_hiring, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cmpRegId= SharedPrefManager.getInstance(getContext()).getValueOfUserId(getContext());

        firstITExpandableView = view.findViewById(R.id.firstITPlanView);
        firstITArrowBtn = view.findViewById(R.id.first_ArrowBtn);
        firstITPlanCardView = view.findViewById(R.id.first_ITPlan);
        planeBtn1=view.findViewById(R.id.plan_btn1);

        secITExpandableView = view.findViewById(R.id.secITPlanView);
        secITArrowBtn = view.findViewById(R.id.sec_ArrowBtn);
        secondITPlanCardView = view.findViewById(R.id.sec_ITPlan);
        planeBtn2=view.findViewById(R.id.plan_btn2);

        thirdITExpandableView = view.findViewById(R.id.thirdITPlanView);
        thirdITArrowBtn = view.findViewById(R.id.third_ArrowBtn);
        thirdITPlanCardView = view.findViewById(R.id.third_ITPlan);
        planeBtn3=view.findViewById(R.id.plan_btn3);

        fourthITExpandableView = view.findViewById(R.id.fourthITPlanView);
        fourthITArrowBtn = view.findViewById(R.id.fourth_ArrowBtn);
        fourthITPlanCardView = view.findViewById(R.id.fourth_ITPlan);
        planeBtn4=view.findViewById(R.id.plan_btn4);

        fifthITExpandableView = view.findViewById(R.id.fifthITPlanView);
        fifthITArrowBtn = view.findViewById(R.id.fifth_ArrowBtn);
        fifthITPlanCardView = view.findViewById(R.id.fifth_ITPlan);
        planeBtn5=view.findViewById(R.id.plan_btn5);

        sixthITExpandableView = view.findViewById(R.id.sixthITPlanView);
        sixthITArrowBtn = view.findViewById(R.id.sixth_ArrowBtn);
        sixthITPlanCardView = view.findViewById(R.id.sixth_ITPlan);
        planeBtn6=view.findViewById(R.id.plan_btn6);

        seventhITExpandableView = view.findViewById(R.id.seventhITPlanView);
        seventhITArrowBtn = view.findViewById(R.id.seventh_ArrowBtn);
        seventhITPlanCardView = view.findViewById(R.id.seventh_ITPlan);
        planeBtn7=view.findViewById(R.id.plan_btn7);

        firstITArrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstITExpandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(firstITPlanCardView, new AutoTransition());
                    firstITExpandableView.setVisibility(View.VISIBLE);
                    firstITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(firstITPlanCardView, new AutoTransition());
                    firstITExpandableView.setVisibility(View.GONE);
                    firstITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        secITArrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (secITExpandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(secondITPlanCardView, new AutoTransition());
                    secITExpandableView.setVisibility(View.VISIBLE);
                    secITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(secondITPlanCardView, new AutoTransition());
                    secITExpandableView.setVisibility(View.GONE);
                    secITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        thirdITArrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thirdITExpandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(thirdITPlanCardView, new AutoTransition());
                    thirdITExpandableView.setVisibility(View.VISIBLE);
                    thirdITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(thirdITPlanCardView, new AutoTransition());
                    thirdITExpandableView.setVisibility(View.GONE);
                    thirdITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        fourthITArrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fourthITExpandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(fourthITPlanCardView, new AutoTransition());
                    fourthITExpandableView.setVisibility(View.VISIBLE);
                    fourthITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(fourthITPlanCardView, new AutoTransition());
                    fourthITExpandableView.setVisibility(View.GONE);
                    fourthITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        fifthITArrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fifthITExpandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(fifthITPlanCardView, new AutoTransition());
                    fifthITExpandableView.setVisibility(View.VISIBLE);
                    fifthITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(fifthITPlanCardView, new AutoTransition());
                    fifthITExpandableView.setVisibility(View.GONE);
                    fifthITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        sixthITArrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sixthITExpandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(sixthITPlanCardView, new AutoTransition());
                    sixthITExpandableView.setVisibility(View.VISIBLE);
                    sixthITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(sixthITPlanCardView, new AutoTransition());
                    sixthITExpandableView.setVisibility(View.GONE);
                    sixthITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        seventhITArrowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (seventhITExpandableView.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(seventhITPlanCardView, new AutoTransition());
                    seventhITExpandableView.setVisibility(View.VISIBLE);
                    seventhITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    TransitionManager.beginDelayedTransition(seventhITPlanCardView, new AutoTransition());
                    seventhITExpandableView.setVisibility(View.GONE);
                    seventhITArrowBtn.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        planeBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = Math.round(Float.parseFloat("725")*100);
                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, +7);
                Date after7Days = cal.getTime();
                SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy ");
                String dateStr=format.format(after7Days);
                paymentOperation(amount,dateStr);
            }
        });
        planeBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = Math.round(Float.parseFloat("1350")*100);
                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, +7);
                Date after7Days = cal.getTime();
                SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy ");
                String dateStr=format.format(after7Days);
                paymentOperation(amount,dateStr);
            }
        });
        planeBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = Math.round(Float.parseFloat("2500")*100);

                Calendar cal = GregorianCalendar.getInstance();
//                SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, +15);
                Date after7Days = cal.getTime();
                SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy ");
                String dateStr=format.format(after7Days);
                paymentOperation(amount,dateStr);
            }
        });
        planeBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = Math.round(Float.parseFloat("12500")*100);
                Calendar cal = GregorianCalendar.getInstance();
//                SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, +30);
                Date after7Days = cal.getTime();
                SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy ");
                String dateStr=format.format(after7Days);
                paymentOperation(amount,dateStr);
            }
        });
        planeBtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = Math.round(Float.parseFloat("105000")*100);

                Calendar cal = GregorianCalendar.getInstance();
//                SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, +90);
                Date after7Days = cal.getTime();
                SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy ");
                String dateStr=format.format(after7Days);
                paymentOperation(amount,dateStr);
            }
        });
        planeBtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = Math.round(Float.parseFloat("180000")*100);

                Calendar cal = GregorianCalendar.getInstance();
//                SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, +180);
                Date after7Days = cal.getTime();
                SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy ");
                String dateStr=format.format(after7Days);
                paymentOperation(amount,dateStr);
            }
        });
        planeBtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = Math.round(Float.parseFloat("450000")*100);

                Calendar cal = GregorianCalendar.getInstance();
//                SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy 'at' h:mm a");
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, +365);
                Date after7Days = cal.getTime();
                SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy ");
                String dateStr=format.format(after7Days);
                paymentOperation(amount,dateStr);
            }
        });

    }

    private void paymentOperation(int amount,String expireDate) {
        Intent intent=new Intent(getContext(), CmpPayActivity.class);
        intent.putExtra("cmpAmount",amount);
        intent.putExtra("cmpRegId",cmpRegId);
        intent.putExtra("expireDate",expireDate);
        startActivity(intent);
    }

    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

}