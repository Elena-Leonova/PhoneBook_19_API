package okhttp;

import com.google.gson.Gson;
import dto.ContactDTO;
import dto.ResponseMessageDTO;
import okhttp3.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

public class DeleteAllContactsTests {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibGVuYS5wb3N0cmFzaEBnbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY5MDgyMDA2OSwiaWF0IjoxNjkwMjIwMDY5fQ.ziwTyzZjLFteNYOVsHFZzetpDxLDWHMSp-zmaP9E8wg";
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    @BeforeMethod
    public void preconditions() throws IOException {
        int i = new Random().nextInt(1000) + 1000;
        ContactDTO contactDTO = ContactDTO.builder()
                .id("")
                .name("Roma")
                .lastName("Pupkin")
                .phone("0585651567")
                .email("pupkin" + i + "@gmail.com")
                .address("Haifa")
                .description("")
                .build();

        RequestBody body = RequestBody.create(gson.toJson(contactDTO), JSON);

        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization", token)
                .post(body)
                .build();

        Response response = client.newCall(request).execute();

        ResponseMessageDTO responseMessageDTO = gson.fromJson(response.body().string(),ResponseMessageDTO.class);
        String message = responseMessageDTO.getMessage();
        System.out.println(message);
    }

    @Test
    public void deleteContactByIDPositive() throws IOException {
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts/clear" )
                .addHeader("Authorization", token)
                .delete()
                .build();

        Response response = client.newCall(request).execute();

        ResponseMessageDTO responseMessageDTO = gson.fromJson(response.body().string(), ResponseMessageDTO.class);
        String message = responseMessageDTO.getMessage();
        System.out.println(message);
    }
}
