package GRpcClient;

import GRpcShared.GRpcAuthenConstant;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.koolsoft.grpcserver.APIResponse;
import com.koolsoft.grpcserver.LoginRequest;
import com.koolsoft.grpcserver.userGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;

import static GRpcShared.GRpcAuthenConstant.secret;

public class GRPCClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",9090).usePlaintext().build();

        BearerToken token = new BearerToken(getJwt());
        userGrpc.userBlockingStub userStub = userGrpc.newBlockingStub(channel).withCallCredentials(token);


        LoginRequest loginRequest = LoginRequest.newBuilder().setUsername("loco").setPassword("hello").build();

        APIResponse response = userStub.login(loginRequest);

        System.out.println(response.getResponseMessage());
    }

    private static String getJwt() {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withClaim("username", "admin")
                .withClaim("password", "user.getPassword()")
                .sign(algorithm);
        return token;
    }
}
