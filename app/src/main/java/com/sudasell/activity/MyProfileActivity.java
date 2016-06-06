package com.sudasell.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sudasell.R;
import com.sudasell.fragments.MyAddFragment;
import com.sudasell.fragments.MyFavFragment;
import com.sudasell.fragments.PublicActivityFragment;

import java.util.ArrayList;
import java.util.List;

public class MyProfileActivity extends AppCompatActivity {
    ImageView Notification, Chat, Advertise, MyProfile,Home;
    ViewPager viewPager;
    TabLayout tabLayout;
    Dialog RegDilog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_my_profile);

      /*  this.getActionBar().setDisplayShowCustomEnabled(true);
        this.getActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.custom_action_bar, null);
        ((TextView) v.findViewById(R.id.title)).setText("My Profile");
        this.getActionBar().setCustomView(v);*/

        Notification = (ImageView) findViewById(R.id.bottom_first_image);
        Chat = (ImageView) findViewById(R.id.bottom_second_image);
        Advertise = (ImageView) findViewById(R.id.bottom_third_image);
        MyProfile = (ImageView) findViewById(R.id.bottom_fourth_image);
        Home = (ImageView) findViewById(R.id.bottom_fifth_image);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        RelativeLayout FullLayout=(RelativeLayout)findViewById(R.id.fulllayout);

        Notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NotificationIntent = new Intent(MyProfileActivity.this, NotificationActivity.class);
                startActivity(NotificationIntent);
                finish();
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
            }
        });
        Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ChatIntent = new Intent(MyProfileActivity.this, ChatActivity.class);
                startActivity(ChatIntent);
                finish();
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
            }
        });
        Advertise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent AdvertiseIntent = new Intent(MyProfileActivity.this, AdvertisementActivity.class);
                startActivity(AdvertiseIntent);
                finish();
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);

            }
        });
        MyProfile.setBackgroundColor(ContextCompat.getColor(this, R.color.selected_bottom_bar_color));
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             /*   Intent HomeIntent = new Intent(MyProfileActivity.this, MainActivity.class);
                startActivity(HomeIntent);*/
                finish();
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
            }
        });

        if (MainActivity.user_id.equals("null")) {
            FullLayout.setVisibility(View.GONE);
            RegDilog = new Dialog(MyProfileActivity.this);
            RegDilog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            RegDilog.setContentView(R.layout.custom_dialog_registration);
            RegDilog.setCancelable(false);

            TextView Registration = (TextView) RegDilog.findViewById(R.id.register);
            TextView Cancel = (TextView) RegDilog.findViewById(R.id.cancel);

            Registration.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent reg = new Intent(MyProfileActivity.this, RegistrationActivity.class);
                    startActivity(reg);
                    MyProfileActivity.this.finish();
                    //overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                }
            });
            Cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent reg = new Intent(MyProfileActivity.this, MainActivity.class);
                    startActivity(reg);
                    MyProfileActivity.this.finish();
                   // overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                }
            });

            RegDilog.show();


        } else {


        }
        }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PublicActivityFragment(), "Public Activity");
        adapter.addFragment(new MyFavFragment(), "My Favorites");
        adapter.addFragment(new MyAddFragment(), "My Adds");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
       // overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
    }
}
