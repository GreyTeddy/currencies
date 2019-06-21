import org.json.JSONArray;
import org.json.JSONObject;

public class ConversionInfo{
	
	private static GetJSON11 getJSON11 = new GetJSON11();
	private static JSONObject JSON = getJSON11.getJSON();
	public String base = JSON.getString("base");
	public static JSONObject rates = JSON.getJSONObject("rates");
	public String date = JSON.getString("date");
	private JSONArray currenciesJSON = rates.names();
	public String[] currencies;
	
	ConversionInfo(){
		int length = currenciesJSON.length();
		currencies = new String[length];
		for(int i=0;i<length;i++) {
			currencies[i] = currenciesJSON.getString(i);
		}
	}
	
	public static void main(String[] args) {
		ConversionInfo conversionInfo = new ConversionInfo();
		System.out.println(conversionInfo.currenciesJSON);
		System.out.println(conversionInfo.currencies[2]);
	}
	
	public static double getValue(String inputNumber,String input, String output) {
		try {
			return Integer.parseInt(inputNumber) / rates.getDouble(input) * rates.getDouble(output);
		} catch (Exception e) {
			return 0;
		}
	}
	
}