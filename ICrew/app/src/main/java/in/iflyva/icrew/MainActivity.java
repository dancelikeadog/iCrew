package in.iflyva.icrew;
import android.app.Activity;
import android.media.Image;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import in.iflyva.icrew.R;

public class MainActivity extends Activity  {
    Button b1;
    EditText ed1;

    private WebView wv1;
    protected LinearLayout contentLayout;
    protected  LinearLayout overlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        wv1=(WebView)findViewById(R.id.webview);
        contentLayout = (LinearLayout)findViewById(R.id.content);
        overlay = (LinearLayout)findViewById(R.id.overlay);

        wv1.setWebViewClient(new MyBrowser());

                String url = "http://app.iflyva.in/";

                wv1.getSettings().setLoadsImagesAutomatically(true);
                wv1.getSettings().setJavaScriptEnabled(true);
                wv1.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                wv1.loadUrl(url);

        wv1.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                MainActivity.this.setTitle("Loading...");
                MainActivity.this.setProgress(progress * 100);
                overlay.setVisibility(View.VISIBLE);
              //  contentLayout.setVisibility(View.GONE);

                if(progress == 100) {
                    MainActivity.this.setTitle(R.string.app_name);
                    overlay.setVisibility(View.GONE);
                   // contentLayout.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override

    public boolean onKeyDown(final int keyCode, final KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv1.canGoBack()) {
            wv1.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}