package com.dealerrater;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ReviewServiceTests {

  ReviewService reviewService;
  private static final String DEALER_URL = "https://www.dealerrater.com/dealer/McKaig-Chevrolet-Buick-A-Dealer-For-The-People-dealer-reviews-23685/";

  @BeforeEach
  public void setup() {
    reviewService = new ReviewService(DEALER_URL);
  }

  @Test
  public void getReviews_returnsReviewsOnFirstFivePages() {
    List<Review> reviews = reviewService.getReviews(5);

    assertFalse(reviews.isEmpty());
    assertEquals(reviews.size(), 50);
    reviews.forEach(review -> {
      assertNotNull(review.getAuthor());
      assertNotNull(review.getBody());
      assertNotNull(review.getDate());
      assertNotNull(review.getType());
      assertNotNull(review.getTitle());
      assertNotNull(review.getRating());
    });
  }

  @Test
  public void getReviews_returnsReviewsOnFirstPage() {
    List<Review> reviews = reviewService.getReviews(1);
    assertEquals(reviews.size(), 10);
    assertFalse(reviews.isEmpty());
    reviews.forEach(review -> {
      assertNotNull(review.getAuthor());
      assertNotNull(review.getBody());
      assertNotNull(review.getDate());
      assertNotNull(review.getType());
      assertNotNull(review.getTitle());
      assertNotNull(review.getRating());
    });

    Review review = reviews.get(0);
    assertEquals("Williamwells", review.getAuthor());
    assertEquals("The whole dealership was willing to stay after hours to make sure we could get the vehicle we wanted!", review.getBody());
    assertEquals("March 17, 2022", review.getDate());
    assertEquals("SALES VISIT - USED", review.getType());
    assertEquals("Tristan was very helpful in finding a vehicle for us.", review.getTitle());
    assertEquals(5, review.getRating());
  }

  @Test
  public void getTopReviews_returnsTopThreeReviews() {
    List<Review> reviews = reviewService.getTopReviews(ReviewTestUtil.createMockReviews(10), 3);
    assertEquals(reviews.size(), 3);
    assertFalse(reviews.isEmpty());
    reviews.forEach(review -> {
      assertNotNull(review.getAuthor());
      assertNotNull(review.getBody());
      assertNotNull(review.getDate());
      assertNotNull(review.getType());
      assertNotNull(review.getTitle());
      assertNotNull(review.getRating());
    });
  }

  @Test
  public void getTopReviews_throwsIllegalArgumentExceptionIfInvalidArguments() {
    IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
      reviewService.getTopReviews(ReviewTestUtil.createMockReviews(1), 5);
    });

    assertEquals("Number of reviews cannot be greater than size of reviews list.", thrown.getMessage());
  }
}
