package org.mangosoft.leagoovn.smarthub.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;
import org.mangosoft.leagoovn.smarthub.R;
import org.mangosoft.leagoovn.smarthub.model.ProductMdl;
import org.mangosoft.leagoovn.smarthub.model.request.RequestProductMdl;
import org.mangosoft.leagoovn.smarthub.ui.adapter.ProductAdapter;
import org.mangosoft.leagoovn.smarthub.ui.interfaces.ClickListener;
import org.mangosoft.leagoovn.smarthub.ui.interfaces.OnFragmentInteractionListener;
import org.mangosoft.leagoovn.smarthub.ui.widget.DividerItemDecoration;
import org.mangosoft.leagoovn.smarthub.ui.widget.RecyclerTounchListener;
import org.mangosoft.leagoovn.smarthub.utils.Constants;
import org.mangosoft.leagoovn.smarthub.utils.JsonUtils;
import org.mangosoft.leagoovn.smarthub.utils.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ProductFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.rv_product)
    RecyclerView rvProduct;
    /*
    @Bind(R.id.textView)
    TextView txt;
     */
    private OnFragmentInteractionListener listener;
    private List<ProductMdl> listProduct = new ArrayList<>();
    private ProductAdapter adapter;
    @Bind(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    public static ProductFragment newInstance() {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ProductAdapter(listProduct);
        RequestProductMdl requestProductMdl = new RequestProductMdl();
        requestProductMdl.setN("nonce");
        requestProductMdl.setT("21342543255623");
        apiGetListProduct(requestProductMdl);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_product, container, false);
        ButterKnife.bind(this, v);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvProduct.setLayoutManager(mLayoutManager);
        rvProduct.setItemAnimator(new DefaultItemAnimator());
        rvProduct.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        rvProduct.setAdapter(adapter);
        rvProduct.addOnItemTouchListener(new RecyclerTounchListener(getActivity(), rvProduct, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                ProductMdl productMdl = listProduct.get(position);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(productMdl.getUrl()));
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
        swipeRefreshLayout.setOnRefreshListener(this);
        return v;
    }

    private void apiGetListProduct(RequestProductMdl requestProduct){
        try {
            String url = JsonUtils.getUrlRequest(Constants.URL_PRODUCT, requestProduct);
            Log.e(Constants.LOG_TAG, url);
        } catch (Exception e) {
            Log.e(Constants.LOG_TAG, e.getMessage());
        }

    }

    private class SuccessProductReponse implements Response.Listener<JSONObject> {
        @Override
        public void onResponse(JSONObject response) {
            Log.e(Constants.LOG_TAG, response);
        }
    }

    private class ErrorProductReponse implements Response.ErrorListener {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e(Constants.LOG_TAG, error);
        }
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

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    /*
     @OnClick({R.id.button, R.id.button2})
    public void buttonOnClick(final Button b) {
        Intent i = new Intent(getContext(), WarrantyConfirmationActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
     */
}
