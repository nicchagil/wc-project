package com.nicchagil.util.dubbo.consumer;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicchagil.util.dubbo.common.UserDubboInput;
import com.nicchagil.util.dubbo.common.UserDubboOutput;
import com.nicchagil.util.dubbo.common.UserDubboService;

@RestController
@RequestMapping("/user/dubbo")
public class UserDubboConsumerController {
	
	@Resource(name="userDubboConsumerService")
	private UserDubboService userDubboService;

	@GetMapping("/getByCriteria")
    public UserDubboOutput get() {
		return this.userDubboService.getByCriteria(new UserDubboInput(123, "NK"));
	}

}
