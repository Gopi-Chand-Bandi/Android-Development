package com.abc.justjob.Candidate.CandidateActivityFragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
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
import android.widget.AbsListView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.ApiInterface;
import com.abc.justjob.ApiFile.CandidateFetchCompany.CndHomeFrgAdapter;
import com.abc.justjob.ApiFile.CandidateFetchCompany.CndHomeFrgValues;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
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
import com.google.android.material.tabs.TabLayout;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private final int UPDATE_REQUEST_CODE = 1001;
    private SearchView searchView;
    private ImageView filterView;

    private NestedScrollView nestedScrollView;
    ProgressBar progressBar;
    Boolean isScrolling = false;
    int currentItems, totleItems, scrollOutItems,isvisible;

    private ShimmerFrameLayout shimmerFrameLayout;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout emptyLayout;

    private String cndRegId;

    private RecyclerView cndHomeRecyclerView1,cndHomeRecyclerView2;
    private LinearLayoutManager manager1,manager2;

    private CndHomeFrgAdapter adapter,adapter1;
    private List<CndHomeFrgValues> list = new ArrayList<>(),searchlist = new ArrayList<>();
    //private List<CndHomeFrgValues> searchlist = new ArrayList<>();
    private static final String url_product = "https://justjobshr.com//JustJobApi/JustJob/student_info_fetch.php?apicall=fetchAllPostedJobs";
    private static final String url_product_search = "https://justjobshr.com//JustJobApi/JustJob/student_info_fetch.php?apicall=fetchPostedJobswithsearch&newText=";

    int limit = 5;
    int offset = 0;
//    private static final String url_product_limit="https://justjobshr.com//JustJobApi/JustJob/fetch_posted_jobs_cnd_home.php?limit=";

    //private static final String url_product_limit = "https://justjobshr.com//JustJobApi/JustJob/student_info_fetch.php?apicall=fetchPostedJobsWithLimit";

    private static final String url_product_limit = "https://justjobshr.com//JustJobApi/JustJob/student_info_fetch.php?apicall=fetchPostedJobsWithLimit&limit=";

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        updateAvailability();



//        checkNightModeActivated();

        cndRegId=SharedPrefManager.getInstance(getContext()).getValueOfUserId(getContext());

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        searchView = view.findViewById(R.id.cnd_home_search_view);
        filterView = view.findViewById(R.id.cnd_home_filter_iv);
        nestedScrollView = view.findViewById(R.id.cnd_home_nested_scroll_view);
        progressBar = view.findViewById(R.id.progress_bar_cnd_home);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout_home_frg);
        cndHomeRecyclerView1 = view.findViewById(R.id.cnd_home_recycler_view1);
        cndHomeRecyclerView2 = view.findViewById(R.id.cnd_home_recycler_view2);
        emptyLayout = view.findViewById(R.id.empty_layout_cnd_home_activity);
        shimmerFrameLayout = view.findViewById(R.id.shimmerFrameLayout_cnd_home);

        shimmerFrameLayout.startShimmer();

        manager1 = new LinearLayoutManager(getContext());
        manager2 = new LinearLayoutManager(getContext());
        cndHomeRecyclerView1.setLayoutManager(manager1);
        cndHomeRecyclerView2.setLayoutManager(manager2);
        adapter = new CndHomeFrgAdapter(list, getContext(), true);
        adapter1 = new CndHomeFrgAdapter(searchlist, getContext(), true);
//        adapter1 = new CndHomeFrgAdapter(searchlist,getContext(),true);
        cndHomeRecyclerView1.setAdapter(adapter);
//        getDataList();
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

        //searchInit();

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
                        cndHomeRecyclerView1.setVisibility(View.GONE);
                        cndHomeRecyclerView2.setVisibility(View.GONE);
                    }

                }
            }

        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

//                getDataList();

                searchView.clearFocus();
                searchView.setQuery("", true);
                offset = 0;
                shimmerFrameLayout.setVisibility(View.VISIBLE);
                shimmerFrameLayout.startShimmer();
