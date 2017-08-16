# PullToRefreshRecyclerView

## 1. 在Module下的build.gradle中添加依赖
### Step 1. Add the JitPack repository to your build file
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
### Step 2. Add the dependency
dependencies {
	        compile 'com.github.Tudie:PullToRefreshRecyclerView:v1.0.1'
	}

## 2. 在布局文件中添加PullToRefreshRecyclerView控件
     <com.tudie.pulltorefresh.PullToRefreshRecyclerView
        android:id="@+id/pullToRefreshRV"
        android:layout_width="368dp"
        android:layout_height="495dp"
        tools:layout_editor_absoluteY="8dp"
        tools:layout_editor_absoluteX="8dp" />

## 3. 初始化PullToRefreshRecyclerView并设置属性和回调

        //是否开启下拉刷新功能
        pullToRefreshRV.setPullRefreshEnabled(true);
        //是否开启上拉加载功能
        pullToRefreshRV.setLoadingMoreEnabled(true);
   //设置刷新回调
     pullToRefreshRV.setOnRefreshListener(this);

## 4.处理刷新加载逻辑
 @Override
    public void onRefresh() {
        pullToRefreshRV.postDelayed(new Runnable() {
            @Override
            public void run() {
                pullToRefreshRV.setRefreshComplete();
                //模拟没有数据的情况
                data.clear();
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
                int size = data.size();
                for (int i = size; i < size + 4; i++) {
                    data.add("" + i + i + i + i);
                }
                adapter.notifyDataSetChanged();
            }
        }, 3000);
    }