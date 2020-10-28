package dev.binhcn;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class HelloWorld {
  private <T> T identityWithThreadLogging(T el, String operation) {
    System.out.println(operation + " -- " + el + " -- " +
        Thread.currentThread().getName());
    return el;
  }


  @Test
  public void test1() {
    Flux.range(1, 3)
        .map(n -> identityWithThreadLogging(n, "map1"))
        .flatMap(n -> Mono.just(n).map(nn -> identityWithThreadLogging(nn, "mono")))
        .subscribeOn(Schedulers.parallel())
        .subscribe(n -> {
          this.identityWithThreadLogging(n, "subscribe");
          System.out.println(n);
        });

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void test2() {
    Flux.range(1, 3)
        .map(n -> identityWithThreadLogging(n, "map1"))
        .flatMap(n -> Mono.just(n).map(nn -> identityWithThreadLogging(nn, "mono")).subscribeOn(Schedulers.elastic()))
        .subscribeOn(Schedulers.parallel())
        .subscribe(n -> {
          this.identityWithThreadLogging(n, "subscribe");
          System.out.println(n);
        });

    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void test3() {
    Flux.range(1, 5).subscribe(
        successValue -> System.out.println(successValue),
        error -> System.err.println(error.getMessage()),
        () -> System.out.println("Flux consumed.")
    );
  }

  @Test
  public void test4() {
    Flux<Double> flux = Flux.create(emitter -> {
      Random rnd = new Random();
      for(int i = 0; i <= 10; i++) emitter.next(rnd.nextDouble());
      int random = rnd.nextInt(2);
      if (random < 1) emitter.complete();
      else emitter.error(new RuntimeException(
          "Bad luck, you had one chance out of 2 to complete the Flux"
      ));
    });
    flux.subscribe(System.out::println);
  }

  @Test
  public void whenMonoProducesString_thenBlockAndConsume() {

    String result1 = blockingHelloWorld().block();
    assertEquals("Hello world!", result1);

    String result2 = blockingHelloWorld()
        .block(Duration.of(1000, ChronoUnit.MILLIS));
    assertEquals("Hello world!", result2);

    Optional<String> result3 = Mono.<String>empty().blockOptional();
    assertEquals(Optional.empty(), result3);
  }

  @Test
  public void whenMonoProducesString_thenConsumeNonBlocking() {

    blockingHelloWorld()
        .doOnNext(result -> assertEquals("Hello world!", result))
        .subscribe();

    blockingHelloWorld()
        .subscribe(result -> assertEquals("Hello world!", result));
  }

  private Mono<String> blockingHelloWorld() {
    // blocking
    return Mono.just("Hello world!");
  }

  @Test
  public void test5() throws InterruptedException {
    Flux<Integer> integerFlux = Flux.create((FluxSink<Integer> fluxSink) -> {
      IntStream.range(0, 5)
          .peek(i -> System.out.println("going to emit - " + i))
          .forEach(fluxSink::next);
    });

    integerFlux.subscribe(i -> System.out.println("First :: " + i));
    integerFlux.subscribe(i -> System.out.println("Second:: " + i));

    System.out.print("Comparing follow:");

    integerFlux.delayElements(Duration.ofMillis(1)).subscribe(i -> System.out.println("First :: " + i));
    integerFlux.delayElements(Duration.ofMillis(2)).subscribe(i -> System.out.println("Second:: " + i));

    Thread.sleep(5000);
  }

  @Test
  public void reactiveNoSubscribeOn() {
    int seconds = LocalTime.now().getSecond();
    Mono<Integer> source;
    if (seconds % 2 == 0) {
      source = Flux.range(1, 10)
          .elementAt(5);
    }
    else if (seconds % 3 == 0) {
      source = Flux.range(0, 4)
          .elementAt(5);
    }
    else {
      source = Flux.just(1, 2, 3, 4)
          .elementAt(5);
    }

    source.block(); //line 116
  }
}
