package org.mangosoft.leagoovn.smarthub.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.mangosoft.leagoovn.smarthub.R;
import org.mangosoft.leagoovn.smarthub.model.StoreAgencyMdl;
import org.mangosoft.leagoovn.smarthub.ui.adapter.StoreAgencyAdapter;
import org.mangosoft.leagoovn.smarthub.ui.interfaces.ClickListener;
import org.mangosoft.leagoovn.smarthub.ui.interfaces.OnFragmentInteractionListener;
import org.mangosoft.leagoovn.smarthub.ui.widget.DividerItemDecoration;
import org.mangosoft.leagoovn.smarthub.ui.widget.RecyclerTounchListener;
import org.mangosoft.leagoovn.smarthub.utils.JsonHelper;
import org.mangosoft.leagoovn.smarthub.utils.Log;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class StoreFragment extends Fragment {
    private static final String list_store_agency = "[{\"name\":\"Headquater\",\"address\":\"2 HOÀNG CẦU (MỚI) – ĐỐNG ĐA – HÀ NỘI.\",\"phone\":\"043 85 99999\",\"managerPerson\":\"Trần Trung\"}, {\"name\":\"Công Ty Hoàng Hà\",\"address\":\"122 Thái Hà, Đống Đa, Hà Nội\",\"phone\":\"094.686.2222\",\"managerPerson\":\"Hoàng Hữu Huỳnh\"},{\"name\":\"Công Ty Techone\",\"address\":\"113 Thái Hà, Đống Đa, Hà Nội\",\"phone\":\"0934.96.9999\",\"managerPerson\":\"Anh Tâm\"}, {\"name\":\"Công Ty Techone\",\"address\":\"\",\"phone\":\"0975.49.6718\",\"managerPerson\":\"Chị Trang\"},{\"name\":\"Công Ty Nhật Cường\",\"address\":\"134 Thái Hà, Đống Đa, Hà Nội\",\"phone\":\"0966.533.633\",\"managerPerson\":\"Anh Minh\"},{\"name\":\"Công Ty Duy Đức\",\"address\":\"291 Bùi Xương Trạch, Khương Đình, TX, Hà Nội\",\"phone\":\"09.4445.6666\",\"managerPerson\":\"Anh Hiền\"},\n" +
            "{\"name\":\"Siêu Thị Mediamart\",\"address\":\"72 Trường Chinh, Đống Đa, Hà Nội\",\"phone\":\"0982.95.7799\",\"managerPerson\":\"Anh Đạt\"},\n" +
            "{\"name\":\"Siêu Thị Mediamart\",\"address\":\"Hà Nội\",\"phone\":\"\",\"managerPerson\":\"Anh Duy\"},\n" +
            "{\"name\":\"Siêu Thị PICO\",\"address\":\"76 Nguyễn Trãi, TX, Hà Nội\",\"phone\":\"0936.35.3456\",\"managerPerson\":\"Anh Huy\"},\n" +
            "{\"name\":\"Siêu Thị PiCO\",\"address\":\"Hà Nội\",\"phone\":\"0904.88.11.77\",\"managerPerson\":\"Anh Nguyên\"},\n" +
            "{\"name\":\"Siêu Thị HC\",\"address\":\"Hà 102 Thái Thịnh, Đống Đa, Hà Nội\",\"phone\":\"\",\"managerPerson\":\"Anh Dũng\"},\n" +
            "{\"name\":\"Siêu Thị HC\",\"address\":\"Hà Nội\",\"phone\":\"0972.50.90.24\",\"managerPerson\":\"Anh Cương\"},\n" +
            "{\"name\":\"Siêu Thị Trần Anh\",\"address\":\"Số 2 Đại Cồ Việt, Hai Bà Trưng, Hà Nội\",\"phone\":\"0904.10.1212\",\"managerPerson\":\"Anh Xuân\"},\n" +
            "{\"name\":\"Siêu Thị Trần Anh\",\"address\":\"Hà Nội\",\"phone\":\"0918.21.3556\",\"managerPerson\":\"Chị Hương\"},\n" +
            "{\"name\":\"Viettel Store\",\"address\":\"145 Thái Hà, Đống Đa, Hà Nội\",\"phone\":\"\",\"managerPerson\":\"Anh Tuấn\"},\n" +
            "{\"name\":\"Viettel Store\",\"address\":\"Hà Nội\",\"phone\":\"0988.06.0506\",\"managerPerson\":\"Chị Nhàn\"},\n" +
            "{\"name\":\"FPT Shop\",\"address\":\"45 Thái Hà, Đống Đa, Hà Nội\",\"phone\":\"090.626.9939\",\"managerPerson\":\"Anh Thắng\"},\n" +
            "{\"name\":\"FPT Shop\",\"address\":\"Hà Nội\",\"phone\":\"0904.80.7688\",\"managerPerson\":\"Chị Lan Anh\"},\n" +
            "{\"name\":\"Công Ty Thành Long\",\"address\":\"Hà Nội\",\"phone\":\"0986.47.6686\",\"managerPerson\":\"Chú Lực\"},\n" +
            "{\"name\":\"Công Ty Đại Đoàn Gia\",\"address\":\"Hà Nội\",\"phone\":\"0902.09.0339\",\"managerPerson\":\"Anh Lự\"},\n" +
            "{\"name\":\"Công Ty Khánh Linh\",\"address\":\"Hà Nội\",\"phone\":\"0981.290.999\",\"managerPerson\":\"Anh Khuê\"},\n" +
            "{\"name\":\"Công ty TNHH Cao Hoàng Thành\",\"address\":\"269 Khương Hạ Mới, TX, HN\",\"phone\":\"\",\"managerPerson\":\"Chị Thúy\"},\n" +
            "{\"name\":\"Công Ty Bắc Hội\",\"address\":\"256 Chu Văn Thịnh, Thành Phố Sơn La\",\"phone\":\"0912.83.9999\",\"managerPerson\":\"Anh Bắc\"},\n" +
            "{\"name\":\"Công Ty D  T\",\"address\":\"Tòa nhà VNPT, Số 1 Tô Hiệu ( đề rõ Công Ty)\",\"phone\":\"0904.14.8988\",\"managerPerson\":\"Anh Chiến\"},\n" +
            "{\"name\":\"Công Ty D  T\",\"address\":\"Tòa nhà VNPT, Số 1 Tô Hiệu ( đề rõ Công Ty)\",\"phone\":\"0976.76.06.16\",\"managerPerson\":\"Anh Dũng\"},\n" +
            "{\"name\":\"Công Ty TC\",\"address\":\"192 Lê Duẩn, Thành Phố Sơn La\",\"phone\":\"0983.26.1988\",\"managerPerson\":\"Anh Chính\"},\n" +
            "{\"name\":\"Công Ty Thắng Trâm\",\"address\":\"Số nhà 760, Tổ 3, Phường Tân Thanh\",\"phone\":\"098.335.1128\",\"managerPerson\":\"Chị Trâm\"},\n" +
            "{\"name\":\"Công Ty Mai Hưng\",\"address\":\"Số 35 Trần Hưng Đạo, Thành Phố Lai Châu\",\"phone\":\"0983.67.9338\",\"managerPerson\":\"Anh Hưng\"},\n" +
            "{\"name\":\"Smatphone Sông Koong\",\"address\":\"Số 199 Tổ dân phố Xuân Miếu 2- Phường Cái Đan- Thị xã Sông Công\",\"phone\":\"0942.54.7888\",\"managerPerson\":\"Anh Sơn\"},\n" +
            "{\"name\":\"Thế  Giới Số\",\"address\":\"Thái Nguyên\",\"phone\":\"0986.48.58.48\",\"managerPerson\":\"Anh Nam\"},\n" +
            "{\"name\":\"Viễn Thông Thái Nguyên\",\"address\":\"Thái Nguyên\",\"phone\":\"0912.757.757\",\"managerPerson\":\"Anh Cường\"},\n" +
            "{\"name\":\"Công Ty Kiên Hồng\",\"address\":\"\",\"phone\":\"0982.541.641\",\"managerPerson\":\"Anh Việt\"},\n" +
            "{\"name\":\"Công Ty Bình Nguyên\",\"address\":\"Cao Bằng\",\"phone\":\"0945.686.888\",\"managerPerson\":\"Anh Cường\"},\n" +
            "{\"name\":\"Công Ty Sơn Nga\",\"address\":\"241-243 Lê Lợi- Hoàng Văn Thụ- Bắc Giang\",\"phone\":\"01237.84.85.86\",\"managerPerson\":\"Chị Nga\"},\n" +
            "{\"name\":\"Công Ty Thành Thái\",\"address\":\"Bắc Ninh\",\"phone\":\"0984.492.693\",\"managerPerson\":\"Anh Luật\"},\n" +
            "{\"name\":\"Công Ty Tân Cường\",\"address\":\"Hưng Yên\",\"phone\":\"986090021\",\"managerPerson\":\"Anh Thắng\"},\n" +
            "{\"name\":\"Công Ty Anh Đào\",\"address\":\"Hải Dương\",\"phone\":\"0973.95.1999\",\"managerPerson\":\"Chị Mai\"},\n" +
            "{\"name\":\"Công Ty VP\",\"address\":\"Hải Phòng\",\"phone\":\"0913.538.538\",\"managerPerson\":\"Anh Hùng\"},\n" +
            "{\"name\":\"Công Ty Tân Bình\",\"address\":\"Quảng Ninh\",\"phone\":\"0913.26.47.81\",\"managerPerson\":\"Anh Nam\"},\n" +
            "{\"name\":\"Công Ty Chính Kính\",\"address\":\"Quảng Ninh\",\"phone\":\"0913.26.86.88\",\"managerPerson\":\"Anh Chính\"},\n" +
            "{\"name\":\"Công Ty Xinh Tươi\",\"address\":\"Quảng Ninh\",\"phone\":\"0912.82.8686\",\"managerPerson\":\"Anh Tươi\"},\n" +
            "{\"name\":\"Công Ty Hà Anh\",\"address\":\"Số 9 Lê Lợi- Phường Vĩnh Trại- Thành Phố Lạng Sơn\",\"phone\":\"0913.27.8991\",\"managerPerson\":\"Anh Sơn\"},\n" +
            "{\"name\":\"Công ty TNHH công nghệ viễn thông Thanh Anh\",\"address\":\"TP Thanh Hóa\",\"phone\":\"0989091118\",\"managerPerson\":\"Mr.Anh\"},\n" +
            "{\"name\":\"Cửa hàng Điện Thoại Tài Thơm\",\"address\":\"TT Nga sơn\",\"phone\":\"0903489033\",\"managerPerson\":\"Mr.Tài\"},\n" +
            "{\"name\":\"Công ty TNHH Thiện Mỹ\",\"address\":\"TP Thanh Hóa\",\"phone\":\"0912028888\",\"managerPerson\":\"Mr.Quang\"},\n" +
            "{\"name\":\"Công Ty TNHH TM Ngọc Long\",\"address\":\"TP Thanh Hóa\",\"phone\":\"0986949999\",\"managerPerson\":\"Mr.Ngọc\"},\n" +
            "{\"name\":\"Công ty TNHH Điện tử viễn thông Biên Hòa\",\"address\":\"TP Thanh Hóa\",\"phone\":\"0978386666\",\"managerPerson\":\"Ngô Trọng Biên\"},\n" +
            "{\"name\":\"CTY Mạnh Đình\",\"address\":\"Thanh Hóa\",\"phone\":\"945468516\",\"managerPerson\":\"MR Mạnh\"},\n" +
            "{\"name\":\"ĐL Đông Cẩm\",\"address\":\"Thanh Hóa\",\"phone\":\"\",\"managerPerson\":\"Anh Đông\"},\n" +
            "{\"name\":\"Công ty TNHH Thương Mại Anh Vinh\",\"address\":\"Thanh Hóa\",\"phone\":\"0978716666\",\"managerPerson\":\"Mr Huy\"},\n" +
            "{\"name\":\"Công ty CPĐT Viễn Thông Đại Phú\",\"address\":\"Nghệ An\",\"phone\":\"0903224484\",\"managerPerson\":\"Trần Đại Tráng\"},\n" +
            "{\"name\":\"Công ty TNHH Viễn Thông Hoàng Nam\",\"address\":\"Nghệ An\",\"phone\":\"913274299\",\"managerPerson\":\"Vũ Quốc Ngà\"},\n" +
            "{\"name\":\"Hoàng Nam Diễm châu> Anh Bình\",\"address\":\"Nghệ An\",\"phone\":\"912590959\",\"managerPerson\":\"Anh Bình\"},\n" +
            "{\"name\":\"Công ty TNHH Viễn Thông Tình Anh\",\"address\":\"Nghệ An\",\"phone\":\"0983688777\",\"managerPerson\":\"Nguyễn Trọng Tình\"},\n" +
            "{\"name\":\"Hộ Kinh Doanh Thiên Phú Mobile\",\"address\":\"Nghệ An\",\"phone\":\"0988285886\",\"managerPerson\":\"Nguyễn Văn Công\"},\n" +
            "{\"name\":\"ĐL Minh Hải\",\"address\":\"Nghệ An\",\"phone\":\"986870666\",\"managerPerson\":\"Mr Hải\"},\n" +
            "{\"name\":\"Cty Hoàng Hải\",\"address\":\"Hà Tỉnh\",\"phone\":\"982858685\",\"managerPerson\":\"Anh Hải\"},\n" +
            "{\"name\":\"Hộ kinh doanh cá Thể Sơn Thành Minh\",\"address\":\"Hà Tỉnh\",\"phone\":\"948157888\",\"managerPerson\":\"Anh Sơn\"},\n" +
            "{\"name\":\"Quyết Nhàn Mobile\",\"address\":\"Hà Tỉnh\",\"phone\":\"913396789\",\"managerPerson\":\"Anh Quyết\"},\n" +
            "{\"name\":\"Cty Kiên Anh\",\"address\":\"Ninh Bình\",\"phone\":\"913518181\",\"managerPerson\":\"Mr Tưởng\"},\n" +
            "{\"name\":\"Phương Thảo Mobile\",\"address\":\"đường 12c Ninh Hòa,. Hoa lư TPNB\",\"phone\":\"977585999\",\"managerPerson\":\"Anh Phương\"},\n" +
            "{\"name\":\"CTY TNHH thương mại Mesia (VÂN SÙNG)\",\"address\":\"46 Lê hồng phong \",\"phone\":\"919058419\",\"managerPerson\":\"Chị Vân Add Mr Dũng\"},\n" +
            "{\"name\":\"CTY TNHH thương mại và dịch vụ Ngị Huệ\",\"address\":\"85 Thành chung cửa bắc TPNĐ\",\"phone\":\"904571775\",\"managerPerson\":\"Chị Huệ\"},\n" +
            "{\"name\":\"Cty Cổ phần XNK Đại Đoàn Kết\",\"address\":\"243 Đường Điện Biên phường cửa bắc TP NĐ\",\"phone\":\"902104555\",\"managerPerson\":\"Anh Chiêu\"},\n" +
            "{\"name\":\"Hộ Kinh Doanh Hoàng Nguyên\",\"address\":\"khu 9 TT định long, Hải Hậu\",\"phone\":\"911588688\",\"managerPerson\":\"mr Nguyên\"},\n" +
            "{\"name\":\"Nhật Minh\",\"address\":\"355 Lý Bôn TB\",\"phone\":\"988696999\",\"managerPerson\":\"Mr Nhật\"},\n" +
            "{\"name\":\"Hộ Kinh Doanh Cá Thể Dũng Hương\",\"address\":\"334 Hùng Thắng TT tiền hải TB\",\"phone\":\"977742747\",\"managerPerson\":\"Chị hương\"},\n" +
            "{\"name\":\"Đại lý Xuân Anh\",\"address\":\"TT bình Bình Mỹ, Bình lục\",\"phone\":\"936277676\",\"managerPerson\":\"Đức Anh\"},\n" +
            "{\"name\":\"Đại lý Phong Thủy\",\"address\":\"Số 76 đường nguyễn trãi 1. phường sao đỏ. Chí Linh \",\"phone\":\"09120383800983357688\",\"managerPerson\":\"Cao thị Thủy\"},\n" +
            "{\"name\":\"ĐL Dương mobile\",\"address\":\"\",\"phone\":\"977621222\",\"managerPerson\":\"Mr Dương\"},\n" +
            "{\"name\":\"Tuấn Hùng\",\"address\":\"2014 hùng vương-nông trang-VT\",\"phone\":\"982755555\",\"managerPerson\":\"A Hùng\"},\n" +
            "{\"name\":\"Tuấn Hạnh\",\"address\":\"Khu 9 TT Thanh Ba Huyện Thanh Ba Tỉnh Phú Thọ\",\"phone\":\"914997766\",\"managerPerson\":\"A. Tuấn\"},\n" +
            "{\"name\":\"Chinh Mai\",\"address\":\"174 phố vàng TT Thanh Sơn H.Thanh Sơn Phú Thọ\",\"phone\":\"962610095\",\"managerPerson\":\"C.Xuân\"},\n" +
            "{\"name\":\"Công ty TNHH Viễn Thông Bình An\",\"address\":\"KDC Cụm Kinh tế xã hội, Phường Đồng Tâm, TP Vĩnh Yên, Vĩnh Phúc\",\"phone\":\"0912 914 888\",\"managerPerson\":\"Anh Bình\"},\n" +
            "{\"name\":\"Hằng Hải TQ\",\"address\":\"Số 386, tổ 23, đường Quang Trung, P.Tân Quang, Thị xã Tuyên Quang, Tỉnh Tuyên Quang, Việt Nam\",\"phone\":\"989800068\",\"managerPerson\":\"A. Hải\"},\n" +
            "{\"name\":\"Thuận Hằng\",\"address\":\"TT Hàm Yên Huyện Hàm Yên Tuyên Quang\",\"phone\":\"964855777\",\"managerPerson\":\"A. Thuận\"},\n" +
            "{\"name\":\"Huy Cúc\",\"address\":\"Tổ 9, TT.Vị Xuyên, Huyện Vị Xuyên, Tỉnh Hà Giang, Việt Nam, Tel: 0967 888 555\",\"phone\":\"0967.888.555\",\"managerPerson\":\"A Huy\"},\n" +
            "{\"name\":\"Hùng Cường\",\"address\":\"Số 366 tổ 10 Phường Nguyễn Trãi , Thành phố Hà Giang, Tỉnh Hà Giang\",\"phone\":\"912037567\",\"managerPerson\":\"A Cường\"},\n" +
            "{\"name\":\"Hoàng Mạnh\",\"address\":\"P Nguyễn Trãi TP Hà Giang Tỉnh Hà Giang\",\"phone\":\"975573866\",\"managerPerson\":\"C Hưng\"},\n" +
            "{\"name\":\"Mạnh Tuấn YB\",\"address\":\"Thôn 9 xã Hợp Minh TP Yên Bái Tỉnh Yên Bái Thôn 9 xã Hợp Minh TP Yên Bái Tỉnh Yên Bái\",\"phone\":\"0979 710 393\",\"managerPerson\":\" A Tuấn\"},\n" +
            "{\"name\":\"Giang Tuấn Hùng\",\"address\":\"TP Yên Bái Tỉnh Yên Bái\",\"phone\":\"915647647\",\"managerPerson\":\"A Tráng\"},\n" +
            "{\"name\":\"Bắc Đô LC\",\"address\":\"ST Máy Tính Bắc Đô, Lô 5678 ngã 6 Khu Đô thị Mới , phường Kim Tân, TP Lào Cai tỉnh Lào Cai\",\"phone\":\"0977 88 15 88\",\"managerPerson\":\"C. Linh\"},\n" +
            "{\"name\":\"Công ty Bắc Hội\",\"address\":\"256 Chu Văn Thịnh, tổ 11 Phường Giềng Lề, TP Sơn La\",\"phone\":\"096 860 6789 \",\"managerPerson\":\"A. Phòng\"}]";

    @Bind(R.id.rv_store_agency)
    RecyclerView storeAgency;
    List<StoreAgencyMdl> arrCustomAgencyMdl = null;
    private OnFragmentInteractionListener listener;
    private StoreAgencyAdapter adapter;
    
    public static StoreFragment newInstance() {
        StoreFragment fragment = new StoreFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /*
        call when Fragment initialize
        - initialize List Object (StoreAgencyMdl) and add all item in list
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
        MainService.getEventBus().observe().subscribeOn(Schedulers.newThread()).subscribe(new Action1<Event>() {
            @Override
            public void call(Event event) {
                Log.json(event);
            }
        });
         */
        try {
            arrCustomAgencyMdl = JsonHelper.toObjectList(StoreAgencyMdl.class, list_store_agency);
        } catch (Exception e) {
            Log.d(e.getMessage());
        }
        adapter = new StoreAgencyAdapter(arrCustomAgencyMdl);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_store, container, false);
        ButterKnife.bind(this, v);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        storeAgency.setLayoutManager(mLayoutManager);
        storeAgency.setItemAnimator(new DefaultItemAnimator());
        storeAgency.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        storeAgency.setAdapter(adapter);

        storeAgency.addOnItemTouchListener(new RecyclerTounchListener(getActivity(), storeAgency, new ClickListener() {
            @Override
            public void onLongClick(View view, int position) {
            }

            @Override
            public void onClick(View view, int position) {
                //TODO event user click
//             StoreAgencyMdl storeAgencyMdl = arrCustomAgencyMdl.get(position);
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
