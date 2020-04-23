package MyGradleProject;



import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import MyGradleProject.repository.UserRepository;

import MyGradleProject.Entities.User;

//@RestController
public class HelloController {

//	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Bootttt!";
	}

	@RequestMapping("/test")
	public String test() {
		return "";
	}
	

}