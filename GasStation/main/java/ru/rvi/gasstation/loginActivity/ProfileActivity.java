package ru.rvi.gasstation.loginActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ru.rvi.gasstation.activityMain.MainActivity;
import ru.rvi.gasstation.R;
import ru.rvi.gasstation.helpActivity.HelpActivity;
import ru.rvi.gasstation.noteActivity.NoteMainActivity;
import ru.rvi.gasstation.calculatorActivity.CalculatorActivity;

/**
 * Класс, отображающий экран, в котором распологается поля для входа
 *
 * @author Исаков.Р, 17ит17
 */

public class ProfileActivity extends AppCompatActivity {
    EditText e1, e2;
    Button b1, b2;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        db = new DatabaseHelper(this);
        e1 = findViewById(R.id.email);
        e2 = findViewById(R.id.lPassword);
        b1 = findViewById(R.id.bLogin);
        b2 = findViewById(R.id.register);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = e1.getText().toString();
                String password = e2.getText().toString();
                Boolean Chkemailpass = db.emailpassword(email, password);
                if (Chkemailpass == true) {
                    Toast.makeText(getApplicationContext(), "Вы успешно вошли", Toast.LENGTH_SHORT).show();
                    Intent b = new Intent(ProfileActivity.this, NoteMainActivity.class);
                    startActivity(b);

                } else {
                    Toast.makeText(getApplicationContext(), "Неправельный логин или пароль ", Toast.LENGTH_SHORT).show();
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProfileActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setSelectedItemId(R.id.nav_person);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_gas:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_person:
                        return true;
                    case R.id.nav_settings:
                        startActivity(new Intent(getApplicationContext(), CalculatorActivity.class));
                        overridePendingTransition(0, 0);
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
}
