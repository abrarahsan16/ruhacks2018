package helloworld.abrarahsan.myapplication;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class History extends Activity {
    BarChart barchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        barchart = (BarChart) findViewById(R.id.barchart);

        barchart.setDrawBarShadow(false);
        barchart.setDrawValueAboveBar(true);
        barchart.setMaxVisibleValueCount(50);
        barchart.setPinchZoom(false);
        barchart.setDrawGridBackground(true);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, 1000));
        barEntries.add(new BarEntry(2, 2000));
        barEntries.add(new BarEntry(3, 500));

        ArrayList<BarEntry> barEntries2 = new ArrayList<>();
        barEntries2.add(new BarEntry(1, 400));
        barEntries2.add(new BarEntry(2, 1000));
        barEntries2.add(new BarEntry(3, 1000));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Income");
        BarDataSet barDataSet2 = new BarDataSet(barEntries2, "Expense");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(barDataSet, barDataSet2);
        data.setBarWidth(0.3f);

        barchart.setData(data);
        barchart.groupBars(1, 0.1f, 0.02f);
        //barchart.invalidate();

        String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May"};
        XAxis xAxis = barchart.getXAxis();
        //YAxis yAxis = barchart.getYAxis();
        //xAxis.setValueFormatter(new MyXAxisValueFormatter(months));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setGranularity(1);
        xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(1);
    }


}



