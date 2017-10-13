package com.yz.dl.integralmanage.ui;

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
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yz.dl.integralmanage.R;
import com.yz.dl.integralmanage.comm.Constants;
import com.yz.dl.integralmanage.view.AddAccessoryView;
import com.yz.dl.integralmanage.view.slidedatetimepicker.SlideDateTimeListener;
import com.yz.dl.integralmanage.view.slidedatetimepicker.SlideDateTimePicker;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 稽查员打分
 * Created by I'M CHAMAN on 2017/9/27.
 */

public class InspectorChecked extends FragmentActivity {
    @Bind(R.id.inspectorchecked_back)
    ImageView inspectorcheckedBack;
    @Bind(R.id.inspector_office_select)
    TextView inspectorOfficeSelect;
    @Bind(R.id.add_accessory)
    ImageView addAccessory;
    @Bind(R.id.accessory_layout)
    AddAccessoryView accessoryLayout;
    @Bind(R.id.inspector_area_select)
    TextView inspectorAreaSelect;
    @Bind(R.id.inspector_gas_select)
    TextView inspectorGasSelect;
    @Bind(R.id.inspector_person_select)
    TextView inspectorPersonSelect;
    @Bind(R.id.inspector_date_select)
    TextView inspectorDateSelect;
    @Bind(R.id.inspector_addpoint_select)
    TextView inspectorAddpointSelect;
    @Bind(R.id.inspector_markdown_select)
    TextView inspectorMarkdownSelect;
    @Bind(R.id.inspector_describe_edit)
    EditText inspectorDescribeEdit;


    File currentImageFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspector_checked);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.inspectorchecked_back, R.id.inspector_office_select, R.id.add_accessory, R.id.inspector_area_select, R.id.inspector_gas_select, R.id.inspector_person_select, R.id.inspector_date_select, R.id.inspector_addpoint_select, R.id.inspector_markdown_select})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.inspectorchecked_back:
                this.finish();
                break;
            case R.id.inspector_office_select:
                String[] items = {"成都分公司", "绵阳分公司"};
                officeSelect(items, inspectorOfficeSelect);
                break;
            case R.id.add_accessory:

                showSimpleListDialog();
                break;
            case R.id.inspector_area_select:
                String[] areas = {"武侯区", "锦江区", "温江区"};
                officeSelect(areas, inspectorAreaSelect);
                break;
            case R.id.inspector_gas_select:
                String[] gass = {"宏大加油站", "江淮加油站", "称心蒲剑油站"};
                officeSelect(gass, inspectorGasSelect);
                break;
            case R.id.inspector_person_select:
                String[] pesons = {"李华", "张明", "鲁信"};
                officeSelect(pesons, inspectorPersonSelect);
                break;
            case R.id.inspector_date_select:
                setDateTime();
                break;
            case R.id.inspector_addpoint_select:
                String[] addpoints = {"+3|主动用普通话交流", "+2|油枪放置规范", "+5|认真对待每一位客户"};
                officeSelect(addpoints, inspectorAddpointSelect);
                inspectorMarkdownSelect.setText("您已选择加分项");
                break;
            case R.id.inspector_markdown_select:
                String[] markdowns = {"-3|无故旷工", "-2|懈怠工作", "-5|辱骂客户"};
                officeSelect(markdowns, inspectorMarkdownSelect);
                inspectorAddpointSelect.setText("您已选择减分项");
                break;
        }
    }

    /**
     * 选择日期和时间
     */
    private void setDateTime() {
        new SlideDateTimePicker.Builder(getSupportFragmentManager())
                .setListener(listener)
                .setInitialDate(new Date())
                //.setMinDate(minDate)
                //.setMaxDate(maxDate)
                //.setIs24HourTime(true)
                //.setTheme(SlideDateTimePicker.HOLO_DARK)
                //.setIndicatorColor(Color.parseColor("#990000"))
                .build()
                .show();
    }


    private SlideDateTimeListener listener = new SlideDateTimeListener() {

        private SimpleDateFormat mFormatter = new SimpleDateFormat("yy-MM-dd HH:mm");

        @Override
        public void onDateTimeSet(Date date) {
            Log.i("TAG", date.toString());
            Toast.makeText(InspectorChecked.this, mFormatter.format(date), Toast.LENGTH_SHORT).show();
            inspectorDateSelect.setText(mFormatter.format(date));
        }

        // Optional cancel listener
        @Override
        public void onDateTimeCancel() {
            Toast.makeText(InspectorChecked.this,
                    "Canceled", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 打分选择
     */
    private void officeSelect(final String[] items, final TextView tv) {
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


    /**
     * 获取文件路径
     **/
    public String uri2filePath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        return path;
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
}
