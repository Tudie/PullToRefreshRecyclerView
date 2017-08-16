package tudie.com.pulltorefreshrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.tudie.pulltorefresh.PullToRefreshRecyclerView;
import com.tudie.pulltorefresh.adapter.CommonAdapter;
import com.tudie.pulltorefresh.adapter.base.ViewHolder;
import com.tudie.pulltorefresh.callback.PullToRefreshListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    public List<String> data = new ArrayList<>();
    private CommonAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final PullToRefreshRecyclerView pullToRefreshRV = (PullToRefreshRecyclerView) findViewById(R.id.pullToRefreshRV);
        pullToRefreshRV.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
        GetList();
        adapter = new CommonAdapter<String>(this, R.layout.item_mode, data) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.textView, s);
            }
        };
        pullToRefreshRV.setRefreshArrowResource(R.drawable.ic_arrow);
        pullToRefreshRV.setRefreshingResource(R.drawable.ic_refreshing);
        pullToRefreshRV.setAdapter(adapter);
        //是否开启下拉刷新功能
        pullToRefreshRV.setPullRefreshEnabled(true);
        //是否开启上拉加载功能
        pullToRefreshRV.setLoadingMoreEnabled(true);
        //设置是否显示上次刷新的时间
        pullToRefreshRV.displayLastRefreshTime(true);
        //设置刷新回调
        pullToRefreshRV.setPullToRefreshListener(new PullToRefreshListener() {
            @Override
            public void onRefresh() {
                pullToRefreshRV.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshRV.setRefreshComplete();
                        //模拟没有数据的情况
                        data.clear();
                        GetList();
                        adapter.notifyDataSetChanged();
                    }
                }, 3000);
            }

            @Override
            public void onLoadMore() {
                pullToRefreshRV.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pullToRefreshRV.setLoadMoreComplete();
                        //模拟加载数据的情况
                        GetList();
                        adapter.notifyDataSetChanged();

                    }
                }, 3000);
            }
        });

    }


    private void GetList() {
        for (int i = 0; i < 5; i++) {
            data.add(i + "Data");
        }

    }
}
