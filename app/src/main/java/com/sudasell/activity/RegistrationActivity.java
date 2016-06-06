package com.sudasell.activity;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.sudasell.R;
import com.sudasell.network.NetworkConnectionDetector;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class RegistrationActivity extends Activity {

    String Heading="Registration";
    Button buttonReg;
    EditText username, usermobile;
    ImageView userimage;
    String error1, message1, mName, mMobile;
    NetworkConnectionDetector NetworkAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registration);

        this.getActionBar().setDisplayShowCustomEnabled(true);
        this.getActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.custom_action_bar, null);
        ((TextView) v.findViewById(R.id.title)).setText(Heading);
        this.getActionBar().setCustomView(v);

        username = (EditText) findViewById(R.id.nameid);
        usermobile = (EditText) findViewById(R.id.mobileid);
        userimage = (ImageView) findViewById(R.id.imageid);
        buttonReg = (Button) findViewById(R.id.buttonreg);

        NetworkAvailable = new NetworkConnectionDetector(getApplicationContext());
        if (!NetworkAvailable.isConnectingToInternet()) {
            AlertDialog.Builder helpBuilder = new AlertDialog.Builder(RegistrationActivity.this);
            helpBuilder.setTitle("Internet Connection Issue");
            helpBuilder.setMessage("Please Check the Internet Connection");
            helpBuilder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            Intent checkIntent = new Intent(RegistrationActivity.this, MainActivity.class);
                            startActivity(checkIntent);
                            finish();
                        }
                    });
            AlertDialog helpDialog = helpBuilder.create();
            helpDialog.show();
        }
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mName = username.getText().toString();
                mMobile = usermobile.getText().toString();

                if (username.getText().toString().equalsIgnoreCase("") && usermobile.getText().toString().equalsIgnoreCase("")) {

                    usermobile.requestFocus();
                    usermobile.setError("Please Enter Your Mobile Number ?");

                    username.requestFocus();
                    username.setError("Please Enter Your User Name ?");

                } else if (username.getText().toString().equalsIgnoreCase("")) {

                    username.requestFocus();
                    username.setError("Please Enter Your User Name ?");

                } else if (usermobile.getText().toString().equalsIgnoreCase("")) {

                    usermobile.requestFocus();
                    usermobile.setError("Please Enter Your Mobile Number ?");

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
            pDialog = new ProgressDialog(RegistrationActivity.this);
            pDialog.setMessage("Verifying....");
            pDialog.setCancelable(false);
            pDialog.show();
        }


        @Override
        protected String doInBackground(String... arg0) {

            try {
                verify(mName, mMobile);


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            fuct_verification();


        }
    }

    public void verify(String name, String mob) throws JSONException {

        HttpClient httpclient = new DefaultHttpClient();

        HttpPost httppost = new HttpPost("http://54.169.81.215/sudasell/shopping/userregister");
        try {
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);


            nameValuePairs.add(new BasicNameValuePair("user_name", name));
            nameValuePairs.add(new BasicNameValuePair("user_mobile", mob));
            nameValuePairs.add(new BasicNameValuePair("user_image", ""));

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

            String activestr = EntityUtils.toString(response.getEntity());

            JSONObject actobj = new JSONObject(activestr);

            error1 = actobj.getString("error");
            message1 = actobj.getString("message");

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fuct_verification() {

        if (error1.equals("false")) {
            Intent RegistrationIntent = new Intent(RegistrationActivity.this, VerificationActivity.class);
            RegistrationIntent.putExtra("mobile", mMobile);
            startActivity(RegistrationIntent);
            RegistrationActivity.this.finish();
            Toast.makeText(RegistrationActivity.this, message1, Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(RegistrationActivity.this, message1, Toast.LENGTH_SHORT).show();
        }
    }
}
