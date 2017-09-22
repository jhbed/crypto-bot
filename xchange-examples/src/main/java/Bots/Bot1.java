package Bots;

import java.io.IOException;
import java.math.BigDecimal;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.bitstamp.BitstampExchange;
import org.knowm.xchange.bitstamp.service.BitstampMarketDataServiceRaw;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.service.marketdata.MarketDataService;

public class Bot1 {

	public static void main(String[] args) throws IOException {

	    // Use the factory to get Bitstamp exchange API using default settings
	    Exchange bitstamp = ExchangeFactory.INSTANCE.createExchange(BitstampExchange.class.getName());

	    // Interested in the public market data feed (no authentication)
	    MarketDataService marketDataService = bitstamp.getMarketDataService();
	    
	    Ticker ticker = marketDataService.getTicker(CurrencyPair.ETH_USD);
	    
	    double init = ticker.getBid().doubleValue();
	    double minProfit = init+0.01;
	    System.out.println("Buy at : " + init);
	    double compareBid = init;
	    while(compareBid < minProfit) {
	    	try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	System.out.println(compareBid);
	    	Ticker tickerCompare = marketDataService.getTicker(CurrencyPair.ETH_USD);
	    	compareBid = tickerCompare.getBid().doubleValue();
	    }
	    System.out.println("Sell at: " + compareBid);

	  }

}
