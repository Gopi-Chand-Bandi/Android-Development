package com.abc.justjob.Candidate.CandidateActivityFragment;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.abc.justjob.R;

public class viewResumeActivity extends AppCompatActivity {

    private String pdfUrlStr;
//    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_resume);

        pdfUrlStr= getIntent().getStringExtra("pdfUrl");
//
//        // initializing our pdf view.
//        pdfView = findViewById(R.id.idPDFView);
//        new RetrivePDFfromUrl().execute(pdfUrlStr);
    }

    // create an async task class for loading pdf file from URL.
    /*private class RetrivePDFfromUrl extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            // we are using inputstream
            // for getting out PDF.
            InputStream inputStream = null;
            try {
                URL url = new URL(strings[0]);
                // below is the step where we are
                // creating our connection.
                HttpURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    // response is success.
                    // we are getting input stream from url
                    // and storing it in our variable.
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                // this is the method
                // to handle errors.
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            // after the execution of our async
            // task we are loading our pdf in our pdf view.
            pdfView.fromStream(inputStream).load();
        }
    }*/
}