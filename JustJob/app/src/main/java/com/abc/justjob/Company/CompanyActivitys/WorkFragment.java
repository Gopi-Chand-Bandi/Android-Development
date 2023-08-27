package com.abc.justjob.Company.CompanyActivitys;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.abc.justjob.ApiFile.CandidateFetchCompany.CndHomeFrgAdapter;
import com.abc.justjob.ApiFile.CandidateFetchCompany.CndHomeFrgValues;
import com.abc.justjob.ApiFile.CompanyFetch.CmpHomeFrgAdapter;
import com.abc.justjob.ApiFile.CompanyFetch.CmpHomeFrgValues;
import com.abc.justjob.Company.FilterOperation.FilterOperationActivity;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class WorkFragment extends Fragment {

    Context context;
    private final int UPDATE_REQUEST_CODE = 1001;
    private SearchView searchView;
    private ImageView filterIv;

    private NestedScrollView nestedScrollView;
    ProgressBar progressBar;
    Boolean isScrolling = false;
    int currentItems, totleItems, scrollOutItems,isvisible;

    private ShimmerFrameLayout shimmerFrameLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout emptyLayout;

    private String cmpRegId;

    private RecyclerView companyWorkRecycler,companyWorkRecycler2;
    private LinearLayoutManager manager1,manager2;

    private CmpHomeFrgAdapter adapter,adapter1;
    private List<CmpHomeFrgValues> list = new ArrayList<>(),searchlist = new ArrayList<>();

    private static final String url_product_limit = "https://justjobshr.com//JustJobApi/JustJob/Company/cmp_fetch.php?apicall=fetchAllCandidatesWithLimit&limit=";

    private static final String url_product_search = "https://justjobshr.com//JustJobApi/JustJob/Company/cmp_fetch.php?apicall=fetchAllCandidatesWithSearch&newText=";

    private int limit = 5, offset = 0;

    public WorkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        updateAvailability();

        cmpRegId=SharedPrefManager.getInstance(getContext()).getValueOfUserId(getContext());

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_work, container, false);

        context = requireActivity().getApplicationContext();

        searchView = view.findViewById(R.id.cmp_Home_search_sv);
        filterIv = view.findViewById(R.id.cmp_home_filter_iv);
        nestedScrollView = view.findViewById(R.id.cmp_home_nested_scroll_view);
        progressBar = view.findViewById(R.id.cmp_home_load_progress_bar);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        companyWorkRecycler = view.findViewById(R.id.company_work_recycler1);
        companyWorkRecycler2 = view.findViewById(R.id.company_work_recycler2);
        emptyLayout = view.findViewById(R.id.cmp_home_empty_layout);
        shimmerFrameLayout = view.findViewById(R.id.shimmerFrameLayout_cmp_home);

        shimmerFrameLayout.startShimmer();

        manager1 = new LinearLayoutManager(getContext());
        manager2 = new LinearLayoutManager(getContext());

        companyWorkRecycler.setLayoutManager(manager1);
        companyWorkRecycler2.setLayoutManager(manager2);

        adapter = new CmpHomeFrgAdapter(list, getContext(), true);
        adapter1 = new CmpHomeFrgAdapter(searchlist,getContext(),true);

        companyWorkRecycler.setAdapter(adapter);

        isvisible=0;
        getDataListWithLimit(limit, offset);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                isScrolling=false;
                if (query.isEmpty()){
                    offset=0;
                    searchlist.clear();
                    shimmerFrameLayout.setVisibility(View.VISIBLE);
                    shimmerFrameLayout.startShimmer();
                    isScrolling=false;
                    getDataListWithLimit(limit, offset);
                    searchView.clearFocus();
                }else {
                    offset=0;
                    searchlist.clear();
                    shimmerFrameLayout.setVisibility(View.VISIBLE);
                    shimmerFrameLayout.startShimmer();
                    getDataListSearch(query,limit,offset);
                    searchView.clearFocus();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                isScrolling=false;
                if (newText.isEmpty()){
                    offset=0;
                    searchlist.clear();
                    shimmerFrameLayout.setVisibility(View.VISIBLE);
                    shimmerFrameLayout.startShimmer();
                    isScrolling=false;
                    getDataListWithLimit(limit, offset);
                    searchView.clearFocus();
                }else {
                    searchlist.clear();
                    if (newText.length()>3){
                    shimmerFrameLayout.setVisibility(View.VISIBLE);
                    shimmerFrameLayout.startShimmer();
                    }
                    getDataListSearch(newText,limit,offset);
                }
                searchlist.clear();
                return true;
            }
        });

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    offset += 5;
                    progressBar.setVisibility(View.VISIBLE);
                    //if (isVisible(cndHomeRecyclerView1))
                    if (isvisible==1){
                        shimmerFrameLayout.startShimmer();
                        getDataListWithLimit(limit, offset);
                    }
                    else if (isvisible==2){
                        shimmerFrameLayout.startShimmer();
                        getDataListSearch(searchView.getQuery().toString(),limit,offset);
                    }
                    else {
                        emptyLayout.setVisibility(View.VISIBLE);
                        shimmerFrameLayout.setVisibility(View.GONE);
                        companyWorkRecycler.setVisibility(View.GONE);
                        companyWorkRecycler2.setVisibility(View.GONE);
                    }

                }
            }

        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                searchView.clearFocus();
                searchView.setQuery("", true);
                offset = 0;
                shimmerFrameLayout.setVisibility(View.VISIBLE);
                shimmerFrameLayout.startShimmer();
                getDataListWithLimit(limit, offset);

            }
        });

        return view;
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
            Log.d("Company Home Fragment", "Update info: " + resultCode);
        }
    }

    private void getDataListSearch(String newText,int limit,int offset) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url_product_search + newText+ "&limit=" + limit +  "&offset=" + offset,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject userObject = array.getJSONObject(i);

                                searchlist.add(new CmpHomeFrgValues(
                                        userObject.getString("cd_id"),
                                        userObject.getString("register_login_id"),
                                        userObject.getString("cd_gender"),
                                        userObject.getString("cd_full_name"),
                                        userObject.getString("cd_email"),
                                        userObject.getString("cd_contact_no"),
                                        userObject.getString("cd_alter_contact"),
                                        userObject.getString("cd_state"),
                                        userObject.getString("cd_city"),
                                        userObject.getString("cd_current_location"),
                                        userObject.getString("cd_date_of_birth"),
                                        userObject.getString("cd_communication"),
                                        userObject.getString("cd_job_profile_one"),
                                        userObject.getString("cd_job_designation_one"),
                                        userObject.getString("cd_job_profile_two"),
                                        userObject.getString("cd_job_designation_two"),
                                        userObject.getString("cd_qualification_std"),
                                        userObject.getString("cd_qualification_stream"),
                                        userObject.getString("cd_college_name"),
                                        userObject.getString("cd_qualification_start_date"),
                                        userObject.getString("cd_qualification_end_date"),
                                        userObject.getString("cd_fresher_intern_exp"),
                                        userObject.getString("cd_exp_job_industry"),
                                        userObject.getString("cd_exp_company_name"),
                                        userObject.getString("cd_exp_current_salary"),
                                        userObject.getString("cd_exp_start_date"),
                                        userObject.getString("cd_exp_end_date"),
                                        userObject.getString("cd_employment_type"),
                                        userObject.getString("cd_location_prefer"),
                                        userObject.getString("cd_languages"),
                                        userObject.getString("cd_vehicle"),
                                        userObject.getString("cd_licence"),
                                        userObject.getString("cd_documents"),
                                        userObject.getString("cd_skill"),
                                        userObject.getString("cd_reference"),
                                        userObject.getString("cd_resume_cv_url")
                                ));
                            }
                            if (searchlist.isEmpty()) {
                                isvisible=3;
                                if (!isScrolling){
                                    emptyLayout.setVisibility(View.VISIBLE);
                                    shimmerFrameLayout.setVisibility(View.GONE);
                                    companyWorkRecycler.setVisibility(View.GONE);
                                    companyWorkRecycler2.setVisibility(View.GONE);
                                }
                            } else {
                                isScrolling=true;
                                isvisible=2;
                                emptyLayout.setVisibility(View.GONE);
                                shimmerFrameLayout.setVisibility(View.GONE);
                                shimmerFrameLayout.stopShimmer();
                                companyWorkRecycler2.setVisibility(View.VISIBLE);
                                companyWorkRecycler.setVisibility(View.GONE);

                                adapter1 = new CmpHomeFrgAdapter(searchlist, getContext(), true);
                                companyWorkRecycler2.setLayoutManager(manager2);
                                companyWorkRecycler2.setAdapter(adapter1);
//                      progressBar.setVisibility(View.GONE);
                                progressBarTimer();
                            }
                            swipeRefreshLayout.setRefreshing(false);
                        } catch (JSONException e) {
                            Log.d("Exception: ", e.getMessage());
                            //isvisible=3;
                            emptyLayout.setVisibility(View.VISIBLE);
                            shimmerFrameLayout.setVisibility(View.GONE);
                            companyWorkRecycler2.setVisibility(View.GONE);
                            companyWorkRecycler.setVisibility(View.GONE);
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

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }

    private void getDataListWithLimit(int limit,int offset) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url_product_limit + limit + "&offset=" + offset,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject userObject = array.getJSONObject(i);

                                list.add(new CmpHomeFrgValues(
                                        userObject.getString("cd_id"),
                                        userObject.getString("register_login_id"),
                                        userObject.getString("cd_gender"),
                                        userObject.getString("cd_full_name"),
                                        userObject.getString("cd_email"),
                                        userObject.getString("cd_contact_no"),
                                        userObject.getString("cd_alter_contact"),
                                        userObject.getString("cd_state"),
                                        userObject.getString("cd_city"),
                                        userObject.getString("cd_current_location"),
                                        userObject.getString("cd_date_of_birth"),
                                        userObject.getString("cd_communication"),
                                        userObject.getString("cd_job_profile_one"),
                                        userObject.getString("cd_job_designation_one"),
                                        userObject.getString("cd_job_profile_two"),
                                        userObject.getString("cd_job_designation_two"),
                                        userObject.getString("cd_qualification_std"),
                                        userObject.getString("cd_qualification_stream"),
                                        userObject.getString("cd_college_name"),
                                        userObject.getString("cd_qualification_start_date"),
                                        userObject.getString("cd_qualification_end_date"),
                                        userObject.getString("cd_fresher_intern_exp"),
                                        userObject.getString("cd_exp_job_industry"),
                                        userObject.getString("cd_exp_company_name"),
                                        userObject.getString("cd_exp_current_salary"),
                                        userObject.getString("cd_exp_start_date"),
                                        userObject.getString("cd_exp_end_date"),
                                        userObject.getString("cd_employment_type"),
                                        userObject.getString("cd_location_prefer"),
                                        userObject.getString("cd_languages"),
                                        userObject.getString("cd_vehicle"),
                                        userObject.getString("cd_licence"),
                                        userObject.getString("cd_documents"),
                                        userObject.getString("cd_skill"),
                                        userObject.getString("cd_reference"),
                                        userObject.getString("cd_resume_cv_url")
                                ));
                            }
                            if (list.isEmpty()) {
                                isvisible=3;
                                if (!isScrolling){
                                    emptyLayout.setVisibility(View.VISIBLE);
                                    shimmerFrameLayout.setVisibility(View.GONE);
                                    companyWorkRecycler.setVisibility(View.GONE);
                                    companyWorkRecycler2.setVisibility(View.GONE);
                                }
                            } else {
                                isScrolling=true;
                                isvisible=1;
                                emptyLayout.setVisibility(View.GONE);
                                shimmerFrameLayout.stopShimmer();
                                shimmerFrameLayout.setVisibility(View.GONE);
                                companyWorkRecycler2.setVisibility(View.GONE);
                                companyWorkRecycler.setVisibility(View.VISIBLE);

                                adapter = new CmpHomeFrgAdapter(list, getContext(), true);
                                companyWorkRecycler.setLayoutManager(manager1);
                                companyWorkRecycler.setAdapter(adapter);
//                      progressBar.setVisibility(View.GONE);
                                progressBarTimer();
                            }
                            swipeRefreshLayout.setRefreshing(false);
                        } catch (JSONException e) {
                            Log.d("Exception: ", e.getMessage());
                            //isvisible=3;
                            emptyLayout.setVisibility(View.VISIBLE);
                            shimmerFrameLayout.setVisibility(View.GONE);
                            companyWorkRecycler2.setVisibility(View.GONE);
                            companyWorkRecycler.setVisibility(View.GONE);
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

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());
        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }

    private void progressBarTimer() {
        progressBar.setVisibility(View.VISIBLE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
            }
        }, 3000);
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