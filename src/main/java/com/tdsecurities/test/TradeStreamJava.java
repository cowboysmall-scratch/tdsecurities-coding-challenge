package com.tdsecurities.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.stream.Stream.concat;

/**
 * The system is event based so trades arrive at this consumer out of order.
 * To fix this issue, tradesStream must be sorted with the following rules:
 * <p>
 * * SWAP trades are represented by two strings (i.e. 123CB SELL or 9898AA BUY), orderNumber and buySell respectively.
 * - order numbers must be sorted lexicographically (i.e 11 before 11C)
 * - Sells (SELL) must be processed before Buys (BUY) by stacking SELL/BUY pairs with the same orderNumber
 * <p>
 * * CASH trades are represented by two numbers (i.e. 984756 34566), code and serial respectively.
 * CASH trades must be processed in order of appearance, after all SWAPs are processed
 * <p>
 * Please take a look at the test class for some examples
 */
class TradeStreamJava {

    private static final List<String> SWAPS = asList("BUY", "SELL");

    private static final Comparator<String> SWAP_COMPARE = (t1, t2) -> {

        String[] s1 = t1.split(" ");
        String[] s2 = t2.split(" ");

        int compareOrderNumbers = s1[0].compareTo(s2[0]);
        int compareTrades = s1[1].compareTo(s2[1]);

        return compareOrderNumbers != 0 ? compareOrderNumbers : -compareTrades;
    };


    //_________________________________________________________________________

    static List<String> orderTrades(Iterator<String> tradesStream) {

        List<String> trades = new ArrayList<>();
        tradesStream.forEachRemaining(trades::add);

        Stream<String> swapTrades =
                trades.stream()
                        .filter(trade -> SWAPS.stream().anyMatch(trade::contains))
                        .sorted(SWAP_COMPARE);

        Stream<String> cashTrades =
                trades.stream()
                        .filter(trade -> SWAPS.stream().noneMatch(trade::contains));

        return concat(swapTrades, cashTrades).collect(Collectors.toList());
    }
}
