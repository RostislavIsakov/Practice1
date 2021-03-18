package ru.rvi.gasstation.helpActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ru.rvi.gasstation.activityMain.MainActivity;
import ru.rvi.gasstation.R;
import ru.rvi.gasstation.calculatorActivity.CalculatorActivity;
import ru.rvi.gasstation.loginActivity.ProfileActivity;

/**
 * Класс, отвечающий за пункт "список СТО"
 *
 * @author Исаков.Р, 17ит17
 */

public class HelpActivity extends AppCompatActivity {
    ListView listView;
    String[] mTitle = {"Avtospec", "Автотехцентр СитиАвто", "Колеса даром", "Шиномонтаж58", "СТО", "R17.RU",
            "G-car", "АвтоПрайд", "AutoStyling"};
    String[] mPhone = {"53-50-77", "28-17-44", "300-62-57", "75-00-76",
            "30-62-62", "75-08-08", "29-12-34", "39-20-90", "00-66-66"};
    String[] mStreet = {"Стрельбищенская ул., 22Б", "ул. Суворова, 2", "Окружная ул., 30",
            "ул. Измайлова, 56",
            "Западная ул., 35",
            "Запорожская ул., 38А",
            "ул. Баумана, 2Б", "Запорожская ул., 38А",
            "ул. Урицкого, 123Б, бокс 3, бокс 10"};

    Button ButtonTowTrucksList, ButtonAccident;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        listView = findViewById(R.id.listView);
        HelpActivity.MyAdapter adapter = new HelpActivity.MyAdapter(this, mTitle, mPhone, mStreet);
        listView.setAdapter(adapter);

        ButtonTowTrucksList = findViewById(R.id.button_list);
        ButtonAccident = findViewById(R.id.button_accident);
        ButtonAccident.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HelpActivity.this, AccidentActivity.class);
                startActivity(i);
            }
        });

        ButtonTowTrucksList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HelpActivity.this, TowTrucksActivity.class);
                startActivity(i);
            }
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setSelectedItemId(R.id.nav_help);

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
                        startActivity(new Intent(getApplicationContext(), CalculatorActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.nav_help:
                        return true;
                }
                return false;
            }
        });
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String[] rTitle;
        String[] rPhone;
        String[] rStreet;

        MyAdapter(Context c, String[] title, String[] description, String[] descriptionv2) {
            super(c, R.layout.row, R.id.textViewMainTitle, title);
            this.context = c;
            this.rTitle = title;
            this.rPhone = description;
            this.rStreet = descriptionv2;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert layoutInflater != null;
            @SuppressLint("ViewHolder") View row = layoutInflater.inflate(R.layout.row_help, parent, false);
            TextView myTitle = row.findViewById(R.id.textViewMainTitle);
            TextView myPhone = row.findViewById(R.id.textViewPhone);
            TextView myStreet = row.findViewById(R.id.textViewStreet);

            // обращаемся к ресурсам
            myTitle.setText(rTitle[position]);
            myPhone.setText(rPhone[position]);
            myStreet.setText(rStreet[position]);
            return row;
        }
    }
}