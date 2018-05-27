package helloworld.abrarahsan.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
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

public class OverviewActivity extends AppCompatActivity {
    BarChart barchart;
    TextView actualTextView;
    TextView projectedTextView;

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.overview_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.history) {
            Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.planning) {
            Intent intent = new Intent(getApplicationContext(), PlanningActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.logout) {
            ParseUser.logOut();
            Intent intent = new Intent(getApplicationContext(), AuthenticationActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

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
                        Toast.makeText(OverviewActivity.this, "No user data found!",
                                Toast.LENGTH_SHORT).show();
                    }

                }
            });
        } else {
            Toast.makeText(this, "Nobody is signed in!", Toast.LENGTH_SHORT).show();
        }
    }

    public void drawChart(double food, double misc, double trans, double util) {
        barchart = (BarChart) findViewById(R.id.barchartOverview);
        barchart.setDrawBarShadow(false);
        barchart.setDrawValueAboveBar(true);
        barchart.setMaxVisibleValueCount(50);
        barchart.setPinchZoom(false);
        barchart.setDrawGridBackground(true);
        barchart.setGridBackgroundColor(Color.TRANSPARENT);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, (float)food));
        barEntries.add(new BarEntry(1, (float)misc));
        barEntries.add(new BarEntry(1, (float)trans));
        barEntries.add(new BarEntry(1, (float)util));

        ArrayList<BarEntry> barEntries2 = new ArrayList<>();
        barEntries.add(new BarEntry(2, (float)food + (float)misc + (float)trans + (float)util));
        //ArrayList<BarEntry>barEntries3 = new ArrayList<>();

        //barEntries.add(new BarEntry(2, null));

        //  ArrayList<BarEntry>barEntries4 = new ArrayList<>();
        //  barEntries.add(new BarEntry(1, 150));


        actualTextView = findViewById(R.id.actualTextView);
        actualTextView.setText("Actual spending: $"
                + String.format("%-1.2f", barEntries.get(0).getY() + barEntries.get(1).getY() +
                barEntries.get(2).getY() + barEntries.get(3).getY()));
        projectedTextView = findViewById(R.id.projectedTextView);
        projectedTextView.setText("Projected spending: $"
                + String.format("%-1.2f", barEntries.get(4).getY()));


        BarDataSet barDataSet = new BarDataSet(barEntries, "Actual spending");
        BarDataSet barDataSet2 = new BarDataSet(barEntries2, "Projected spending");
//     //   BarDataSet barDataSet4 = new BarDataSet(barEntries4, "Cat4");


        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);


        BarData data = new BarData(barDataSet, barDataSet2);
        data.setBarWidth(0.27f);

        barchart.setData(data);
        //barchart.invalidate();
        barchart.groupBars(0, 0f, 0f);
        //String[] tasks = new String[]{"Real", "Projected"};
        // String[] months =
        XAxis xAxis = barchart.getXAxis();
        //xAxis.setValueFormatter(new myXAxisValueFormatter(tasks));


        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(0);

        barchart.invalidate();
    }
}
