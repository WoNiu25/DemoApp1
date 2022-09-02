package com.example.ycxy;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a1031.R;
import com.example.ycxy.home.CodeAnalysis;
import com.example.ycxy.home.NewsMore;
import com.example.ycxy.home.TicketHelper;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class FameFragment extends Fragment  implements View.OnClickListener{
    private LinearLayout click_sao;
    private LinearLayout click_ticketHelper;
    private TextView news1;
    private Button newsMore;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fame, container, false);
        click_sao=view.findViewById(R.id.click_sao);
        click_ticketHelper=view.findViewById(R.id.click_helper);
        newsMore=view.findViewById(R.id.newsMore);

        click_ticketHelper.setOnClickListener(this);
        click_sao.setOnClickListener(this);
        newsMore.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.click_sao:
                Intent intent_analysis=new Intent(getActivity(), CodeAnalysis.class);
                startActivity(intent_analysis);
                break;
            case R.id.click_helper:
                Intent intent_ticket=new Intent(getActivity(), TicketHelper.class);
                startActivity(intent_ticket);
                break;
            case R.id.newsMore:
                Intent intent_newsMore = new Intent(getActivity(), NewsMore.class);
                startActivity(intent_newsMore);
        }
    }

}