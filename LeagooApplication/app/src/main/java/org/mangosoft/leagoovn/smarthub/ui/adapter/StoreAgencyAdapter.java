package org.mangosoft.leagoovn.smarthub.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.mangosoft.leagoovn.smarthub.R;
import org.mangosoft.leagoovn.smarthub.model.StoreAgencyMdl;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kienhv on 4/7/2016.
 * fill all item StoreAgency in RecycleView
 * param: listStoreAgencyMdl: List Item want fill
 */
public class StoreAgencyAdapter extends RecyclerView.Adapter<StoreAgencyAdapter.StoreAgencyHolder> {

    private List<StoreAgencyMdl> listStoreAgencyMdl;

    public StoreAgencyAdapter(List<StoreAgencyMdl> listStoreAgencyMdl) {
        this.listStoreAgencyMdl = listStoreAgencyMdl;
    }

    @Override
    public StoreAgencyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_store_agency, parent, false);
        return new StoreAgencyHolder(rowView);
    }

    @Override
    public void onBindViewHolder(StoreAgencyHolder holder, int position) {
        StoreAgencyMdl itemStoreAgency = listStoreAgencyMdl.get(position);
        holder.nameStore.setText(itemStoreAgency.getName());
        holder.managerStore.setText(Html.fromHtml("<b>Người quản lý: </b>" + itemStoreAgency.getManagerPerson()));
        holder.phone.setText(Html.fromHtml("<b>Số điện thoại: </b>" + itemStoreAgency.getPhone()));
        holder.address.setText(Html.fromHtml("<b>Địa chỉ: </b>" + itemStoreAgency.getAddress()));
    }

    @Override
    public int getItemCount() {
        return listStoreAgencyMdl.size();
    }

    public class StoreAgencyHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.name_store)
        TextView nameStore;
        @Bind(R.id.manager_store)
        TextView managerStore;
        @Bind(R.id.phone)
        TextView phone;
        @Bind(R.id.address)
        TextView address;

        public StoreAgencyHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
