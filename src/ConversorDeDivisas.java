import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class ConversorDeDivisas {
    private final static String API_URL = "https://v6.exchangerate-api.com/v6/f4ae730050e2d6e62d187fc4/latest/USD";
    private final HttpClient cliente = HttpClient.newHttpClient();
    private final Gson gson = new Gson();

    public String convertir(int opcion, double cantidad) {
        String jsonOpcion = obtenerTazaDeCambio();
        Map tasas =gson.fromJson(jsonOpcion, Map.class);
        Map<String, Double> tasaDeCambio = (Map<String, Double>)tasas.get("conversion_rates");

        double resultado = 0.0;
        Locale locale = Locale.US;
        switch (opcion) {
            case 1:
                resultado = cantidad * tasaDeCambio.get("ARS");
                locale = new Locale("es", "AR");
                break;

            case 2:
                resultado = cantidad / tasaDeCambio.get("ARS");
                locale = Locale.US;
                break;

            case 3:
                resultado = cantidad * tasaDeCambio.get("BRL");
                locale = new Locale("pt", "BR");
                break;

            case 4:
                resultado = cantidad / tasaDeCambio.get("BRL");
                locale = Locale.US;
                break;

            case 5:
                resultado = cantidad * tasaDeCambio.get("COP");
                locale = new Locale("es", "CO");
                break;

            case 6:
                resultado = cantidad / tasaDeCambio.get("COP");
                locale = Locale.US;
                break;

            case 7:
                resultado = cantidad * tasaDeCambio.get("DOP");
                locale = new Locale("es", "DO");
                break;

            case 8:
                resultado = cantidad / tasaDeCambio.get("DOP");
                locale = Locale.US;
                break;
        }

        return formatCurrency(resultado, locale);
    }

    private String formatCurrency(double amount, Locale locale){
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        return currencyFormat.format(amount);
    }

    private String obtenerTazaDeCambio(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest solicitud = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        try {
            HttpResponse<String> respuesta = cliente.send(solicitud, HttpResponse.BodyHandlers.ofString());
            return respuesta.body();
        } catch (Exception e){
            e.printStackTrace();;
            return "{}";
        }
    }



}
