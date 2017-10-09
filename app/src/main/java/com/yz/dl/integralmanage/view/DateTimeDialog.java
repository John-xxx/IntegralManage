package com.yz.dl.integralmanage.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.yz.dl.integralmanage.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 自定义显示时间日期的dialog
 * Created by chaman on 2017/10/8.
 */

public class DateTimeDialog extends Dialog {

    private Context context = null;

    private Button btn;

    private int s_year;
    private int s_month;
    private int s_day;
    private int s_hour;
    private int s_minute;

    private DatePicker datePicker = null;

    private TimePicker timePicker = null;

    private String stime = "";

    private String flag = "";

    private Calendar calendar = null;

    private Date date = null;

    // 构造一个回调函数
    public interface GetDateTimeEventListener {
        public void getdatetime(String datetime);
    }

    private GetDateTimeEventListener dateTimeEventListener;

    /**
     *
     * @param context
     * @param dateTimeEventListener
     * @param stime
     *            传进来的时间字符串 (可以为空)
     * @param flag
     *            显示日期时间的形式 all为全部 , onlyDate 为只显示日期 , onlyTime 为只显示时间
     */
    public DateTimeDialog(Context context,
                          GetDateTimeEventListener dateTimeEventListener, String stime,
                          String flag) {
        super(context);
        this.context = context;
        this.dateTimeEventListener = dateTimeEventListener;
        this.stime = stime;
        this.flag = flag;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datetimedialog);

        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        btn = (Button) findViewById(R.id.btn_datetime);

        // 设置日期简洁显示
        datePicker.setCalendarViewShown(false);
        // 设置时间显示为24小时制
        timePicker.setIs24HourView(true);

        initView();

        calendar = Calendar.getInstance(Locale.SIMPLIFIED_CHINESE);

        if (!stime.equals("")) {



//            switch (flag) {
//                case "onlyDate":
//                    date = DateUtils.strtoDate(stime, "yyyy-MM-dd");
//                    break;
//                case "onlyTime":
//                    date = DateUtils.strtoDate(stime, "HH:mm");
//                    break;
//                case "all":
//                    date = DateUtils.strtoDate(stime, "yyyy-MM-dd HH:mm");
//                    break;
//                default:
//                    break;
//            }
        } else {
            date = new Date(System.currentTimeMillis());
        }

        calendar.setTime(date);

        s_year = calendar.get(Calendar.YEAR);
        s_month = calendar.get(Calendar.MONTH);
        s_day = calendar.get(Calendar.DAY_OF_MONTH);

        s_hour = calendar.get(Calendar.HOUR_OF_DAY);
        s_minute = calendar.get(Calendar.MINUTE);

        if (!flag.equals("onlyTime")) {
            datePicker.init(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    new DatePicker.OnDateChangedListener() {

                        @Override
                        public void onDateChanged(DatePicker arg0, int year,
                                                  int month, int dayofmonth) {
                            s_year = year;
                            s_month = month;
                            s_day = dayofmonth;
                        }
                    });
        }

        if (!flag.equals("onlyDate")) {

            timePicker.setCurrentHour(calendar.get(Calendar.HOUR_OF_DAY));
            timePicker.setCurrentMinute(calendar.get(Calendar.MINUTE));

            timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

                @Override
                public void onTimeChanged(TimePicker arg0, int hour, int minute) {
                    s_hour = hour;
                    s_minute = minute;
                }
            });
        }

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // 最后返回的值月份 + 1
                String tmp = String.format(Locale.SIMPLIFIED_CHINESE,
                        "%d-%02d-%02d %02d:%02d", s_year, s_month + 1, s_day,
                        s_hour, s_minute);

                // 如果不是为all ,则根据flag的值进行字符串截取
                if (!flag.equals("all")) {
                    tmp = GetValueByFlag(tmp);
                }
                dateTimeEventListener.getdatetime(tmp);
                dismiss();
            }
        });
    }

    /**
     *
     * @param value
     * @return 根据flag截取日期时间字符串
     */
    public String GetValueByFlag(String value) {

        String temp = "";

        String[] tempString = value.split(" ");

        switch (flag) {
            case "onlyDate":

                temp = tempString[0];

                break;

            case "onlyTime":
                temp = tempString[1];
                break;
            default:
                break;
        }

        return temp;
    }

    /**
     * 初始化view
     */
    private void initView() {

        if (!flag.equals("")) {
            switch (flag) {
                case "onlyTime":

                    this.setTitle("请选择时间");
                    datePicker.setVisibility(View.GONE);

                    break;
                case "onlyDate":

                    this.setTitle("请选择日期");
                    timePicker.setVisibility(View.GONE);

                    break;
                default:
                    this.setTitle("请选择日期时间");
                    break;
            }
        }

    }
}