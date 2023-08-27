package com.abc.justjob.Company.CompanyActivitys;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
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

import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class CompanyPlanFragment extends Fragment {


    /*private Button freePostBtn,premiumPostBtn,premMoreOpBtn,compRecBtn,compRecMoreOpBtn;

    private ScrollView main_scroll_view;

    //new plane
    TabLayout tabLayout;
    TabItem itPlan, nonItPlan;
    ViewPager planPager;

    PricePageAdapter pricePageAdapter;
    //new plane*/


    LinearLayout expandableView1Day, expandableView7Day, expandableView15Day, expandableView30Day, expandableView60Day,
            expandableView90Day, expandableView180Day, expandableView365Day;

    ImageView arrowBtn1Day,arrowBtn7Day,arrowBtn15Day,arrowBtn30Day,arrowBtn60Day,arrowBtn90Day,arrowBtn180Day,arrowBtn365Day;

    CardView cardView1Day,cardView7Day,cardView15Day,cardView30Day,cardView60Day,cardView90Day,cardView180Day,cardView365Day;

    private AppCompatButton planeBtn1Day,planeBtn7Day,planeBtn15Day,planeBtn30Day,planeBtn60Day,planeBtn90Day,planeBtn180Day,planeBtn365Day;
    private String cmpRegId;

    public CompanyPlanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_company_plan, container, false);
        /*main_scroll_view=view.findViewById(R.id.main_scroll_view);
        freePostBtn=view.findViewById(R.id.free_view_pln_btn);

        freePostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getContext(), cmpPlaneFreePostActivity.class));
            }
        });

        premiumPostBtn=view.findViewById(R.id.pre_view_pln_btn);
        premiumPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), cmpPlanePremiumPostActivity.class));
            }
        });

        premMoreOpBtn=view.findViewById(R.id.pre_more_price_btn);
        premMoreOpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), cmpPlanePremMoreOptionActivity.class));
            }
        });

        compRecBtn=view.findViewById(R.id.comRecView_btn);
        compRecBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), cmpPlaneCompRecruitmentActivity.class));
            }
        });

        compRecMoreOpBtn=view.findViewById(R.id.recMorePrice_btn);
        compRecMoreOpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), cmpCompRecruitmentMoreOpActivity.class));

            }
        });
        return view;*/
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        cmpRegId= SharedPrefManager.getInstance(getContext()).getValueOfUserId(getContext());

        expandableView1Day = view.findViewById(R.id.layout_1_day);
        expandableView7Day = view.findViewById(R.id.layout_7_day);
        expandableView15Day = view.findViewById(R.id.layout_15_day);
        expandableView30Day = view.findViewById(R.id.layout_30_day);
        expandableView60Day = view.findViewById(R.id.layout_60_day);
        expandableView90Day = view.findViewById(R.id.layout_90_day);
        expandableView180Day = view.findViewById(R.id.layout_180_day);
        expandableView365Day = view.findViewById(R.id.layout_365_day);

        arrowBtn1Day = view.findViewById(R.id.arrow_1_day_Btn);
        arrowBtn7Day = view.findViewById(R.id.arrow_7_day_Btn);
        arrowBtn15Day = view.findViewById(R.id.arrow_15_day_Btn);
        arrowBtn30Day = view.findViewById(R.id.arrow_30_day_Btn);
        arrowBtn60Day = view.findViewById(R.id.arrow_60_day_Btn);
        arrowBtn90Day = view.findViewById(R.id.arrow_90_day_Btn);
        arrowBtn180Day = view.findViewById(R.id.arrow_180_day_Btn);
        arrowBtn365Day = view.findViewById(R.id.arrow_365_day_Btn);

        cardView1Day = view.findViewById(R.id.plane_card_1_day);
        cardView7Day = view.findViewById(R.id.plane_card_7_day);
        cardView15Day = view.findViewById(R.id.plane_card_15_day);
        cardView30Day = view.findViewById(R.id.plane_card_30_day);
        cardView60Day = view.findViewById(R.id.plane_card_60_day);
        cardView90Day = view.findViewById(R.id.plane_card_90_day);
        cardView180Day = view.findViewById(R.id.plane_card_180_day);
        cardView365Day = view.findViewById(R.id.plane_card_365_day);

        planeBtn1Day=view.findViewById(R.id.plan_1_day_btn);
        planeBtn7Day=view.findViewById(R.id.plan_7_day_btn);
        planeBtn15Day=view.findViewById(R.id.plan_15_day_btn);
        planeBtn30Day=view.findViewById(R.id.plan_30_day_btn);
        planeBtn60Day=view.findViewById(R.id.plan_60_day_btn);
        planeBtn90Day=view.findViewById(R.id.plan_90_day_btn);
        planeBtn180Day=view.findViewById(R.id.plan_180_day_btn);
        planeBtn365Day=view.findViewById(R.id.plan_365_day_btn);

        arrowBtn1Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView1Day.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView1Day, new AutoTransition());
                    expandableView1Day.setVisibility(View.VISIBLE);
                    arrowBtn1Day.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                //    TransitionManager.beginDelayedTransition(cardView1Day, new AutoTransition());
                    expandableView1Day.setVisibility(View.GONE);
                    arrowBtn1Day.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        arrowBtn7Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView7Day.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView7Day, new AutoTransition());
                    expandableView7Day.setVisibility(View.VISIBLE);
                    arrowBtn7Day.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                //    TransitionManager.beginDelayedTransition(cardView7Day, new AutoTransition());
                    expandableView7Day.setVisibility(View.GONE);
                    arrowBtn7Day.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });
        arrowBtn15Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView15Day.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView15Day, new AutoTransition());
                    expandableView15Day.setVisibility(View.VISIBLE);
                    arrowBtn15Day.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                //    TransitionManager.beginDelayedTransition(cardView15Day, new AutoTransition());
                    expandableView15Day.setVisibility(View.GONE);
                    arrowBtn15Day.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });
        arrowBtn30Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView30Day.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView30Day, new AutoTransition());
                    expandableView30Day.setVisibility(View.VISIBLE);
                    arrowBtn30Day.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                //    TransitionManager.beginDelayedTransition(cardView30Day, new AutoTransition());
                    expandableView30Day.setVisibility(View.GONE);
                    arrowBtn30Day.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });
        arrowBtn60Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView60Day.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView60Day, new AutoTransition());
                    expandableView60Day.setVisibility(View.VISIBLE);
                    arrowBtn60Day.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                //    TransitionManager.beginDelayedTransition(cardView60Day, new AutoTransition());
                    expandableView60Day.setVisibility(View.GONE);
                    arrowBtn60Day.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });
        arrowBtn90Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView90Day.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView90Day, new AutoTransition());
                    expandableView90Day.setVisibility(View.VISIBLE);
                    arrowBtn90Day.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                //    TransitionManager.beginDelayedTransition(cardView90Day, new AutoTransition());
                    expandableView90Day.setVisibility(View.GONE);
                    arrowBtn90Day.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });
        arrowBtn180Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView180Day.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView180Day, new AutoTransition());
                    expandableView180Day.setVisibility(View.VISIBLE);
                    arrowBtn180Day.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                //    TransitionManager.beginDelayedTransition(cardView180Day, new AutoTransition());
                    expandableView180Day.setVisibility(View.GONE);
                    arrowBtn180Day.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });
        arrowBtn365Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView365Day.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(cardView365Day, new AutoTransition());
                    expandableView365Day.setVisibility(View.VISIBLE);
                    arrowBtn365Day.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                //    TransitionManager.beginDelayedTransition(cardView365Day, new AutoTransition());
                    expandableView365Day.setVisibility(View.GONE);
                    arrowBtn365Day.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        planeBtn1Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int amount = Math.round(Float.parseFloat("20")*100);
                int dataAccess=1,dailyDataAccess = 1,weeklyDataAccess = 0,postJobCount = 0,
                        unlockCndAplcContact = 50,tenureUnlockingDay = 30;

                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, +1);
                Date after7Days = cal.getTime();
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String expireDateStr=format.format(after7Days);

                paymentOperationData(amount,"1",expireDateStr,dataAccess,dailyDataAccess,weeklyDataAccess,postJobCount,unlockCndAplcContact,tenureUnlockingDay);

            }
        });

        planeBtn7Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int amount = Math.round(Float.parseFloat("250")*100);
            //    int amount = Math.round(Float.parseFloat("250")*100);
                int dataAccess=25,dailyDataAccess = 4,weeklyDataAccess = 25,postJobCount = 20,
                        unlockCndAplcContact = 200,tenureUnlockingDay = 29;

                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, +7);
                Date after7Days = cal.getTime();
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String expireDateStr=format.format(after7Days);

                paymentOperationData(amount,"7",expireDateStr,dataAccess,dailyDataAccess,weeklyDataAccess,postJobCount,unlockCndAplcContact,tenureUnlockingDay);

            }
        });

        planeBtn15Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int amount = Math.round(Float.parseFloat("450")*100);
                int dataAccess=50,dailyDataAccess = 3,weeklyDataAccess = 23,postJobCount = 30,
                        unlockCndAplcContact = 300,tenureUnlockingDay = 20;
                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, +15);
                Date after7Days = cal.getTime();
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String expireDateStr=format.format(after7Days);

                paymentOperationData(amount,"15",expireDateStr,dataAccess,dailyDataAccess,weeklyDataAccess,postJobCount,unlockCndAplcContact,tenureUnlockingDay);

            }
        });

        planeBtn30Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int amount = Math.round(Float.parseFloat("800")*100);
                int dataAccess=100,dailyDataAccess = 3,weeklyDataAccess = 23,postJobCount = 40,
                        unlockCndAplcContact = 400,tenureUnlockingDay = 13;
                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, +30);
                Date after7Days = cal.getTime();
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String expireDateStr=format.format(after7Days);

                paymentOperationData(amount,"30",expireDateStr,dataAccess,dailyDataAccess,weeklyDataAccess,postJobCount,unlockCndAplcContact,tenureUnlockingDay);

            }
        });

        planeBtn60Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int amount = Math.round(Float.parseFloat("3500")*100);
                int dataAccess=500,dailyDataAccess = 8,weeklyDataAccess = 58,postJobCount = 200,
                        unlockCndAplcContact = 2000,tenureUnlockingDay = 33;
                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, +60);
                Date after7Days = cal.getTime();
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String expireDateStr=format.format(after7Days);

                paymentOperationData(amount,"60",expireDateStr,dataAccess,dailyDataAccess,weeklyDataAccess,postJobCount,unlockCndAplcContact,tenureUnlockingDay);

            }
        });

        planeBtn90Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int amount = Math.round(Float.parseFloat("30000")*100);
                int dataAccess=5000,dailyDataAccess = 56,weeklyDataAccess = 389,postJobCount = 0000,
                        unlockCndAplcContact = 0000,tenureUnlockingDay = 0000;
                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, +90);
                Date after7Days = cal.getTime();
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String expireDateStr=format.format(after7Days);

                paymentOperationData(amount,"90",expireDateStr,dataAccess,dailyDataAccess,weeklyDataAccess,postJobCount,unlockCndAplcContact,tenureUnlockingDay);

            }
        });

        planeBtn180Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int amount = Math.round(Float.parseFloat("50000")*100);
                int dataAccess=10000,dailyDataAccess = 56,weeklyDataAccess = 389,postJobCount = 0000,
                        unlockCndAplcContact = 0000,tenureUnlockingDay = 0000;
                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, +180);
                Date after7Days = cal.getTime();
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String expireDateStr=format.format(after7Days);

                paymentOperationData(amount,"180",expireDateStr,dataAccess,dailyDataAccess,weeklyDataAccess,postJobCount,unlockCndAplcContact,tenureUnlockingDay);

            }
        });

        planeBtn365Day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int amount = Math.round(Float.parseFloat("120000")*100);
                int dataAccess=30000,dailyDataAccess = 82,weeklyDataAccess = 575,postJobCount = 0000,
                        unlockCndAplcContact = 0000,tenureUnlockingDay = 0000;
                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, +365);
                Date after7Days = cal.getTime();
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String expireDateStr=format.format(after7Days);

                paymentOperationData(amount,"365",expireDateStr,dataAccess,dailyDataAccess,weeklyDataAccess,postJobCount,unlockCndAplcContact,tenureUnlockingDay);

            }
        });

        /*tabLayout = view.findViewById(R.id.new_pricing_plan_tablayout);
        itPlan = view.findViewById(R.id.new_it_plan_tab);
        nonItPlan = view.findViewById(R.id.new_nonit_plan_tab);
        planPager = view.findViewById(R.id.new_pricing_plan_pager);

        pricePageAdapter = new PricePageAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        planPager.setAdapter(pricePageAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                planPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0 || tab.getPosition() == 1)
                    pricePageAdapter.notifyDataSetChanged();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        planPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));*/
    }


    private void paymentOperationData(int amount, String days, String expireDateStr, int dataAccess, int dailyDataAccess, int weeklyDataAccess, int postJobCount, int unlockCndAplcContact, int tenureUnlockingDay) {


        Intent intent=new Intent(getContext(), CmpPayActivity.class);
        intent.putExtra("cmpAmount",amount);
        intent.putExtra("cmpRegId",cmpRegId);
        intent.putExtra("cmpDays",days);
        intent.putExtra("expireDate",expireDateStr);
        intent.putExtra("dataAccess",dataAccess);
        intent.putExtra("dailyDataAccess",dailyDataAccess);
        intent.putExtra("weeklyDataAcces",weeklyDataAccess);
        intent.putExtra("postJobCount",postJobCount);
        intent.putExtra("unlockCndAplcContact",unlockCndAplcContact);
        intent.putExtra("tenureUnlockingDay",tenureUnlockingDay);
        startActivity(intent);
    }

}