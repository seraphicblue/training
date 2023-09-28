package stockm.config;

import java.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

public class JwtFilter extends GenericFilterBean{

	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtFilter.class);

	public static final String AUTHORIZATION_HEADER = "Authorization";

	private TokenProvider tokenProvider;
	/**
	 * @paramtokenProvider
	 */
	public JwtFilter(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}
	
	@Override
	public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response,
	        jakarta.servlet.FilterChain chain) throws IOException, jakarta.servlet.ServletException {
	    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
	    String jwt = resolveToken(httpServletRequest);
	    String requestURI = httpServletRequest.getRequestURI();
	    if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
	        Authentication authentication = tokenProvider.getAuthentication(jwt);
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        LOGGER.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
	    } else {
	        LOGGER.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI);
	    }

	    chain.doFilter(request, response);
	}

	
	//request Header에서 토큰 정보를 꺼내오기 위한 메소드
	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	
	}
