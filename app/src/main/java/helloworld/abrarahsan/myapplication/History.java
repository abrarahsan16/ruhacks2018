package helloworld.abrarahsan.myapplication;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class History extends Activity{
    BarChart barchart;
    public static boolean isRecursionEnable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //runInBackground();

        barchart = (BarChart) findViewById(R.id.barchart);

        barchart.setDrawBarShadow(false);
        barchart.setDrawValueAboveBar(true);
        barchart.setMaxVisibleValueCount(50);
        barchart.setPinchZoom(false);
        barchart.setDrawGridBackground(true);

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername().toString());
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if (e == null && !objects.isEmpty()) {
                    ParseUser user = objects.get(0);
                    double food = user.getDouble("food");
                    double misc = user.getDouble("misc");
                    double trans = user.getDouble("trans");
                    double util = user.getDouble("util");

                    Description description = new Description();
                    description.setText(Double.toString(food) + ", " + Double.toString(misc)
                            + ", " + Double.toString(trans) + ", " + Double.toString(util));
                    barchart.setDescription(description);
                } else {
                    Description description = new Description();
                    description.setText("No user data found");
                    barchart.setDescription(description);
                }
            }
        });

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, 1000));
        barEntries.add(new BarEntry(2, 2000));
        barEntries.add(new BarEntry(3, 500));

        ArrayList<BarEntry> barEntries2 = new ArrayList<>();
        barEntries2.add(new BarEntry(1, 400));
        barEntries2.add(new BarEntry(2, 1000));
        barEntries2.add(new BarEntry(3, 1000));

        /*ArrayList<String>labels = new ArrayList<String>();
        labels.add("Mar");
        labels.add("Feb");
        labels.add("Jan");*/

        BarDataSet barDataSet = new BarDataSet(barEntries, "Actual Spending");
        BarDataSet barDataSet2 = new BarDataSet(barEntries2, "Projected Spending");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(barDataSet, barDataSet2);
        data.setBarWidth(0.35f);

        barchart.setData(data);
        barchart.groupBars(1, 0.1f, 0.02f);
        //barchart.invalidate();
        XAxis xAxis = barchart.getXAxis();
       // String[] months =


        //xAxis.setValueFormatter(new myXAxisValueFormatter(months));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(1);
        onStop();
    }


/*    public void runInBackground() {
        if (isRecursionEnable)
            return;

        isRecursionEnable = true; //on exception on thread make it true again
        new Thread(new Runnable() {
            @Override
            public void run() {
                //do task here

                //YAxis yAxis = barchart.getYAxis();

                /*if (activity_is_not_in_background) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            runInBackground();
                        }
                    });
                } else {
                    runInBackground();
                }
            }
        }).start();

    }*/
    public class myXAxisValueFormatter implements IAxisValueFormatter{

        private String[] mValues;
        public myXAxisValueFormatter(String[]values){
            this.mValues = values;
        }
        @Override
        public String getFormattedValue(float value, AxisBase axis)
        {
            return mValues[(int)value];
        }
    }
    public void onStop() {

        super.onStop();
    }
}



