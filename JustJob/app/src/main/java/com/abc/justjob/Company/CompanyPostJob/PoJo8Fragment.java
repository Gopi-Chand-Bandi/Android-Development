package com.abc.justjob.Company.CompanyPostJob;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import com.abc.justjob.ApiFile.ApiClient;
import com.abc.justjob.ApiFile.Api_cmp_post_job;
import com.abc.justjob.ApiFile.LoginRegisterApi.Result;
import com.abc.justjob.R;
import com.abc.justjob.SharedPrefManager;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoJo8Fragment extends Fragment {

    private CheckBox shiftDayCb,shiftNightCb,shiftRotateCb;
    private LinearLayout dayLinear,nightLinear,rotateLinear;
    private boolean dayBl,nightBl,rotateBl;
    private TextInputEditText dayStartEt,dayEndEt,nightStartEt,nightEndEt,rotateStartEt,rotateEndEt;

    private ImageView ibSubmitFragmentNight;

    String lastUserId;

    public PoJo8Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_po_jo9, container, false);

        lastUserId= SharedPrefManager.getInstance(getContext()).getValueOfLastId(getContext());
        functionOperation(v);

        return v;
    }

    private void insertToDatabase(String lastUserId, String dayStartStr, String dayEndStr,
                                  String nightStartStr, String nightEndStr, String rotateStartStr,
                                  String rotateEndStr) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Inserting Data into database...");
        progressDialog.show();

        Api_cmp_post_job api = ApiClient.getApiClient().create(Api_cmp_post_job.class);

        Call<Result> call= api.cmpPostJobInterfaceNine(lastUserId,dayStartStr,dayEndStr,nightStartStr,
                nightEndStr,rotateStartStr,rotateEndStr);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                progressDialog.dismiss();

                try {
                    if (!response.body().getError()) {
                        Toast.makeText(getContext(), "inserted successful", Toast.LENGTH_SHORT).show();

                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.post_job_fragments_container,
                                new PoJo9Fragment()).commit();
                    } else {
                        Toast.makeText(getContext(), Objects.requireNonNull(response.body()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Exception: "+e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {

                progressDialog.dismiss();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void functionOperation(View view) {

        shiftDayCb=view.findViewById(R.id.shift_day_cb);
        shiftNightCb=view.findViewById(R.id.shift_night_cb);
        shiftRotateCb=view.findViewById(R.id.shift_rotate_cb);

        dayLinear=view.findViewById(R.id.linear_day);
        nightLinear=view.findViewById(R.id.linear_night);
        rotateLinear=view.findViewById(R.id.linear_rotate);

        dayStartEt=view.findViewById(R.id.day_start_et);
        dayEndEt=view.findViewById(R.id.day_end_et);
        nightStartEt=view.findViewById(R.id.night_start_et);
        nightEndEt=view.findViewById(R.id.night_end_et);
        rotateStartEt=view.findViewById(R.id.rotate_start_et);
        rotateEndEt=view.findViewById(R.id.rotate_end_et);

        ibSubmitFragmentNight=view.findViewById(R.id.pj_ib_9_submit);

        operationClicked();

        ibSubmitFragmentNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dayBl||nightBl||rotateBl) {
                    clickOperation();
                }else{
                    Toast.makeText(getContext(), "Select once data...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void clickOperation() {
        String dayStartStr,dayEndStr,nightStartStr,nightEndStr,rotateStartStr,rotateEndStr;

        if (dayBl) {
            dayStartStr=dayStartEt.getText().toString();
            dayEndStr=dayEndEt.getText().toString();
        }else{
            dayStartStr="--";
            dayEndStr="--";
        }
        if (nightBl) {
            nightStartStr=nightStartEt.getText().toString();
            nightEndStr=nightEndEt.getText().toString();
        }else{
            nightStartStr="--";
            nightEndStr="--";
        }
        if (rotateBl) {
            rotateStartStr=rotateStartEt.getText().toString();
            rotateEndStr=rotateEndEt.getText().toString();
        }else{
            rotateStartStr="--";
            rotateEndStr="--";
        }

        checkDataEmpty(dayStartStr,dayEndStr,nightStartStr,nightEndStr,rotateStartStr,rotateEndStr);
    }

    private void checkDataEmpty(String dayStartStr, String dayEndStr, String nightStartStr, String nightEndStr, String rotateStartStr, String rotateEndStr) {

        if (dayBl && isStringEmpty(dayStartStr,dayEndStr)) {
            Toast.makeText(getContext(), "Day is empty...", Toast.LENGTH_SHORT).show();
        } else if (nightBl && isStringEmpty(nightStartStr, nightEndStr)) {
            Toast.makeText(getContext(), "Night is Empty...", Toast.LENGTH_SHORT).show();
        } else if (rotateBl && isStringEmpty(rotateStartStr, rotateEndStr)) {
            Toast.makeText(getContext(), "Rotate is empty...", Toast.LENGTH_SHORT).show();
        }else{
//            insertToDatabase(lastUserId,dayStartStr,dayEndStr,nightStartStr,nightEndStr,rotateStartStr,rotateEndStr);

            store8Fragment(dayStartStr,dayEndStr,nightStartStr,nightEndStr,rotateStartStr,rotateEndStr);
        }
    }

    private void store8Fragment(String dayStartStr, String dayEndStr, String nightStartStr, String nightEndStr, String rotateStartStr, String rotateEndStr) {
        postJobSharedPref.getInstance(getContext()).fragment8SharedPref(dayStartStr,dayEndStr,nightStartStr,nightEndStr,rotateStartStr,rotateEndStr);

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.post_job_fragments_container,
                new PoJo9Fragment()).commit();
    }

    private void operationClicked() {

        int width = (int)(getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (int)(getResources().getDisplayMetrics().heightPixels*0.90);


        dayStartEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int[] t2Hour = new int[1];
                final int[] t2Minute = new int[1];
                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
                        android.app.AlertDialog.THEME_HOLO_DARK,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //Initialize hour and minute
                                t2Hour[0] = hourOfDay;
                                t2Minute[0] = minute;
                                //store hour and minute in string
                                String time = t2Hour[0] + ":" + t2Minute[0];
                                //Initialize 24 hours time format
                                SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                                try {
                                    Date date = f24Hours.parse(time);
                                    //Initialize 12 hours time
                                    SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                                    //set selected time on text view
                                    dayStartEt.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                //set transparent background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //displayed previous selected time
                timePickerDialog.updateTime(t2Hour[0], t2Minute[0]);
                //show dialog
                timePickerDialog.show();

            }
        });
        dayEndEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int[] t2Hour = new int[1];
                final int[] t2Minute = new int[1];
                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
                        android.app.AlertDialog.THEME_HOLO_DARK,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //Initialize hour and minute
                                t2Hour[0] = hourOfDay;
                                t2Minute[0] = minute;
                                //store hour and minute in string
                                String time = t2Hour[0] + ":" + t2Minute[0];
                                //Initialize 24 hours time format
                                SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                                try {
                                    Date date = f24Hours.parse(time);
                                    //Initialize 12 hours time
                                    SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                                    //set selected time on text view
                                    dayEndEt.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                //displayed previous selected time
                timePickerDialog.updateTime(t2Hour[0], t2Minute[0]);
                //show dialog
                timePickerDialog.show();

            }
        });
        nightStartEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int[] t2Hour = new int[1];
                final int[] t2Minute = new int[1];
                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
                        android.app.AlertDialog.THEME_HOLO_DARK,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //Initialize hour and minute
                                t2Hour[0] = hourOfDay;
                                t2Minute[0] = minute;
                                //store hour and minute in string
                                String time = t2Hour[0] + ":" + t2Minute[0];
                                //Initialize 24 hours time format
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);
                                    //Initialize 12 hours time
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    //set selected time on text view
                                    nightStartEt.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                //displayed previous selected time
                timePickerDialog.updateTime(t2Hour[0], t2Minute[0]);
                //show dialog
                timePickerDialog.show();

            }
        });
        nightEndEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int[] t2Hour = new int[1];
                final int[] t2Minute = new int[1];
                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
                        android.app.AlertDialog.THEME_HOLO_DARK,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //Initialize hour and minute
                                t2Hour[0] = hourOfDay;
                                t2Minute[0] = minute;
                                //store hour and minute in string
                                String time = t2Hour[0] + ":" + t2Minute[0];
                                //Initialize 24 hours time format
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);
                                    //Initialize 12 hours time
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    //set selected time on text view
                                    nightEndEt.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                //displayed previous selected time
                timePickerDialog.updateTime(t2Hour[0], t2Minute[0]);
                //show dialog
                timePickerDialog.show();

            }
        });
        rotateStartEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int[] t2Hour = new int[1];
                final int[] t2Minute = new int[1];
                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
                        android.app.AlertDialog.THEME_HOLO_DARK,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //Initialize hour and minute
                                t2Hour[0] = hourOfDay;
                                t2Minute[0] = minute;
                                //store hour and minute in string
                                String time = t2Hour[0] + ":" + t2Minute[0];
                                //Initialize 24 hours time format
                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);
                                    //Initialize 12 hours time
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    //set selected time on text view
                                    rotateStartEt.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                //displayed previous selected time
                timePickerDialog.updateTime(t2Hour[0], t2Minute[0]);
                //show dialog
                timePickerDialog.show();

            }
        });
        rotateEndEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final int[] t2Hour = new int[1];
                final int[] t2Minute = new int[1];
                //Initialize time picker dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        getContext(),
                        android.app.AlertDialog.THEME_HOLO_DARK,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                //Initialize hour and minute
                                t2Hour[0] = hourOfDay;
                                t2Minute[0] = minute;
                                //store hour and minute in string
                                String time = t2Hour[0] + ":" + t2Minute[0];
                                //Initialize 24 hours time format
                                SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                                try {
                                    Date date = f24Hours.parse(time);
                                    //Initialize 12 hours time
                                    SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                                    //set selected time on text view
                                    rotateEndEt.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                //displayed previous selected time
                timePickerDialog.updateTime(t2Hour[0], t2Minute[0]);
                //show dialog
                timePickerDialog.show();

            }
        });

        shiftDayCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    shiftNightCb.setChecked(false);
                    shiftRotateCb.setChecked(false);
                    dayLinear.setVisibility(View.VISIBLE);
                    dayBl=true;
                }else{
                    dayLinear.setVisibility(View.GONE);
                    dayBl=false;
                    dayStartEt.setText("");
                    dayEndEt.setText("");
                }
            }
        });
        shiftNightCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    shiftDayCb.setChecked(false);
                    shiftRotateCb.setChecked(false);
                    nightLinear.setVisibility(View.VISIBLE);
                    nightBl=true;
                }else{
                    nightLinear.setVisibility(View.GONE);
                    nightBl=false;
                    nightStartEt.setText("");
                    nightEndEt.setText("");
                }
            }
        });
        shiftRotateCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    shiftDayCb.setChecked(false);
                    shiftNightCb.setChecked(false);
                    rotateLinear.setVisibility(View.VISIBLE);
                    rotateBl=true;
                }else{
                    rotateLinear.setVisibility(View.GONE);
                    rotateBl=false;
                    rotateStartEt.setText("");
                    rotateEndEt.setText("");
                }
            }
        });
    }

    boolean isStringEmpty(String startStr,String endStr){
        if (TextUtils.isEmpty(startStr)) {
            return true;
        } else if (TextUtils.isEmpty(endStr)) {
            return true;
        }else
            return false;
    }

}