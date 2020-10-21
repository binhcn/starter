package dev.binhcn.cashier;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.util.concurrent.TimeUnit;

public class GrpcServer {

  public static void main(String[] args) throws InterruptedException {
    System.out.println("PaymentEngine Grpc Service starting...");
    Server server;
    GrpcController grpcController = new GrpcController();
    try {
        server = ServerBuilder.forPort(8090)
            .addService(grpcController)
            .build().start();


      System.out.println("PaymentEngine Service GRPC started, ready to serve asset!");
    } catch (Exception ex) {
      System.out.println("Grpc Server for PaymentEngine Service error" + ex);
      throw new RuntimeException("Grpc Server for PaymentEngine Service error", ex);
    }

    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      System.out.println("gRPC server is shutting down!");
      server.shutdown();
    }));

    // wait for 1 hr
    server.awaitTermination(1, TimeUnit.HOURS);

  }


}
