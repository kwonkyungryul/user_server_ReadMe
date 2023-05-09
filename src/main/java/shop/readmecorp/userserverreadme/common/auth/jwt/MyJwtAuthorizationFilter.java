package shop.readmecorp.userserverreadme.common.auth.jwt;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import shop.readmecorp.userserverreadme.common.auth.session.MyUserDetails;
import shop.readmecorp.userserverreadme.common.jpa.RoleType;
import shop.readmecorp.userserverreadme.modules.user.entity.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class MyJwtAuthorizationFilter extends BasicAuthenticationFilter {

    public MyJwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String prefixJwt = request.getHeader(MyJwtProvider.HEADER);

        if (prefixJwt == null) {
            chain.doFilter(request, response);
            return;
        }

        String jwt = prefixJwt.replace(MyJwtProvider.TOKEN_PREFIX, "");
        try {
            DecodedJWT decodedJWT = MyJwtProvider.verify(jwt);
            Integer id = decodedJWT.getClaim("id").asInt();
            String role = decodedJWT.getClaim("role").asString();

            User user = User.builder().id(id).role(RoleType.valueOf(role).toString()).build();
            MyUserDetails myUserDetails = new MyUserDetails(user);
            Authentication authentication =
                    new UsernamePasswordAuthenticationToken(
                            myUserDetails,
                            myUserDetails.getPassword(),
                            myUserDetails.getAuthorities()
                    );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (SignatureVerificationException sve) {
            log.error("토큰 검증 실패");
        } catch (TokenExpiredException tee) {
            log.error("토큰 만료됨");
        } finally {
            chain.doFilter(request, response);
        }
    }
}