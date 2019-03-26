package com.izycrush;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.izycrush.model.mongo.Conversation;
import com.izycrush.repository.mongo.ConversationRepository;

@Configuration
@EnableCaching
@SpringBootApplication
@EnableMongoRepositories
public class IzyCrushApplication
{

	public static void main(String[] args) {
		SpringApplication.run(IzyCrushApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ConversationRepository conversationRepository) {

		return args -> {

			Conversation conversation = new Conversation();
			conversation.setLastMessageDate(new Date());
			conversation.setUserIds(new ArrayList<>());
			conversation.getUserIds().add("asda");
			conversation.getUserIds().add("asd");
			conversationRepository.saveConversation(conversation);

			List<Conversation> results = conversationRepository.getByUserId("asda");
			System.out.println(results.get(0).getUserIds());

		};

	}
}
