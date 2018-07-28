package com.nicchagil.util.dubbo.consumer;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nicchagil.util.dubbo.common.UserDubboInput;
import com.nicchagil.util.dubbo.common.UserDubboOutput;
import com.nicchagil.util.dubbo.common.UserDubboService;
import com.nicchagil.util.exception.ExceptionCodeEnum;
import com.nicchagil.util.exception.GlobalHttpReturn;

@RestController
@RequestMapping("/user/dubbo")
public class UserDubboConsumerController {
	
	@Resource(name="userDubboConsumerService")
	private UserDubboService userDubboService;

	@GetMapping("/getByCriteria")
    public GlobalHttpReturn<UserDubboOutput> getByCriteria(Integer id, String name) {
		GlobalHttpReturn<UserDubboOutput> globalHttpReturn = new GlobalHttpReturn<>();
		
		return globalHttpReturn.withCode(ExceptionCodeEnum.SUCCESS_00001.name())
				.withData(this.userDubboService.getByCriteria(new UserDubboInput(id, name)));
	}

}
