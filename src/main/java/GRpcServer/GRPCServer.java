package GRpcServer;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GRPCServer {
    public static void main (String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(9090)
                .addService(new UserLoginService())
//                .intercept(new AuthorizationServerInterceptor())
                .build();
        server.start();
        System.out.println("server port : "+ server.getPort());
        server.awaitTermination();
    }
}
