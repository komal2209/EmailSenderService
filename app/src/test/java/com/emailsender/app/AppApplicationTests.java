package com.emailsender.app;

import com.emailsender.app.services.EmailService;
import com.emailsender.app.services.Impl.EmailServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@SpringBootTest
class AppApplicationTests {

	@Autowired
	EmailService emailService;


 /* @Test
  void emailSendTest()
  {
	System.out.println("sending emails");
	emailService.sendEmail("kgovind144@gmail.com","Email from Spring Boot","This email is send using Spring boot while create spring services");
}

@Test
	void emailSendToMultipleUser()
	{
		String[] str={"kgovind144@gmail.com","komalkumari0601999@gmail.com"};
		System.out.println("sending emails to multiple peoples.");
		emailService.sendEmail(str,"Email from your friend","This email is send using wireless services 🤣🤣.Acha chalta hu dua me yaad rakhna.");
	}

   @Test
	void sendHtmlInEmail()
	{
		String html=""
				+"<h1 style='color:blue;border:6px solid blue;'>Chill karo bhai.Life too short khao piyo aur maja karo.Aise bohot se dost aate hai aur chale jate hai. So chill ✌️</h1>"+"";
		System.out.println("sending emails with html");
		emailService.sendEmailWithHtml("komalkumari0601999@gmail.com","Email from Spring Boot",html);
	}
	@Test
	void sendFileInEmail()
	{
		String filePath = "C:\\Users\\USER\\Downloads";
		File file = new File(filePath);
		System.out.println("sending emails to  person with file.");
		emailService.sendEmailWithFile(
				"kgovind144@gmail.com",
				"Friend Wedding Pictures",
				"some memories of wedding.",
				new File("C:\\Users\\USER\\Pictures\\Screenshots\\Screenshot 2024-11-29 211248.png")
		        );
	}*/

	@Test
	void sendFileInEmail()
	{
		File filePath = new File("C:\\Users\\USER\\Pictures\\Screenshots\\Screenshot 2024-11-29 211248.png");
		try {
			InputStream ins=new FileInputStream(filePath);
			System.out.println("sending emails to  person with file.");
			emailService.sendEmailWithFile(
					"komalkumari0601999@gmail.com",
					"Friend Wedding Pictures",
					"some memories of wedding.",
					ins
			);
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}


}
