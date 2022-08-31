package com.example.ycxy.three;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.a1031.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Yijianfankui_Activity extends AppCompatActivity {
    private TextInputLayout mySuggestionSubmitLayout;
    private TextInputEditText mySuggestionSubmit;
    private Button btnSuggestionSubmit;
    private  Button btnRecords;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//要求窗口没有title
        super.setContentView(R.layout.fragment3_yijianfankui);
        // todo 在我们的这个位置的话就是设置我们的已将反馈的界面
        initView();
    }

    private void initView() {
        mySuggestionSubmitLayout = (TextInputLayout) findViewById(R.id.my_suggestion_submit_layout);
        mySuggestionSubmit = (TextInputEditText) findViewById(R.id.my_suggestion_submit);
        btnSuggestionSubmit = (Button) findViewById(R.id.btn_suggestion_submit);
        btnRecords = (Button) findViewById(R.id.records);
        // 在我们的这个位置的话就是设置我们的相关的意见反馈的界面
        mySuggestionSubmit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // 在我们的这个位置的话就是判断我们输入的字体的输入的数量
                if(editable.length() > 300){
                    editable.delete(299,editable.length()-1);
                    // 在我们的这个位置的话就是弹出一个我们的toast的弹窗然后的恶化自动的将我们的超出的部分进行删除
                    AlertDialog alertDialog2 = new AlertDialog.Builder(Yijianfankui_Activity.this)
                            .setTitle("不能超出300字")
                            .setIcon(R.mipmap.ic_launcher)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加"Yes"按钮
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(Yijianfankui_Activity.this, "确定", Toast.LENGTH_SHORT).show();
                                }
                            })

                            .setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加取消
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(Yijianfankui_Activity.this, "取消", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .create();
                    alertDialog2.show();
//                    MyApplication.showToast("不能超出150字");
                }
            }
        });
//        btnSuggestionSubmit.setOnClickListener(view -> {
//            // 在我们的这个位置的话就是设置我们的相关的方法然后的话囧事将我们的东西提交到我们的服务器
//            HashMap<String,Object> map = new HashMap<>(); // 这个的话就是我们的hashmap的列表
//            String s = mySuggestionSubmit.getText().toString();
//            if(TextUtils.isEmpty(s)){
//
//                // todo 意见反馈为空请输入我们的内容
//                Toast.makeText(MainActivity.this,"意见不能为空请重新输入",Toast.LENGTH_LONG).show();
//            }else{
//                Toast.makeText(MainActivity.this,"意见提交成功",Toast.LENGTH_LONG).show();
//                mySuggestionSubmit.setText("");
//                // 然后的话在我们的这个位置的话就是清空我们的输入框中的内容
//                finish();
//            }
//        });
        btnRecords.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //在屏幕上显示提示 Toast 吐司
                Intent intent = new Intent();
                intent.setClass(Yijianfankui_Activity.this,records.class);
                startActivity(intent);
            }
        });
    }
}
