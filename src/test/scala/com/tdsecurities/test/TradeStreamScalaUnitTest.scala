package com.tdsecurities.test

import org.scalatest.{FlatSpec, Matchers}

class TradeStreamScalaUnitTest extends FlatSpec with Matchers {

  it should "order trade codes (1)" in {
    val input = Seq("1921020 212",
                    "9898AA SELL",
                    "32332 443",
                    "784758458 2112",
                    "9898AA BUY",
                    "123CB BUY",
                    "232334 121211221",
                    "123CB SELL")
    val actual: Seq[String] = TradeStreamScala.orderTrades(input: _*)
    val expected = List(
      "123CB SELL",
      "123CB BUY",
      "9898AA SELL",
      "9898AA BUY",
      "1921020 212",
      "32332 443",
      "784758458 2112",
      "232334 121211221"
    )

    actual shouldBe expected
  }

  it should "order trade codes (2)" in {
    val input = Seq(
      "2432 233",
      "234 32434",
      "2532YY SELL",
      "455 65456",
      "323 44455",
      "2511AC SELL",
      "110BB SELL",
      "234434 5545",
      "110BB BUY",
      "3233 11123",
      "3223 12",
      "110AF BUY",
      "826382 BUY",
      "444 12111",
      "444 32122",
      "8273222 SELL",
      "444 983",
      "2532YY BUY",
      "2511AC BUY",
      "223211 3487",
      "110AF SELL",
      "8273222 BUY",
      "24352 BUY",
      "2122 1156",
      "24352 SELL",
      "826382 SELL"
    )
    val expected = List(
      "110AF SELL",
      "110AF BUY",
      "110BB SELL",
      "110BB BUY",
      "24352 SELL",
      "24352 BUY",
      "2511AC SELL",
      "2511AC BUY",
      "2532YY SELL",
      "2532YY BUY",
      "826382 SELL",
      "826382 BUY",
      "8273222 SELL",
      "8273222 BUY",
      "2432 233",
      "234 32434",
      "455 65456",
      "323 44455",
      "234434 5545",
      "3233 11123",
      "3223 12",
      "444 12111",
      "444 32122",
      "444 983",
      "223211 3487",
      "2122 1156"
    )
    val actual: Seq[String] = TradeStreamScala.orderTrades(input: _*)

    actual shouldBe expected
  }
}
