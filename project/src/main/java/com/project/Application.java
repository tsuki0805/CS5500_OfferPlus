package com.project;

import com.project.model.DailyActivityDetail;
import com.project.model.DailyActivitySummary;
import com.project.repository.DetailRepository;
import com.project.repository.SummaryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner runner(DetailRepository detailRepository, SummaryRepository summaryRepository) {
		return args -> {
			System.out.println("-----Create Activity Detail-----");
			detailRepository.save(new DailyActivityDetail("walking", 136,
					1400, "20220101", 200, 100,
					"20220101T132707-0800", "20220101T132847-0800",
					70));
			System.out.println("\n-----Get Activity Detail by Date-----");
			detailRepository.findDetailByDate("20130209").forEach(item -> System.out.println(item));
			System.out.println("\n-----Get Activity Detail by Category-----");
			detailRepository.findDetailByCategory("cycling").forEach(item -> System.out.println(item));
			System.out.println("\n-----Get Activity Detail Count-----");
			System.out.println("Count of daily activity details: " + detailRepository.count());
			System.out.println("\n-----Delete Activity Detail by Date-----");
			detailRepository.deleteDetailByDate("20220101").forEach(item -> System.out.println(item));
			System.out.println("\n-----Final Activity Detail Count-----");
			System.out.println("Count of daily activity details: " + detailRepository.count());
			System.out.println("-----Create Activity Summary-----");
			summaryRepository.save(new DailyActivitySummary("walking", 136,
					1400, "20220101", 200, 100, 599));
			System.out.println("\n-----Get Activity Summary by Date-----");
			summaryRepository.findSummaryByDate("20130209").forEach(item -> System.out.println(item));
			System.out.println("\n-----Get Activity Summary by Category-----");
			summaryRepository.findSummaryByCategory("cycling").forEach(item -> System.out.println(item));
			System.out.println("\n-----Get Activity Summary Count-----");
			System.out.println("Count of daily activity summary: " + summaryRepository.count());
			System.out.println("\n-----Delete Activity Summary by Date-----");
			summaryRepository.deleteSummaryByDate("20220101").forEach(item -> System.out.println(item));
			System.out.println("\n-----Final Activity Summary Count-----");
			System.out.println("Count of daily activity summary: " + summaryRepository.count());
			System.out.println("\n-----Make HTTP Requests-----");
//			makeHttpRequestForDailyActivityDetailByDate();
//			makeHttpRequestForDailyActivityDetailByCategory();
		};
	}

//	public void makeHttpRequestForDailyActivityDetailByDate() throws Exception {
//		String url = "http://localhost:8080/api/fetch/dailyActivityDetailByDate?date=20130209";
//		URL obj = new URL(url);
//		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//		con.setRequestMethod("GET");
//		int responseCode = con.getResponseCode();
//		if (responseCode == HttpURLConnection.HTTP_OK) {
//			BufferedReader in = new BufferedReader(new InputStreamReader(
//					con.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//			while ((inputLine = in.readLine()) != null) {
//				response.append(inputLine);
//			}
//			in.close();
//			System.out.println(response);
//		} else {
//			System.out.println("HTTP request failed with response code: " + responseCode);
//		}
//	}

//	public void makeHttpRequestForDailyActivityDetailByCategory() throws Exception {
//		String url = "http://localhost:8080/api/fetch/dailyActivityDetailByCategory?category=walking";
//		URL obj = new URL(url);
//		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//		con.setRequestMethod("GET");
//		int responseCode = con.getResponseCode();
//		if (responseCode == HttpURLConnection.HTTP_OK) {
//			BufferedReader in = new BufferedReader(new InputStreamReader(
//					con.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//			while ((inputLine = in.readLine()) != null) {
//				response.append(inputLine);
//			}
//			in.close();
//			System.out.println(response);
//		} else {
//			System.out.println("HTTP request failed with response code: " + responseCode);
//		}
//	}

	@Configuration
	public class CorsConfiguration {
		@Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**")
							.allowedOrigins("*")
							.allowedMethods("GET", "POST", "PUT", "DELETE")
							.allowedHeaders("*");
				}
			};
		}
	}

}
