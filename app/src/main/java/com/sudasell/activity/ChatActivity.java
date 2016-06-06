package com.sudasell.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sudasell.R;

public class ChatActivity extends Activity {
    String Heading = "Chat";
    ImageView Notification, Chat, Advertise, MyProfile, Home;
    Dialog RegDilog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_chat);

        this.getActionBar().setDisplayShowCustomEnabled(true);
        this.getActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.custom_action_bar, null);
        ((TextView) v.findViewById(R.id.title)).setText(Heading);
        this.getActionBar().setCustomView(v);

        Notification = (ImageView) findViewById(R.id.bottom_first_image);
        Chat = (ImageView) findViewById(R.id.bottom_second_image);
        Advertise = (ImageView) findViewById(R.id.bottom_third_image);
        MyProfile = (ImageView) findViewById(R.id.bottom_fourth_image);
        Home = (ImageView) findViewById(R.id.bottom_fifth_image);

        RelativeLayout FullLayout = (RelativeLayout) findViewById(R.id.fulllayout);

        Notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NotificationIntent = new Intent(ChatActivity.this, NotificationActivity.class);
                startActivity(NotificationIntent);
                finish();
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
            }
        });
        Chat.setBackgroundColor(ContextCompat.getColor(this, R.color.selected_bottom_bar_color));
        Advertise.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent AdvertiseIntent = new Intent(ChatActivity.this, AdvertisementActivity.class);

                startActivity(AdvertiseIntent);
                finish();
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);

            }
        });
        MyProfile.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent MyProfileIntent = new Intent(ChatActivity.this, MyProfileActivity.class);

                startActivity(MyProfileIntent);
                finish();
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
            }
        });
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent HomeIntent = new Intent(ChatActivity.this, MainActivity.class);
                startActivity(HomeIntent);*/
                finish();
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
            }
        });

        if (MainActivity.user_id.equals("null")) {
            FullLayout.setVisibility(View.GONE);
            RegDilog = new Dialog(ChatActivity.this);
            RegDilog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            RegDilog.setContentView(R.layout.custom_dialog_registration);
            RegDilog.setCancelable(false);

            TextView Registration = (TextView) RegDilog.findViewById(R.id.register);
            TextView Cancel = (TextView) RegDilog.findViewById(R.id.cancel);

            Registration.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent reg = new Intent(ChatActivity.this, RegistrationActivity.class);
                    startActivity(reg);
                    ChatActivity.this.finish();
                   // overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                }
            });
            Cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent reg = new Intent(ChatActivity.this, MainActivity.class);
                    startActivity(reg);
                    ChatActivity.this.finish();
                    //overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                }
            });

            RegDilog.show();


        } else {

            //Sura do activity actions here
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        // overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }

}
