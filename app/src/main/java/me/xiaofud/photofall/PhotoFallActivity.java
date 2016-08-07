package me.xiaofud.photofall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.xiaofud.photofall.adapters.ImageAdapter;
import me.xiaofud.photofall.staticresources.ImageURL;

public class PhotoFallActivity extends AppCompatActivity {

//    http://blog.csdn.net/duanymin/article/details/44979355
    // 比较详细的教程
//    https://guides.codepath.com/android/Using-the-RecyclerView#overview

    /**
     * 几列图片
     */
    public static final int SPAN_COUNT = 2;

    @BindView(R.id.recycleView)
    RecyclerView mRecyclerView;

    private ImageAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<String> image_urls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mLayoutManager = new StaggeredGridLayoutManager(
                SPAN_COUNT,
                StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        image_urls =  Arrays.asList(ImageURL.imageUrls);
        mAdapter = new ImageAdapter(image_urls, this, R.drawable.minguo_loading);
        mRecyclerView.setAdapter(mAdapter);
    }

}
