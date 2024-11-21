package com.pet.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 * Service definition for Pet operations
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: pet.proto")
public final class PetServiceGrpc {

  private PetServiceGrpc() {}

  public static final String SERVICE_NAME = "com.pet.proto.PetService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.pet.proto.PetOuterClass.RegisterPetRequest,
      com.pet.proto.PetOuterClass.RegisterPetResponse> getRegisterPetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterPet",
      requestType = com.pet.proto.PetOuterClass.RegisterPetRequest.class,
      responseType = com.pet.proto.PetOuterClass.RegisterPetResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.pet.proto.PetOuterClass.RegisterPetRequest,
      com.pet.proto.PetOuterClass.RegisterPetResponse> getRegisterPetMethod() {
    io.grpc.MethodDescriptor<com.pet.proto.PetOuterClass.RegisterPetRequest, com.pet.proto.PetOuterClass.RegisterPetResponse> getRegisterPetMethod;
    if ((getRegisterPetMethod = PetServiceGrpc.getRegisterPetMethod) == null) {
      synchronized (PetServiceGrpc.class) {
        if ((getRegisterPetMethod = PetServiceGrpc.getRegisterPetMethod) == null) {
          PetServiceGrpc.getRegisterPetMethod = getRegisterPetMethod = 
              io.grpc.MethodDescriptor.<com.pet.proto.PetOuterClass.RegisterPetRequest, com.pet.proto.PetOuterClass.RegisterPetResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.pet.proto.PetService", "RegisterPet"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.pet.proto.PetOuterClass.RegisterPetRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.pet.proto.PetOuterClass.RegisterPetResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PetServiceMethodDescriptorSupplier("RegisterPet"))
                  .build();
          }
        }
     }
     return getRegisterPetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.pet.proto.PetOuterClass.SearchPetsRequest,
      com.pet.proto.PetOuterClass.SearchPetsResponse> getSearchPetsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SearchPets",
      requestType = com.pet.proto.PetOuterClass.SearchPetsRequest.class,
      responseType = com.pet.proto.PetOuterClass.SearchPetsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.pet.proto.PetOuterClass.SearchPetsRequest,
      com.pet.proto.PetOuterClass.SearchPetsResponse> getSearchPetsMethod() {
    io.grpc.MethodDescriptor<com.pet.proto.PetOuterClass.SearchPetsRequest, com.pet.proto.PetOuterClass.SearchPetsResponse> getSearchPetsMethod;
    if ((getSearchPetsMethod = PetServiceGrpc.getSearchPetsMethod) == null) {
      synchronized (PetServiceGrpc.class) {
        if ((getSearchPetsMethod = PetServiceGrpc.getSearchPetsMethod) == null) {
          PetServiceGrpc.getSearchPetsMethod = getSearchPetsMethod = 
              io.grpc.MethodDescriptor.<com.pet.proto.PetOuterClass.SearchPetsRequest, com.pet.proto.PetOuterClass.SearchPetsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.pet.proto.PetService", "SearchPets"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.pet.proto.PetOuterClass.SearchPetsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.pet.proto.PetOuterClass.SearchPetsResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new PetServiceMethodDescriptorSupplier("SearchPets"))
                  .build();
          }
        }
     }
     return getSearchPetsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static PetServiceStub newStub(io.grpc.Channel channel) {
    return new PetServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static PetServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new PetServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static PetServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new PetServiceFutureStub(channel);
  }

  /**
   * <pre>
   * Service definition for Pet operations
   * </pre>
   */
  public static abstract class PetServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void registerPet(com.pet.proto.PetOuterClass.RegisterPetRequest request,
        io.grpc.stub.StreamObserver<com.pet.proto.PetOuterClass.RegisterPetResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterPetMethod(), responseObserver);
    }

    /**
     */
    public void searchPets(com.pet.proto.PetOuterClass.SearchPetsRequest request,
        io.grpc.stub.StreamObserver<com.pet.proto.PetOuterClass.SearchPetsResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSearchPetsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterPetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.pet.proto.PetOuterClass.RegisterPetRequest,
                com.pet.proto.PetOuterClass.RegisterPetResponse>(
                  this, METHODID_REGISTER_PET)))
          .addMethod(
            getSearchPetsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.pet.proto.PetOuterClass.SearchPetsRequest,
                com.pet.proto.PetOuterClass.SearchPetsResponse>(
                  this, METHODID_SEARCH_PETS)))
          .build();
    }
  }

  /**
   * <pre>
   * Service definition for Pet operations
   * </pre>
   */
  public static final class PetServiceStub extends io.grpc.stub.AbstractStub<PetServiceStub> {
    private PetServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PetServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PetServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PetServiceStub(channel, callOptions);
    }

    /**
     */
    public void registerPet(com.pet.proto.PetOuterClass.RegisterPetRequest request,
        io.grpc.stub.StreamObserver<com.pet.proto.PetOuterClass.RegisterPetResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterPetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void searchPets(com.pet.proto.PetOuterClass.SearchPetsRequest request,
        io.grpc.stub.StreamObserver<com.pet.proto.PetOuterClass.SearchPetsResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSearchPetsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * Service definition for Pet operations
   * </pre>
   */
  public static final class PetServiceBlockingStub extends io.grpc.stub.AbstractStub<PetServiceBlockingStub> {
    private PetServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PetServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PetServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PetServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.pet.proto.PetOuterClass.RegisterPetResponse registerPet(com.pet.proto.PetOuterClass.RegisterPetRequest request) {
      return blockingUnaryCall(
          getChannel(), getRegisterPetMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.pet.proto.PetOuterClass.SearchPetsResponse searchPets(com.pet.proto.PetOuterClass.SearchPetsRequest request) {
      return blockingUnaryCall(
          getChannel(), getSearchPetsMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * Service definition for Pet operations
   * </pre>
   */
  public static final class PetServiceFutureStub extends io.grpc.stub.AbstractStub<PetServiceFutureStub> {
    private PetServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private PetServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected PetServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new PetServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.pet.proto.PetOuterClass.RegisterPetResponse> registerPet(
        com.pet.proto.PetOuterClass.RegisterPetRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterPetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.pet.proto.PetOuterClass.SearchPetsResponse> searchPets(
        com.pet.proto.PetOuterClass.SearchPetsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSearchPetsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_PET = 0;
  private static final int METHODID_SEARCH_PETS = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final PetServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(PetServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER_PET:
          serviceImpl.registerPet((com.pet.proto.PetOuterClass.RegisterPetRequest) request,
              (io.grpc.stub.StreamObserver<com.pet.proto.PetOuterClass.RegisterPetResponse>) responseObserver);
          break;
        case METHODID_SEARCH_PETS:
          serviceImpl.searchPets((com.pet.proto.PetOuterClass.SearchPetsRequest) request,
              (io.grpc.stub.StreamObserver<com.pet.proto.PetOuterClass.SearchPetsResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class PetServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    PetServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.pet.proto.PetOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("PetService");
    }
  }

  private static final class PetServiceFileDescriptorSupplier
      extends PetServiceBaseDescriptorSupplier {
    PetServiceFileDescriptorSupplier() {}
  }

  private static final class PetServiceMethodDescriptorSupplier
      extends PetServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    PetServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (PetServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new PetServiceFileDescriptorSupplier())
              .addMethod(getRegisterPetMethod())
              .addMethod(getSearchPetsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
