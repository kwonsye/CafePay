package com.example.kwons.cafepay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.kwons.cafepay.Service.EvaluateByCafeMenuService;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by sujin.kim on 2018. 8. 11..
 */

public class RatingPopup extends DialogFragment {
    private Button yes;
    private Button no;
    private RatingBar ratingBar;
    private String userId;
    private int paymentId;
    private float value;
    private EvaluateByCafeMenuService evaluateByCafeMenuService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static RatingPopup newInstance(String userId, Integer paymentId) {
        RatingPopup popupDialog = new RatingPopup();
        Bundle args = new Bundle();
        args.putString("userId", userId);
        args.putInt("paymentId", paymentId);
        popupDialog.setArguments(args);
        return popupDialog;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rating_popup, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if (getArguments() != null) {
            userId = getArguments().getString("userId");
            paymentId = getArguments().getInt("paymentId");
        }
        yes = view.findViewById(R.id.answer_yes);
        no = view.findViewById(R.id.answer_no);
        ratingBar = view.findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                value = rating;
                Log.d("Rating",value+"점");
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Retrofit
                evaluateByCafeMenuService = RetrofitClient.getClient().create(EvaluateByCafeMenuService.class);
                Call<JSONObject> evaluateByCafeMenuCall = evaluateByCafeMenuService.evaluateByCafeMenuService(userId,paymentId, value);

                evaluateByCafeMenuCall.enqueue(new Callback<JSONObject>() {
                    @Override
                    public void onResponse(Call<JSONObject> call, retrofit2.Response<JSONObject> response) {
                        if (call.isExecuted()) {
                            Log.d("evaluate", "success");
                        }else
                            Log.d("evaluate", "fail");
                    }

                    @Override
                    public void onFailure(Call<JSONObject> call, Throwable t) {
                        Log.d("evaluate error ", t.getMessage());
                        call.cancel();

                    }
                });
                dismiss();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
