package com.project.lsh.controller;

import javax.annotation.Resource;
import javax.validation.groups.Default;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.lsh.beans.UserBean;
import com.project.lsh.service.MailSendService;
import com.project.lsh.service.UserService;
import com.project.lsh.validator.UserValidator;
import com.project.lsh.validator.ValidationGroups;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private MailSendService mailSendService;
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	@GetMapping("/login")
	public String login(@ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean,
						@RequestParam(value = "fail", defaultValue = "false") boolean fail,
						Model model) {
		model.addAttribute("fail", fail);
		
		return "user/login";
	}
	
	@PostMapping("/login_pro")
	public String loginPro(@Validated(ValidationGroups.PasswordGroup.class)
							@ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean,
							BindingResult result) {
		if (result.hasErrors()) {
			return "user/login";
		}
		
		userService.selectUserForLogin(tempLoginUserBean);
		
		if (loginUserBean.isUserLogin() == true) {
			if (loginUserBean.getUser_auth_status().equals("Y")) {
				return "user/login_success";
			}
			
			return "user/not_auth_user";
		}
		
		return "user/login_fail";
	}
	
	@GetMapping("/join")
	public String join(@ModelAttribute("joinUserBean") UserBean joinUserBean) {
		return "user/join";
	}
	
	@PostMapping("/join_pro")
	public String joinPro(@Validated({Default.class, ValidationGroups.PasswordGroup.class, ValidationGroups.EmailGroup.class})
						   @ModelAttribute("joinUserBean") UserBean joinUserBean,
						   BindingResult result,
						   Model model) {
		if (result.hasErrors()) {
			return "user/join";
		}
		
		boolean isGenerableUserId = userService.checkUserIdExist(joinUserBean.getUser_id());
		
		if (isGenerableUserId == true) {
			userService.insertUser(joinUserBean);
			mailSendService.sendAuthMailForJoin(joinUserBean);
			
			model.addAttribute("user_email", joinUserBean.getUser_email());
			
			return "user/add_user";
		}
			
		return "user/add_user_fail";
	}
	
	@GetMapping("/mail/join")
	public String authUserEmail(@RequestParam("user_email") String user_email,
								  @RequestParam("auth_key") String auth_key) {
		boolean isCheckedEmail = userService.checkValidAuthKeyForJoin(user_email, auth_key);
		
		mailSendService.deleteAuthKeyAfterCheckMail(auth_key);
		
		if (isCheckedEmail == true) {
			return "user/join_success";
		}
		
		return "user/join_fail";
	}
	
	@GetMapping("/not_auth_user")
	public String notAuthUser() {
		return "user/not_auth_user";
	}
	
	@GetMapping("/mypage")
	public String mypage(@ModelAttribute("readUserBean") UserBean readUserBean) {
		userService.selectUser(readUserBean);
		
		return "user/mypage";
	}
	
	@GetMapping("/modify_profile")
	public String modfiyProfile(@ModelAttribute("modifyUserBean") UserBean modifyUserBean) {
		userService.selectUser(modifyUserBean);
		
		return "user/modify_profile";
	}
	
	@PostMapping("/modify_profile_pro")
	public String modifyProfilePro(@ModelAttribute("modifyUserBean") UserBean modifyUserBean) {
		userService.updateUserProfileImg(modifyUserBean);
		
		return "user/modify_success";
	}
	
	@GetMapping("/modify_password")
	public String modifyPassword(@ModelAttribute("modifyUserBean") UserBean modifyUserBean) {
		return "user/modify_password";
	}
	
	@PostMapping("/modify_password_pro")
	public String modifyPasswordPro(@Validated(ValidationGroups.PasswordGroup.class)
									  @ModelAttribute("modifyUserBean") UserBean modifyUserBean,
									  BindingResult result) {
		if (result.hasErrors()) {
			return "user/modify_password";
		}
		
		userService.updateUserPassword(modifyUserBean);
		
		return "user/modify_success";
	}
	
	@GetMapping("/modify_email")
	public String modifyEmail(@ModelAttribute("modifyUserBean") UserBean modifyUserBean) {
		return "user/modify_email";
	}
	
	@PostMapping("/modify_email_pro")
	public String modifyEmailPro(@Validated(ValidationGroups.EmailGroup.class)
								   @ModelAttribute("modifyUserBean") UserBean modifyUserBean,
								   BindingResult result) {
		if (result.hasErrors()) {
			return "user/modify_email";
		}
		
		mailSendService.sendAuthMailForUpdate(modifyUserBean);
		
		return "redirect:/user/mypage";
	}
	
	@GetMapping("/mail/modify_email")
	public String modifyUserEmail(@RequestParam("user_email") String user_email,
			  						@RequestParam("auth_key") String auth_key) {
		boolean isCheckedEmail = userService.checkValidAuthKeyForUpdate(user_email, auth_key);
		
		mailSendService.deleteAuthKeyAfterCheckMail(auth_key);
		
		if (isCheckedEmail == true) {
			return "user/modify_email_success";
		}
		
		return "user/modify_email_fail";
	}
	
	@GetMapping("/logout")
	public String logout() {
		loginUserBean.setUserLogin(false);
		
		return "user/logout";
	}
	
	@GetMapping("/not_login")
	public String notLogin() {
		return "user/not_login";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator validator1 =  new UserValidator();
		
		binder.addValidators(validator1);
	}
}
