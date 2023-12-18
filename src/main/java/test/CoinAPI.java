package test;

import java.net.URI; 
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import test.model.CoinBean;
import com.google.gson.Gson;

public class CoinAPI {
	private ArrayList<CoinBean> coinList;
	public CoinAPI(String[] coinNames) throws Exception {
		ArrayList<CoinBean> coinList = new ArrayList<CoinBean>();
		for (int i = 0; i < coinNames.length; i++) {
			CoinBean coin = new CoinBean();
			Gson gson = new Gson();
			HttpRequest getRequest = HttpRequest.newBuilder()
				.uri(new URI("https://api.coingecko.com/api/v3/coins/"+coinNames[i]))
				.build();
			HttpClient httpClient = HttpClient.newHttpClient();
			HttpResponse<String> getResponse = httpClient.send(getRequest, BodyHandlers.ofString());
			coin = gson.fromJson(getResponse.body(), CoinBean.class);
			coinList.add(coin);
		}
		this.coinList=coinList;
	}
	public ArrayList<CoinBean> getCoinList() {
		return coinList;
	}
	
}
