package ru.rvi.gasstation.activityMain;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import ru.rvi.gasstation.R;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import ru.rvi.gasstation.helpActivity.HelpActivity;
import ru.rvi.gasstation.loginActivity.ProfileActivity;
import ru.rvi.gasstation.calculatorActivity.CalculatorActivity;

/**
 * Главный класс, отображающий экран, в котором распологается список азс с их адресами
 *
 * @author Исаков.Р, 17ит17
 */


public class MainActivity extends AppCompatActivity {
    ListView listView;
    String[] mTitle = {"Лукойл", "Лукойл", "Лукойл", "Лукойл", "Лукойл", "Газпром",
            "Газпром", "Газпром", "Газпром", "Апельсин", "Апельсин", "Апельсин",
            "Татнефть", "Татнефть", "Shell", "Shell"};
    String[] mDescription = {"просп. Победы, 120А", "Окружная ул., 113А/74А", "ул. Рябова, 1К", "Окружная ул., 298",
            "ул. Карпинского, 188", "Дизельная ул. 3", "Бекешская ул., 31А", "ул. Калинина, 150А", "Революционная ул., 12",
            "просп. Победы, 115Б", "ул. Строителей, 5", "Стрельбищенская ул., 23", "ул. Аустрина, 164К", "ул. 65-летия Победы, 8",
            "ул. Стасова, 12А", "просп. Строителей, 11Д"};
    int[] images = {R.drawable.lukoil, R.drawable.lukoil, R.drawable.lukoil, R.drawable.lukoil, R.drawable.lukoil, R.drawable.gasprom,
            R.drawable.gasprom, R.drawable.gasprom, R.drawable.gasprom, R.drawable.orange, R.drawable.orange, R.drawable.orange,
            R.drawable.tatneft, R.drawable.tatneft, R.drawable.shell, R.drawable.shell};

    String[] mCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        MyAdapter adapter = new MyAdapter(this, mTitle, mDescription, images);
        listView.setAdapter(adapter);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setSelectedItemId(R.id.nav_gas);

        mCategories = getResources().getStringArray(R.array.categories);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_gas:
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
                        startActivity(new Intent(getApplicationContext(), HelpActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String[] rTitle;
        String[] rDescription;
        int[] rImgs;

        MyAdapter(Context c, String title[], String description[], int imgs[]) {
            super(c, R.layout.row, R.id.textView1, title);
            this.context = c;
            this.rTitle = title;
            this.rDescription = description;
            this.rImgs = imgs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.image);
            TextView myTitle = row.findViewById(R.id.textView1);
            TextView myDescription = row.findViewById(R.id.textView2);

            // обращаемся к ресурсам
            images.setImageResource(rImgs[position]);
            myTitle.setText(rTitle[position]);
            myDescription.setText(rDescription[position]);
            return row;
        }
    }
}


