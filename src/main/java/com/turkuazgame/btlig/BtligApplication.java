package com.turkuazgame.btlig;

import com.turkuazgame.btlig.entity.BaseInfo;
import com.turkuazgame.btlig.entity.Competitor;
import com.turkuazgame.btlig.repository.CompetitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class BtligApplication implements CommandLineRunner {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(BtligApplication.class, args);
	}

	@Autowired
	private CompetitorRepository competitorRepository;

	@Override
	public void run(String... args) throws Exception {

		BaseInfo baseInfo = new BaseInfo();
		baseInfo.setUpdatedFrom("BootApp");
		baseInfo.setUpdatedBy("goktug");
		baseInfo.setCreatedBy("goktug");
		baseInfo.setStatus("H");

		Competitor competitor1 = new Competitor();
		competitor1.setDisplayName("Göktuğ Elkovan");
		competitor1.setUserUID("Ju8pUOawsfWay0VvrHacZhk6SZf1");
		competitor1.setBaseInfo(baseInfo);
		//competitorRepository.save(competitor1);

		Competitor competitor2 = new Competitor();
		competitor2.setDisplayName("Bahar Elkovan");
		competitor2.setUserUID("agtvQDYFohVIbN1Q631LudH0TwO2");
		competitor2.setBaseInfo(baseInfo);
		//competitorRepository.save(competitor2);

	}

}
