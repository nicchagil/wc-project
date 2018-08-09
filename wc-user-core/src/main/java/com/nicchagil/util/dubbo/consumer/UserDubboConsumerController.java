package com.nicchagil.util.dubbo.consumer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.nicchagil.util.dubbo.common.UserDubboInput;
import com.nicchagil.util.dubbo.common.UserDubboOutput;
import com.nicchagil.util.dubbo.common.interfaces.UserDubboService;
import com.nicchagil.util.exception.StandardResponse;

@RestController
@RequestMapping("/user/dubbo")
public class UserDubboConsumerController {
	
	@Reference
	private UserDubboService userDubboService;
	
	@GetMapping("/getByCriteria")
	public StandardResponse<UserDubboOutput> getByCriteria(Integer id, String name) {
		return StandardResponse.getSuccessResponse(this.userDubboService.getByCriteria(new UserDubboInput(id, name)));
	}

	@GetMapping("/insert")
    public StandardResponse<UserDubboOutput> insert(Integer id, String name) {
		return StandardResponse.getSuccessResponse(this.userDubboService.insert(new UserDubboInput(id, name)));
	}

}
