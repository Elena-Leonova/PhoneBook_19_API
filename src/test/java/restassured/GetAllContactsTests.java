package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import dto.GetAllContactsDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class GetAllContactsTests {
    String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJST0xFX1VTRVIiXSwic3ViIjoibGVuYS5wb3N0cmFzaEBnbWFpbC5jb20iLCJpc3MiOiJSZWd1bGFpdCIsImV4cCI6MTY5MDgyMDA2OSwiaWF0IjoxNjkwMjIwMDY5fQ.ziwTyzZjLFteNYOVsHFZzetpDxLDWHMSp-zmaP9E8wg";

    @BeforeMethod
    public void precondition() {
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }

    @Test
    public void getAllContactsPositive(){
        GetAllContactsDTO list = given()
                .header("Authorization", token)
                .when()
                .get("contacts")
                .then()
                .assertThat().statusCode(200)
                .extract().as(GetAllContactsDTO.class);

        for(ContactDTO contactDTO:list.getContacts()){
            System.out.println(contactDTO.getId());
            System.out.println(contactDTO.getEmail());
            System.out.println("====================================");
        }
    }
}
