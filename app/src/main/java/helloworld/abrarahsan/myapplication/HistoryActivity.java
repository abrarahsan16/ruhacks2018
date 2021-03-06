package helloworld.abrarahsan.myapplication;


import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

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
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class HistoryActivity extends Activity {
    public static boolean isRecursionEnable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //runInBackground();

        if (ParseUser.getCurrentUser() != null) {

            ParseQuery<ParseUser> query = ParseUser.getQuery();
            query.whereEqualTo("email", ParseUser.getCurrentUser().getEmail().toString());
            query.findInBackground(new FindCallback<ParseUser>() {


                @Override
                public void done(List<ParseUser> objects, ParseException e) {
                    if (e == null && !objects.isEmpty()) {
                        ParseUser user = objects.get(0);
                        double food = user.getDouble("food");
                        double misc = user.getDouble("misc");
                        double trans = user.getDouble("trans");
                        double util = user.getDouble("util");

                        drawChart(food, misc, trans, util);

                        /** FOUR DATABASE FIELDS GIVEN HERE, USE THIS TO PRESENT THE DATA */

                        /*Description description = new Description();
                        description.setText(Double.toString(barEntries.get(0)) + ", " + Double.toString(barEntries.get(1))
                                + ", " + Double.toString(trans[0]) + ", " + Double.toString(util[0]));
                        barchart.setDescription(description);*/
                    } else {
                        Toast.makeText(HistoryActivity.this, "No user data found!",
                                Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } else {
            Toast.makeText(this, "Nobody is signed in!", Toast.LENGTH_SHORT).show();
        }
    }

    public void drawChart(double food, double misc, double trans, double util) {
        BarChart barchart = (BarChart) findViewById(R.id.barchart);

        barchart.setDrawBarShadow(false);
        barchart.setDrawValueAboveBar(true);
        barchart.setMaxVisibleValueCount(50);
        barchart.setPinchZoom(false);
        barchart.setDrawGridBackground(true);
        barchart.setGridBackgroundColor(Color.TRANSPARENT);

        final ArrayList<Double> vals = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.0));

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, (float)food)); //food
        barEntries.add(new BarEntry(1, (float)misc)); //misc
        barEntries.add(new BarEntry(2, (float)trans)); //trans
        barEntries.add(new BarEntry(3, (float)util)); //util

        ArrayList<BarEntry> barEntries2 = new ArrayList<>();
        barEntries2.add(new BarEntry(1, 400));
        barEntries2.add(new BarEntry(2, 1000));
        barEntries2.add(new BarEntry(3, 1000));

        Log.i("Food", Double.toString(food));
        Log.i("Trans", Double.toString(misc));
        Log.i("Util", Double.toString(trans));
        Log.i("Misc", Double.toString(util));

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

        barchart.invalidate();
    }

    public void onStop() {

        super.onStop();
    }
}



