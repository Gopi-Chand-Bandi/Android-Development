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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

//public class CompanyPlanFragmentNew extends Fragment implements PaymentResultListener {
public class CompanyPlanFragmentNew extends Fragment {

//    View view;
//    TabLayout tabLayout;
//    TabItem itPlan, nonItPlan;
//    ViewPager planPager;
//
//    PricePageAdapter pricePageAdapter;

    private String ContactNumber,Email;

    private LinearLayout FreePlan,Plan1,Plan2,Plan3,Plan4;

    private ImageView FreeArrow,arrow1,arrow2,arrow3,arrow4;

    private CardView Free_Card,Plan1_Card,Plan2_Card,Plan3_Card,Plan4_Card;

    private AppCompatButton Plan1Btn,Plan2Btn,Plan3Btn,Plan4Btn;

    private String cmpRegId;

    private static final String url = "https://justjobshr.com//JustJobApi/JustJob/Company/cmp_payments.php?apicall=PaymentInfoFetch&id=";

    public CompanyPlanFragmentNew() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_company_plan_new, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        cmpRegId = SharedPrefManager.getInstance(getContext()).getValueOfUserId(getContext());

        //FreePlan = view.findViewById(R.id.layout5_plan);
        Plan1 = view.findViewById(R.id.layout1_plan);
        Plan2 = view.findViewById(R.id.layout2_plan);
        Plan3 = view.findViewById(R.id.layout3_plan);
        Plan4 = view.findViewById(R.id.layout4_plan);

        //FreeArrow = view.findViewById(R.id.arrow5_Btn);
        arrow1 = view.findViewById(R.id.arrow1_Btn);
        arrow2 = view.findViewById(R.id.arrow2_Btn);
        arrow3 = view.findViewById(R.id.arrow3_Btn);
        arrow4 = view.findViewById(R.id.arrow4_Btn);

        Free_Card = view.findViewById(R.id.free_card_company);
        Plan1_Card = view.findViewById(R.id.plan1_card);
        Plan2_Card = view.findViewById(R.id.plan2_card);
        Plan3_Card = view.findViewById(R.id.plan3_card);
        Plan4_Card = view.findViewById(R.id.plan4_card);

