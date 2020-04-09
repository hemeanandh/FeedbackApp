package MyGradleProject;



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
public class HelloController {

//	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Bootttt!";
	}

//	@RequestMapping("/test")
	public String test() {
		return "Test Greetings from Spring Bootttt!";
	}

}