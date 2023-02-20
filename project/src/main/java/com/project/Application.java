package com.project;

import com.project.model.DailyActivityDetail;
import com.project.model.DailyActivitySummary;
import com.project.repository.DetailRepository;
import com.project.repository.SummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@SpringBootApplication
@EnableMongoRepositories
public class Application implements CommandLineRunner {

	@Autowired
	DetailRepository dailyActivityDetailRepo;

	@Autowired
	SummaryRepository dailyActivitySummaryRepo;

	/**
	 * CREATE operation for dailyActivityDetails collection.
	 */
	void createDailyActivityDetail() {
		System.out.println("Data creation started...");
		dailyActivityDetailRepo.save(new DailyActivityDetail("walking", 136,
				1400, "20220101", 200, 100,
				"20220101T132707-0800", "20220101T132847-0800",
				70));
		dailyActivityDetailRepo.save(new DailyActivityDetail("transport", 1400,
				"20220101", 200, 100,
				"20220101T180007-0800", "20220101T180147-0800"));
		dailyActivityDetailRepo.save(new DailyActivityDetail("cycling", 100,
				1400, "20220101", 200, 100,
				"20220101T182007-0800", "20220101T182147-0800"));
		System.out.println("Data creation complete...");
	}

	/**
	 * READ dailyActivityDetails collection by date.
	 * @param date to look for any daily activity detail
	 */
	public void getDailyActivityDetailByDate(String date) {
		dailyActivityDetailRepo.findDetailByDate(date).forEach(item -> System.out.println(item));
	}

	/**
	 * READ dailyActivityDetails collection by category.
	 * @param category to look for any detail activity detail
	 */
	public void getDailyActivityDetailByCategory(String category) {
		dailyActivityDetailRepo.findDetailByCategory(category).forEach(item -> System.out.println(item));
	}

	/**
	 * READ dailyActivityDetails collection by total count of documents.
	 */
	public void findCountOfDailyActivityDetails() {
		long count = dailyActivityDetailRepo.count();
		System.out.println("Number of documents in the collection: " + count);
	}

	/**
	 * DELETE dailyActivityDetails document by given date.
	 * @param date to find dailyActivityDetails
	 */
	public void deleteDailyActivityDetailByDate(String date) {
		List<DailyActivityDetail> list = dailyActivityDetailRepo.findDetailByDate(date);
		dailyActivityDetailRepo.deleteAll(list);
		list.forEach(item -> System.out.println("Deleted " + item));
	}

	/**
	 * CREATE operation for dailyActivitySummary collection.
	 */
	void createDailyActivitySummary() {
		System.out.println("Data creation started...");
		dailyActivitySummaryRepo.save(new DailyActivitySummary("walking", 136,
				1400, "20220101", 200, 100, 599));
		dailyActivitySummaryRepo.save(new DailyActivitySummary("transport", 1400,
				"20220101", 200, 100));
		dailyActivitySummaryRepo.save(new DailyActivitySummary("cycling", 1400,
				220, "20220101", 200, 100));
		System.out.println("Data creation complete...");
	}

	/**
	 * READ dailyActivitySummary collection by date.
	 * @param date to look for any daily activity summary
	 */
	public void getDailyActivitySummaryByDate(String date) {
		dailyActivitySummaryRepo.findSummaryByDate(date).forEach(item -> System.out.println(item));
	}

	/**
	 * READ dailyActivitySummary collection by category.
	 * @param category to look for any detail activity summary
	 */
	public void getDailyActivitySummaryByCategory(String category) {
		dailyActivitySummaryRepo.findSummaryByCategory(category).forEach(item -> System.out.println(item));
	}

	/**
	 * READ dailyActivitySummary collection by total count of documents.
	 */
	public void findCountOfDailyActivitySummary() {
		long count = dailyActivitySummaryRepo.count();
		System.out.println("Number of documents in the collection: " + count);
	}

	/**
	 * DELETE dailyActivitySummary document by given date.
	 * @param date to find dailyActivitySummary
	 */
	public void deleteDailyActivitySummaryByDate(String date) {
		List<DailyActivitySummary> list = dailyActivitySummaryRepo.findSummaryByDate(date);
		dailyActivitySummaryRepo.deleteAll(list);
		list.forEach(item -> System.out.println("Deleted " + item));
	}


	public void run(String... args) {
		System.out.println("-----Create Activity Detail-----");
		createDailyActivityDetail();
		System.out.println("\n-----Get Activity Detail by Date-----");
		getDailyActivityDetailByDate("20130209");
		System.out.println("\n-----Get Activity Detail by Category-----");
		getDailyActivityDetailByCategory("cycling");
		System.out.println("\n-----Get Activity Detail Count-----");
		findCountOfDailyActivityDetails();
		System.out.println("\n-----Delete Activity Detail by Date-----");
		deleteDailyActivityDetailByDate("20220101");
		System.out.println("\n-----Final Activity Detail Count-----");
		findCountOfDailyActivityDetails();
		System.out.println("-----Create Activity Summary-----");
		createDailyActivitySummary();
		System.out.println("\n-----Get Activity Summary by Date-----");
		getDailyActivitySummaryByDate("20130209");
		System.out.println("\n-----Get Activity Summary by Category-----");
		getDailyActivitySummaryByCategory("cycling");
		System.out.println("\n-----Get Activity Summary Count-----");
		findCountOfDailyActivitySummary();
		System.out.println("\n-----Delete Activity Summary by Date-----");
		deleteDailyActivitySummaryByDate("20220101");
		System.out.println("\n-----Final Activity Summary Count-----");
		findCountOfDailyActivitySummary();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