        Plan1Btn = view.findViewById(R.id.plan1_btn);
        Plan2Btn = view.findViewById(R.id.plan2_btn);
        Plan3Btn = view.findViewById(R.id.plan3_btn);
        Plan4Btn = view.findViewById(R.id.plan4_btn);

        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.GET,
                url + cmpRegId,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            ContactNumber = response.getString("cmp_contact");
                            Email = response.getString("cmp_email");
                        }catch (JSONException e) {
                            Log.d("Exception: ", e.getMessage());
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        try {
                            if (volleyError instanceof TimeoutError || volleyError instanceof NoConnectionError) {
                                Toast.makeText(getContext(), "No Connection/Communication Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof AuthFailureError) {
                                Toast.makeText(getContext(), "Authentication/ Auth Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof ServerError) {
                                Toast.makeText(getContext(), "Server Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof NetworkError) {
                                Toast.makeText(getContext(), "Network Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof ParseError) {
                                Toast.makeText(getContext(), "Parse Error!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        //adding the string request to request queue
        requestQueue.add(stringRequest);

//        FreeArrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (FreePlan.getVisibility() == View.GONE) {
//                    TransitionManager.beginDelayedTransition(Free_Card, new AutoTransition());
//                    FreePlan.setVisibility(View.VISIBLE);
//                    FreeArrow.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
//                } else {
//                    //    TransitionManager.beginDelayedTransition(cardView1Day, new AutoTransition());
//                    FreePlan.setVisibility(View.GONE);
//                    FreeArrow.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
//                }
//            }
//        });

        arrow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Plan1.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(Plan1_Card, new AutoTransition());
                    Plan1.setVisibility(View.VISIBLE);
                    arrow1.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    //    TransitionManager.beginDelayedTransition(cardView1Day, new AutoTransition());
                    Plan1.setVisibility(View.GONE);
                    arrow1.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        arrow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Plan2.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(Plan2_Card, new AutoTransition());
                    Plan2.setVisibility(View.VISIBLE);
                    arrow2.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    //    TransitionManager.beginDelayedTransition(cardView1Day, new AutoTransition());
                    Plan2.setVisibility(View.GONE);
                    arrow2.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        arrow3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Plan3.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(Plan3_Card, new AutoTransition());
                    Plan3.setVisibility(View.VISIBLE);
                    arrow3.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    //    TransitionManager.beginDelayedTransition(cardView1Day, new AutoTransition());
                    Plan3.setVisibility(View.GONE);
                    arrow3.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        arrow4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Plan4.getVisibility() == View.GONE) {
                    TransitionManager.beginDelayedTransition(Plan4_Card, new AutoTransition());
                    Plan4.setVisibility(View.VISIBLE);
                    arrow4.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24);
                } else {
                    //    TransitionManager.beginDelayedTransition(cardView1Day, new AutoTransition());
                    Plan4.setVisibility(View.GONE);
                    arrow4.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_down_24);
                }
            }
        });

        Plan1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int amount = Math.round(Float.parseFloat("9999")*100);
                int JobPosts=5,Application_Unlocks=50,CandidateContactUnlocks=1000,CandidateProfileViews=0000,CompanyProfileViews=0000,
                        CompanyUnlocks=10,Validity=30;

                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, +30);
                Date after7Days = cal.getTime();
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String expireDateStr=format.format(after7Days);

                paymentOperationData(amount,"30",expireDateStr,JobPosts,Application_Unlocks,CandidateContactUnlocks,CandidateProfileViews,CompanyProfileViews,CompanyUnlocks,Validity,"Bronze Plan");


            }
        });

        Plan2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int amount = Math.round(Float.parseFloat("24999")*100);
                int JobPosts=30,Application_Unlocks=200,CandidateContactUnlocks=4000,CandidateProfileViews=0000,CompanyProfileViews=0000,
                        CompanyUnlocks=60,Validity=90;

                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, +90);
                Date after7Days = cal.getTime();
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String expireDateStr=format.format(after7Days);

                paymentOperationData(amount,"90",expireDateStr,JobPosts,Application_Unlocks,CandidateContactUnlocks,CandidateProfileViews,CompanyProfileViews,CompanyUnlocks,Validity,"Silver Plan");

            }
        });

        Plan3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int amount = Math.round(Float.parseFloat("49999")*100);
                int JobPosts=50,Application_Unlocks=600,CandidateContactUnlocks=8000,CandidateProfileViews=0000,CompanyProfileViews=0000,
                        CompanyUnlocks=120,Validity=180;

                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, +180);
                Date after7Days = cal.getTime();
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String expireDateStr=format.format(after7Days);

                paymentOperationData(amount,"180",expireDateStr,JobPosts,Application_Unlocks,CandidateContactUnlocks,CandidateProfileViews,CompanyProfileViews,CompanyUnlocks,Validity,"Gold Plan");

            }
        });

        Plan4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int amount = Math.round(Float.parseFloat("79999")*100);
                int JobPosts=120,Application_Unlocks=5000,CandidateContactUnlocks=15000,CandidateProfileViews=0000,CompanyProfileViews=0000,
                        CompanyUnlocks=300,Validity=360;

                Calendar cal = GregorianCalendar.getInstance();
                cal.setTime(new Date());
                cal.add(Calendar.DAY_OF_YEAR, +360);
                Date after7Days = cal.getTime();
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
                String expireDateStr=format.format(after7Days);

                paymentOperationData(amount,"360",expireDateStr,JobPosts,Application_Unlocks,CandidateContactUnlocks,CandidateProfileViews,CompanyProfileViews,CompanyUnlocks,Validity,"Platinum Plan");

            }
        });

    }

    private void paymentOperationData(int amount, String days, String expireDateStr, int JobPosts,
                                      int Application_Unlocks, int CandidateContactUnlocks, int CandidateProfileViews,
                                      int CompanyProfileViews, int CompanyUnlocks,int Validity,String PlanName) {

        double percentage = 0.18;
        double addition = amount * percentage;

        double result = amount + addition;

        //double price = amount + 0.18*amount;

        Intent intent=new Intent(getContext(), CmpPayActivity.class);
        intent.putExtra("cmpAmount",result);
        intent.putExtra("cmpRegId",cmpRegId);
        intent.putExtra("cmpDays",days);
        intent.putExtra("expireDate",expireDateStr);
        intent.putExtra("JobPosts",JobPosts);
        intent.putExtra("Application_Unlocks",Application_Unlocks);
        intent.putExtra("CandidateContactUnlocks",CandidateContactUnlocks);
        intent.putExtra("CandidateProfileViews",CandidateProfileViews);
        intent.putExtra("CompanyProfileViews",CompanyProfileViews);
        intent.putExtra("CompanyUnlocks",CompanyUnlocks);
        intent.putExtra("Validity",Validity);
        intent.putExtra("PlanName",PlanName);
        intent.putExtra("ContactNumber",ContactNumber);
        intent.putExtra("Email",Email);

        startActivity(intent);

    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.fragment_company_plan_new,container,false);
//        planPager = view.findViewById(R.id.pricing_plan_pager);
//        tabLayout = view.findViewById(R.id.pricing_plan_tablayout);
//        itPlan = view.findViewById(R.id.it_plan_tab);
//        nonItPlan = view.findViewById(R.id.nonit_plan_tab);
//
//        pricePageAdapter = new PricePageAdapter(getChildFragmentManager(), tabLayout.getTabCount());
//        planPager.setAdapter(pricePageAdapter);
//
//        return view;
    //return inflater.inflate(R.layout.fragment_company_plan_new, container, false);
//    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                planPager.setCurrentItem(tab.getPosition());
//
//                if (tab.getPosition() == 0 || tab.getPosition() == 1)
//                    pricePageAdapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//        planPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//    }

//    @Override
//    public void onPaymentSuccess(String s) {
//        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
//        builder.setTitle("Payment Id");
//        builder.setMessage(s);
//        builder.show();
//
//        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onPaymentError(int i, String s) {
//        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
//    }



//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }


}