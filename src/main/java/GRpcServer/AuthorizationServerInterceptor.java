package GRpcServer;

import GRpcShared.GRpcAuthenConstant;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.koolsoft.authen.service.DTO.JWTTokenClaim;
import com.koolsoft.authen.service.JwtService;
import io.grpc.*;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static GRpcShared.GRpcAuthenConstant.*;

public class AuthorizationServerInterceptor implements ServerInterceptor {

    private JwtParser parser = Jwts.parser().setSigningKey(secret);

    public JWTTokenClaim decodeToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance
            DecodedJWT decodedJWT = verifier.verify(token);
            Date expiredDate = decodedJWT.getExpiresAt();
            String username = decodedJWT.getClaim("username").asString();
            String password = decodedJWT.getClaim("password").asString();

            return new JWTTokenClaim(username,password, expiredDate);
        } catch (JWTVerificationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException();
        }
    }

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        String value = metadata.get(GRpcAuthenConstant.AUTHORIZATION_METADATA_KEY);

        Status status;
        if (value == null) {
            status = Status.UNAUTHENTICATED.withDescription("Authorization token is missing");
        } else if (!value.startsWith(BEARER_TYPE)) {
            status = Status.UNAUTHENTICATED.withDescription("Unknown authorization type");
        } else {
            String token = value.substring(BEARER_TYPE.length()).trim();
            JWTTokenClaim jwtTokenClaim  = decodeToken(token);
            if (jwtTokenClaim.getUsername().equals("admin")){
                try {
                    Context ctx = Context.current().withValue(CLIENT_ID_CONTEXT_KEY, jwtTokenClaim.getUsername());
//                    Jws<Claims> claims = parser.parseClaimsJws(token);
//                    Context ctx = Context.current().withValue(CLIENT_ID_CONTEXT_KEY, claims.getBody().getSubject());
                    return Contexts.interceptCall(ctx, serverCall, metadata, serverCallHandler);
                } catch (Exception e) {
                    status = Status.UNAUTHENTICATED.withDescription(e.getMessage()).withCause(e);
                }
            }else {
                status = Status.UNAUTHENTICATED.withDescription("cannot access");
            }
        }

        serverCall.close(status, metadata);
        return new ServerCall.Listener() {
            // noop
        };
    }
}
