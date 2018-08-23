package com.nicchagil.util.session.springsession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicchagil.util.exception.StandardResponse;

@ConditionalOnProperty(name = "springSessionEnable", havingValue = "true")
@RestController
@RequestMapping("/session/springsession")
public class SessionController {
	
	@GetMapping("/buildSession")
	public StandardResponse<String> buildSession(HttpServletRequest request, boolean buildSession) {
		
		HttpSession httpSession = null;
		if (buildSession) {
			httpSession = request.getSession(true);
		} else {
			httpSession = request.getSession(false);
		}
		
		return StandardResponse.getSuccessResponse(httpSession != null ? "HAVE SESSION" : "HAVE NOT SESSION");
	}

}
