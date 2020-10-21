package dev.binhcn.grpc;

import dev.binhcn.consul.ConsulServiceDiscovery;
import dev.binhcn.consul.ServiceDiscovery;
import dev.binhcn.proto.GreeterGrpc;
import dev.binhcn.proto.HelloReply;
import dev.binhcn.proto.HelloRequest;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWorldServer {
  private static Logger log = LoggerFactory.getLogger(HelloWorldServer.class);
  private Server server;
  public void start() throws IOException {
    int port = 50051;
    server = ServerBuilder.forPort(port)
        .addService(new HelloWorldService())
        .build()
        .start();
    log.info("Server started, listening on " + port);

    ServiceDiscovery serviceDiscovery = ConsulServiceDiscovery.singleton("localhost", 8500);

    serviceDiscovery.createService("hello-world", "instance-1", null,
        "localhost", 50051,
        null, Inet4Address.getLocalHost().getHostAddress() + ":50051",
        "10s", "5s");

    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        System.err.println("*** shutting down gRPC server since JVM is shutting down");
        HelloWorldServer.this.stop();
        System.err.println("*** server shut down");
      }
    });
  }
  public void stop() {
    if (server != null) {
      server.shutdown();
    }
  }
  /**
   * Await termination on the main thread since the grpc library uses daemon threads.
   */
  public void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }
  public static void main(String[] args) throws Exception {
    // start server.
    final HelloWorldServer server = new HelloWorldServer();
    server.start();
    server.blockUntilShutdown();
  }
  private static class HelloWorldService extends GreeterGrpc.GreeterImplBase {
    @Override
    public void sayHello(HelloRequest request,
        io.grpc.stub.StreamObserver<HelloReply> responseObserver) {
      String name = request.getName();
      HelloReply response = HelloReply.newBuilder().setMessage("hello " + name).build();
      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
  }
}
