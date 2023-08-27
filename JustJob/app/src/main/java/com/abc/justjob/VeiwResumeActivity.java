package com.abc.justjob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class VeiwResumeActivity extends AppCompatActivity {

    private static final String TAG = VeiwResumeActivity.class.getSimpleName();
    WebView webView;
    private String resumeUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veiw_resume);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        Bundle bundle = getIntent().getExtras();
        resumeUrl = bundle.getString("resumeUrl");

        String pdf = "https://justjobshr.com//JustJobApi/JustJob/candidates_resume/8.pdf";
        String myPdfUrl = "https://justjobshr.com//JustJobApi/JustJob/candidates_resume/1003.docx";

        webView = findViewById(R.id.webView1);

        String urlExtension=resumeUrl.substring(resumeUrl.lastIndexOf(".") + 1);

        if (urlExtension.equals("jpg") || urlExtension.equals("jpeg")){
            openWebView(resumeUrl);
        }else if (urlExtension.equals("pdf")){
            String url = "https://drive.google.com/viewerng/viewer?embedded=true&url=" + resumeUrl;
            openWebView(url);
        }else {
            String url = "https://drive.google.com/gview?embedded=true&url=" + resumeUrl;
            openWebView(url);
        }

    }

    @SuppressLint("SetJavaScriptEnabled")
    private void openWebView(String resumeUrl) {

//        WebSettings webSettings=webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new CallBack());
        webView.setInitialScale(1);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.loadUrl(resumeUrl);
    }

    private class CallBack extends WebViewClient {
        @Override
        public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
            return false;
        }
    }
}