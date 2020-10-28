package dev.binhcn;

import com.google.gson.Gson;
import dev.binhcn.dto.TpeOrderInfo;
import dev.binhcn.util.GsonUtil;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

public class TpeOrderInfoDemo {

  private static OkHttpClient client = new OkHttpClient();

  public static void main(String[] args) {
    RequestBody formBody = new FormBody.Builder()
        .build();

    RequestBody jsonBody = RequestBody.create("", MediaType.parse("application/json"));

    HttpUrl.Builder urlBuilder = HttpUrl.parse("http://10.50.1.9:9713/v001/tpe/zpi/getorderinfo").newBuilder();
    urlBuilder.addQueryParameter("appid", "606");
    urlBuilder.addQueryParameter("zptranstoken", "2010200000015001su23F3");
    urlBuilder.addQueryParameter("clientid", "1");
    urlBuilder.addQueryParameter("reqdate", "1603191138196");
    urlBuilder.addQueryParameter("sig", "caa3ecec4006349fefefdd57bb23d6d0c565d0e6dc5caeb4c6ba62e1e8a6ded9");
    String url = urlBuilder.build().toString();

    Request request = new Request.Builder()
        .url(url)
//        .header("Content-Type", "application/json")
        .post(jsonBody)
        .build();

    Call call = client.newCall(request);
    call.enqueue(new Callback() {
      @Override
      public void onFailure(@NotNull Call call, @NotNull IOException e) {
        System.out.printf("You called failure with {} and message: {}\n",
            e, e.getMessage());
      }

      @Override
      public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        TpeOrderInfo tpeOrderInfo = null;
        String body = response.body().string();
        tpeOrderInfo = GsonUtil.fromJsonString(body, TpeOrderInfo.class);

//        Gson gson = new Gson();
//        tpeOrderInfo = gson.fromJson(body, TpeOrderInfo.class);

        System.out.println(body);
        System.out.println(response.toString());
        System.out.println(tpeOrderInfo);
      }
    });

  }
}
