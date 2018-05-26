package helloworld.abrarahsan.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        continueButton = findViewById(R.id.continueButton);
    }

    public void continuePressed(View view) {
        Intent intent = new Intent(getApplicationContext(), AuthenticationActivity.class);
        startActivity(intent);
    }

    public void googlePressed(View view) {
        Intent intent = new Intent(getApplicationContext(), GoogleImageGraphActivity.class);
        startActivity(intent);
    }

    public void historyPressed(View view) {
        Intent intent = new Intent(getApplicationContext(), History.class);
        startActivity(intent);
    }

    public void overviewPressed(View view) {
        Intent intent = new Intent(getApplicationContext(), Overview.class);
        startActivity(intent);
    }

    public void planningPressed(View view) {
        Intent intent = new Intent(getApplicationContext(), Planning.class);
        startActivity(intent);
    }
}
