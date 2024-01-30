package com.hr.pereless.activities.email;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

import com.hr.pereless.R;
import com.hr.pereless.base.CommonActivity;
import com.htmleditor.HtmlTextEditor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class EmailSendActivity extends CommonActivity {
    TextView txtTitle;
    ImageView imgback, imgsend, imgclick;
    RelativeLayout clicklayout;
    int status = 0;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_send)
    ImageView imgSend;
    @BindView(R.id.profile_image)
    CircleImageView profileImage;
    @BindView(R.id.click_icon)
    ImageView clickIcon;

    @BindView(R.id.img_scorecard)
    ImageView imgScorecard;
    @BindView(R.id.close)
    ImageView close;
    @BindView(R.id.applied)
    TextView applied;
    @BindView(R.id.smslayout)
    LinearLayout smslayout;
    @BindView(R.id.make)
    TextView make;
    @BindView(R.id.emaillayout)
    LinearLayout emaillayout;
    @BindView(R.id.item_layout)
    LinearLayout itemLayout;
    @BindView(R.id.click_layout)
    RelativeLayout clickLayout;
    @BindView(R.id.rejection)
    TextView rejection;
    @BindView(R.id.setupinterview)
    TextView setupinterview;
    @BindView(R.id.phoneinterview)
    TextView phoneinterview;
    String mailType;
    String mailtext;
    String Token;
    @BindView(R.id.edtemailcontent)
    EditText edtemailcontent;
    @BindView(R.id.html_editor)
    HtmlTextEditor htmlEditor;
    @BindView(R.id.btn_send)
    Button btnSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_send);
        ButterKnife.bind(this);
        txtTitle = (TextView) findViewById(R.id.txt_title);
        txtTitle.setText("Letter");
        imgback = (ImageView) findViewById(R.id.img_back);
        imgsend = (ImageView) findViewById(R.id.img_send);
        imgclick = (ImageView) findViewById(R.id.click_icon);
        clicklayout = (RelativeLayout) findViewById(R.id.click_layout);
        imgsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String temp;
                temp = htmlEditor.getText().toString();
                if (Validate(mailType, temp)) {
                    tosendCommnicationmessge("8", temp, mailType, "503");
                }

                //Toast.makeText(EmailSendActivity.this, "Mail Send", Toast.LENGTH_LONG).show();
                //finish();
            }
        });
        imgclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status == 0) {
                    clicklayout.setVisibility(View.GONE);
                    status = 1;
                } else if (status == 1) {
                    clicklayout.setVisibility(View.VISIBLE);
                    status = 0;
                }


            }
        });

        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
    }

    @OnClick({R.id.btn_send,R.id.img_back, R.id.applied, R.id.make, R.id.rejection, R.id.setupinterview, R.id.phoneinterview})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;

            case R.id.btn_send:
                finish();
                break;
            case R.id.applied:
                clickLayout.setVisibility(View.GONE);
                mailType = "Applied";
                break;

            case R.id.make:
                clickLayout.setVisibility(View.GONE);
                mailType = "Ake Offer";
                break;
            case R.id.rejection:
                clickLayout.setVisibility(View.GONE);
                mailType = "Rejection";
                break;
            case R.id.setupinterview:

                clickLayout.setVisibility(View.GONE);
                mailType = "Setup Interview";
                break;
            case R.id.phoneinterview:
                clickLayout.setVisibility(View.GONE);
                mailType = "Setup Phone Interview";
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private boolean Validate(String emailtype, String message) {

        if (TextUtils.isEmpty(emailtype)) {
            showAlertDialog( "Select Email Type");
            return false;
        } else if (TextUtils.isEmpty(message)) {
            showAlertDialog( "Can't send without body text");
            return false;
        }
        return true;
    }


    private void tosendCommnicationmessge(String userid, String message, String messgetype, String uid) {

    }

}
