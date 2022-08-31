package com.example.ycxy;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.a1031.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class FameFragment extends Fragment{
    LinearLayout saoma_layout;
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {

    // Inflate the layout for this fragment
    View view= inflater.inflate(R.layout.fragment_fame, container, false);
    saoma_layout=view.findViewById(R.id.saoma_layout);
    saoma_layout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            IntentIntegrator intentIntegrator=new IntentIntegrator(getActivity());
             intentIntegrator.setPrompt("请将二维码放入取景框中");
             intentIntegrator.setBeepEnabled(true);
             intentIntegrator.setOrientationLocked(true);
             intentIntegrator.setCaptureActivity(Capture.class);//
            intentIntegrator.initiateScan();
        }
    });
     return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResultresult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(intentResultresult != null) {
            if(intentResultresult.getContents()!=null) {
                AlertDialog.Builder builder=new AlertDialog.Builder(
                        getActivity()
                );
                builder.setTitle("扫描结果");
                builder.setMessage(intentResultresult.getContents());
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
            } else {
                Toast.makeText(this.getActivity(), "扫码内容: " + intentResultresult.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}