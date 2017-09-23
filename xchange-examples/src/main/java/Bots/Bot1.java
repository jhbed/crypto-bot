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
		
		long startTime, endTime;
		double totalProfit = 0, 
				BTCUSDProfit = 0,
				ETHBTCProfit = 0,
				ETHUSDProfit = 0;

	    // Use the factory to get Bitstamp exchange API using default settings
	    Exchange bitstamp = ExchangeFactory.INSTANCE.createExchange(BitstampExchange.class.getName());

	    // Interested in the public market data feed (no authentication)
	    MarketDataService marketDataService = bitstamp.getMarketDataService();
	    
	    Ticker tickerBTCUSD = marketDataService.getTicker(CurrencyPair.BTC_USD);
	    Ticker tickerETHBTC = marketDataService.getTicker(CurrencyPair.ETH_BTC);
	    Ticker tickerETHUSD = marketDataService.getTicker(CurrencyPair.ETH_USD);
	    
	    double askBTCUSD = tickerBTCUSD.getAsk().doubleValue();
	    double profBTCUSD = askBTCUSD*1.0001;
	    
	    double askETHBTC = tickerETHBTC.getAsk().doubleValue();
	    double profETHBTC = askETHBTC*1.0001;
	    
	    double askETHUSD = tickerETHUSD.getAsk().doubleValue();
	    double profETHUSD = askETHUSD*1.00001;
	    
	    
	    startTime = System.currentTimeMillis();
	    endTime = startTime + 3600000;
	    System.out.printf("Bought in at: " + "%.7f" , askETHUSD);
	    System.out.println();
	    do {
	    	double bidBTCUSD = marketDataService.getTicker(CurrencyPair.BTC_USD).getBid().doubleValue();
	    	double bidETHBTC = marketDataService.getTicker(CurrencyPair.ETH_BTC).getBid().doubleValue();
		    double bidETHUSD = marketDataService.getTicker(CurrencyPair.ETH_USD).getBid().doubleValue();

	    	/*if (bidBTCUSD > profBTCUSD) {
	    		totalProfit += bidBTCUSD - profBTCUSD;
	    		BTCUSDProfit += bidBTCUSD - profBTCUSD;
	    		System.out.println("BTCUSD Just made: " + (bidBTCUSD - profBTCUSD) + " dollars");
	    	}
	    		
	    	if (bidETHBTC > profETHBTC) {
	    		totalProfit += bidETHBTC - profETHBTC;
	    		ETHBTCProfit += bidETHBTC - profETHBTC;
	    		System.out.println("ETHBTC Just made: " + (bidETHBTC - profETHBTC) + " Bitcoin");
	    	}
	    	*/
		    
	    	System.out.printf("Bid: " + "%.7f" + " Profit at: " + "%.7f" , bidETHUSD ,profETHUSD);
	    	System.out.println();
	    	if (bidETHUSD > profETHUSD) {
	    		totalProfit += bidETHUSD - profETHUSD;
	    		ETHUSDProfit += bidETHUSD - profETHUSD;
	    		System.out.println("ETHUSD Just made: " + (bidETHUSD - profETHUSD) + " dollars");
	    	}		    	
	    	//(100*(askBTCUSD-bidBTCUSD)/askBTCUSD < 0.01) 
	    	
	    } while (System.currentTimeMillis() < endTime);
	    
	    /*
	    while(compareBid < minProfit) {
	    	try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	System.out.printf("%.9f", compareBid);
	    	System.out.println();
	    	Ticker tickerCompare = marketDataService.getTicker(CurrencyPair.BTC_USD);
	    	compareBid = tickerCompare.getBid().doubleValue();
	    }
	    */
	    
	    
	   // System.out.printf("Sell at: " + "%.9f", compareBid);
	    // System.out.println();

	  }

}
