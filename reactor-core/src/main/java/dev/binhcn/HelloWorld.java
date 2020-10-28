package dev.binhcn;

import reactor.core.publisher.Flux;

public class HelloWorld {
  public static void main(String[] args) {
    Flux<Integer> just = Flux.just(1, 2, 3, 4);
    System.out.println(just);
  }
}
