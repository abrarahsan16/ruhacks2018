package helloworld.abrarahsan.myapplication;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Overview extends AppCompatActivity {
    BarChart barchart;
    TextView actualTextView;
    TextView projectedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);
        barchart = (BarChart) findViewById(R.id.barchartOverview);
        barchart.setDrawBarShadow(false);
        barchart.setDrawValueAboveBar(true);
        barchart.setMaxVisibleValueCount(50);
        barchart.setPinchZoom(false);
        barchart.setDrawGridBackground(true);
        barchart.setGridBackgroundColor(Color.TRANSPARENT);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, 400));
        barEntries.add(new BarEntry(2, 1000));

        ArrayList<BarEntry>barEntries2 = new ArrayList<>();
        barEntries.add(new BarEntry(1, 250));
        barEntries.add(new BarEntry(2, null));

        ArrayList<BarEntry>barEntries3 = new ArrayList<>();
          barEntries.add(new BarEntry(1, 100));
        barEntries.add(new BarEntry(2, null));

        ArrayList<BarEntry>barEntries4 = new ArrayList<>();
          barEntries.add(new BarEntry(1, 150));
        barEntries.add(new BarEntry(2, null));



        actualTextView = findViewById(R.id.actualTextView);
        actualTextView.setText("Actual spending: $"
                + String.format("%-1.2f", barEntries.get(0).getY()));
        projectedTextView = findViewById(R.id.projectedTextView);
        projectedTextView.setText("Projected spending: $"
                + String.format("%-1.2f", barEntries.get(1).getY()));



        BarDataSet barDataSet = new BarDataSet(barEntries, "Cat1");
        BarDataSet barDataSet2 = new BarDataSet(barEntries2, "Cat2");
        BarDataSet barDataSet3 = new BarDataSet(barEntries3, "Cat3");
        BarDataSet barDataSet4 = new BarDataSet(barEntries4, "Cat4");


        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet3.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet4.setColors(ColorTemplate.COLORFUL_COLORS);


        BarData data = new BarData(barDataSet, barDataSet2, barDataSet3, barDataSet4);
        data.setBarWidth(0.2f);

        barchart.setData(data);
        //barchart.invalidate();
        barchart.groupBars(1, 0.2f, 0.01f);
        //String[] tasks = new String[]{"Real", "Projected"};
        // String[] months =
        XAxis xAxis = barchart.getXAxis();
        //xAxis.setValueFormatter(new myXAxisValueFormatter(tasks));


        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(0);
    }
    public class myXAxisValueFormatter implements IAxisValueFormatter {

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

}
