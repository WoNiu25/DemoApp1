package com.example.ycxy.three;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.a1031.R;

public class Xiazaidizhi_Activity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//要求窗口没有title
        super.setContentView(R.layout.fragment3_xiazaidizhi);


    }
    @Override
    public void onClick(View view) {

    }
}
