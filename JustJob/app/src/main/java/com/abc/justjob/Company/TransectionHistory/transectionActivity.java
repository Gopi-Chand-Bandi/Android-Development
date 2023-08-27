package com.abc.justjob.Company.TransectionHistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.abc.justjob.ApiFile.CompanyFetch.CmpHomeFrgAdapter;
import com.abc.justjob.ApiFile.CompanyFetch.CmpHomeFrgValues;
import com.abc.justjob.Company.TransectionHistory.transectionActivity;
import com.abc.justjob.Company.CompanyApplicationFrg.CmpAplcAdapter;
import com.abc.justjob.Company.CompanyApplicationFrg.cmpAplcResponse;
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

public class transectionActivity extends AppCompatActivity {

    private NestedScrollView nestedScrollView;
//    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayout emptyLayout;
    private LinearLayoutManager manager;
    private RecyclerView transectionRecycler;

    private ShimmerFrameLayout shimmerFrameLayout;
    private String cmpRegId;

    ProgressBar progressBar;

    private transectionAdapter adapter;
    private List<transectionModel> list = new ArrayList<>();

    private static final String url_product = "https://justjobshr.com//JustJobApi/JustJob/Company/cmp_payments.php?apicall=get_payments_history";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transection);

//        swipeRefreshLayout=findViewById(R.id.transection_swipe_refresh_layout);
        nestedScrollView=findViewById(R.id.transection_nested_scroll_view);
        shimmerFrameLayout=findViewById(R.id.transection__shimmerFrameLayout);
        transectionRecycler=findViewById(R.id.transection_recycler);
        progressBar=findViewById(R.id.transection_progress_bar);
        emptyLayout=findViewById(R.id.transection_empty_layout);


        cmpRegId= "21"; //SharedPrefManager.getInstance(getApplicationContext()).getValueOfUserId(getApplicationContext());

        Toast.makeText(this, cmpRegId, Toast.LENGTH_SHORT).show();

        getDataList();

        /*swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataList();
                shimmerFrameLayout.startShimmer();
            }
        });
*/


    }

    private void getDataList() {

        transectionRecycler.setHasFixedSize(true);
        transectionRecycler.setLayoutManager(new LinearLayoutManager(transectionActivity.this));
        transectionRecycler.addItemDecoration(new DividerItemDecoration(transectionActivity.this, DividerItemDecoration.VERTICAL));

        StringRequest stringRequest=new StringRequest(Request.Method.POST, url_product,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject userObject = array.getJSONObject(i);
                                list.add(new transectionModel(
                                        userObject.getString("cmp_reg_id"),
                                        userObject.getString("cmp_payed_date"),
                                        userObject.getString("cmp_pay_expire"),
                                        userObject.getString("cmp_transection_id"),
                                        userObject.getString("cmp_days"),
                                        userObject.getString("cmp_pay_mrp"),
                                        userObject.getString("cmp_data_acccess"),
                                        userObject.getString("cmp_daily_data_access"),
                                        userObject.getString("cmp_weekly_data_access"),
                                        userObject.getString("cmp_post_job"),
                                        userObject.getString("cmp_unlock_cnd_aplc_contact"),
                                        userObject.getString("cmp_tenure_unlocking_day")
                                ));
//                                    adapter = new CmpAplcAdapter(list, context);
                            }
                            if (list.isEmpty()) {
                                emptyLayout.setVisibility(View.VISIBLE);
                                shimmerFrameLayout.setVisibility(View.GONE);
                                shimmerFrameLayout.stopShimmer();
                                transectionRecycler.setVisibility(View.GONE);
                                Toast.makeText(transectionActivity.this, "Data is empty...!", Toast.LENGTH_SHORT).show();
                            }else{
                                emptyLayout.setVisibility(View.GONE);
                                shimmerFrameLayout.setVisibility(View.GONE);
                                shimmerFrameLayout.stopShimmer();
                                transectionRecycler.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);

                                adapter=new transectionAdapter(list,transectionActivity.this);
                                transectionRecycler.setAdapter(adapter);

                                Toast.makeText(transectionActivity.this, "Data is fetched.", Toast.LENGTH_SHORT).show();
                            }
                         //   swipeRefreshLayout.setRefreshing(false);
                        } catch (JSONException e) {
                            Toast.makeText(transectionActivity.this, "exception: "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(transectionActivity.this, "Error: "+volleyError.toString(), Toast.LENGTH_SHORT).show();

                        shimmerFrameLayout.stopShimmer();
                        shimmerFrameLayout.setVisibility(View.GONE);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cmp_reg_id", cmpRegId);
                return params;
            }
        };
        Volley.newRequestQueue(transectionActivity.this).add(stringRequest);

    }
}