package helloworld.abrarahsan.myapplication;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;



public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        WebView webView = (WebView) findViewById(R.id.webView1);
        String content = "<html>";
+"            <head>
+"               <script type = \"text/javascript\" src=\"jsapi.js\"></script>"
+"                <script type = \"text/javascript\">"
+"                    google.load(\"visualization\",\"1",{packages:[\"corechart\"]});"
+"                    google.setOnLoadCallback(drawChart);"
+"                    function drawChart(){"
+"                      var data = google.visualization.arrayToDataTable(["
+"                      ['Year', 'Month', 'Income', 'Expenses'],"
+"                      ['2010' , 'Jan;, 1000, 400],"
+"
+"                </script>"
+"            </head>"
    }
}
