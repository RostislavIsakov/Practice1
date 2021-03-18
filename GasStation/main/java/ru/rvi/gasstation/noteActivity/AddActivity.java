package ru.rvi.gasstation.noteActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ru.rvi.gasstation.R;

/**
 * Класс, отвечающий за добавление заметки
 *
 * @author Исаков.Р, 17ит17
 */

public class AddActivity extends AppCompatActivity {

    EditText traversed, fuel, paid;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_activity_add);

        traversed = findViewById(R.id.drive_input);
        fuel = findViewById(R.id.fueled_input);
        paid = findViewById(R.id.paid_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(traversed.getText().toString().trim(),
                        fuel.getText().toString().trim(),
                        paid.getText().toString().trim());
            }
        });
    }
}
