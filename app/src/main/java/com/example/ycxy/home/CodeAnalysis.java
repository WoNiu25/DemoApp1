package com.example.ycxy.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.a1031.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.GlobalHistogramBinarizer;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.QRCodeReader;

import java.util.Hashtable;
import java.util.Vector;

public class CodeAnalysis extends AppCompatActivity {
    private ActivityResultLauncher activityResultLauncher;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.code_analysis);

        Toast.makeText(this,"当前手机sdk版本为"+ Build.VERSION.SDK_INT,Toast.LENGTH_SHORT).show();
        //代替StartActivityForResult的船新方式，只能在OnCreate里面赋值
        activityResultLauncher=registerForActivityResult(new ActivityResultContracts.
                StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                Uri uri=result.getData().getData();
                try {
                    Bitmap bitmap= MediaStore.Images.Media.getBitmap(CodeAnalysis.this.getContentResolver(),uri);
                    decodeQRCode(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void onScanQrcode(View v){
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
        integrator.setPrompt("扫描二维码");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.initiateScan();
    }

    //布局中button2的android:onClick="onDecodeQrcode"绑定的回调方法
    public void onDecodeQrcode(View v){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        activityResultLauncher.launch(intent);
    }

    /**
     * 解析图片
     *
     * @param srcBitmap
     * @return
     */
    private String decodeQRCode(Bitmap srcBitmap) {
        // 解码的参数
        Hashtable<DecodeHintType, Object> hints = new Hashtable<>(2);
        // 可以解析的编码类型
        Vector<BarcodeFormat> decodeFormats = new Vector<>();
        if (decodeFormats.isEmpty()) {
            decodeFormats.add(BarcodeFormat.QR_CODE);
        }
        hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        Result result = null;
        int width = srcBitmap.getWidth();
        int height = srcBitmap.getHeight();
        int[] pixels = new int[width * height];
        srcBitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        //新建一个RGBLuminanceSource对象
        RGBLuminanceSource source = new RGBLuminanceSource(width, height, pixels);
        //将图片转换成二进制图片
        BinaryBitmap binaryBitmap = new BinaryBitmap(new GlobalHistogramBinarizer(source));
        QRCodeReader reader = new QRCodeReader();//初始化解析对象
        try {
            result = reader.decode(binaryBitmap, hints);//开始解析
        } catch (NotFoundException | ChecksumException | FormatException e) {
            e.printStackTrace();
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
        if (result != null) {
            Toast.makeText(this, "解析成功，二维码值: "+result.getText(), Toast.LENGTH_LONG).show();
            return result.getText();
        }
        return null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "扫码取消！", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "扫描成功，二维码值: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
