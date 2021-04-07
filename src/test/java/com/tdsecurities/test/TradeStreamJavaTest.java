package com.tdsecurities.test;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TradeStreamJavaTest {

    @Test
    public void orderTrades1() {
        List<String> input = Arrays.asList(
                "1921020 212",
                "9898AA SELL",
                "32332 443",
                "784758458 2112",
                "9898AA BUY",
                "123CB BUY",
                "232334 121211221",
                "123CB SELL");
        List<String> actual = TradeStreamJava.orderTrades(input.iterator());
        List<String> expected = Arrays.asList(
                "123CB SELL",
                "123CB BUY",
                "9898AA SELL",
                "9898AA BUY",
                "1921020 212",
                "32332 443",
                "784758458 2112",
                "232334 121211221"
        );

        assertThat(actual, is(expected));
    }

    @Test
    public void orderTrades2() {
        List<String> input = Arrays.asList(
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
                "826382 SELL");
        List<String> actual = TradeStreamJava.orderTrades(input.iterator());
        List<String> expected = Arrays.asList(
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
        );

        assertThat(actual, is(expected));
    }
}