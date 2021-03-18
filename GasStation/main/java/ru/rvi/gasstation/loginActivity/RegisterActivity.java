package ru.rvi.gasstation.loginActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ru.rvi.gasstation.R;

/**
 * Класс, отображающий экран, в котором распологается поля для регестрации
 *
 * @author Исаков.Р, 17ит17
 */

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper sqLiteDatabase;
    EditText Login, Password, PasswordC;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        sqLiteDatabase = new DatabaseHelper(this);

        Login = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        PasswordC = findViewById(R.id.cPassword);
        b1 = findViewById(R.id.register);
        b2 = findViewById(R.id.fLogin);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this, ProfileActivity.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = Login.getText().toString();
                String s2 = Password.getText().toString();
                String s3 = PasswordC.getText().toString();
                if (s1.equals("") || s2.equals("") || s3.equals("")) {
                    Toast.makeText(getApplicationContext(), "Поля пусты", Toast.LENGTH_SHORT).show();
                } else {
                    if (s2.equals((s3))) {
                        Boolean chkemail = sqLiteDatabase.chkemail(s1);
                        if (chkemail == true) {
                            Boolean insert = sqLiteDatabase.insert(s1, s2);
                            if (insert == true) {
                                Toast.makeText(getApplicationContext(), "Успешно зарегестрирован", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Электронная почта уже существует", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Пароль не совпадает", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
