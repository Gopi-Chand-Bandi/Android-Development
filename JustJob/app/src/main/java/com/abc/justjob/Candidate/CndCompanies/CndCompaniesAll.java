package com.abc.justjob.Candidate.CndCompanies;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
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
import android.widget.SearchView;
import android.widget.Toast;

import com.abc.justjob.ApiFile.CandidateFetchCompany.CndHomeFrgAdapter;
import com.abc.justjob.ApiFile.CandidateFetchCompany.CndHomeFrgValues;
import com.abc.justjob.ApiFile.CompiesDetails.CompaiesDetailFrgAdapter;
import com.abc.justjob.ApiFile.CompiesDetails.CompaniesDetailFrgValues;
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

import java.util.ArrayList;
import java.util.List;


public class CndCompaniesAll extends Fragment {

    private String cndRegId;

    private final int UPDATE_REQUEST_CODE = 1001;

    private SearchView searchView;


    private NestedScrollView nestedScrollView;
    ProgressBar progressBar;
    Boolean isScrolling = false;
    int currentItems, totleItems, scrollOutItems,isvisible;

    private ShimmerFrameLayout shimmerFrameLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout emptyLayout;

    private RecyclerView allCompaniesRecyclerView,allCompaniesRecyclerView2;
    private LinearLayoutManager manager,manager2;

    private CompaiesDetailFrgAdapter adapter,adapter2;
    private List<CompaniesDetailFrgValues> list = new ArrayList<>(),searchlist = new ArrayList<>();

    int limit=8;
    int offset=0;

    private static final String url_product_limit = "https://justjobshr.com//JustJobApi/JustJob/student_info_fetch.php?apicall=CompanyInfofetch&limit=";
    private static final String url_product_search = "https://justjobshr.com//JustJobApi/JustJob/student_info_fetch.php?apicall=CompanyInfofetchWithSearch&newText=";



