package ru.rvi.gasstation.helpActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import ru.rvi.gasstation.R;

/**
 * Класс, отвечающий за пункт "список эвакуаторов"
 *
 * @author Исаков.Р, 17ит17
 */

public class TowTrucksActivity extends AppCompatActivity {
    ListView listView;
    String[] mTitle = {"АвтоСпас", "Эвакуатор 58", "Пегасс", "Эвакуатор Пенза", "Chevrolet NIVA", "Batman",
            "Автокласс", "Автотехпомощь", "Авторейнджер-Пенза", "Пенза АвтоСпас", "Автоспутник", "Гараж 811",
            "Служба эвакуации автомобилей", "Penza-SpaS", "Шико", "Феникс-Авто"};
    String[] mPhone = {"73-66-66", "79-26-26", "77-74-55", "72-42-42",
            "25-22-42", "75-08-08", "29-12-34", "39-20-90", "00-66-66",
            "39-26-26", "79-33-99", "25-28-11", "39-08-00", "39-33-73",
            "39-33-57", "99-15-99"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tow_trucks);
        listView = findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter(this, mTitle, mPhone);
        listView.setAdapter(adapter);
    }

    class MyAdapter extends ArrayAdapter<String> {

        Context context;
        String[] rTitle;
        String[] rPhone;

        MyAdapter(Context c, String[] title, String[] description) {
            super(c, R.layout.row, R.id.textViewMainTitle, title);
            this.context = c;
            this.rTitle = title;
            this.rPhone = description;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            assert layoutInflater != null;
            @SuppressLint("ViewHolder") View row = layoutInflater.inflate(R.layout.row_trucks, parent, false);
            TextView myTitle = row.findViewById(R.id.textViewMainTitle);
            TextView myDescription = row.findViewById(R.id.textViewPhone);

            // обращаемся к ресурсам
            myTitle.setText(rTitle[position]);
            myDescription.setText(rPhone[position]);
            return row;
        }
    }
}


