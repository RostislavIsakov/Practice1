package ru.rvi.gasstation.noteActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import ru.rvi.gasstation.R;

/**
 * Класс, отчечающий за обнавление заметки
 *
 * @author Исаков.Р, 17ит17
 */

public class UpdateActivity extends AppCompatActivity {

    EditText drive_input, fueled_input, paid_input;
    Button update_button, delete_button;

    String id, drive, fueled, paid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_activity_update);

        drive_input = findViewById(R.id.title_input2);
        fueled_input = findViewById(R.id.author_input2);
        paid_input = findViewById(R.id.pages_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(drive);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                drive = drive_input.getText().toString().trim();
                fueled = fueled_input.getText().toString().trim();
                paid = paid_input.getText().toString().trim();
                myDB.updateData(id, drive, fueled, paid);
            }
        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("drive") &&
                getIntent().hasExtra("fueled") && getIntent().hasExtra("paid")) {

            id = getIntent().getStringExtra("id");
            drive = getIntent().getStringExtra("drive");
            fueled = getIntent().getStringExtra("fueled");
            paid = getIntent().getStringExtra("paid");

            //Setting Intent Data
            drive_input.setText(drive);
            fueled_input.setText(fueled);
            paid_input.setText(paid);
            Log.d("stev", drive + " " + fueled + " " + paid);
        } else {
            Toast.makeText(this, "Нет данных.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Удалить ?");
        builder.setMessage("Вы уверены что удалить ?");
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
