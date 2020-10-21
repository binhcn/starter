package dev.binhcn.cashier;

import io.grpc.stub.StreamObserver;
import zalopay.entity.protobuf.cashier.v1.CashierServiceGrpc;
import zalopay.entity.protobuf.cashier.v1.ConsultPayViewRequest;
import zalopay.entity.protobuf.cashier.v1.ConsultPayViewResponse;

public class GrpcController extends CashierServiceGrpc.CashierServiceImplBase {

  @Override
  public void consultPayView(
      ConsultPayViewRequest request, StreamObserver<ConsultPayViewResponse> responseObserver) {
    ConsultPayViewResponse consultPayViewResponse = ConsultPayViewResponse.newBuilder().setReturnCode(2).build();
    responseObserver.onNext(consultPayViewResponse);
    responseObserver.onCompleted();
  }
}
