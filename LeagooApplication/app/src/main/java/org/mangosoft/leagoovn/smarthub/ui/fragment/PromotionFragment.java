package org.mangosoft.leagoovn.smarthub.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.mangosoft.leagoovn.smarthub.R;
import org.mangosoft.leagoovn.smarthub.model.PromotionMdl;
import org.mangosoft.leagoovn.smarthub.ui.adapter.PromotionAdapter;
import org.mangosoft.leagoovn.smarthub.ui.interfaces.ClickListener;
import org.mangosoft.leagoovn.smarthub.ui.interfaces.OnFragmentInteractionListener;
import org.mangosoft.leagoovn.smarthub.ui.widget.DividerItemDecoration;
import org.mangosoft.leagoovn.smarthub.ui.widget.RecyclerTounchListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PromotionFragment extends Fragment {
    @Bind(R.id.rv_promotion)
    RecyclerView rvPromotion;

    private OnFragmentInteractionListener listener;
    private List<PromotionMdl> listPromotion = new ArrayList<>();
    private PromotionAdapter adapter;

    public static PromotionFragment newInstance() {
        PromotionFragment fragment = new PromotionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listPromotion.add(new PromotionMdl("Khai trương showroom smartphone Leagoo Việt Nam", R.drawable.sukien1, "2016-03-23", "Sự kiện khai trương Showroom diễn ra sáng ngày 20/3/2016 tại số 2 Hoàng Cầu (mới) đã đón nhận được rất nhiều sự quan tâm của khách hàng.Rất nhiều vị khách đã may mắn nhận được những phầ...", "http://www.leagoo.vn/news/event/20160323/193.html", "LEAGOO_VN"));
        listPromotion.add(new PromotionMdl("Buổi trưng bày sản phẩm của LEAGOO tại CeBIT (Phần 1)", R.drawable.sukien2, "2016-03-19", "Vào đầu năm, với đam mê và mơ ước, LEAGOO đã đến Hannover tham dự ICT....", "http://www.leagoo.vn/news/event/20150614/LEAGOO_Showtime_in_CeBIT_1.html", "LEAGOO  "));
        listPromotion.add(new PromotionMdl("“Sáng tạo làm chủ tương lai”– LEAGOO được mời tham dự diễn đàn “Chief Exe", R.drawable.sukien3, "2016-03-19", "Hiện nay, đối mặt với nhu cầu thị trường và công nghệ đang ngày càng thay đổi nhanh chóng, làm thế nào đểnắm bắt được cơ hội phát triển và nâng cao khả năng cạnh tranh?...", "http://www.leagoo.vn/news/event/20150614/17.html", "LEAGOO"));
        listPromotion.add(new PromotionMdl("Buổi trình diễn của LEAGOO tại CeBIT (Phần 2)", R.drawable.sukien4, "2016-03-23", "CeBIT sẽ được tổ chức theo đúng lịch tại trung tâm triển lãm Hannover (Đức) vào ngày 16 đến 20 tháng 3 năm 2015, cùng với hàng nghìn thương hiệu và ông trùm nổi tiếng trong lĩnh vực khoa học và công...", "http://www.leagoo.vn/news/event/20150617/73.html", "LEAGOO"));
        listPromotion.add(new PromotionMdl("Các cửa hàng LEAGOO chính thức mở tại Iran", R.drawable.sukien5, "2016-03-23", "LEAGOO 4152015-09-09 Tin tốt đây, mọi người! Phản hồi cho tất cả những người yêu LEAGOO! Có 3 cửa hàng LEAGOO chính thức được mở cửa ở Iran. Bạn có thể nhận tin tức cập nhật nhất và mua những c...", "http://www.leagoo.vn/news/event/20160323/193.html", "LEAGOO_VN"));
        adapter = new PromotionAdapter(listPromotion);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_promotion, container, false);
        ButterKnife.bind(this, v);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        rvPromotion.setLayoutManager(mLayoutManager);
        rvPromotion.setItemAnimator(new DefaultItemAnimator());
        rvPromotion.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        rvPromotion.setAdapter(adapter);
        rvPromotion.addOnItemTouchListener(new RecyclerTounchListener(getActivity(), rvPromotion, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                PromotionMdl promotion = listPromotion.get(position);
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(promotion.getUrl()));
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));
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

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}
