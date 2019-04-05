package com.izycrush;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.izycrush.model.mongo.Question;
import com.izycrush.service.QuestionService;

@SpringBootApplication
public class IzyCrushApplication
{

	public static void main(String[] args) {
		SpringApplication.run(IzyCrushApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(QuestionService questionService) {
		return args -> {

			questionService.saveQuestion(new Question(1,"Kendimi feminist olarak görüyorum"));
			questionService.saveQuestion(new Question(2,"Yanımda Sigara içilmesi benim için problem değil"));
			questionService.saveQuestion(new Question(3,"Dövme yaptırıyorum yada ilerde yaptırmayı düşünüyorum"));
			questionService.saveQuestion(new Question(4,"Empati yeteneğim çok gelişmiştir"));
			questionService.saveQuestion(new Question(5,"Sosyal medyayı hergün düzenli kullanıyorum"));
			questionService.saveQuestion(new Question(6,"Evrime inanıyorum"));
			questionService.saveQuestion(new Question(7,"Ofansif mizahtan hoşlanırım"));
			questionService.saveQuestion(new Question(8,"Evrensel bir güce veya bir yaratıcıya inanıyorum"));
			questionService.saveQuestion(new Question(9,"Çocuğumun gay/lezbiyen olması benim için problem değil"));
			questionService.saveQuestion(new Question(10,"Herşeye şüpheyle yaklaşırım"));
			questionService.saveQuestion(new Question(11,"Yasalara ve kurallara uyarım"));
			questionService.saveQuestion(new Question(12,"Doğanın güzelliğinden zevk alırım"));
		};
	}
}
