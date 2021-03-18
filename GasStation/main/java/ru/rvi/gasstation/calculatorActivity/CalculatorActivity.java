package ru.rvi.gasstation.calculatorActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ru.rvi.gasstation.activityMain.MainActivity;
import ru.rvi.gasstation.R;
import ru.rvi.gasstation.helpActivity.HelpActivity;
import ru.rvi.gasstation.loginActivity.ProfileActivity;

/**
 * Класс, отыечающий за расчет стоимости поездки
 *
 * @author Исаков.Р, 17ит17
 */

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator_activity);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setSelectedItemId(R.id.nav_settings);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_gas:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_person:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_settings:
                        return true;
                    case R.id.nav_help:
                        startActivity(new Intent(getApplicationContext(), HelpActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    public void onButtonClick(View v) {
        EditText e1 = (EditText) findViewById(R.id.distance);
        EditText e2 = (EditText) findViewById(R.id.price);
        EditText e3 = (EditText) findViewById(R.id.cost);
        TextView resText = (TextView) findViewById(R.id.result);

        int distance = Integer.parseInt(e1.getText().toString());
        int price = Integer.parseInt(e2.getText().toString());
        int cost = Integer.parseInt(e3.getText().toString());
        int result = price * distance * cost / 100;

        resText.setText(Integer.toString(result));


    }
}


