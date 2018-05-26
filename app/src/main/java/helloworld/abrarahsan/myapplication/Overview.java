package helloworld.abrarahsan.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, 500));
        barEntries.add(new BarEntry(2, 1000));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Actual Spending");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.4f);

        barchart.setData(data);
        //barchart.invalidate();
        barchart.groupBars(1, 0.1f, 0.02f);
        String[] tasks = new String[]{"Real", "Projected"};
        // String[] months =
        XAxis xAxis = barchart.getXAxis();
        //xAxis.setValueFormatter(new myXAxisValueFormatter(tasks));


        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMinimum(1);
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
