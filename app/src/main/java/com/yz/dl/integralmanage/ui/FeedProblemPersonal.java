package com.yz.dl.integralmanage.ui;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.comm.Constants;
import com.yz.dl.integralmanage.view.AddAccessoryView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by I'M CHAMAN on 2017/9/27.
 */

public class FeedProblemPersonal extends Activity {
    @Bind(R.id.feedback_personal_back)
    ImageView feedbackPersonalBack;
    @Bind(R.id.feedback_personal_study)
    TextView feedbackPersonalStudy;
    @Bind(R.id.feedback_personal_to)
    TextView feedbackPersonalTo;
    @Bind(R.id.feedback_personal_describe)
    EditText feedbackPersonalDescribe;
    @Bind(R.id.feedback_add_accessory)
    ImageView feedbackAddAccessory;
    @Bind(R.id.feedbacd_accessory_layout)
    AddAccessoryView feedbacdAccessoryLayout;
    @Bind(R.id.feedback_reapit)
    Button feedbackReapit;
    @Bind(R.id.feedback_submit)
    Button feedbackSubmit;


    File currentImageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedproblempersonal);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.feedback_personal_back, R.id.feedback_personal_study, R.id.feedback_personal_to, R.id.feedback_add_accessory, R.id.feedback_reapit, R.id.feedback_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.feedback_personal_back:
                this.finish();
                break;
            case R.id.feedback_personal_study:
                String[] studys = {"培训类", "实践类", "理论类"};
                selectDefault(studys, feedbackPersonalStudy);
                break;
            case R.id.feedback_personal_to:
                String[] tos = {"授课", "教育", "学习"};
                selectDefault(tos, feedbackPersonalTo);
                break;
            case R.id.feedback_add_accessory:

                showSimpleListDialog();

                break;
            case R.id.feedback_reapit:
                break;
            case R.id.feedback_submit:
                break;
        }
    }

    /**
     * 默认的选择器
     *
     * @param items
     * @param tv
     */
    private void selectDefault(final String[] items, final TextView tv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tv.setText(items[i]);
            }
        });
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
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
                switch (i) {
                    case 0:
                        takePhoto();
                        break;
                    case 1:
                        selectPhoto();
                        break;
                }
            }
        });
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 从相册选择图片并处理
     */
    private void selectPhoto() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "选择图片"), Constants.ACTION_SELCET_PHOTO);
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

    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            String sdStatus = Environment.getExternalStorageState();
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                Log.i("TestFile", "SD card is not avaiable/writeable right now.");
                return;
            }

            //原图
            String filePath = null;

            if (requestCode == Constants.ACTION_TAKE_PHOTO) {
                filePath = currentImageFile.getAbsolutePath();
            }
            if (requestCode == Constants.ACTION_SELCET_PHOTO && data != null) {

                ContentResolver resolver = getContentResolver();
                //照片的原始资源地址
                Uri originalUri = data.getData();
                filePath = getRealFilePath(getApplicationContext(), originalUri);
            }

            Bitmap bitmap = BitmapFactory.decodeFile(filePath);
            //利用Bitmap对象创建缩略图
            Bitmap showbitmap = ThumbnailUtils.extractThumbnail(bitmap, 250, 250);
            ImageView imageView = new ImageView(this);
            imageView.setImageBitmap(showbitmap);
            feedbacdAccessoryLayout.addView(imageView);
//            addView2Accessory(showbitmap);
        }
    }

}