    public CndCompaniesAll(){
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){


        updateAvailability();

        cndRegId= SharedPrefManager.getInstance(getContext()).getValueOfUserId(getContext());


        View view = inflater.inflate(R.layout.fragment_cnd_companies_all,container,false);


        searchView = view.findViewById(R.id.all_companies_home_search_view);

        nestedScrollView=view.findViewById(R.id.all_companies_nested_scroll_view);
        progressBar=view.findViewById(R.id.progress_bar_all_companies);
        swipeRefreshLayout=view.findViewById(R.id.swipe_refresh_layout_all_companies_frg);
        allCompaniesRecyclerView=view.findViewById(R.id.all_companies_recycler_view);
        allCompaniesRecyclerView2=view.findViewById(R.id.all_companies_recycler_view2);
        emptyLayout=view.findViewById(R.id.empty_layout_all_companies_activity);
        shimmerFrameLayout=view.findViewById(R.id.shimmerFrameLayout_all_companies);

        shimmerFrameLayout.startShimmer();

        manager = new LinearLayoutManager(getContext());
        manager2 = new LinearLayoutManager(getContext());

        allCompaniesRecyclerView.setLayoutManager(manager);
        allCompaniesRecyclerView2.setLayoutManager(manager2);

        adapter = new CompaiesDetailFrgAdapter(list,getContext(),true);
        adapter2 = new CompaiesDetailFrgAdapter(searchlist,getContext(),true);

        allCompaniesRecyclerView.setAdapter(adapter);

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
                    //if (newText.length()>3){
                        shimmerFrameLayout.setVisibility(View.VISIBLE);
                        shimmerFrameLayout.startShimmer();
                    //}
                    getDataListSearch(newText,limit,offset);
                }
                //searchlist.clear();
                return true;
            }
        });


        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY ==v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()){
                    offset +=8;
                    progressBar.setVisibility(View.VISIBLE);

                    if (isvisible==1){
                        shimmerFrameLayout.startShimmer();
                        getDataListWithLimit(limit, offset);
                    }else if (isvisible==2){
                        shimmerFrameLayout.startShimmer();
                        getDataListSearch(searchView.getQuery().toString(),limit,offset);
                    }else {
                        emptyLayout.setVisibility(View.VISIBLE);
                        shimmerFrameLayout.setVisibility(View.GONE);
                        allCompaniesRecyclerView.setVisibility(View.GONE);
                        allCompaniesRecyclerView2.setVisibility(View.GONE);
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

                                searchlist.add(new CompaniesDetailFrgValues(
                                        userObject.getString("cmp_date_time"),
                                        userObject.getString("cmp_uniq_id"),
                                        userObject.getString("cmp_id"),
                                        userObject.getString("cmp_candidate_view"),
                                        userObject.getString("cmp_full_name"),
                                        userObject.getString("cmp_contact"),
                                        userObject.getString("cmp_designation"),
                                        userObject.getString("cmp_email"),
                                        userObject.getString("cmp_company_name"),
                                        userObject.getString("cmp_head_office_location"),
                                        userObject.getString("cmp_industry"),
                                        userObject.getString("cmp_company_category"),
                                        userObject.getString("cmp_job_type"),
                                        userObject.getString("cmp_company_address"),
                                        userObject.getString("cmp_about_company"),
                                        userObject.getString("cmp_link"),
                                        userObject.getString("emp_size"),
                                        userObject.getString("cmp_state")
                                ));
                            }

                            if (searchlist.isEmpty()) {
                                isvisible = 3;
                                if (!isScrolling) {
                                    emptyLayout.setVisibility(View.VISIBLE);
                                    shimmerFrameLayout.setVisibility(View.GONE);
                                    allCompaniesRecyclerView.setVisibility(View.GONE);
                                    allCompaniesRecyclerView2.setVisibility(View.GONE);
                                }
                            } else {
                                isScrolling = true;
                                isvisible = 2;
                                emptyLayout.setVisibility(View.GONE);
                                shimmerFrameLayout.setVisibility(View.GONE);
                                shimmerFrameLayout.stopShimmer();
                                allCompaniesRecyclerView2.setVisibility(View.VISIBLE);
                                allCompaniesRecyclerView.setVisibility(View.GONE);

                                adapter2 = new CompaiesDetailFrgAdapter(searchlist, getContext(), true);
                                allCompaniesRecyclerView2.setLayoutManager(manager2);
                                allCompaniesRecyclerView2.setAdapter(adapter2);
//                      progressBar.setVisibility(View.GONE);
                                progressBarTimer();
                            }
                            swipeRefreshLayout.setRefreshing(false);
                        } catch (JSONException e) {
                            Log.d("Exception: ", e.getMessage());
                            //isvisible=3;
                            emptyLayout.setVisibility(View.VISIBLE);
                            shimmerFrameLayout.setVisibility(View.GONE);
                            allCompaniesRecyclerView2.setVisibility(View.GONE);
                            allCompaniesRecyclerView.setVisibility(View.GONE);
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
        //searchlist.clear();
    }

    private void getDataListWithLimit(int limit, int offset){

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url_product_limit + limit + "&offset=" + offset,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject userObject = array.getJSONObject(i);

                                list.add(new CompaniesDetailFrgValues(
                                        userObject.getString("cmp_date_time"),
                                        userObject.getString("cmp_uniq_id"),
                                        userObject.getString("cmp_id"),
                                        userObject.getString("cmp_candidate_view"),
                                        userObject.getString("cmp_full_name"),
                                        userObject.getString("cmp_contact"),
                                        userObject.getString("cmp_designation"),
                                        userObject.getString("cmp_email"),
                                        userObject.getString("cmp_company_name"),
                                        userObject.getString("cmp_head_office_location"),
                                        userObject.getString("cmp_industry"),
                                        userObject.getString("cmp_company_category"),
                                        userObject.getString("cmp_job_type"),
                                        userObject.getString("cmp_company_address"),
                                        userObject.getString("cmp_about_company"),
                                        userObject.getString("cmp_link"),
                                        userObject.getString("emp_size"),
                                        userObject.getString("cmp_state")
                                ));
                            }
                            if (list.isEmpty()) {
                                isvisible=3;
                                if (!isScrolling){
                                    emptyLayout.setVisibility(View.VISIBLE);
                                    shimmerFrameLayout.setVisibility(View.GONE);
                                    allCompaniesRecyclerView2.setVisibility(View.GONE);
                                    allCompaniesRecyclerView.setVisibility(View.GONE);
                                }
                            } else {
                                isScrolling=true;
                                isvisible=1;
                                emptyLayout.setVisibility(View.GONE);
                                shimmerFrameLayout.stopShimmer();
                                shimmerFrameLayout.setVisibility(View.GONE);

                                allCompaniesRecyclerView2.setVisibility(View.GONE);
                                allCompaniesRecyclerView.setVisibility(View.VISIBLE);

                                adapter = new CompaiesDetailFrgAdapter(list,getContext(),true);
                                allCompaniesRecyclerView.setLayoutManager(manager);
                                allCompaniesRecyclerView.setAdapter(adapter);
                                //progressBar.setVisibility(View.VISIBLE);
//                                cndHomeRecyclerView2.setVisibility(View.GONE);
//                                cndHomeRecyclerView1.setVisibility(View.VISIBLE);
//
//                                adapter = new CndHomeFrgAdapter(list, getContext(), true);
//                                cndHomeRecyclerView1.setLayoutManager(manager1);
//                                cndHomeRecyclerView1.setAdapter(adapter);
//                                progressBar.setVisibility(View.GONE);
                                progressBarTimer();
                            }
                            swipeRefreshLayout.setRefreshing(false);
                        } catch (JSONException e) {
                            Log.d("Exception: ", e.getMessage());
                            //isvisible=3;
                            emptyLayout.setVisibility(View.VISIBLE);
                            shimmerFrameLayout.setVisibility(View.GONE);
                            allCompaniesRecyclerView.setVisibility(View.GONE);
                            allCompaniesRecyclerView2.setVisibility(View.GONE);
//                            cndHomeRecyclerView1.setVisibility(View.GONE);
//                            cndHomeRecyclerView2.setVisibility(View.GONE);
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