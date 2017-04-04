package ru.kononov.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import ru.kononov.objects.User;

@Controller
@SessionAttributes("userObj")
public class LoginController {

	@ModelAttribute(name="userObj")
	public User createNewUser() {
		User user = new User();
		user.setName("Джером Морроу");
		user.setPassword("123456");
		user.setAdmin(true);
		return user;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main() {
		return "index";
	}
	
	@RequestMapping(value = "/check-user", method = RequestMethod.POST)
	public String checkUser(@Valid @ModelAttribute(name = "userObj") User user, BindingResult bindingResult, 
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) return "index";
		redirectAttributes.addFlashAttribute("redirect", true);
		return "redirect:/uploadingFile";
	}
	
	/*@RequestMapping(value = "/uploadingFile", method = RequestMethod.GET)
	public String anotherPage(HttpServletRequest request) {
		Map <String, ?> map = RequestContextUtils.getInputFlashMap(request);
		if (map != null) {
			System.out.println("redirect");
		} else {
			System.out.println("update");
		}
		return "uploadingFile";
	}*/
	
}
