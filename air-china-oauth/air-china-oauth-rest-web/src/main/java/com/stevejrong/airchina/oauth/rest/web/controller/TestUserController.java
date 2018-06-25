//package com.stevejrong.airchina.oauth.rest.web.controller;
//
//import com.stevejrong.airchina.common.util.HttpStatus;
//import com.stevejrong.airchina.common.wrapper.ResponseWrapper;
//import com.stevejrong.airchina.oauth.common.constant.Constants;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.apache.shiro.authz.annotation.RequiresAuthentication;
//import org.apache.shiro.authz.annotation.RequiresRoles;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * Controller - 用户测试
// * TODO 需迁移到user微服务中
// *
// * @author Steve Jrong
// * @since 1.0 create date: 2018年06月24日 上午10:59
// */
//@Api(description = "用户API")
//@RestController
//@RequestMapping(Constants.REQUEST_ROOT_SUFFIX + "/user")
//public class TestUserController {
//    private static final Logger LOGGER = LoggerFactory.getLogger(TestUserController.class);
//
//    /**
//     * 获取所有用户
//     * @return
//     */
//    @ApiOperation(value = "获取所有用户")
//    @GetMapping("/list")
//    @RequiresAuthentication
//    @RequiresRoles("super-admin")
//    public @ResponseBody
//    Object listAllUsers() {
//        LOGGER.debug("获取用户列表...");
//        return ResponseWrapper.response(HttpStatus.OK.code(), HttpStatus.OK.message(), "mock data");
//    }
//
//	/*private void recordRequestLog() {
//		List<?> principals = SecurityUtils.getSubject().getPrincipals().asList();
//		String userName = principals.get(0).toString();
//		recordRequestLogTask.doRecordRequestLog(userName); // 多线程记录日志
//	}*/
//}
