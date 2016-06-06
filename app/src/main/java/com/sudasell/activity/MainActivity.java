package com.sudasell.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import android.view.KeyEvent;
import android.view.MenuInflater;
import android.view.View;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;

import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sudasell.R;
import com.sudasell.adapter.AdapterGridviewHome;

import java.io.File;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, Animation.AnimationListener {

    android.support.v4.app.Fragment fragment = null;

    public FragmentManager fragmentManager = getSupportFragmentManager();
    Dialog openDialog;
    ImageView Notification, Chat, Advertise, MyProfile, Home, Search;
    EditText searchedit;
    TextView AppName;
    String spin;
    SQLiteOpenHelper dbHelper;
    public static String user_id = "null";
    LinearLayout SpinnerLayout, Bottombar;
    Spinner Category, Subcategory, Filters;
    int sp_position;
    String all[] = {"All"};
    String animal[] = {"All", "Cow", "Dog", "Lion", "Elephent", "Cat"};
    String top[] = {"All", "For Sale", "Wanted", "Service"};
    String birds[] = {"All", "Crow", "Parrot", "Sparrow", "Peagon"};
    String cars[] = {"All", "Tata", "Maruthi", "Suzuki", "Honda"};
    String mobiles[] = {"All", "Samsung", "I-Phones", "Micromax", "Lenevo", "Carbon"};
    String land[] = {"All", "1 acre", "2 acre", "3 acre", "4 acre", "5 acre"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Notification = (ImageView) findViewById(R.id.bottom_first_image);
        Chat = (ImageView) findViewById(R.id.bottom_second_image);
        Advertise = (ImageView) findViewById(R.id.bottom_third_image);

        MyProfile = (ImageView) findViewById(R.id.bottom_fourth_image);
        Home = (ImageView) findViewById(R.id.bottom_fifth_image);
        SpinnerLayout = (LinearLayout) findViewById(R.id.spinner_linear);

        Bottombar = (LinearLayout) findViewById(R.id.bottom_bar);
        Search = (ImageView) findViewById(R.id.searchimg);
        AppName = (TextView) findViewById(R.id.name);
        searchedit = (EditText) findViewById(R.id.et_search);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(searchedit, InputMethodManager.SHOW_IMPLICIT);

        Category = (Spinner) findViewById(R.id.category);
        Subcategory = (Spinner) findViewById(R.id.subcategory);
        Filters = (Spinner) findViewById(R.id.filters);

        GridView gridView = (GridView) findViewById(R.id.gridview);

        gridView.setAdapter(new AdapterGridviewHome(MainActivity.this));


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pos = position + 1;
                Toast.makeText(MainActivity.this, "You have clicked Grid Number:" + pos, Toast.LENGTH_SHORT).show();
            }
        });


        SpinnerLayout.setVisibility(View.VISIBLE);


        final ArrayAdapter<String> spinnerArray = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, top);
        spinnerArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Filters.setPopupBackgroundResource(R.drawable.drawable_gray_spinner_background);
        Filters.setAdapter(spinnerArray);

        final ArrayAdapter<String> spinnerArray0 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, all);
        spinnerArray0.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> spinnerArray1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, animal);
        spinnerArray1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> spinnerArray2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, birds);
        spinnerArray2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> spinnerArray3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cars);
        spinnerArray3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> spinnerArray4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, mobiles);
        spinnerArray4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<String> spinnerArray5 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, land);
        spinnerArray5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.cat, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Category.setAdapter(adapter);

        Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Subcategory.setAdapter(spinnerArray0);
                } else if (position == 1) {
                    Subcategory.setAdapter(spinnerArray1);
                } else if (position == 2) {
                    Subcategory.setAdapter(spinnerArray2);
                } else if (position == 3) {
                    Subcategory.setAdapter(spinnerArray3);
                } else if (position == 4) {
                    Subcategory.setAdapter(spinnerArray4);
                } else if (position == 5) {
                    Subcategory.setAdapter(spinnerArray5);
                }
                Log.e("suraa", "" + sp_position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppName.getVisibility() == View.VISIBLE) {
                    Search.setImageResource(R.drawable.image_cross);
                    AppName.setVisibility(View.GONE);
                    searchedit.setVisibility(View.VISIBLE);
                } else {
                    Search.setImageResource(R.drawable.image_search_icon);
                    AppName.setVisibility(View.VISIBLE);
                    searchedit.setVisibility(View.GONE);
                }

            }
        });
        searchedit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    if (searchedit.getText().toString().trim() != "") {
                        String abc = searchedit.getText().toString().trim();
                        spin = Subcategory.getSelectedItem().toString();

                        Toast.makeText(MainActivity.this, "You have given Text:" + abc + "and Filter:" + spin + " to image_search_icon", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(MainActivity.this, "Please enter the text to image_search_icon", Toast.LENGTH_SHORT).show();

                    }
                    return true;
                }
                return false;
            }
        });

        Notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NotificationIntent = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(NotificationIntent);
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
            }
        });
        Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ChatIntent = new Intent(MainActivity.this, ChatActivity.class);
                startActivity(ChatIntent);
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);

            }
        });
        Advertise.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent AdvertiseIntent = new Intent(MainActivity.this, AdvertisementActivity.class);
                startActivity(AdvertiseIntent);
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
            }
        });
        MyProfile.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent MyProfileIntent = new Intent(MainActivity.this, MyProfileActivity.class);
                startActivity(MyProfileIntent);
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);

            }
        });
        Home.setBackgroundColor(ContextCompat.getColor(this, R.color.selected_bottom_bar_color));


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if(doesDatabaseExist((ContextWrapper)getApplicationContext(),"Sudasellprofile"))
        {

            dbHelper = new SQLiteOpenHelper(getApplicationContext(), "Sudasellprofile", null, 1) {

                @Override
                public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

                }

                @Override
                public void onCreate(SQLiteDatabase db) {

                }
            };


            String col[] = {"Userid"};

            Cursor cursor = dbHelper.getReadableDatabase().query("SudasellTable", col, null, null, null, null, null);
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {

                user_id = cursor.getString(0);

                cursor.moveToNext();

            }

            cursor.close();
            dbHelper.close();


        }


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;

    }

   /* @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_lang_ads) {
            openDialog = new Dialog(MainActivity.this);
            openDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            openDialog.setContentView(R.layout.custom_dialog_language);

            TextView EnglishLang = (TextView) openDialog.findViewById(R.id.english_lang);
            TextView ArabicLang = (TextView) openDialog.findViewById(R.id.arabic_lang);
            TextView dialogCloseButton = (TextView) openDialog.findViewById(R.id.dialog_button);

            EnglishLang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "You have selected English Language", Toast.LENGTH_SHORT).show();
                    openDialog.dismiss();
                }
            });
            ArabicLang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this, "You have selected Arabic Language", Toast.LENGTH_SHORT).show();
                    openDialog.dismiss();
                }
            });
            dialogCloseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openDialog.dismiss();
                }
            });
            openDialog.show();
        } else if (id == R.id.nav_share) {

            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            //Sura Add subject here
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Download the apk using below link");
            //Sura Add message to be shared
            String shareMessage = "";

            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareMessage);
            startActivity(Intent.createChooser(shareIntent, "Share this app"));

        } else if (id == R.id.nav_about) {
            Intent reg = new Intent(MainActivity.this, AboutUsActivity.class);
            startActivity(reg);

        } /*else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }
    //

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    private boolean doesDatabaseExist(ContextWrapper applicationContext, String string) {

        File dbFile = applicationContext.getDatabasePath(string);
        return dbFile.exists();
    }



}
