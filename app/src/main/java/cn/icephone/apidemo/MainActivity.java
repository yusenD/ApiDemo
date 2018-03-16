package cn.icephone.apidemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import cn.icephone.apidemo.bean.NewsBean;
import cn.icephone.apidemo.net.GetApiService;
import cn.icephone.apidemo.net.RetrofitManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tvNewsTitle;
    private ImageView ivPic;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        RetrofitManager.getInstance().create(GetApiService.class)
                .getBean()
                .enqueue(new Callback<NewsBean>() {
                    @Override
                    public void onResponse(@NonNull Call<NewsBean> call, @NonNull Response<NewsBean> response) {
                        NewsBean bean = response.body();
                        if (bean.getStories() != null) {
                            tvNewsTitle.setText(bean.getStories().get(0).getTitle());
                            Log.d("pic", bean.getStories().get(0).getImages().get(0));
                            Glide.with(MainActivity.this)
                                    .load(bean.getStories().get(0).getImages().get(0))
                                    .into(ivPic);
                            Log.d("news", bean.getDate());
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<NewsBean> call, @NonNull Throwable t) {
                        Log.d("news", t.getMessage());
                    }
                });

    }

    public void initView() {
        tvNewsTitle = findViewById(R.id.tv_news_title);
        ivPic = findViewById(R.id.iv_image);
    }
}
