package inc.thebest.newsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebBrowser extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_browser);

        Intent intent = getIntent();

        webView = findViewById(R.id.my_web_view);

        //enabling JS
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        //code so that links open in this web view
        webView.setWebViewClient(new WebViewClient());

        webView.loadUrl(intent.getStringExtra("toLoadUrl"));
    }
}