//                if (isvisible==2){
//                    shimmerFrameLayout.setVisibility(View.VISIBLE);
//                    shimmerFrameLayout.startShimmer();
//                    getDataListSearch(searchView.getQuery().toString(),limit,offset);
//                }else {
//                    shimmerFrameLayout.setVisibility(View.VISIBLE);
//                    shimmerFrameLayout.startShimmer();
                    getDataListWithLimit(limit, offset);
//                }
//                getDataListWithLimit();
            }
        });


        /*cndHomeRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling=true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                currentItems=manager.getChildCount();
                totleItems=manager.getItemCount();
                scrollOutItems=manager.findFirstCompletelyVisibleItemPosition();

                if (isScrolling && (currentItems + scrollOutItems == totleItems)) {
                    //data fetch
                    isScrolling=false;
                    fetchData();
                }
            }
        });*/


        return view;
    }

//    private void showHomeFragment() {
//        HomeFragment homeFragment = new HomeFragment();
//        FragmentManager fragmentManager = getChildFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.home_layout, homeFragment)
//                .commitAllowingStateLoss();
//    }
//
//    private void showApplicationFragment() {
//        ApplicationFragment applicationFragment = new ApplicationFragment();
//        FragmentManager fragmentManager = getChildFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.home_layout, applicationFragment)
//                .commitAllowingStateLoss();
//    }

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

    /*public void checkNightModeActivated() {
        if (SharedPrefManager.getInstance(getContext()).GetIsDarkMode(getContext())) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }*/

    private boolean isLastItemDisplaying(RecyclerView recyclerView) {
        //Check if the adapter item count is greater than 0
        if (recyclerView.getAdapter().getItemCount() != 0) {
            //get the last visible item on screen using the layout manager
            int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();

            if (lastVisibleItemPosition != RecyclerView.NO_POSITION && lastVisibleItemPosition == recyclerView.getAdapter().getItemCount() - 1) {
                return true;
            }
        }
        return false;
    }

    private void searchInit() {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //adapter.getFilter().filter(query);
                //getDataListSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                //adapter.getFilter().filter(newText);
                //getDataListSearch(newText);

                return false;

                /*ArrayList<CndHomeFrgValues> filterList=new ArrayList<CndHomeFrgValues>();
                for (CndHomeFrgValues values : list){
                    if (values.getCmp_pojo_title().toLowerCase().contains(newText.toLowerCase())) {
                        filterList.add(values);
                    } else if (values.getCmp_pojo_locality().toLowerCase().contains(newText.toLowerCase())) {
                        filterList.add(values);
                    } else if (values.getCmp_pojo_company_email().toLowerCase().contains(newText.toLowerCase())) {
                        filterList.add(values);
                    } else if (values.getCmp_pojo_description().toLowerCase().contains(newText.toLowerCase())) {
                        filterList.add(values);
                    } else if (values.getCmp_pojo_job_type().toLowerCase().contains(newText.toLowerCase())) {
                        filterList.add(values);
                    } else if (values.getCmp_pojo_company_type().toLowerCase().contains(newText.toLowerCase())) {
                        filterList.add(values);
                    } else if (values.getCmp_pojo_role().toLowerCase().contains(newText.toLowerCase())) {
                        filterList.add(values);
                    }
                }
                CndHomeFrgAdapter cndHomeFrgAdapter=new CndHomeFrgAdapter(filterList,getContext(),true);
                cndHomeRecyclerView.setAdapter(cndHomeFrgAdapter);
                return false;*/

            }
        });

        filterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupFilterDialog();
            }
        });
    }

    private void popupFilterDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext(), android.app.AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        final View view = getLayoutInflater().inflate(R.layout.cnd_home_filter, null);

        RadioButton titleRb, locationRb, cmpTypeRb, salaryRb, experienceRb, newJobsRb;

        titleRb = view.findViewById(R.id.cnd_home_filter_title_rb);
        locationRb = view.findViewById(R.id.cnd_home_filter_location_rb);
        cmpTypeRb = view.findViewById(R.id.cnd_home_filter_type_rb);
        salaryRb = view.findViewById(R.id.cnd_home_filter_salary_rb);
        experienceRb = view.findViewById(R.id.cnd_home_filter_experience_rb);
        newJobsRb = view.findViewById(R.id.cnd_home_filter_new_job_rb);

        builder.setView(view);
        builder.setCancelable(true);
        builder.setTitle("Short");

        titleRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    locationRb.setChecked(false);
                    cmpTypeRb.setChecked(false);
                    salaryRb.setChecked(false);
                    experienceRb.setChecked(false);
                    newJobsRb.setChecked(false);
                }
            }
        });
        locationRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    titleRb.setChecked(false);
                    cmpTypeRb.setChecked(false);
                    salaryRb.setChecked(false);
                    experienceRb.setChecked(false);
                    newJobsRb.setChecked(false);
                }
            }
        });
        cmpTypeRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    locationRb.setChecked(false);
                    titleRb.setChecked(false);
                    salaryRb.setChecked(false);
                    experienceRb.setChecked(false);
                    newJobsRb.setChecked(false);
                }
            }
        });
        salaryRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    locationRb.setChecked(false);
                    cmpTypeRb.setChecked(false);
                    titleRb.setChecked(false);
                    experienceRb.setChecked(false);
                    newJobsRb.setChecked(false);
                }
            }
        });
        experienceRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    locationRb.setChecked(false);
                    cmpTypeRb.setChecked(false);
                    salaryRb.setChecked(false);
                    titleRb.setChecked(false);
                    newJobsRb.setChecked(false);
                }
            }
        });
        newJobsRb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    locationRb.setChecked(false);
                    cmpTypeRb.setChecked(false);
                    salaryRb.setChecked(false);
                    experienceRb.setChecked(false);
                    titleRb.setChecked(false);
                }
            }
        });

        builder.setPositiveButton("Ok", (dialog, which) -> {
            if (titleRb.isChecked()) {
                Collections.sort(list, new Comparator<CndHomeFrgValues>() {
                    @Override
                    public int compare(CndHomeFrgValues o1, CndHomeFrgValues o2) {
                        return o1.getCmp_pojo_title().compareTo(o2.getCmp_pojo_title());
                    }
                });
            } else if (locationRb.isChecked()) {
                Collections.sort(list, new Comparator<CndHomeFrgValues>() {
                    @Override
                    public int compare(CndHomeFrgValues o1, CndHomeFrgValues o2) {
                        return o1.getCmp_pojo_locality().compareTo(o2.getCmp_pojo_locality());
                    }
                });
            } else if (cmpTypeRb.isChecked()) {
                Collections.sort(list, new Comparator<CndHomeFrgValues>() {
                    @Override
                    public int compare(CndHomeFrgValues o1, CndHomeFrgValues o2) {
                        return o1.getCmp_pojo_job_type().compareTo(o2.getCmp_pojo_job_type());
                    }
                });
            } else if (salaryRb.isChecked()) {
                Collections.sort(list, new Comparator<CndHomeFrgValues>() {
                    @Override
                    public int compare(CndHomeFrgValues o1, CndHomeFrgValues o2) {
                        return o1.getCmp_pojo_offering_min_salary().compareTo(o2.getCmp_pojo_offering_min_salary());
                    }
                });
            } else if (experienceRb.isChecked()) {
                Collections.sort(list, new Comparator<CndHomeFrgValues>() {
                    @Override
                    public int compare(CndHomeFrgValues o1, CndHomeFrgValues o2) {
                        return o1.getCmp_pojo_min_exp().compareTo(o2.getCmp_pojo_min_exp());
                    }
                });
            } else if (newJobsRb.isChecked()) {
                Collections.sort(list, new Comparator<CndHomeFrgValues>() {
                    @Override
                    public int compare(CndHomeFrgValues o1, CndHomeFrgValues o2) {
                        return o2.getCmp_id().compareTo(o1.getCmp_id());
                    }
                });
            }
            adapter.notifyDataSetChanged();
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> {
            Toast.makeText(getContext(), "Canceled...", Toast.LENGTH_SHORT).show();
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }




    private void getDataListSearch(String newText,int limit,int offset) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url_product_search + newText+ "&limit=" + limit +  "&offset=" + offset+ "&cndRegId=" +cndRegId,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {

                                JSONObject userObject = array.getJSONObject(i);

                                searchlist.add(new CndHomeFrgValues(
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
                                        userObject.getString("cmp_pojo_company_type"),
                                        userObject.getString("cmp_pojo_industry"),
                                        userObject.getString("cmp_pojo_company_about"),
                                        userObject.getString("cmp_pojo_company_posted_by")
                                ));
                            }
                            if (searchlist.isEmpty()) {
                                isvisible=3;
                                if (!isScrolling){
                                    emptyLayout.setVisibility(View.VISIBLE);
                                    shimmerFrameLayout.setVisibility(View.GONE);
                                    cndHomeRecyclerView1.setVisibility(View.GONE);
                                    cndHomeRecyclerView2.setVisibility(View.GONE);
                                }
                            } else {
                                isScrolling=true;
                                isvisible=2;
                                emptyLayout.setVisibility(View.GONE);
                                shimmerFrameLayout.setVisibility(View.GONE);
                                shimmerFrameLayout.stopShimmer();
                                cndHomeRecyclerView2.setVisibility(View.VISIBLE);
                                cndHomeRecyclerView1.setVisibility(View.GONE);

                                adapter1 = new CndHomeFrgAdapter(searchlist, getContext(), true);
                                cndHomeRecyclerView2.setLayoutManager(manager2);
                                cndHomeRecyclerView2.setAdapter(adapter1);
//                      progressBar.setVisibility(View.GONE);
                                progressBarTimer();
                            }
                            swipeRefreshLayout.setRefreshing(false);
                        } catch (JSONException e) {
                            Log.d("Exception: ", e.getMessage());
                            //isvisible=3;
                            emptyLayout.setVisibility(View.VISIBLE);
                            shimmerFrameLayout.setVisibility(View.GONE);
                            cndHomeRecyclerView2.setVisibility(View.GONE);
                            cndHomeRecyclerView1.setVisibility(View.GONE);
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


    private void getDataListWithLimit(int limit, int offset) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url_product_limit + limit + "&offset=" + offset+ "&cndRegId=" +cndRegId,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
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
                                        userObject.getString("cmp_pojo_company_type"),
                                        userObject.getString("cmp_pojo_industry"),
                                        userObject.getString("cmp_pojo_company_about"),
                                        userObject.getString("cmp_pojo_company_posted_by")
                                ));
                            }
                            if (list.isEmpty()) {
                                isvisible=3;
                                if (!isScrolling){
                                    emptyLayout.setVisibility(View.VISIBLE);
                                    shimmerFrameLayout.setVisibility(View.GONE);
                                    cndHomeRecyclerView1.setVisibility(View.GONE);
                                    cndHomeRecyclerView2.setVisibility(View.GONE);
                                }
                            } else {
                                isScrolling=true;
                                isvisible=1;
                                emptyLayout.setVisibility(View.GONE);
                                shimmerFrameLayout.stopShimmer();
                                shimmerFrameLayout.setVisibility(View.GONE);
                                cndHomeRecyclerView2.setVisibility(View.GONE);
                                cndHomeRecyclerView1.setVisibility(View.VISIBLE);

                                adapter = new CndHomeFrgAdapter(list, getContext(), true);
                                cndHomeRecyclerView1.setLayoutManager(manager1);
                                cndHomeRecyclerView1.setAdapter(adapter);
//                                progressBar.setVisibility(View.GONE);
                                progressBarTimer();
                            }
                            swipeRefreshLayout.setRefreshing(false);
                        } catch (JSONException e) {
                            Log.d("Exception: ", e.getMessage());
                            //isvisible=3;
                            emptyLayout.setVisibility(View.VISIBLE);
                            shimmerFrameLayout.setVisibility(View.GONE);
                            cndHomeRecyclerView1.setVisibility(View.GONE);
                            cndHomeRecyclerView2.setVisibility(View.GONE);
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