package com.hr.pereless.activities.setting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.common.util.IOUtils;
import com.hr.pereless.R;
import com.hr.pereless.api.API;
import com.hr.pereless.api.BaseJsonObjectRequest;
import com.hr.pereless.base.CommonActivity;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.commons.Helper;
import com.hr.pereless.dialog.SelectJobOneDialog;
import com.hr.pereless.model.onboarding.RecruitteamModel;
import com.hr.pereless.util.RoundedCornersTransformation;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Random;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import com.fxn.pix.Options;
import com.fxn.pix.Pix;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONException;
import org.json.JSONObject;

public class EditProfileActivity extends CommonActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img_scorecard)
    ImageView imgScorecard;
    @BindView(R.id.img_upload)
    CircleImageView imgUpload;
    @BindView(R.id.layout_one)
    RelativeLayout layoutOne;
    @BindView(R.id.edt_email)
    EditText edtEmail;
    @BindView(R.id.edt_mobno)
    EditText edtMobno;
    @BindView(R.id.edt_address)
    EditText edtAddress;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.edt_firstname)
    EditText edtFirstName;
    @BindView(R.id.edt_lastname)
    EditText edtLastName;
    @BindView(R.id.edt_timezone)
    TextView edt_timezone;
    String name, email, mobileno, address;
    public static float maxHeight = 150.0f;
    String first_name, last_name;
    int timezone = 0;
    ArrayList<String>returnValue = new ArrayList<>();
    Uri imageUri ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        txtTitle.setText("Details");
       /* edtUsername.setSelection(name.length());
        edtEmail.setSelection(email.length());
        edtMobno.setSelection(mobileno.length());
        edtAddress.setSelection(address.length());*/

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        initLayout();
    }

    void initLayout(){
        Glide.with(_context).load(Commons.g_user.getAvatar()).placeholder(R.drawable.profile_pic).dontAnimate().apply(RequestOptions.bitmapTransform(
                new RoundedCornersTransformation(_context, Commons.glide_radius, Commons.glide_magin, "#FFFECC53", Commons.glide_boder))).into(imgUpload);
        edtFirstName.setText(Commons.g_user.getFirstname());
        edtLastName.setText(Commons.g_user.getLastname());
        edtEmail.setText(Commons.g_user.getEmail());
        edtMobno.setText(Commons.g_user.getPhone());
        timezone = Commons.g_user.getTimezone();
        for(int i =0;i<Commons.timeZoneModels.size();i++){
            if(Commons.timeZoneModels.get(i).getOffset() == timezone ){
                edt_timezone.setText(Commons.timeZoneModels.get(i).getName());
                break;
            }
        }
    }


    void callUpdateProfile(){

    }

    void choicePicture(){
        Options options = Options.init()
                .setRequestCode(100)                                           //Request code for activity results
                .setCount(1)                                                   //Number of images to restict selection count
                .setFrontfacing(false)                                         //Front Facing camera on start
                .setPreSelectedUrls(returnValue)                               //Pre selected Image Urls
                .setSpanCount(4)                                               //Span count for gallery min 1 & max 5
                .setMode(Options.Mode.Picture)                                     //Option to select only pictures or videos or both
                .setVideoDurationLimitinSeconds(30)                            //Duration for video recording
                .setScreenOrientation(Options.SCREEN_ORIENTATION_PORTRAIT)     //Orientaion
                .setPath("/pix/images");                                       //Custom Path For media Storage

        Pix.start(this, options);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);

            Uri uri = Uri.fromFile(new File(returnValue.get(0)));
            Intent intent = CropImage.activity(uri)
                    .setGuidelines(CropImageView.Guidelines.ON).setCropShape(CropImageView.CropShape.RECTANGLE).setInitialCropWindowPaddingRatio(0).setAspectRatio(1, 1)
                    .getIntent(this);

            startActivityForResult(intent, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
        }else  if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                imageUri = result.getUri();
                Glide.with(_context).load(imageUri).placeholder(R.drawable.profile_pic).dontAnimate().apply(RequestOptions.bitmapTransform(
                    new RoundedCornersTransformation(_context, Commons.glide_radius, Commons.glide_magin, "#FFFECC53", Commons.glide_boder))).into(imgUpload);
                  uploadProfilePic();

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }
    void uploadProfilePic(){
        showProgress();
        String api_link = API.CANDIDATEPROFILE;
        JSONObject params = new JSONObject();
        try {
            File file = new File(Helper.getUriRealPathAboveKitkat(this, imageUri));
            FileInputStream imageInFile = new FileInputStream(file);
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);
            String base64Image = null;
            base64Image = Base64.getEncoder().encodeToString(imageData);
            int upperbound = 1000000000;
            //generate random values from 0-24
            Random rand = new Random(); //instance of random class
            int int_random = rand.nextInt(upperbound);

            params.put("filename",String.valueOf(int_random) + ".jpg");
            params.put("fullFileName",base64Image);
        } catch (Exception e) {
            Log.d("Exception",e.toString());
        }

        Log.d("aaaaaa",params.toString());

        new BaseJsonObjectRequest(
                Request.Method.POST, api_link, params,
                response -> {
                    closeProgress();
                    try {
                        Log.d("aaaaa",response.toString());
                        Commons.g_user.setAvatar(response.getJSONObject("Image").getString("url"));
                        Toast.makeText(this, "Profile Picture updated Successfully!", Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }, this::handleMultiPartResponseError);
    }
    private void handleMultiPartResponseError(VolleyError error) {
        closeProgress();
        Log.d("Exception ===" ,error.toString());
    }
    @OnClick({R.id.img_back, R.id.img_upload, R.id.btn_update,R.id.layout_one,R.id.edt_timezone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                //   finish();
                break;

            case R.id.img_upload:
                choicePicture();
                break;
            case R.id.edt_timezone:
                ArrayList<String> arrayList = new ArrayList();
                for(int i =0;i<Commons.timeZoneModels.size();i++){
                    arrayList.add(Commons.timeZoneModels.get(i).getName());
                }
                SelectJobOneDialog selectJobOneDialog = new SelectJobOneDialog();
                selectJobOneDialog.setOnConfirmListener(new SelectJobOneDialog.OnConfirmListener() {
                    @Override
                    public void onConfirm(int selectPosstion) {
                        edt_timezone.setText(arrayList.get(selectPosstion));
                    }
                },arrayList,1);
                selectJobOneDialog.show(getSupportFragmentManager(), "DeleteMessage");
                break;
            case R.id.btn_update:
                if (Validate()){
                    callUpdateProfile();

                }
                break;
        }
    }

    private boolean Validate() {

        email = edtEmail.getText().toString();
        first_name = edtFirstName.getText().toString();
        last_name = edtLastName.getText().toString();
        mobileno = edtMobno.getText().toString();
        address = edtAddress.getText().toString();

        if (TextUtils.isEmpty(first_name)) {
           showToast( "Enter First Name");
            return false;
        }else  if (TextUtils.isEmpty(last_name)) {
            showToast( "Enter Last Name");
            return false;
        }else  if (TextUtils.isEmpty(email)) {
            showToast("Enter Email");
            return false;
//        }else  if (TextUtils.isEmpty(mobileno)) {
//            showToast("Enter Mobile.No");
//            return false;
        }
        return true;
    }
}