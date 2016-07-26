package org.mangosoft.leagoovn.smarthub.ui.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import org.mangosoft.leagoovn.smarthub.R;
import org.mangosoft.leagoovn.smarthub.ui.interfaces.OnFragmentInteractionListener;
import org.mangosoft.leagoovn.smarthub.utils.Log;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SupportFragment extends Fragment {
    @Bind(R.id.webView1)
    WebView webView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    private OnFragmentInteractionListener listener;

    public static SupportFragment newInstance() {
        SupportFragment fragment = new SupportFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_support, container, false);
        ButterKnife.bind(this, v);
        webViewLoadURL(webView, "http://www.leagoo.vn/support/pre_sale_services/");
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @SuppressWarnings("deprecation")
    private void webViewLoadURL(WebView view, String url) {
        view.getSettings().setBlockNetworkImage(false);
        view.getSettings().setBlockNetworkLoads(false);
        view.setWebViewClient(new MyWebViewClient());
        WebSettings webSettings = view.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        view.getSettings().setDomStorageEnabled(true);
        view.getSettings().setPluginState(WebSettings.PluginState.ON);
        view.loadUrl(url);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (!url.equals("about:blank") && url.substring(0, 4).equalsIgnoreCase("http")) {
                webViewLoadURL(webView, url);
            }
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
            super.onReceivedHttpError(view, request, errorResponse);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            Log.e("", "errorCode: " + errorCode + ", description: " + description + ", description: " + description);
            progressBar.setVisibility(View.GONE);
        }
    }
}
