package com.yz.dl.integralmanage.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.comm.Constants;
import com.yz.dl.integralmanage.view.AddAccessoryView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by I'M CHAMAN on 2017/9/27.
 */

public class InspectorChecked extends Activity {
    @Bind(R.id.inspectorchecked_back)
    ImageView inspectorcheckedBack;
    @Bind(R.id.inspector_office_select)
    TextView inspectorOfficeSelect;
    @Bind(R.id.add_accessory)
    ImageView addAccessory;
    @Bind(R.id.accessory_layout)
    AddAccessoryView accessoryLayout;
    File currentImageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspector_checked);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.inspectorchecked_back, R.id.inspector_office_select, R.id.add_accessory})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.inspectorchecked_back:
                break;
            case R.id.inspector_office_select:
//                AlertDialog.Builder builder = new AlertDialog.Builder(this);
//                //设置标题
//                builder.setTitle("请选择");
//                //设置图标
//                builder.setIcon(R.mipmap.ic_launcher);
//                builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        Toast.makeText(getApplicationContext(), "你点击的是条目" + i, Toast.LENGTH_SHORT).show();
//                    }
//                });
//                builder.create();
//                builder.show();
                break;
            case R.id.add_accessory:

                showSimpleListDialog();
                break;
        }
    }

    /**
     * 附件上传弹出dialog选择拍照或者相册
     */
    private void showSimpleListDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String[] Items = {"拍摄", "从手机相册选择"};
        builder.setItems(Items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(openCameraIntent, 1);
                takePhoto();
            }
        });
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    /**
     * 拍照并保存到sd卡下integral文件夹下
     */
    private void takePhoto() {
        File dir = new File(Environment.getExternalStorageDirectory(), "integral");//在sd下创建文件夹myimage；Environment.getExternalStorageDirectory()得到SD卡路径文件
        if (!dir.exists()) {    //exists()判断文件是否存在，不存在则创建文件
            dir.mkdirs();
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HHmmss");//设置日期格式在android中，创建文件时，文件名中不能包含“：”冒号
        String filename = df.format(new Date());
        currentImageFile = new File(dir, filename + ".jpg");
        if (!currentImageFile.exists()) {
            try {
                currentImageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(currentImageFile));
        startActivityForResult(openCameraIntent, Constants.ACTION_TAKE_PHOTO);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == Constants.ACTION_TAKE_PHOTO) {
            String sdStatus = Environment.getExternalStorageState();
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                Log.i("TestFile", "SD card is not avaiable/writeable right now.");
                return;
            }
            //原图
            String filePath = currentImageFile.getAbsolutePath();
            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
            //利用Bitmap对象创建缩略图
            Bitmap showbitmap = ThumbnailUtils.extractThumbnail(bitmap, 250, 250);
            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(showbitmap);
            accessoryLayout.addView(imageView);
//            addView2Accessory(showbitmap);
        }
    }

//    private void addView2Accessory(Bitmap bitmap){
//        accessoryLayout.addView(imageView,0);
//    }
}
