package helloworld.abrarahsan.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

public class PlanningActivity extends AppCompatActivity {

    EditText foodEditText;
    EditText transEditText;
    EditText utilEditText;
    EditText miscEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);

        setTitle("Planning");

        foodEditText = findViewById(R.id.foodEditText);
        transEditText = findViewById(R.id.transEditText);
        utilEditText = findViewById(R.id.utilEditText);
        miscEditText = findViewById(R.id.miscEditText);
    }

    public double round(double value) {
        return Math.round(value * 100) / 100;
    }

    public void save(View view) {
        if (ParseUser.getCurrentUser() != null) {
            ParseUser user = ParseUser.getCurrentUser();
            boolean successful = true;

            if (!foodEditText.getText().toString().isEmpty()) {
                user.put("food", round(Double.valueOf(foodEditText.getText().toString())));
            } else {
                successful = false;
            }

            if (!transEditText.getText().toString().isEmpty()) {
                user.put("trans", round(Double.valueOf(transEditText.getText().toString())));
            } else {
                successful = false;
            }

            if (!utilEditText.getText().toString().isEmpty()) {
                user.put("util", round(Double.valueOf(utilEditText.getText().toString())));
            } else {
                successful = false;
            }

            if (!miscEditText.getText().toString().isEmpty()) {
                user.put("misc", round(Double.valueOf(miscEditText.getText().toString())));
            } else {
                successful = false;
            }

            if (successful) {
                user.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(PlanningActivity.this,
                                    "Data saved successfully!",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(PlanningActivity.this,
                                    "There was a problem saving your data.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Toast.makeText(this, "Please enter values in all four categories!",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Nobody is signed in!", Toast.LENGTH_SHORT).show();
        }
    }
}
