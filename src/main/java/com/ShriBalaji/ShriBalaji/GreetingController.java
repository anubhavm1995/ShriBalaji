package com.ShriBalaji.ShriBalaji;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@RestController
public class GreetingController {


//	@Autowired
//    private JavaMailSender javaMailSender;
//	
	@GetMapping(path = "/") 
	public String firstPage(){
	    return "Default Page";
    }
	
	//GMAIL
	/*@CrossOrigin(origins = "*")
	@PostMapping(path = "/contactme")
	public String postData(@RequestBody ContactUs obj){
	System.out.println("Entering into function postData");  
    SimpleMailMessage msg = new SimpleMailMessage();
    msg.setTo("shreebalajitour2020@gmail.com");
    msg.setSubject("Message Sent from "+obj.getFullName());
    msg.setText("Hi Shahrukh ! I have some query: \n\n\n"+"Fullname : "+obj.getFullName()+" \n"
    		+ "Email Id : "+obj.getEmail()+" \n"
    		+"Number : "+obj.getNumber()+" \n"
    		+"Message : "+obj.getMessage());
  
    System.out.println("Sendingg....");
    try
    {
    	javaMailSender.send(msg);
    }
    catch(Exception e)
    {
    	System.out.println("Exception occurred:--> " + e);
    	return "Failed";
    }
    System.out.println("Email Sent");
    return "Email Sent";
    }*/
	
	@CrossOrigin(origins = "*")
	@PostMapping(path = "/contactme")
	public String postData(@RequestBody ContactUs obj) throws IOException{
		System.out.println("Entering into function postData");  
	    Email from = new Email("shreebalajitour2020@gmail.com");
	    String subject = "Hello World from the SendGrid Java Library!";
	    Email to = new Email("shreebalajitour2020@gmail.com");
	    Content content = new Content("text/plain", "Hello, Email!");
	    Mail mail = new Mail(from, subject, to, content);

	    SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (IOException ex) {
	      throw ex;
	    }
		return "sent";
		  
    }
	
	
	@GetMapping(path = "/test") 
	public String getData(){
	
    return "hi buddy";
    }
}
