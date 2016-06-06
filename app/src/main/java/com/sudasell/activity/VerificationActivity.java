package com.sudasell.activity;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sudasell.R;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VerificationActivity extends Activity {

    Button buttonClick;
    EditText verification;
    String error1, message1, verific, vMobile;
    public static String user_id = "null";
    String  Heading="OTP Verification";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_verification);

        this.getActionBar().setDisplayShowCustomEnabled(true);
        this.getActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.custom_action_bar, null);
        ((TextView) v.findViewById(R.id.title)).setText(Heading);
        this.getActionBar().setCustomView(v);

        verification = (EditText) findViewById(R.id.verifyid);
        buttonClick = (Button) findViewById(R.id.buttonclick);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Log.e("VerificationActivity", "" + vMobile);
            vMobile = bundle.getString("mobile");
        }
        buttonClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verific = verification.getText().toString();
                if (verification.getText().toString().equalsIgnoreCase("")) {
                    verification.requestFocus();
                    verification.setError("Please Enter OTP ?");
                } else {

                    new Verify().execute();

                }
            }


        });
    }

    class Verify extends AsyncTask<String, Void, String> {
        ProgressDialog pDialog;

        protected void onPreExecute() {

            super.onPreExecute();
            pDialog = new ProgressDialog(VerificationActivity.this);
            pDialog.setMessage("Verifying....");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... arg0) {

            try {
                verify(verific, vMobile);


            } catch (JSONException e) {
                e.printStackTrace();
                //Analytics catching exceptions
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            fuct_verification();


        }
    }

    public void verify(String ver, String mob) throws JSONException {

        DefaultHttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost("http://54.169.81.215/sudasell/shopping/verification");
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);


            nameValuePairs.add(new BasicNameValuePair("verification", ver));
            nameValuePairs.add(new BasicNameValuePair("user_mobile", mob));

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);


            String activestr = EntityUtils.toString(response.getEntity());

            JSONObject actobj = new JSONObject(activestr);

            error1 = actobj.getString("error");
            message1 = actobj.getString("message");
            user_id = actobj.getString("user_id");

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }

    public void fuct_verification() {

        if (error1.equals("false")) {

            SQLiteDatabase mydb;
            mydb = openOrCreateDatabase("Sudasellprofile", 0, null);
            mydb.execSQL("create table if not exists SudasellTable" +
                    "(Userid varchar(100));");
            mydb.execSQL("insert into SudasellTable values ('" + user_id + "');");

            mydb.close();

            Intent Verified = new Intent(VerificationActivity.this, MainActivity.class);
            startActivity(Verified);
            VerificationActivity.this.finish();
            Toast.makeText(VerificationActivity.this, message1, Toast.LENGTH_LONG).show();


        } else {

            Toast.makeText(VerificationActivity.this, message1, Toast.LENGTH_LONG).show();
        }
    }


}
