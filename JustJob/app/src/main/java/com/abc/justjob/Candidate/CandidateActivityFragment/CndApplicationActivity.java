package com.abc.justjob.Candidate.CandidateActivityFragment;

import androidx.appcompat.app.AppCompatActivity;
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

import com.abc.justjob.ApiFile.CandidateFetchCompany.CndHomeFrgAdapter;
import com.abc.justjob.ApiFile.CandidateFetchCompany.CndHomeFrgValues;
import com.abc.justjob.NetworkError.NetworkChangeListener;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
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

public class CndApplicationActivity extends AppCompatActivity {

    private NetworkChangeListener networkChangeListener=new NetworkChangeListener();
    private ShimmerFrameLayout shimmerFrameLayout;
    private SwipeRefreshLayout swipeRefreshLayout;

    private LinearLayout emptyLayout,shimmerLayout;
    private RecyclerView appliedJobsRecyclerView;

    private String cndRegId;

    private CndHomeFrgAdapter adapter;
    private List<CndHomeFrgValues> list;
    private static final String url_product="https://justjobshr.com//JustJobApi/JustJob/cndApplyForJobs.php?apicall=fetch_applied_jobs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cnd_application);

        //cndRegId=SharedPrefManager.getInstance(getContext()).getValueOfUserId(getContext());

        swipeRefreshLayout=findViewById(R.id.swipe_refresh_layout_cnd_aplc_frg_activity);
        shimmerFrameLayout=findViewById(R.id.shimmerFrameLayout_cnd_application_activity);
        appliedJobsRecyclerView=findViewById(R.id.cnd_application_recycler_view_activity);
        emptyLayout=findViewById(R.id.empty_layout_activity);
        shimmerLayout=findViewById(R.id.shimmer_layout_activity);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                //getDataList();
                shimmerFrameLayout.startShimmer();

            }
        });
    }


//    private void getDataList() {
//
//        String cndRegisterId= SharedPrefManager.getInstance(CndApplicationActivity.this).getValueOfUserId(CndApplicationActivity.this);
//        appliedJobsRecyclerView.setHasFixedSize(true);
//        appliedJobsRecyclerView.setLayoutManager(new LinearLayoutManager(CndApplicationActivity.this));
//        appliedJobsRecyclerView.addItemDecoration(new DividerItemDecoration(CndApplicationActivity.this, DividerItemDecoration.VERTICAL));
//        list=new ArrayList<>();
//
//        StringRequest stringRequest=new StringRequest(Request.Method.POST, url_product,
//                new com.android.volley.Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONArray array = new JSONArray(response);
//
//                            shimmerFrameLayout.setVisibility(View.GONE);
//                            shimmerFrameLayout.stopShimmer();
//                            for (int i = 0; i < array.length(); i++)
//                            {
//                                JSONObject userObject = array.getJSONObject(i);
//
//                                list.add(new CndHomeFrgValues(
//                                        userObject.getString("cmp_post_job_id"),
//                                        userObject.getString("cmp_id"),
//                                        userObject.getString("cmp_pojo_title"),
//                                        userObject.getString("cmp_pojo_role"),
//                                        userObject.getString("cmp_pojo_designation"),
//                                        userObject.getString("cmp_pojo_job_type"),
//                                        userObject.getString("cmp_pojo_state"),
//                                        userObject.getString("cmp_pojo_cities"),
//                                        userObject.getString("cmp_pojo_description"),
//                                        userObject.getString("cmp_pojo_salary_time"),
//                                        userObject.getString("cmp_pojo_offering_min_salary"),
//                                        userObject.getString("cmp_pojo_offering_max_salary"),
//                                        userObject.getString("cmp_pojo_min_age"),
//                                        userObject.getString("cmp_pojo_max_age"),
//                                        userObject.getString("cmp_pojo_locality"),
//                                        userObject.getString("cmp_pojo_opening"),
//                                        userObject.getString("cmp_pojo_fresher_can"),
//                                        userObject.getString("cmp_pojo_experience_can"),
//                                        userObject.getString("cmp_pojo_min_exp"),
//                                        userObject.getString("cmp_pojo_max_exp"),
//                                        userObject.getString("cmp_pojo_education"),
//                                        userObject.getString("cmp_pojo_male_or_female"),
//                                        userObject.getString("cmp_pojo_english_know"),
//                                        userObject.getString("cmp_pojo_language_know"),
//                                        userObject.getString("cmp_pojo_vehicle"),
//                                        userObject.getString("cmp_pojo_processor"),
//                                        userObject.getString("cmp_pojo_phone"),
//                                        userObject.getString("cmp_pojo_documents"),
//                                        userObject.getString("cmp_pojo_working_day"),
//                                        userObject.getString("cmp_pojo_day_shift_from"),
//                                        userObject.getString("cmp_pojo_day_shift_to"),
//                                        userObject.getString("cmp_pojo_night_shift_from"),
//                                        userObject.getString("cmp_pojo_night_shift_to"),
//                                        userObject.getString("cmp_pojo_rotate_shift_from"),
//                                        userObject.getString("cmp_pojo_rotate_shift_to"),
//                                        userObject.getString("cmp_pojo_reimbursement"),
//                                        userObject.getString("cmp_pojo_incentive"),
//                                        userObject.getString("cmp_pojo_depositing"),
//                                        userObject.getString("cmp_pojo_deposit_amount"),
//                                        userObject.getString("cmp_pojo_deposit_purpose"),
//                                        userObject.getString("cmp_pojo_company_name"),
//                                        userObject.getString("cmp_pojo_company_email"),
//                                        userObject.getString("cmp_pojo_company_poc_name"),
//                                        userObject.getString("cmp_pojo_company_poc_contact"),
//                                        userObject.getString("cmp_pojo_company_poc_designation"),
//                                        userObject.getString("cmp_pojo_company_poc_head_office_location"),
//                                        userObject.getString("cmpPojoCompanyType"),
//                                        userObject.getString("cmp_pojo_industry"),
//                                        userObject.getString("cmp_pojo_company_about"),
//                                        userObject.getString("cmp_pojo_company_posted_by")
//                                ));
//                            }
//                            if (list.isEmpty()) {
//                                emptyLayout.setVisibility(View.VISIBLE);
//                                shimmerLayout.setVisibility(View.GONE);
//                                swipeRefreshLayout.setVisibility(View.GONE);
//                            }else {
//                                emptyLayout.setVisibility(View.GONE);
//                                shimmerLayout.setVisibility(View.VISIBLE);
//                                appliedJobsRecyclerView.setVisibility(View.VISIBLE);
//                                adapter = new CndHomeFrgAdapter(list,CndApplicationActivity.this,false);
//                                appliedJobsRecyclerView.setAdapter(adapter);
//                            }
//                            swipeRefreshLayout.setRefreshing(false);
//                        } catch (JSONException e) {
//                            Toast.makeText(CndApplicationActivity.this, "Exception: "+e.toString(), Toast.LENGTH_SHORT).show();
//                            //e.printStackTrace();
//                        }
//                    }
//                },
//                new com.android.volley.Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(CndApplicationActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("cndRegId", cndRegisterId);
//                return params;
//            }
//        };
//        Volley.newRequestQueue(CndApplicationActivity.this).add(stringRequest);
//    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(CndApplicationActivity.this,CandidateActivity.class));
        this.finish();
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        //getDataList();
        shimmerFrameLayout.startShimmer();
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