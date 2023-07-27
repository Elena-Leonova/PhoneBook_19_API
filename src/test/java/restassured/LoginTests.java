package restassured;

import com.jayway.restassured.RestAssured;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class LoginTests {

    @BeforeMethod
    public void precondition(){
        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";
        RestAssured.basePath = "v1";
    }

    @Test
    public void loginPositive(){
        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("lena.postrash@gmail.com")
                .password("Mynameislena1!")
                .build();

        AuthResponseDTO responseDTO = given()
                .body(requestDTO)
                .contentType("application/json; charset=utf-8")
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(200)
                .extract()
                .as(AuthResponseDTO.class);

        System.out.println(responseDTO.getToken());
    }


    @Test
    public void loginNegativeWrongEmail(){

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("lena.postrashgmail.com")
                .password("Mynameislena1!")
                .build();



        ErrorDTO errorDTO = given()
                .body(requestDTO)
                .contentType("application/json; charset=utf-8")
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(401)
                .extract()
                .as(ErrorDTO.class);

        System.out.println(errorDTO.getMessage());
    }

    @Test
    public void loginNegativeWrongPassword(){

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("lena.postrash@gmail.com")
                .password("Mynameislena1")
                .build();

        ErrorDTO errorDTO = given()
                .body(requestDTO)
                .contentType("application/json; charset=utf-8")
                .when()
                .post("user/login/usernamepassword")
                .then()
                .assertThat().statusCode(401)
                .extract()
                .as(ErrorDTO.class);

        System.out.println(errorDTO.getMessage());
    }
}
