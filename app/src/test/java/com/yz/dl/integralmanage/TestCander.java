package com.yz.dl.integralmanage;

/**
 * Created by I'M CHAMAN on 2017/9/29.
 */

public class TestCander {


    public class MainActivity extends Activity {

        private TextView getTime;
        private Calendar calendar;// 用来装日期的
        private DatePickerDialog dialog;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            getTime = (TextView) findViewById(R.id.time);
            getTime.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    calendar = Calendar.getInstance();
                    dialog = new DatePickerDialog(MainActivity.this,
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {
                                    System.out.println("年-->" + year + "月-->"
                                            + monthOfYear + "日-->" + dayOfMonth);
                                    getTime.setText(year + "/" + monthOfYear + "/"
                                            + dayOfMonth);
                                }
                            }, calendar.get(Calendar.YEAR), calendar
                            .get(Calendar.MONTH), calendar
                            .get(Calendar.DAY_OF_MONTH));
                    dialog.show();
                }
            });

        }
    }
}
