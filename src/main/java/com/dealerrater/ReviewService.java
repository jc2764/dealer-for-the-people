package com.dealerrater;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A service for Review capabilities.
 */
@Service
public class ReviewService {

  public String dealerUrl;

  public ReviewService(@Value("${dealer.url}") String dealerUrl) {
    this.dealerUrl = dealerUrl;
  }

  /**
   * Get all reviews for a number of pages.
   *
   * @param pages the pages to retrieve reviews for
   * @return all reviews for the passed in pages
   */
  public List<Review> getReviews(Integer pages) {
    List<Review> reviews = new ArrayList<>();
    int pageNumber = 1;
    while (pageNumber <= pages) {
      Document document = fetchDocument(pageNumber);
      Elements reviewEntries = document.getElementsByClass("review-entry");
      for (Element element : reviewEntries) {

        String author = ReviewUtil.getAuthor(element);
        String date = ReviewUtil.getDate(element);
        String type = ReviewUtil.getType(element);
        String title = ReviewUtil.getTitle(element);
        String body = ReviewUtil.getBody(element);
        Integer rating = ReviewUtil.getRating(element, "div.dealership-rating");
        Integer customerServiceRating = ReviewUtil.getTableRating("Customer Service", element);
        Integer friendliness = ReviewUtil.getTableRating("Friendliness", element);
        Integer pricing = ReviewUtil.getTableRating("Pricing", element);
        Integer overallExperience = ReviewUtil.getTableRating("Overall Experience", element);
        Review review = new Review(author, date, title, type, body, rating, customerServiceRating, friendliness, pricing, overallExperience);

        reviews.add(review);
      }
      pageNumber++;
    }

    return reviews;
  }

  /**
   * Takes a list of reviews and returns the top reviews.
   *
   * @param reviews         a list of reviews
   * @param numberOfReviews number of reviews to return
   * @return top reviews list
   */
  public List<Review> getTopReviews(List<Review> reviews, Integer numberOfReviews) {
    if (numberOfReviews > reviews.size()) {
      throw new IllegalArgumentException("Number of reviews cannot be greater than size of reviews list.");
    }

    reviews.sort((r1, r2) -> {
      if (r1.getRatingSum() < r2.getRatingSum()) {
        return 1;
      } else if (r1.getRatingSum() > r2.getRatingSum()) {
        return -1;
      }
      return 0;
    });
    return reviews.subList(0, numberOfReviews);
  }

  /**
   * A helper for fetching a DealerReview document with JSoup.
   *
   * @param pageNumber the page number to retrieve
   * @return a document
   */
  private Document fetchDocument(Integer pageNumber) {
    try {
      String dealerPageUrl = dealerUrl + "page" + pageNumber + "/";
      return Jsoup.connect(dealerPageUrl).get();
    } catch (IOException e) {
      throw new ReviewException("Unable to retrieve web page using URL: " + dealerUrl);
    }
  }
}
