package okhttp;

import com.google.gson.Gson;
import dto.ContactDTO;
import dto.GetAllContactsDTO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetAllContactsTests {

    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibGVuYS5wb3N0cmFzaEBnbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY5MDgyMDA2OSwiaWF0IjoxNjkwMjIwMDY5fQ.ziwTyzZjLFteNYOVsHFZzetpDxLDWHMSp-zmaP9E8wg";
    Gson gson = new Gson();
    OkHttpClient client = new OkHttpClient();

    @Test
    public void getAllContactsPositive() throws IOException {
        Request request = new Request.Builder()
                .url("https://contactapp-telran-backend.herokuapp.com/v1/contacts")
                .addHeader("Authorization", token)
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertTrue(response.isSuccessful());

        GetAllContactsDTO contacts = gson.fromJson(response.body().string(), GetAllContactsDTO.class);

        for(ContactDTO contactDTO : contacts.getContacts()){
            System.out.println(contactDTO.getId());
            System.out.println(contactDTO.getEmail());
            System.out.println("======================================");
        }
    }

}
