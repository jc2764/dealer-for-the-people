# A Dealer For The People

## Contact
For any comments or feedback, feel free to contact me at joshua.clark02@gmail.com.

## About Project
The KGB has noticed a resurgence of overly excited reviews for a McKaig Chevrolet Buick, a dealership they have planted 
in the United States. In order to avoid attracting unwanted attention, you’ve been enlisted to scrape reviews for this 
dealership from DealerRater.com and uncover the top three worst offenders of these overly positive endorsements.

This tool:
1. Scrapes the first five pages of reviews 
2. Identifies the top three most “overly positive” endorsements 
3. Outputs these three reviews to the console, in order of severity

## Built With
* [Spring Boot](https://spring.io/projects/spring-boot)

## Getting Started

### Prerequisites

In order to run this project, Java 11 will need to be installed.

Install Java 11 LTS - https://adoptopenjdk.net/

Or use Homebrew

```shell
$ brew install openjdk@11
```

Once installed, you can verify the installation by running the following command

```shell
$ java --version
```

### Usage

Build the app with
```shell
./mvnw clean install
```

Run the app with the following command
```shell
./mvnw spring-boot:run
```

Run the tests with the following command
```shell
./mvnw test
```

## Endorsement Criteria

The endorsement criteria used to dictate the top reviews is a comparison of the stars on each review. The larger the sum
the better the review.


## Future Enhancements

- More specific endorsement criteria
- Some parts of reviews are not scraped, e.g. "Employees Worked With"
- More robust testing suite