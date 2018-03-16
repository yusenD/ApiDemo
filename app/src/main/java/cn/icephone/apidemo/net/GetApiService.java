package cn.icephone.apidemo.net;

import cn.icephone.apidemo.bean.NewsBean;
import cn.icephone.apidemo.util.Constants;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dong on 18-3-16.
 */

public interface GetApiService {
    @GET(Constants.NEWS_URL)
    Call<NewsBean> getBean();
}
