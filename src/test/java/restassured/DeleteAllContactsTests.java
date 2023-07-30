package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;


public class DeleteAllContactsTests {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibGVuYS5wb3N0cmFzaEBnbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY5MDgyMDA2OSwiaWF0IjoxNjkwMjIwMDY5fQ.ziwTyzZjLFteNYOVsHFZzetpDxLDWHMSp-zmaP9E8wg";
    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";

        int i = new Random().nextInt(1000) + 1000;

        ContactDTO contactDTO = ContactDTO.builder()
                .name("Roma")
                .lastName("Pupkin")
                .phone("0585651567")
                .email("pupkin" + i + "@gmail.com")
                .address("Haifa")
                .description("")
                .build();
    }

    @Test
    public void deleteAllContactsPositive(){
given()
        .header("Authorization", token)
        .contentType(ContentType.JSON)
        .when()
        .delete("contacts/clear" )
        .then()
        .assertThat().statusCode(200)
        .assertThat().body("message", containsString("All contacts was deleted!"));
    }
}
