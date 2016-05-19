package org.mangosoft.leagoovn.smarthub.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.mangosoft.leagoovn.smarthub.R;
import org.mangosoft.leagoovn.smarthub.model.ProductMdl;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kienhv on 4/8/2016.
 * fill list item product in RecyleView
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductAdapterHolder> {
    private List<ProductMdl> listProduct;

    public ProductAdapter(List<ProductMdl> listProduct) {
        this.listProduct = listProduct;
    }

    @Override
    public ProductAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product, parent, false);
        return new ProductAdapterHolder(rowView);
    }

    @Override
    public void onBindViewHolder(final ProductAdapterHolder holder, int position) {
        ProductMdl product = listProduct.get(position);
        holder.nameProduct.setText(product.getName());
        holder.description.setText(product.getDescription());
        holder.imageProduct.setImageResource(product.getResourceImage());
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ProductAdapterHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.image)
        ImageView imageProduct;
        @Bind(R.id.name_product)
        TextView nameProduct;
        @Bind(R.id.description_product)
        TextView description;

        public ProductAdapterHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
