package com.sudasell.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sudasell.R;

import java.io.File;


public class AdvertisementActivity extends Activity {
    ImageView Notification, Chat, Advertise, MyProfile, Home;
    ImageView imgView;

    TextView ButtonUpload;

    Button DeleteImage, AddImage;

    File cameraPhotoFile;

    Dialog RegDilog;

    Spinner Language, Category, SubCategory, Type;

    EditText Price, UploadDate, EngDesc, ArabDesc, Contact, AdNameEng, AdNameArab;
    String Heading = "Add Advertisement";
    String language[] = {"English", "Arabic"};
    String cat[] = {"Animal", "Birds", "Land", "Mobile"};
    String subcat[] = {"Subcategory1", "Subcategory2", "Subcategory3", "Subcategory4", "Subcategory5", "Subcategory6"};
    String type[] = {"type1", "type2", "type3"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_advertisement);

        this.getActionBar().setDisplayShowCustomEnabled(true);
        this.getActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflator = LayoutInflater.from(this);
        View v = inflator.inflate(R.layout.custom_action_bar, null);
        ((TextView) v.findViewById(R.id.title)).setText(Heading);
        this.getActionBar().setCustomView(v);

        Language = (Spinner) findViewById(R.id.language);
        Category = (Spinner) findViewById(R.id.cat);
        SubCategory = (Spinner) findViewById(R.id.subcat);
        Type = (Spinner) findViewById(R.id.type);

        DeleteImage = (Button) findViewById(R.id.delete_image);
        AddImage = (Button) findViewById(R.id.add_image);

        Price = (EditText) findViewById(R.id.price);
        UploadDate = (EditText) findViewById(R.id.Upload_date);
        EngDesc = (EditText) findViewById(R.id.eng_desc);
        ArabDesc = (EditText) findViewById(R.id.arabic_desc);
        Contact = (EditText) findViewById(R.id.contact);
        AdNameEng = (EditText) findViewById(R.id.add_english);
        AdNameArab = (EditText) findViewById(R.id.add_arabic);

        ButtonUpload = (TextView) findViewById(R.id.upload_button);

        imgView = (ImageView) findViewById(R.id.ad_image);
        Notification = (ImageView) findViewById(R.id.bottom_first_image);
        Chat = (ImageView) findViewById(R.id.bottom_second_image);
        Advertise = (ImageView) findViewById(R.id.bottom_third_image);
        MyProfile = (ImageView) findViewById(R.id.bottom_fourth_image);
        Home = (ImageView) findViewById(R.id.bottom_fifth_image);

        RelativeLayout FullLayout = (RelativeLayout) findViewById(R.id.fulllayout);


        Notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent NotificationIntent = new Intent(AdvertisementActivity.this, NotificationActivity.class);
                startActivity(NotificationIntent);
                finish();
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
            }
        });
        Chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ChatIntent = new Intent(AdvertisementActivity.this, ChatActivity.class);
                startActivity(ChatIntent);
                finish();
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
            }
        });

        Advertise.setBackgroundColor(ContextCompat.getColor(this, R.color.selected_bottom_bar_color));

        MyProfile.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                Intent MyProfileIntent = new Intent(AdvertisementActivity.this, MyProfileActivity.class);
                startActivity(MyProfileIntent);
                finish();
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);

            }
        });
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent HomeIntent = new Intent(AdvertisementActivity.this, MainActivity.class);
                startActivity(HomeIntent);*/
                finish();
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);

            }
        });

        if (MainActivity.user_id.equals("null")) {
            FullLayout.setVisibility(View.GONE);
            RegDilog = new Dialog(AdvertisementActivity.this);
            RegDilog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            RegDilog.setContentView(R.layout.custom_dialog_registration);
            RegDilog.setCancelable(false);

            TextView Registration = (TextView) RegDilog.findViewById(R.id.register);
            TextView Cancel = (TextView) RegDilog.findViewById(R.id.cancel);

            Registration.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent reg = new Intent(AdvertisementActivity.this, RegistrationActivity.class);
                    startActivity(reg);
                    AdvertisementActivity.this.finish();
                   // overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                }
            });
            Cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent reg = new Intent(AdvertisementActivity.this, MainActivity.class);
                    startActivity(reg);
                    AdvertisementActivity.this.finish();
                   // overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);
                }
            });

            RegDilog.show();
        } else {

            final ArrayAdapter<String> languagearray = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, language);
            languagearray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Language.setPopupBackgroundResource(R.drawable.drawable_gray_spinner_background);
            Language.setAdapter(languagearray);

            final ArrayAdapter<String> categoryarray = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cat);
            categoryarray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Category.setPopupBackgroundResource(R.drawable.drawable_gray_spinner_background);
            Category.setAdapter(categoryarray);

            final ArrayAdapter<String> subcatarray = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, subcat);
            subcatarray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            SubCategory.setPopupBackgroundResource(R.drawable.drawable_gray_spinner_background);
            SubCategory.setAdapter(subcatarray);

            final ArrayAdapter<String> typearray = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, type);
            typearray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Type.setPopupBackgroundResource(R.drawable.drawable_gray_spinner_background);
            Type.setAdapter(typearray);


            imgView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final Dialog openDialog = new Dialog(AdvertisementActivity.this);
                    openDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    openDialog.setContentView(R.layout.custom_dialog_gallery);
                    TextView Camera = (TextView) openDialog.findViewById(R.id.camera);
                    TextView Gallery = (TextView) openDialog.findViewById(R.id.gallery);
                    TextView dialogCloseButton = (TextView) openDialog.findViewById(R.id.cancel);

                    Camera.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            openDialog.dismiss();
                            // Sura ACTION_IMAGE_CAPTURE is used for opening camera
                            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            File pics = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                            cameraPhotoFile = new File(pics, System.currentTimeMillis() + ".jpg");
                            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraPhotoFile));
                            //PICK_FROM_CAMERA taken as 1 by Sura
                            startActivityForResult(cameraIntent, 1);

                        }
                    });


                    Gallery.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            openDialog.dismiss();

                            Intent intent = new Intent();
                            intent.setType("image/*");
                            intent.setAction(Intent.ACTION_GET_CONTENT);


                            intent.putExtra("crop", "true");
                            intent.putExtra("aspectX", 800);
                            intent.putExtra("aspectY", 800);

                            //Sura Output decider is done here
                            intent.putExtra("outputX", 1200);
                            intent.putExtra("outputY", 1200);

                            try {

                                intent.putExtra("return-data", true);
                                //PICK_FROM_GALLERY taken as 2 by Sura
                                startActivityForResult(Intent.createChooser(intent, "Complete action using"), 2);

                            } catch (ActivityNotFoundException e) {

                                //Sura do something here

                            }
                        }
                    });
                    dialogCloseButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            openDialog.dismiss();
                        }
                    });
                    openDialog.show();


                }
            });


            DeleteImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            AddImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

            ButtonUpload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String UpLanguage = Language.getSelectedItem().toString();
                    String UpCatregory = Category.getSelectedItem().toString();
                    String UpSubcategory = SubCategory.getSelectedItem().toString();
                    String UpType = Type.getSelectedItem().toString();
                    String UpPrice = Price.getText().toString();
                    String UpDate = UploadDate.getText().toString();
                    String UpEngDesc = EngDesc.getText().toString();
                    String UpArabDesc = ArabDesc.getText().toString();
                    String UpContact = Contact.getText().toString();
                    String UpAdNameEng = AdNameEng.getText().toString();
                    String UpAdNameArab = AdNameArab.getText().toString();

                    Toast.makeText(AdvertisementActivity.this, "Language:" + UpLanguage + " Category:" + UpCatregory, Toast.LENGTH_LONG).show();

                }
            });

        }
    }


    public void cropCapturedImage(Uri picUri) {

        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        cropIntent.setDataAndType(picUri, "image/*");

        cropIntent.putExtra("crop", "true");
        cropIntent.putExtra("aspectX", 800);
        cropIntent.putExtra("aspectY", 800);

        //Sura output is set here
        cropIntent.putExtra("outputX", 1200);
        cropIntent.putExtra("outputY", 1200);
        cropIntent.putExtra("return-data", true);

        startActivityForResult(cropIntent, 3);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            if (requestCode == 1) {
                MediaScannerConnection.scanFile(this, new String[]{cameraPhotoFile.getAbsolutePath()}, null, null);
                try {
                    cropCapturedImage(Uri.fromFile(cameraPhotoFile));

                } catch (ActivityNotFoundException aNFE) {
                    Toast.makeText(this, "Sorry – your device doesn’t support the crop action!", Toast.LENGTH_SHORT).show();
                }

            }

            if (requestCode == 2) {
                Bundle extras2 = data.getExtras();
                if (extras2 != null) {
                    Bitmap photo = extras2.getParcelable("data");
                    imgView.setImageBitmap(photo);
                }
            }
            if (requestCode == 3) {
                Bundle extras = data.getExtras();
                Bitmap thePic = extras.getParcelable("data");
                imgView.setImageBitmap(thePic);
            }
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        //overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left);

    }
}
