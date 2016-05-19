package org.mangosoft.leagoovn.smarthub.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.mangosoft.leagoovn.smarthub.R;
import org.mangosoft.leagoovn.smarthub.model.PromotionMdl;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kienhv on 4/8/2016.
 */
public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.PromotionAdapterHolder> {
    private List<PromotionMdl> listPromotion;

    public PromotionAdapter(List<PromotionMdl> listPromotion) {
        this.listPromotion = listPromotion;
    }

    @Override
    public PromotionAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_promotion, parent, false);
        return new PromotionAdapterHolder(rowView);
    }

    @Override
    public void onBindViewHolder(PromotionAdapterHolder holder, int position) {
        PromotionMdl promotion = listPromotion.get(position);
        holder.title.setText(promotion.getTitle());
        holder.image.setImageResource(promotion.getThumb());
        holder.content.setText(promotion.getDescription());
        holder.locale.setText(Html.fromHtml("<font color=\"#00FFFF\">" + promotion.getAddress() + "</font> | " + promotion.getTime()));
    }

    @Override
    public int getItemCount() {
        return listPromotion.size();
    }

    public class PromotionAdapterHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.image)
        ImageView image;
        @Bind(R.id.content)
        TextView content;
        @Bind(R.id.locale)
        TextView locale;

        public PromotionAdapterHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
