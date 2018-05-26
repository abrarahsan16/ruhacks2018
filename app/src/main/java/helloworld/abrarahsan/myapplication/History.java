package helloworld.abrarahsan.myapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class History extends AppCompatActivity {
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
        barEntries.add(new BarEntry(1, 1000, 400));
        barEntries.add(new BarEntry(2, 2000, 1000));
        barEntries.add(new BarEntry(3, 500, 1000));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Set1");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data=new BarData(barDataSet);
        data.setBarWidth(0.8f);

        barchart.setData(data);


        /*String content = "<html>"
                + "            <head>"
                + "               <script type = \"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>"
                + "                <script type = \"text/javascript\">"
                + "                    google.charts.load(\"current\",\"1\",{packages:[\"bar\"]});"
                + "                    google.setOnLoadCallback(drawChart);"
                + "                    function drawChart(){"
                + "                      var data = google.visualization.arrayToDataTable(["
                + "                      ['Month', 'Income', 'Expenses'],"
                + "                      ['Jan', 1000, 400],"
                + "                      ['Feb' , 2000 , 1000]"
                + "                       ['Mar' , 500, 1000]"
                + "                      ]);"
                + "                      var options = {"
                + "                      chart:{"
                + "                      title: 'Income & Expenses',"
                + "                      hAxis: {title: 'Month', titleTextStyle:{color:'blue'}}"
                + "                      "
                + "                       };"
                + "                      var chart = new google.charts.Bar(document.getElementById('chart_div'));"
                + "                      chart.draw(data,google.charts.convertOptions(options));"
                + "                      }"
                + "                </script>"
                + "            </head>"
                + "            <body>"
                + "              <div id=\"chart_div\" style=\"width:1000px; height:500px;\"></div>"
                + "            </body>" + "</html>";
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.requestFocusFromTouch();
        webView.loadDataWithBaseURL("file:///android_asset/", content, "text/html", "utf-8", null);
    }*/

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflates menu, this item adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

 /*   @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_to_image:
                Intent intent = new Intent(History.this, GoogleImageGraphActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }*/
    }
    
}



