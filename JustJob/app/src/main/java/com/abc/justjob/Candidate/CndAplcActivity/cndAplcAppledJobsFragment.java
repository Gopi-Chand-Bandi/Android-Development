package com.abc.justjob.Candidate.CndAplcActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.abc.justjob.ApiFile.CandidateFetchCompany.CndHomeFrgAdapter;
import com.abc.justjob.ApiFile.CandidateFetchCompany.CndHomeFrgValues;
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

public class cndAplcAppledJobsFragment extends Fragment {

    private ShimmerFrameLayout shimmerFrameLayout;
    private SwipeRefreshLayout swipeRefreshLayout;

    private LinearLayout emptyLayout;
    private RecyclerView appliedJobsRecyclerView1;

    private NestedScrollView nestedScrollView;
    ProgressBar progressBar;

    Boolean isvisible = false;

    private String cndRegId;

    private LinearLayoutManager manager1;

    private CndHomeFrgAdapter adapter1;
    private List<CndHomeFrgValues> list;
    private static final String url_product="https://justjobshr.com//JustJobApi/JustJob/cndApplyForJobs.php?apicall=fetch_applied_jobs";

    public cndAplcAppledJobsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cnd_aplc_appled_jobs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefreshLayout=view.findViewById(R.id.swipe_refresh_layout_cnd_aplc_frg);
        shimmerFrameLayout=view.findViewById(R.id.shimmerFrameLayout_cnd_application_frg);
        appliedJobsRecyclerView1=view.findViewById(R.id.cnd_application_recycler_view1_frg);
        emptyLayout=view.findViewById(R.id.empty_layout_frg);
        nestedScrollView=view.findViewById(R.id.cnd_aplc_frg_nested_scroll_view);
        progressBar=view.findViewById(R.id.progress_bar_cnd_aplc_frag);

        cndRegId=SharedPrefManager.getInstance(getContext()).getValueOfUserId(getContext());

        manager1 = new LinearLayoutManager(getContext());

        appliedJobsRecyclerView1.setLayoutManager(manager1);

        adapter1 = new CndHomeFrgAdapter(list, getContext(), true);
        appliedJobsRecyclerView1.setAdapter(adapter1);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shimmerFrameLayout.setVisibility(View.VISIBLE);
                getDataList();
                shimmerFrameLayout.startShimmer();
                //getDataList();

            }
        });
        //swipeRefreshLayout.setOnRefreshListener();

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    progressBar.setVisibility(View.VISIBLE);
                    //if (isVisible(cndHomeRecyclerView1))
//                    if (isvisible==1){
//                        shimmerFrameLayout.startShimmer();
//                        getDataListWithLimit(limit, offset);
//                    }else if (isvisible==2){
//                        shimmerFrameLayout.startShimmer();
//                        getDataListSearch(searchView.getQuery().toString(),limit,offset);
//                    }else {
//                        emptyLayout.setVisibility(View.VISIBLE);
//                        shimmerFrameLayout.setVisibility(View.GONE);
//                        cndHomeRecyclerView1.setVisibility(View.GONE);
//                        cndHomeRecyclerView2.setVisibility(View.GONE);
//                    }
                    shimmerFrameLayout.startShimmer();
                    getDataList();
                }
            }

        });
    }

    @Override
    public void onStart() {
        super.onStart();
        shimmerFrameLayout.startShimmer();
        shimmerFrameLayout.setVisibility(View.VISIBLE);
        getDataList();
    }


    private void getDataList() {

        String cndRegisterId= SharedPrefManager.getInstance(getContext()).getValueOfUserId(getContext());

        //appliedJobsRecyclerView.setHasFixedSize(true);
        //appliedJobsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //appliedJobsRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        list=new ArrayList<>();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url_product,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++)
                            {
                                JSONObject userObject = array.getJSONObject(i);

                                list.add(new CndHomeFrgValues(
                                        userObject.getString("cmp_post_job_id"),
                                        userObject.getString("cmp_id"),
                                        userObject.getString("cmp_job_status"),
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
                                        userObject.getString("cmpPojoCompanyType"),
                                        userObject.getString("cmp_pojo_industry"),
                                        userObject.getString("cmp_pojo_company_about"),
                                        userObject.getString("cmp_pojo_company_posted_by")
                                ));
                            }
                            if (list.isEmpty() && !isvisible) {
                                emptyLayout.setVisibility(View.VISIBLE);
                                appliedJobsRecyclerView1.setVisibility(View.GONE);
                                shimmerFrameLayout.setVisibility(View.GONE);
                                shimmerFrameLayout.stopShimmer();
                            }else {
                                isvisible=true;
                                emptyLayout.setVisibility(View.GONE);
                                shimmerFrameLayout.stopShimmer();
                                shimmerFrameLayout.setVisibility(View.GONE);
                                appliedJobsRecyclerView1.setVisibility(View.VISIBLE);

                                adapter1 = new CndHomeFrgAdapter(list,getContext(),true);
                                appliedJobsRecyclerView1.setLayoutManager(manager1);
                                appliedJobsRecyclerView1.setAdapter(adapter1);
                                progressBarTimer();
                            }
                            swipeRefreshLayout.setRefreshing(false);
                        } catch (JSONException e) {
                            Toast.makeText(getContext(), "Exception: "+e.toString(), Toast.LENGTH_SHORT).show();
                            //e.printStackTrace();
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
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cndRegId", cndRegisterId);
                return params;
            }
        };
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }
    private void progressBarTimer() {
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        }, 0);
    }

}