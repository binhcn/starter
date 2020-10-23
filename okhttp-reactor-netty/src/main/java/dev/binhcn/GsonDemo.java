package dev.binhcn;

import com.google.gson.Gson;
import dev.binhcn.dto.Car;
import dev.binhcn.dto.TpeOrderInfo;

public class GsonDemo {
  public static void main(String[] args) {
    String json = "{\"brand\":\"Jeep\", \"doors\": 3}";
    String json1 = "{\"doors\": 3}";
    String tpe = "{\"appid\":606,\"apptransid\":\"201020000000003\",\"appuser\":\"Bamboo\",\"apptime\":1603191137968,\"embeddata\":\"{\\\"bookingID\\\":\\\"BB201020T00000004\\\",\\\"flightType\\\":\\\"DOMESTIC\\\",\\\"providerCode\\\":\\\"BAMBOO\\\",\\\"promotioninfo\\\":\\\"\\\"}\",\"item\":\"[{\\\"item_name\\\":\\\"ticket\\\",\\\"quantity\\\":1,\\\"price\\\":4036000,\\\"amount\\\":4036000,\\\"ticket_num\\\":1,\\\"chargeitemamount\\\":22000}]\",\"amount\":20000,\"description\":\"Bamboo Airways, BB201020T00000004\\n Lượt đi: HAN 24/10/2020 14:00 - PQC 24/10/2020 16:05 \\n\",\"campaigncode\":\"\",\"mac\":\"b8ff3f4820398d2926b5180c192dd2fb67b9aca6f055740ba8fded76f2315489\",\"tpebankcode\":\"\",\"zpgwnextstep\":0,\"zpgwnextstepdata\":\"\",\"discountamount\":0,\"subappuser\":\"\",\"subappid\":\"\",\"zptransid\":0,\"isprocessing\":true,\"suggestmessage\":\"\",\"suggestaction\":[],\"sub_error_code\":\"\",\"returncode\":10,\"returnmessage\":\"\"}";
    String tpe1 = "{\"appid\":606}";

    Gson gson = new Gson();

    Car car = gson.fromJson(json1, Car.class);
    System.out.println(car.toString());

    TpeOrderInfo tpeOrderInfo = gson.fromJson(tpe, TpeOrderInfo.class);
    System.out.println(tpeOrderInfo.toString());

  }
}
