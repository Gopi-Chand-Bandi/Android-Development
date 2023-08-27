package com.abc.justjob.Candidate.CndAplcActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentSender;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.abc.justjob.ApiFile.CndAplcCallHistory.CndAplcCallHistoryFrgAdaper;
import com.abc.justjob.ApiFile.CndAplcCallHistory.CndAplcCallHistoryFrgValues;
import com.abc.justjob.ApiFile.CompanyFetch.companyProfileResult;
import com.abc.justjob.ApiFile.CompiesDetails.CompaniesContactedFrgAdapter;
import com.abc.justjob.ApiFile.CompiesDetails.CompaniesContactedFrgValues;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class cndAplcCallsHistoryFragment extends Fragment {

    private String cndRegName,cndId;

    private final int UPDATE_REQUEST_CODE = 1001;

    private NestedScrollView nestedScrollView;
    ProgressBar progressBar;
    Boolean isScrolling = false;
    int isvisible;

    private ShimmerFrameLayout shimmerFrameLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout emptyLayout;

    private RecyclerView allCandidatesAplcCallsHistoryRecyclerView;
    private LinearLayoutManager manager;

    private CndAplcCallHistoryFrgAdaper adapter;
    private List<CndAplcCallHistoryFrgValues> list = new ArrayList<>();

    int limit=1500;
    int offset=0;

    private static final String url = "https://justjobshr.com//JustJobApi/JustJob/student_info_fetch.php?apicall=CndAplcHistory_Fetch";

    public cndAplcCallsHistoryFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        updateAvailability();

        cndRegName= SharedPrefManager.getInstance(getContext()).getUserName(getContext());

        cndId = SharedPrefManager.getInstance(getContext()).getValueOfUserId(getContext());

        View view = inflater.inflate(R.layout.fragment_cnd_aplc_calls_history,container,false);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_cnd_aplc_call_history);

        nestedScrollView = view.findViewById(R.id.candidates_Aplc_contacted_call_history_nested_scroll_view);

        shimmerFrameLayout = view.findViewById(R.id.shimmerFrameLayout_candidates_Aplc_contacted_call_history);

        allCandidatesAplcCallsHistoryRecyclerView = view.findViewById(R.id.candidates_Aplc_contacted_call_history_recycler_view);

        progressBar = view.findViewById(R.id.progress_bar_candidates_Aplc_contacted_call_history);

        emptyLayout = view.findViewById(R.id.empty_layout_candidates_Aplc_contacted_call_history);

        shimmerFrameLayout.startShimmer();

        manager = new LinearLayoutManager(getContext());

        allCandidatesAplcCallsHistoryRecyclerView.setLayoutManager(manager);

        adapter = new CndAplcCallHistoryFrgAdaper(list,getContext(),true);

        allCandidatesAplcCallsHistoryRecyclerView.setAdapter(adapter);

        isvisible=0;
        getDataListWithLimit(limit, offset,cndId);

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY ==v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()){
                    offset +=1500;
                    progressBar.setVisibility(View.VISIBLE);

                    if (isvisible==1){
                        shimmerFrameLayout.startShimmer();
                        getDataListWithLimit(limit, offset,cndId);
                    }else {
                        emptyLayout.setVisibility(View.VISIBLE);
                        shimmerFrameLayout.setVisibility(View.GONE);
                        allCandidatesAplcCallsHistoryRecyclerView.setVisibility(View.GONE);
                    }
                }
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                offset = 0;
                list.clear();

                shimmerFrameLayout.setVisibility(View.VISIBLE);
                shimmerFrameLayout.startShimmer();
                getDataListWithLimit(limit, offset,cndId);

            }
        });

        return view;


    }

    private void getDataListWithLimit(int limit, int offset, String cndId) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONArray array = new JSONArray(response);

                                for (int i = 0; i < array.length(); i++) {
                                    JSONObject userObject = array.getJSONObject(i);

                                    list.add(new CndAplcCallHistoryFrgValues(
                                            userObject.getString("date_and_time"),
                                            userObject.getString("contact_by_id"),
                                            userObject.getString("contact_by_name"),
                                            userObject.getString("contact_by_email"),
                                            userObject.getString("jobid"),
                                            userObject.getString("job_role"),
                                            userObject.getString("contacted_to_id"),
                                            userObject.getString("contacted_to_name"),
                                            userObject.getString("contacted_to_email"),
                                            userObject.getString("contacted_by_mobile"),
                                            userObject.getString("contacted_by_email"),
                                            userObject.getString("contacted_by_resume"),
                                            userObject.getString("state_from"),
                                            userObject.getString("state_to")
                                    ));
                                }
                                if (list.isEmpty()) {
                                    isvisible=0;
                                    if (!isScrolling){
                                        emptyLayout.setVisibility(View.VISIBLE);
                                        shimmerFrameLayout.setVisibility(View.GONE);
                                        allCandidatesAplcCallsHistoryRecyclerView.setVisibility(View.GONE);
                                    }
                                }else {
                                    isScrolling=true;
                                    isvisible=1;
                                    emptyLayout.setVisibility(View.GONE);
                                    shimmerFrameLayout.setVisibility(View.GONE);
                                    shimmerFrameLayout.stopShimmer();
                                    allCandidatesAplcCallsHistoryRecyclerView.setVisibility(View.VISIBLE);
                                    adapter = new CndAplcCallHistoryFrgAdaper(list,getContext(),true);
                                    allCandidatesAplcCallsHistoryRecyclerView.setLayoutManager(manager);
                                    allCandidatesAplcCallsHistoryRecyclerView.setAdapter(adapter);
                                    progressBarTimer();
                                }
                                swipeRefreshLayout.setRefreshing(false);
                            } catch (JSONException e) {
                                Log.d("Exception: ", e.getMessage());
                                emptyLayout.setVisibility(View.VISIBLE);
                                shimmerFrameLayout.setVisibility(View.GONE);
                                allCandidatesAplcCallsHistoryRecyclerView.setVisibility(View.GONE);
                            }
                        }
                },
                new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        Toast.makeText(getContext(), "No Connection/Communication Error!", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof AuthFailureError) {
                        Toast.makeText(getContext(), "Authentication/ Auth Error!", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ServerError) {
                        Toast.makeText(getContext(), "Server Error!", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof NetworkError) {
                        Toast.makeText(getContext(), "Network Error!", Toast.LENGTH_SHORT).show();
                    } else if (error instanceof ParseError) {
                        Toast.makeText(getContext(), "Parse Error!", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("limit", String.valueOf(limit));
                params.put("offset", String.valueOf(offset));
                params.put("cndId", cndId);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        requestQueue.add(stringRequest);
    }

    @Override
    public void onResume() {
        super.onResume();
        updateAvailability();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;

        if (requestCode == UPDATE_REQUEST_CODE) {
            Toast.makeText(getContext(), "Downloading...", Toast.LENGTH_SHORT).show();
            Log.d("Candidate Home Fragment", "Update info: " + resultCode);
        }
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

    private void updateAvailability() {

        AppUpdateManager appUpdateManager = AppUpdateManagerFactory.create(getContext());

        // Returns an intent object that you use to check for an update.
        Task<AppUpdateInfo> appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();

        // Checks that the platform will allow the specified type of update.
        appUpdateInfoTask.addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    // This example applies an immediate update. To apply a flexible update
                    // instead, pass in AppUpdateType.FLEXIBLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {

                try {
                    appUpdateManager.startUpdateFlowForResult(appUpdateInfo, AppUpdateType.IMMEDIATE, getActivity(), UPDATE_REQUEST_CODE);

                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}