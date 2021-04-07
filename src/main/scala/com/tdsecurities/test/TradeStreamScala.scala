package com.tdsecurities.test

/**
 * The system is event based so trades arrive at this consumer out of order.
 * To fix this issue, tradesStream must be sorted with the following rules:
 *
 * * SWAP trades are represented by two strings (i.e. 123CB SELL or 9898AA BUY), orderNumber and buySell respectively.
 *   - order numbers must be sorted lexicographically (i.e 11 before 11C)
 *   - Sells (SELL) must be processed before Buys (BUY) by stacking SELL/BUY pairs with the same orderNumber
 *
 * * CASH trades are represented by two numbers (i.e. 984756 34566), code and serial respectively.
 * CASH trades must be processed in order of appearance, after all SWAPs are processed
 *
 * Please take a look at the test class for some examples
 */
object TradeStreamScala {

  val swaps = List("BUY", "SELL")

  val swapCompare: (String, String) => Boolean = (t1, t2) => {

    val s1 = t1 split " "
    val s2 = t2 split " "

    val compareOrderNumbers = s1(0) compareTo s2(0)
    val compareTrades = s1(1) compareTo s2(1)

    if (compareOrderNumbers != 0) compareOrderNumbers < 0 else compareTrades > 0
  }


  //_________________________________________________________________________

  def orderTrades(tradesStream: String*): Seq[String] = {

    val swapTrades =
      tradesStream
        .filter(trade => swaps.exists(trade contains _))
        .sortWith(swapCompare)

    val cashTrades =
      tradesStream
        .filter(trade => !swaps.exists(trade contains _))

    swapTrades ++ cashTrades
  }
}
