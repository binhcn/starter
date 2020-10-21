package dev.binhcn.cashier;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import zalopay.entity.protobuf.cashier.v1.CashierServiceGrpc;
import zalopay.entity.protobuf.cashier.v1.ClientInfo;
import zalopay.entity.protobuf.cashier.v1.ConsultPayViewRequest;
import zalopay.entity.protobuf.cashier.v1.ConsultPayViewResponse;
import zalopay.entity.protobuf.cashier.v1.OrderInfo;

public class GrpcClient {
  public static void main(String[] args) {
    ManagedChannel channel = ManagedChannelBuilder
        .forAddress("localhost", 8090)
        .usePlaintext()
        .build();
    CashierServiceGrpc.CashierServiceBlockingStub serviceStub = CashierServiceGrpc.newBlockingStub(channel);
    ConsultPayViewRequest consultPayViewRequest = ConsultPayViewRequest.newBuilder()
        .setUserId("12345")
        .setOrderInfo(OrderInfo.newBuilder().build())
        .setClientInfo(ClientInfo.newBuilder().build())
        .build();

    ConsultPayViewResponse response = serviceStub.consultPayView(consultPayViewRequest);
    System.out.println(response.getReturnCode());
  }
}
