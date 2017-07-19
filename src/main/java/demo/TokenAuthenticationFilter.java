package demo;

import demo.token.Token;
import demo.token.TokenRepository;
import demo.user.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenRepository tokenRepository;

    TokenAuthenticationFilter(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String tokenString = request.getHeader("token");

        if(tokenString == null) {
            continueChain(request, response, chain); // user is not authenticated here
            return;
        }

        Token token = tokenRepository.findTokenByTokenValue(tokenString);

        if(token == null) {
            continueChain(request, response, chain); // user is not authenticated still
            return;
        }

        User user = token.getUser();

        if(user == null) {
            continueChain(request, response, chain); // user is not authenticated ...
            return;
        }

        // auth user system wide
        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        continueChain(request, response, chain);
    }

    private void continueChain(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }

}
