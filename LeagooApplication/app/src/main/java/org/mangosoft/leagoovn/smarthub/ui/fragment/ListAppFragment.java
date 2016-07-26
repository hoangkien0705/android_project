package org.mangosoft.leagoovn.smarthub.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.mangosoft.leagoovn.smarthub.R;
import org.mangosoft.leagoovn.smarthub.ui.interfaces.OnFragmentInteractionListener;
import org.mangosoft.leagoovn.smarthub.utils.Log;

public class ListAppFragment extends Fragment {
    private OnFragmentInteractionListener listener;

    public static ListAppFragment newInstance() {
        ListAppFragment fragment = new ListAppFragment();
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
        return inflater.inflate(R.layout.fragment_list_app, container, false);
    }

    @Override
    public void onAttach(Context ctx) {
        super.onAttach(ctx);
        try {
            listener = (OnFragmentInteractionListener) getActivity();
        } catch (ClassCastException ce) {
            Log.e(ce, "Container activity must implement 'OnFragmentInteractionListener' interface");
        }
    }
}
