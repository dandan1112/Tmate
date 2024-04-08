package kr.ac.kopo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.ac.kopo.admin.user.UserService;

@Controller
public class MainController {

	@Autowired
	UserService userService;
	
	@GetMapping("/")
	public String index() {
		return "login";
	}
	
}
