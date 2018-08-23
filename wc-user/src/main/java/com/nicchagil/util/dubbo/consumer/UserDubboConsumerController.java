package com.nicchagil.util.dubbo.consumer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.rpc.service.EchoService;
import com.nicchagil.dubbo.interfaces.user.UserDubboService;
import com.nicchagil.dubbo.model.user.UserDubboInput;
import com.nicchagil.dubbo.model.user.UserDubboOutput;
import com.nicchagil.util.exception.StandardResponse;

@ConditionalOnProperty(name = "dubboEnable", havingValue = "true")
@RestController
@RequestMapping("/user/dubbo")
public class UserDubboConsumerController {
	
	@Resource(name = "userDubboService")
	private UserDubboService userDubboService;
	
	@GetMapping("/getByCriteria")
	public StandardResponse<UserDubboOutput> getByCriteria(HttpServletRequest request, Integer id, String name) {
		UserDubboOutput userDubboOutput = this.userDubboService.getByCriteria(new UserDubboInput(id, name));
		return StandardResponse.getSuccessResponse(userDubboOutput);
	}

	@GetMapping("/insert")
    public StandardResponse<UserDubboOutput> insert(Integer id, String name) {
		UserDubboOutput userDubboOutput = this.userDubboService.insert(new UserDubboInput(id, name));
		return StandardResponse.getSuccessResponse(userDubboOutput);
	}
	
	@GetMapping("/echo")
	public StandardResponse<Object> echo(HttpServletRequest request, Integer id, String name) {
		EchoService echoService = (EchoService) userDubboService;
		Object echoResult = echoService.$echo("OK");
		return StandardResponse.getSuccessResponse(echoResult);
	}
	
	@GetMapping("/sleep20Second")
	public StandardResponse<UserDubboOutput> sleep20Second(HttpServletRequest request, Integer id, String name) {
		UserDubboOutput userDubboOutput = this.userDubboService.sleep20Second(new UserDubboInput(id, name));
		return StandardResponse.getSuccessResponse(userDubboOutput);
	}
	
	@GetMapping("/occasionally")
	public StandardResponse<UserDubboOutput> occasionally(HttpServletRequest request, Integer id, String name) {
		UserDubboOutput userDubboOutput = this.userDubboService.occasionally(new UserDubboInput(id, name));
		return StandardResponse.getSuccessResponse(userDubboOutput);
	}

}
