package GRpcServer;

import com.koolsoft.grpcserver.APIResponse;
import com.koolsoft.grpcserver.LoginRequest;
import com.koolsoft.grpcserver.RegisterRequest;
import com.koolsoft.grpcserver.userGrpc;
import io.grpc.stub.StreamObserver;

public class UserLoginService extends userGrpc.userImplBase {

     @Override
    public void login(LoginRequest loginRequest, StreamObserver<APIResponse> responseObserver){
         System.out.println("inside login");
         String username = loginRequest.getUsername();
         String password = loginRequest.getPassword();
         APIResponse.Builder response = APIResponse.newBuilder();

         if (username.equals(password)){
             response.setResponseCode("0").setResponseMessage("Success");
         }
         else {
             response.setResponseCode("1").setResponseMessage("invalid password");
         }

         responseObserver.onNext(response.build());
         responseObserver.onCompleted();
     }

    @Override
    public void register(RegisterRequest registerRequest, StreamObserver<APIResponse> responseObserver){
        System.out.println("inside register");
        APIResponse.Builder response = APIResponse.newBuilder();
        response.setResponseCode("0").setResponseMessage("Success");
        responseObserver.onNext(response.build());
        responseObserver.onCompleted();

    }

}
