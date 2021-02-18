import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Weather {
    public static String getWeather(String message, Model model) throws IOException {
        String uUrl = "http://api.openweathermap.org/data/2.5/weather?q=Lviv&units=metric&appid=65b15a75b268e360a4e0e54ddb108047";
        URL url = new URL(uUrl);

        Scanner scanner = new Scanner(url.openStream());

        System.out.println(url.toString());
        String result = "";
        while (scanner.hasNext()) {
            result += scanner.nextLine();
        }
        scanner.close();

        JSONObject obj = new JSONObject(result);
        model.setName(obj.getString("name"));

        JSONObject main = obj.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));

        JSONArray getArray = obj.getJSONArray("weather");
        for (int i = 0; i < getArray.length(); i++) {
            JSONObject obj1 = getArray.getJSONObject(i);
            model.setIcon((String) obj1.get("icon"));
            model.setMain((String) obj1.get("main"));
        }

        return "City" + model.getName() + "\n" +
                "Temperature" + model.getTemp() + "\n" +
                "Humidity" + model.getHumidity() + "\n" +
                "http://openweathermap.org/img/w/" + model.getIcon() + ".png" + "\n" +
                "Main" + model.getMain() + "\n";
    }

}
