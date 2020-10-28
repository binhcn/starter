package dev.binhcn;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.function.Function;
import okhttp3.ResponseBody;
import org.junit.Test;
import reactor.core.publisher.Mono;

public class HelloWorld {
  @Test
  public void test1() throws MalformedURLException, InterruptedException {
    HttpClient httpClient = new HttpClientBuilder().build();

    HttpRequest request = new HttpRequest(HttpMethod.GET, new URL("https://api.github.com/users/binhcn"));
    request.setHeader("Content-Type", "application/json");

    Mono<HttpResponse> responseMono = httpClient.send(request);

    Mono<String> responseStringMono = responseMono.flatMap(res -> {
      return res.getBodyStringMono();
    });

    responseStringMono.subscribe(System.out::println);

//    Mono<String> contentMono = responseMono.flatMap((Function<HttpResponse, Mono<String>>) httpResponse ->
//        httpResponse.getBodyAsString());
//
//    String content = contentMono.block();
//
//    System.out.println(content);

    Thread.sleep(3000);
  }

  @Test
  public void test2() throws MalformedURLException {
    HttpClient httpClient = new HttpClientBuilder().build();

    HttpRequest request = new HttpRequest(HttpMethod.POST,
        new URL("http://10.50.1.9:9713/v001/tpe/zpi/getorderinfo?appid=606&zptranstoken=2010200000015001su23F3&clientid=1&reqdate=1603191138196&sig=caa3ecec4006349fefefdd57bb23d6d0c565d0e6dc5caeb4c6ba62e1e8a6ded9"));
    request.setHeader("Content-Type", "application/json");

    Mono<HttpResponse> responseMono = httpClient.send(request);

    Mono<String> contentMono = responseMono.flatMap((Function<HttpResponse, Mono<String>>) httpResponse ->
        httpResponse.getBodyAsString());

    String content = contentMono.block();

    System.out.println(content);
  }
}
