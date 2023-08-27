package com.abc.justjob.Company.CompanyActivitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.abc.justjob.ApiFile.CompanyFetch.CmpHomeFrgAdapter;
import com.abc.justjob.Company.CompanyApplicationFrg.CmpAplcAdapter;
import com.abc.justjob.Company.CompanyApplicationFrg.cmpAplcResponse;
import com.abc.justjob.NetworkError.NetworkChangeListener;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCandidateActivity extends AppCompatActivity {

    private NetworkChangeListener networkChangeListener = new NetworkChangeListener();

    private String cmpRegId;

    private SwipeRefreshLayout swipeRefreshLayout;
    private ShimmerFrameLayout shimmerFrameLayout;

    private RecyclerView cmpAppliedCndForJobRecycler;
    private LinearLayout shimmerLayout, emptyLayout;

    private CmpAplcAdapter adapter;
    private List<cmpAplcResponse> list;

    private static final String url_product = "https://justjobshr.com//JustJobApi/JustJob/Company/cmp_hire_cnd.php?apicall=cmpFetchPostedJobs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_candidate);

//        checkNightModeActivated();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        cmpRegId = SharedPrefManager.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());


        swipeRefreshLayout = findViewById(R.id.swipe_refresh_myCnd_layout_activity);
        emptyLayout = findViewById(R.id.cmp_empty_layout_activity);
        shimmerFrameLayout = findViewById(R.id.shimmerFrameLayout_cnd_application_activity);
        cmpAppliedCndForJobRecycler = findViewById(R.id.mycandidate_frg_recycler_activity);

        getDataList();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataList();
                shimmerFrameLayout.startShimmer();
            }
        });

    }

    private void getDataList() {

        cmpAppliedCndForJobRecycler.setHasFixedSize(true);
        cmpAppliedCndForJobRecycler.setLayoutManager(new LinearLayoutManager(MyCandidateActivity.this));
        cmpAppliedCndForJobRecycler.addItemDecoration(new DividerItemDecoration(MyCandidateActivity.this, DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_product,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            shimmerFrameLayout.setVisibility(View.GONE);
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject userObject = array.getJSONObject(i);
                                list.add(new cmpAplcResponse(
                                        userObject.getString("cmp_post_job_id"),
                                        userObject.getString("cmp_register_id"),
                                        userObject.getString("view_count"),
                                        userObject.getString("cmp_pojo_title"),
                                        userObject.getString("cmp_pojo_role"),
                                        userObject.getString("cmp_pojo_designation"),
                                        userObject.getString("cmp_pojo_job_type"),
                                        userObject.getString("cmp_pojo_state"),
                                        userObject.getString("cmp_pojo_cities"),
                                        userObject.getString("cmp_pojo_description"),
                                        userObject.getString("cmp_pojo_salary_time"),
                                        userObject.getString("cmp_pojo_offering_min_salary"),
                                        userObject.getString("cmp_pojo_offering_max_salary"),
                                        userObject.getString("cmp_pojo_min_age"),
                                        userObject.getString("cmp_pojo_max_age"),
                                        userObject.getString("cmp_pojo_locality"),
                                        userObject.getString("cmp_pojo_opening"),
                                        userObject.getString("cmp_pojo_fresher_can"),
                                        userObject.getString("cmp_pojo_experience_can"),
                                        userObject.getString("cmp_pojo_min_exp"),
                                        userObject.getString("cmp_pojo_max_exp"),
                                        userObject.getString("cmp_pojo_education"),
                                        userObject.getString("cmp_pojo_male_or_female"),
                                        userObject.getString("cmp_pojo_english_know"),
                                        userObject.getString("cmp_pojo_language_know"),
                                        userObject.getString("cmp_pojo_vehicle"),
                                        userObject.getString("cmp_pojo_processor"),
                                        userObject.getString("cmp_pojo_phone"),
                                        userObject.getString("cmp_pojo_documents"),
                                        userObject.getString("cmp_pojo_working_day"),
                                        userObject.getString("cmp_pojo_day_shift_from"),
                                        userObject.getString("cmp_pojo_day_shift_to"),
                                        userObject.getString("cmp_pojo_night_shift_from"),
                                        userObject.getString("cmp_pojo_night_shift_to"),
                                        userObject.getString("cmp_pojo_rotate_shift_from"),
                                        userObject.getString("cmp_pojo_rotate_shift_to"),
                                        userObject.getString("cmp_pojo_reimbursement"),
                                        userObject.getString("cmp_pojo_incentive"),
                                        userObject.getString("cmp_pojo_depositing"),
                                        userObject.getString("cmp_pojo_deposit_amount"),
                                        userObject.getString("cmp_pojo_deposit_purpose"),
                                        userObject.getString("cmp_pojo_company_name"),
                                        userObject.getString("cmp_pojo_company_email"),
                                        userObject.getString("cmp_pojo_company_poc_name"),
                                        userObject.getString("cmp_pojo_company_poc_contact"),
                                        userObject.getString("cmp_pojo_company_poc_designation"),
                                        userObject.getString("cmp_pojo_company_poc_head_office_location"),
                                        userObject.getString("cmp_pojo_company_type"),
                                        userObject.getString("cmp_pojo_industry"),
                                        userObject.getString("cmp_pojo_company_about"),
                                        userObject.getString("cmp_pojo_company_posted_by"),
                                        userObject.getString("cmp_pojo_location")
                                ));
//                                    adapter = new CmpAplcAdapter(list, context);
                            }
                            if (list.isEmpty()) {
                                emptyLayout.setVisibility(View.VISIBLE);
                                shimmerFrameLayout.setVisibility(View.GONE);
                                shimmerFrameLayout.stopShimmer();
                                cmpAppliedCndForJobRecycler.setVisibility(View.GONE);
                            } else {
                                emptyLayout.setVisibility(View.GONE);
                                shimmerFrameLayout.setVisibility(View.GONE);
                                shimmerFrameLayout.stopShimmer();
                                cmpAppliedCndForJobRecycler.setVisibility(View.VISIBLE);
                                adapter = new CmpAplcAdapter(list, MyCandidateActivity.this);
                                cmpAppliedCndForJobRecycler.setAdapter(adapter);
                            }
                            swipeRefreshLayout.setRefreshing(false);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(MyCandidateActivity.this, volleyError.toString(), Toast.LENGTH_SHORT).show();

                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                        try {
                            if (volleyError instanceof TimeoutError || volleyError instanceof NoConnectionError) {
                                Toast.makeText(MyCandidateActivity.this, "No Connection/Communication Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof AuthFailureError) {
                                Toast.makeText(MyCandidateActivity.this, "Authentication/ Auth Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof ServerError) {
                                Toast.makeText(MyCandidateActivity.this, "Server Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof NetworkError) {
                                Toast.makeText(MyCandidateActivity.this, "Network Error!", Toast.LENGTH_SHORT).show();
                            } else if (volleyError instanceof ParseError) {
                                Toast.makeText(MyCandidateActivity.this, "Parse Error!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cmp_register_id", cmpRegId);
                return params;
            }
        };
        Volley.newRequestQueue(MyCandidateActivity.this).add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(MyCandidateActivity.this, CompanyActivity.class));
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListener, filter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListener);
        super.onStop();
    }

    /*public void checkNightModeActivated() {
        if (SharedPrefManager.getInstance(getApplicationContext()).GetIsDarkMode(getApplicationContext())) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }*/
}