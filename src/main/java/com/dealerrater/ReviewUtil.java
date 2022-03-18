package com.dealerrater;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * ReviewUtil is a utility class that holds methods for retrieving review attributes from HTML Elements.
 */
public class ReviewUtil {

  public static String getDate(Element element) {
    Elements dateElement = element.getElementsByClass("italic col-xs-6 col-sm-12 pad-none margin-none font-20");
    return dateElement.text();
  }

  public static String getAuthor(Element element) {
    Elements userElement = element.getElementsByClass("italic font-16 bolder notranslate");
    return userElement.text().substring(3);
  }

  public static String getType(Element element) {
    Elements typeElement = element.getElementsByClass("col-xs-12 hidden-xs pad-none margin-top-sm small-text dr-grey");
    return typeElement.text();
  }

  public static String getBody(Element element) {
    Elements reviewBodyElement = element.getElementsByClass("review-whole");
    return reviewBodyElement.text();
  }

  public static String getTitle(Element element) {
    Elements reviewTitleElement = element.getElementsByClass("review-title");
    return reviewTitleElement.text();
  }

  public static Integer getRating(Element element, String cssQuery) {
    Element ratingElement = element.selectFirst(cssQuery);
    if (ratingElement != null) {
      if (ratingElement.selectFirst("div.rating-50") != null) {
        return 5;
      } else if (ratingElement.selectFirst("div.rating-40") != null) {
        return 4;
      } else if (ratingElement.selectFirst("div.rating-30") != null) {
        return 3;
      } else if (ratingElement.selectFirst("div.rating-20") != null) {
        return 2;
      } else if (ratingElement.selectFirst("div.rating-10") != null) {
        return 1;
      }
    }
    return 0;
  }

  public static Integer getTableRating(String category, Element element) {
    Element ratingTable = element.getElementsByClass("table width-100 pad-left-none pad-right-none margin-bottom-md").get(0);

    Elements tr = ratingTable.getElementsByClass("tr");
    for (Element e : tr) {
      Elements elementsByClass = e.getElementsByClass("lt-grey small-text td");
      if (!elementsByClass.isEmpty()) {
        if (elementsByClass.get(0).text(category) != null) {
          return getRating(e, "div.rating-static-indv");
        }
      }

    }
    return 0;
  }
}
