package com.sudasell.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sudasell.R;
import com.sudasell.adapter.AdapterListviewNotification;
import com.sudasell.model.ModelNotificationCard;

public class NotificationActivity extends Activity {
    ImageView Notification, Chat, Advertise, MyProfile, Home;
    RelativeLayout FullLayout;
    String Heading = "Notifications";
    ListView NotificationList;
    Dialog RegDilog;

    public ModelNotificationCard notificationcard;
    public static  AdapterListviewNotification notificationadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_notification);

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

        NotificationList = (ListView) findViewById(R.id.notificaton_list);

        Notification.setBackgroundColor(ContextCompat.getColor(this, R.color.selected_bottom_bar_color));
        Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ChatIntent = new Intent(NotificationActivity.this, ChatActivity.class);
                startActivity(ChatIntent);
                finish();
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
            }
        });

        Advertise.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent AdvertiseIntent = new Intent(NotificationActivity.this, AdvertisementActivity.class);
                startActivity(AdvertiseIntent);
                finish();
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);

            }
        });

        MyProfile.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent MyProfileIntent = new Intent(NotificationActivity.this, MyProfileActivity.class);
                startActivity(MyProfileIntent);
                finish();
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
            }
        });

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent HomeIntent = new Intent(NotificationActivity.this, MainActivity.class);
                startActivity(HomeIntent);*/
                finish();
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);

            }
        });
        FullLayout = (RelativeLayout) findViewById(R.id.fulllayout);



        String notname = "Notification name";
        String detail = "Notification name";
        int number = R.drawable.image_user_icon;
        String image = "Notification name";
        notificationadapter = new AdapterListviewNotification(NotificationActivity.this, R.layout.custom_notification_list_item);
        NotificationList.setAdapter(notificationadapter);
        for (int i = 0; i < 20; i++) {
            notificationcard = new ModelNotificationCard(notname, detail, number, image);
            notificationadapter.add(notificationcard);
        }





        if (MainActivity.user_id.equals("null")) {
            FullLayout.setVisibility(View.GONE);
            RegDilog = new Dialog(NotificationActivity.this);
            RegDilog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            RegDilog.setContentView(R.layout.custom_dialog_registration);
            RegDilog.setCancelable(false);

            TextView Registration = (TextView) RegDilog.findViewById(R.id.register);
            TextView Cancel = (TextView) RegDilog.findViewById(R.id.cancel);

            Registration.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent reg = new Intent(NotificationActivity.this, RegistrationActivity.class);
                    startActivity(reg);
                    NotificationActivity.this.finish();
                    // overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                }
            });
            Cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent reg = new Intent(NotificationActivity.this, MainActivity.class);
                    startActivity(reg);
                    NotificationActivity.this.finish();
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
