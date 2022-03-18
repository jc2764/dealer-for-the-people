package com.dealerrater;

import com.github.javafaker.Faker;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * ReviewTestUtil is a utility class that holds methods for testing.
 */
public class ReviewTestUtil {

  /**
   * Create mock reviews for testing.
   *
   * @param numberOfReviews number of mock reviews to create
   * @return a list of mock reviews
   */
  public static List<Review> createMockReviews(Integer numberOfReviews) {
    Faker faker = new Faker();
    return IntStream.range(0, numberOfReviews)
            .mapToObj(i -> new Review(faker.name().fullName(),
                    faker.date().toString(),
                    faker.space().starCluster(),
                    faker.space().constellation(),
                    faker.space().constellation(),
                    faker.random().nextInt(5),
                    faker.random().nextInt(5),
                    faker.random().nextInt(5),
                    faker.random().nextInt(5),
                    faker.random().nextInt(5))).collect(Collectors.toList());
  }
}
