# Supermarket checkout micro-service tdd kata

Kata to practice outside-in TDD with [double loop](http://coding-is-like-cooking.info/2013/04/outside-in-development-with-double-loop-tdd/). 

The idea is let tdd to guide us through **architecture and domain design** in a more realistic and day to day context, a
 micro-services environment.

Inspired on [supermarket code kata](http://codekata.com/kata/kata01-supermarket-pricing/)

There is no implementation because this repository is the kata description itself, made for an internal exercise in N26,
 feel free to create PRs for your solutions or to use it in your own katas.

## Kata description

[Slides](https://slides.com/albertllousasortiz-1/supermarket-checkout-kata)

We are going to implement a supermarket checkout, that basically calculates the total price given a list of items a
 customer
 have on the cart.
   
In order to do that we will need and stock of the supermarket, it should have this information, at least:

```bash
SKU     Price   Description 
---------------------------
  A      2.00     Potato 
  B      4.00     Tomato
```

There is no any restriction on how implement the stock. 

And these are the expected input/outputs for the app.

For the following API call:

```bash
curl --request POST http://localhost:8080/supermarket/checkout \
  --header 'Content-Type: application/json' \
  --data '{ skus: ["A","B","A","B","A","A","A"] }' \
  --include
```

The output should be `201 CREATED` with body:

```json
{
  "total": 24.00
}
```
   
**Notes**: 
* Rule: Write prod code in terms of architecture and domain
* SKUs are [Stock keeping units](https://en.wikipedia.org/wiki/Stock_keeping_unit)
* Should respond with 204 for an empty cart request, `{ skus: [] }` 

Have fun!

## Tips (My rules)

* Let TDD guide/drive you through the design
* Don't Mock What You Don't Own
* Don't mock everything (double testing is ok)
* SoC: Separate orchestration from calculation
* YAGNI
* Don't go always by the book

## Where to start

We have provided the exercise with the [acceptance test](./src/test/kotlin/de/tech26/supermarket/acceptance/CalculateCartTotalAcceptanceTest.kt) already
 set up, we don't want to waste time designing an API.

So jump [here](./src/test/kotlin/de/tech26/supermarket/changemeplease/CheckoutControllerTest.kt) and start coding.

## Bonus: Second part

Let's finish first the previous one ;)

## Tech stack

* Kotlin
* [Spring boot](https://spring.io/projects/spring-boot)
* Testing libraries/frameworks:
    * [JUnit 5](https://junit.org/junit5/docs/current/user-guide/)
    * [Assertj](https://joel-costigliola.github.io/assertj/)
    * [Mockk](https://mockk.io/)
    * [REST Assured](http://rest-assured.io/)
    
They are just the most common used, feel free to add/remove/change any dependency that fits to you

## Running the tests

Run all tests:
```bash
./gradlew test
```

Run unit tests:
```bash
./gradlew unitTest
```

Run integration tests:
```bash
./gradlew integrationTest
```

Run acceptance tests:
```bash
./gradlew acceptanceTest
```
