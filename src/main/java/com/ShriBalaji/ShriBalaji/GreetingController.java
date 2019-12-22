package com.ShriBalaji.ShriBalaji;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {


	@Autowired
    private JavaMailSender javaMailSender;
	
	@GetMapping(path = "/") 
	public String firstPage(){
	    return "Default Page";
    }
	
	@PostMapping(path = "/contactme") 
	public String postData(@RequestBody ContactUs obj){
	System.out.println(obj.toString());
    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setTo("dynamicindiandude@gmail.com");
    msg.setSubject("Message Sent from "+obj.getFullName());
    msg.setText("Fullname : "+obj.getFullName()+" \n"
    		+ "Address : "+obj.getEmail()+" \n"
    		+"Number : "+obj.getNumber()+" \n"
    		+"Message : "+obj.getMessage());
  
    System.out.println("Sendingg....");    
    javaMailSender.send(msg);
    System.out.println("Sent");
    return "Email Sent";
    }
	
	
	@GetMapping(path = "/test") 
	public String getData(){
	
    return "hi buddy";
    }
}
