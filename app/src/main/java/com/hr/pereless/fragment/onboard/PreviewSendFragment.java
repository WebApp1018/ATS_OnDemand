package com.hr.pereless.fragment.onboard;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.hr.pereless.R;
import com.hr.pereless.activities.onboarding.OnBoardingTaskActivity;
import com.hr.pereless.api.API;
import com.hr.pereless.api.BaseJsonObjectRequest;
import com.hr.pereless.application.AppController;
import com.hr.pereless.commons.Commons;
import com.hr.pereless.model.onboarding.DocumentModel;
import com.htmleditor.HtmlTextEditor;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.hr.pereless.base.BaseActivity.closeProgress;


public class PreviewSendFragment extends Fragment {

    View view ;
    Unbinder unbinder;
    OnBoardingTaskActivity context;
    @BindView(R.id.html_editor)
    HtmlTextEditor html_editor;
    @BindView(R.id.btn_previous)
    Button btn_previous;
    @BindView(R.id.btn_next)
    Button btn_next;
    public static PreviewSendFragment newInstance(String param1, String param2) {
        PreviewSendFragment fragment = new PreviewSendFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getSetting();

    }
    void getSetting(){
        context.showProgress();

        String api_link = API.GET_SETTING ;
        StringRequest myRequest = new StringRequest(
                Request.Method.GET,
                api_link,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String json) {
                        closeProgress();
                        try {
                            JSONObject jsonObject = new JSONObject(json);
                            String str = jsonObject.getString("defaultOnboardingEmailMessage");
                            html_editor.setText(str);
                        }catch (Exception e){
                            context.closeProgress();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        context.closeProgress();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("Authorization", Commons.token);
                return params;
            }
        };
        myRequest.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(myRequest, "tag");
    }

    void updateSetting(){
        context.showProgress();
        String api_link = API.SENDSETTING;
        JSONObject params = new JSONObject();
        try {

            params.put("rid",context.onboardingModel.getRid());

        } catch (JSONException e) {

        }

        Log.d("aaaaa",params.toString());
        new BaseJsonObjectRequest(
                Request.Method.PUT, api_link, params,
                response -> {
                    context.closeProgress();
                    Toast.makeText(context, "Onboarding Moved Successfully!", Toast.LENGTH_SHORT).show();
                    context.viewPager.setCurrentItem(4);
                }, this::handleMultiPartResponseError);
    }
    private void handleMultiPartResponseError(VolleyError error) {
        context.closeProgress();
        Log.d("Exception ===" ,error.toString());
    }
    @OnClick({R.id.btn_previous,R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_next:
                updateSetting();
                break;
            case R.id.btn_previous:
                context.viewPager.setCurrentItem(3);
                break;

        }
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (OnBoardingTaskActivity) context;

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_preview_send, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
}