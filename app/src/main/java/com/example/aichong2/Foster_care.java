package com.example.aichong2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Foster_care<SelectPopupWindow, shop_info> extends AppCompatActivity implements View.OnClickListener {
    private SelectPopupWindow mPopupWindow = null;
    private TextView f_c_area;
    private String[] parentStrings = {"全城","西湖区","下沙区","滨江区","萧山区","余杭区","上城区","下城区","富阳区","拱墅区"};
    private String[][] childrenStrings={{"不限","1000m范围","2000m范围","3000m范围","5000m范围"},
            {"西湖1","西湖2","西湖3","西湖4","西湖5","西湖6","西湖7","西湖8","西湖9","西湖10","西湖11","西湖12","西湖13","西湖14","西湖15"},
            {"下沙1","下沙2","下沙3","下沙4","下沙5","下沙6","下沙7","下沙8","下沙9","下沙10","下沙11","下沙12","下沙13","下沙14","下沙15"},
            {"滨江1","滨江2","滨江3","滨江4","滨江5","滨江6","滨江7","滨江8","滨江9","滨江10","滨江11","滨江12","滨江13","滨江14","滨江15"},
            {"萧山1","萧山2","萧山3","萧山4","萧山5","萧山6","萧山7","萧山8","萧山9","萧山10","萧山11","萧山12","萧山13","萧山14","萧山15"},
            {"余杭1","余杭2","余杭3","余杭4","余杭5"},
            {"上城1","上城2","上城3","上城4","上城5","上城6","上城7","上城8","上城9","上城10","上城11","上城12","上城13","上城14","上城15"},
            {"郑东新区1","郑东新区2","郑东新区3","下城4","下城5","下城6","下城7","下城8","下城9","下城10","下城11","下城12","下城13","下城14","下城15"},
            {"高新区1","高新区2","高新区3","富阳4","富阳5","富阳6","富阳7","富阳8","富阳9","富阳10","富阳11","富阳12","富阳13","富阳14","富阳15"},
            {"经开区1","经开区2","经开区3","拱墅4","拱墅5","拱墅6","拱墅7","拱墅8","拱墅9","拱墅10","拱墅11","拱墅12","拱墅13","拱墅14","拱墅15"},
    };
    private Spinner spinner;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    private ArrayList<shop_info> shoplist;
    private ListView lv;

//    SQLiteDatabase db;

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
//        db.close();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foster_care);

        f_c_area = (TextView) findViewById(R.id.f_c_area);
        f_c_area.setOnClickListener(this);

        Button btn1=(Button)findViewById(R.id.add_shop);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Foster_care.this, AddshopActivity.class);
//                startActivity(intent);
            }
        });

        RadioButton btn2=(RadioButton)findViewById(R.id.rb_better);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Foster_care.this,UserActivity.class);
                startActivity(intent);

            }
        });

        RadioButton btn3=(RadioButton)findViewById(R.id.rb_channel);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent =new Intent(Foster_care.this,baikeActivity.class);
//                startActivity(intent);
            }
        });

        spinner = (Spinner) findViewById(R.id.f_c_map);

        //数据
        data_list = new ArrayList<String>();
        data_list.add("位置");
        data_list.add("杭州");
        data_list.add("北京");
        data_list.add("上海");
        data_list.add("广州");
        data_list.add("深圳");
        data_list.add("长沙");
        data_list.add("南京");
        data_list.add("合肥");

        //适配器
        arr_adapter= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arr_adapter);

//        initList();

//        ItemAdapter itemAdapter = new ItemAdapter(Foster_care.this, R.layout.shopshowlayout, shoplist);
        ListView listView = (ListView) findViewById(R.id.shop_lv);
//        listView.setAdapter(itemAdapter);
        // 短暂点击
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                shop_info st = shoplist.get(position);
//                Intent intent = new Intent(Foster_care.this, Shop_info.class);
                Bundle mybundle = new Bundle();
//                mybundle.putString("ID", st.getName());
//                intent.putExtras(mybundle);
//                startActivity(intent);

            }
        });
    }

//    private void initList() {
//
//        //创建或打开数据库
//        MainActivity main = new MainActivity();
//        db = SQLiteDatabase.openOrCreateDatabase(this.getFilesDir().toString()
//                + "/test.dbs", null);
////        main.db = db;
//        shoplist = new ArrayList<>();
//        //扫描数据库,将数据库信息放入shoplist
//        Cursor cursor = db.rawQuery("select * from info_shop",null);
//        while (cursor.moveToNext()){
//            String name = cursor.getString(cursor.getColumnIndex("name"));
//            String shopname = cursor.getString(cursor.getColumnIndex("shopname"));
//            String address = cursor.getString(cursor.getColumnIndex("address"));
//            String shopkind = cursor.getString(cursor.getColumnIndex("shopkind"));
////            shop_info st = new shop_info(name,shopname,address,shopkind);    //student_info存一个条目的数据
////            shoplist.add(st);//把数据库的每一行加入数组中
//        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.f_c_area:
                if(mPopupWindow == null){
//                    mPopupWindow = new SelectPopupWindow(parentStrings,childrenStrings,this,selectCategory);
                }
//                mPopupWindow.showAsDropDown(f_c_area, -5, 10);
                break;
        }
    }

    /**
     * 选择完成回调接口
     */
//    private SelectPopupWindow.SelectCategory selectCategory=new SelectPopupWindow.SelectCategory() {
//        @SuppressLint({"ShowToast", "WrongConstant"})
////        @Override
//        public void selectCategory(int parentSelectposition,int childrenSelectposition) {
//            String parentStr=parentStrings[parentSelectposition];
//            String childrenStr=childrenStrings[parentSelectposition][childrenSelectposition];
//
//            Toast.makeText(Foster_care.this, "父类别:"+parentStr+"  子类别:"+childrenStr, 0).show();
//        }
//    };

}


