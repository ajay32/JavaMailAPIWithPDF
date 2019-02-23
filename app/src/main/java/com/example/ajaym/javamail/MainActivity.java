package com.example.ajaym.javamail;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button send = (Button) this.findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                AsyncTaskRunner runner = new AsyncTaskRunner();
                runner.execute();

            }
        });

    }

    //Using async task to do network operation(send mail) in background
    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params) {


            try {
                // required email and pass of your gmail ...and go to google security and turn on email from less secured apps <----
                GMailSender sender = new GMailSender("ajaymehta104@gmail.com", "");
                sender.sendMail("This is Subject",
                        "This is Body",
                        "ajaymehta104@gmail.com",
                        "ajaym@chetu.com");
            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }
         return null;

        }


        @Override
        protected void onPostExecute(String result) {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this, "email Sent",Toast.LENGTH_SHORT).show();
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(MainActivity.this,
                    "ProgressDialog",
                    "email is sending");
        }


        @Override
        protected void onProgressUpdate(String... text) {
         //   finalResult.setText(text[0]);

        }
    }




}
