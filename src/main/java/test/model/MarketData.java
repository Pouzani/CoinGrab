package test.model;

public class MarketData {
	private CurrentPrice current_price;
	private MarketCap market_cap;
	private String price_change_percentage_24h;
	private TotalVolume total_volume;
	
	public CurrentPrice getCurrent_price() {
		return current_price;
	}
	public void setCurrent_price(CurrentPrice current_price) {
		this.current_price = current_price;
	}
	public MarketCap getMarket_cap() {
		return market_cap;
	}
	public void setMarket_cap(MarketCap market_cap) {
		this.market_cap = market_cap;
	}
	public String getPrice_change_percentage_24h() {
		return price_change_percentage_24h;
	}
	public void setPrice_change_percentage_24h(String price_change_percentage_24h) {
		this.price_change_percentage_24h = price_change_percentage_24h;
	}
	public TotalVolume getTotal_volume() {
		return total_volume;
	}
	public void setTotal_volume(TotalVolume total_volume) {
		this.total_volume = total_volume;
	}
	

}
