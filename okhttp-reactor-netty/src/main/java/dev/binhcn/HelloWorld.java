package dev.binhcn;

import dev.binhcn.reactor.ReactorNettyCall;
import dev.binhcn.reactor.ReactorNettyCallFactory;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Mono;

public class HelloWorld {
  private static OkHttpClient client = new OkHttpClient();

  public static void main(String[] args) throws Exception {
    Request request = new Request.Builder()
        .url("https://api.github.com/users/binhcn")
        .header("Content-Type", "application/json")
        .build();

//    Response response = client.newCall(request).execute();
//    String body = response.body().string();
//    System.out.println(body);
//    System.out.println(response.toString());
//
//    System.out.println("call asynchoronous:");
//    Call call = client.newCall(request);
//    call.enqueue(new Callback() {
//      @Override
//      public void onFailure(@NotNull Call call, @NotNull IOException e) {
//        System.out.printf("You called failure with {} and message: {}\n",
//            e, e.getMessage());
//      }
//
//      @Override
//      public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//        String body = response.body().string();
//        System.out.println(body);
//        System.out.println(response.toString());
//      }
//    });

//    System.out.println("call reactor:");
//    ReactorNettyCallFactory nettyCallFactory = new ReactorNettyCallFactory();
//    Call reactorCall = nettyCallFactory.newCall(request);
//    reactorCall.enqueue(new Callback() {
//      @Override
//      public void onFailure(@NotNull Call call, @NotNull IOException e) {
//        System.out.printf("You called failure with {} and message: {}\n",
//            e, e.getMessage());
//      }
//
//      @Override
//      public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//        String body = response.body().string();
//        System.out.println(body);
//        System.out.println(response.toString());
//      }
//    });

    System.out.println("call reactor:");
    ReactorNettyCallFactory nettyCallFactory = new ReactorNettyCallFactory();
    ReactorNettyCall reactorNettyCall = nettyCallFactory.newCall(request);
    Mono<Response> responseMono = reactorNettyCall.getExecutable().map(response -> {
      ResponseBody responseBody = response.body();
      System.out.println(responseBody);
      return response;
    });

    reactorNettyCall.setExecutable(responseMono);


    reactorNettyCall.enqueue(new Callback() {
      @Override
      public void onFailure(@NotNull Call call, @NotNull IOException e) {
        System.out.printf("You called failure with {} and message: {}\n",
            e, e.getMessage());
      }

      @Override
      public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        String body = response.body().string();
        System.out.println(body);
        System.out.println(response.toString());
      }
    });
    

    Thread.sleep(10000);
  }
}
