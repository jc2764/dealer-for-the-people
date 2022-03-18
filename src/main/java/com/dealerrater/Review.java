package com.dealerrater;

import com.github.javafaker.Bool;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model of a Review.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
  private String author;
  private String date;
  private String title;
  private String type;
  private String body;
  private Integer rating;
  private Integer customerServiceRating;
  private Integer friendliness;
  private Integer pricing;
  private Integer overallExperience;

  public Integer getRatingSum() {
    return rating + customerServiceRating + friendliness + pricing + overallExperience;
  }
}