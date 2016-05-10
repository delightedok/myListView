package com.example.delightale.mylistview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private Context mContext;
    private ListView mListView;
    private View mHeadView;
    private View mFloatBarInLvHeader;
    private View mFloatBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        mListView=(ListView)findViewById(R.id.lv);
        //ListView顶部隐藏的浮动栏
        mFloatBar=findViewById(R.id.float_bar);
        //ListView第一个头部控件（效果图中的红色区域）
        mHeadView= LayoutInflater.from(mContext).inflate(R.layout.listitem_headview,mListView,false);
        mListView.addHeaderView(mHeadView);
        //ListView第二个头部控件（浮动栏）
        mFloatBarInLvHeader=LayoutInflater.from(mContext).inflate(R.layout.include_floatbar,mListView,false);
        mListView.addHeaderView(mFloatBarInLvHeader);
        List<String> data=new ArrayList<>(100);
        for(int i=0;i<100;i++){
            data.add(String.valueOf(i));
        }
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,data);
        mListView.setAdapter(adapter);
        mListView.setSelection(1);
        //监听ListView滑动事件
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //判断ListView头部中的浮动栏（mFloatBarInLvHeader）当前是否可见来决定隐藏或显示浮动栏（mFloatBar）
                if (firstVisibleItem>=1){
                    mFloatBar.setVisibility(View.VISIBLE);
                }else{
                    mFloatBar.setVisibility(View.GONE);
                }
            }
        });
    }
}
