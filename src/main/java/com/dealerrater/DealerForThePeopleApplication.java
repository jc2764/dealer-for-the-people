package com.dealerrater;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * The entrypoint to the application. Spins up a Command Line Runner and executes code to retrieve
 * and display top reviews.
 */
@SpringBootApplication
public class DealerForThePeopleApplication implements CommandLineRunner {

  private ReviewService reviewService;

  public DealerForThePeopleApplication(ReviewService reviewService) {
    this.reviewService = reviewService;
  }

  public static void main(String[] args) {
    SpringApplication.run(DealerForThePeopleApplication.class, args);
  }

  @Override
  public void run(String... args) {
    List<Review> reviews = reviewService.getReviews(5);
    List<Review> topReviews = reviewService.getTopReviews(reviews, 3);

    for (Review review : topReviews) {
      System.out.println(review);
    }

    System.exit(0);
  }
}
