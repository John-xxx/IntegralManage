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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.comm.Constants;

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

public class ManagerChecked extends Activity {
    @Bind(R.id.managerchecked_back)
    ImageView managercheckedBack;
    @Bind(R.id.manager_area_select)
    TextView managerAreaSelect;
    @Bind(R.id.manager_gas_select)
    TextView managerGasSelect;
    @Bind(R.id.manager_person_select)
    TextView managerPersonSelect;
    @Bind(R.id.manager_datetime_select)
    TextView managerDatetimeSelect;
    @Bind(R.id.manager_addpoint_select)
    TextView managerAddpointSelect;
    @Bind(R.id.manager_markdown_select)
    TextView managerMarkdownSelect;
    @Bind(R.id.manager_describe_select)
    EditText managerDescribeSelect;
    @Bind(R.id.manager_add_accessory)
    ImageView managerAddAccessory;
    @Bind(R.id.accessory_layout)
    LinearLayout accessoryLayout;
    @Bind(R.id.manager_checked_ok)
    Button managerCheckedOk;
    @Bind(R.id.manager_checked_cancle)
    Button managerCheckedCancle;



    File currentImageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_checked);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.managerchecked_back, R.id.manager_area_select, R.id.manager_gas_select, R.id.manager_person_select, R.id.manager_datetime_select, R.id.manager_addpoint_select, R.id.manager_markdown_select, R.id.manager_add_accessory, R.id.manager_checked_ok, R.id.manager_checked_cancle})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.managerchecked_back:
            case R.id.manager_checked_cancle:
                this.finish();
                break;
            case R.id.manager_area_select:

                String[] areas = {"武侯区", "锦江区", "温江区"};
                selectDefault(areas, managerAreaSelect);
                break;
            case R.id.manager_gas_select:
                String[] gass = {"宏达加油站", "川威加油站", "龙潭寺加油站"};
                selectDefault(gass, managerGasSelect);
                break;
            case R.id.manager_person_select:
                String[] persons = {"龙腾", "安军", "陈信宏"};
                selectDefault(persons, managerPersonSelect);
                break;
            case R.id.manager_datetime_select:

                break;
            case R.id.manager_addpoint_select:
                String[] addpoints = {"+3|主动用普通话交流", "+2|油枪放置规范", "+5|认真对待每一位客户"};
                selectDefault(addpoints, managerAddpointSelect);
                managerMarkdownSelect.setText("您已选择加分项");
                break;
            case R.id.manager_markdown_select:
                String[] markDowns = {"-3|无故旷工", "-2|懈怠工作", "-5|辱骂客户"};
                selectDefault(markDowns, managerMarkdownSelect);
                managerAddpointSelect.setText("您已选择减分项");
                break;
            case R.id.manager_add_accessory:

                showSimpleListDialog();

                break;
            case R.id.manager_checked_ok:
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
            accessoryLayout.addView(imageView);
//            addView2Accessory(showbitmap);
        }
    }
}
