package ru.rvi.gasstation.helpActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static ru.rvi.gasstation.R.layout.activity_accident;

import androidx.appcompat.app.AppCompatActivity;

import ru.rvi.gasstation.R;

/**
 * Класс, отвечающий за пункт "Действия при ДТП"
 *
 * @author Исаков.Р, 17ит17
 */

public class AccidentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedBundle) {
        super.onCreate(savedBundle);
        setContentView(activity_accident);
        setShownContent();
    }

    private void setShownContent() {
        WebView webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("file:///android_asset/accident.html");
    }
}