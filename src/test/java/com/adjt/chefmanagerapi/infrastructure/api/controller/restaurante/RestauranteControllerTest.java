package com.adjt.chefmanagerapi.infrastructure.api.controller.restaurante;

import com.adjt.chefmanagerapi.infraestructure.BaseIntegrationTest;
import com.adjt.chefmanagerapi.model.AtualizarRestauranteRequest;
import com.adjt.chefmanagerapi.model.RestauranteRequest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

class RestauranteControllerTest extends BaseIntegrationTest {
    @Test
    void deveCriarRestauranteComSucesso() {
        RestauranteRequest restauranteRequest = RestauranteRequestHelper.getRestauranteRequest();

        given()
                .contentType(ContentType.JSON)
                .body(restauranteRequest)
                .when()
                .post("/restaurantes")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("nome", equalTo("Camarões"))
                .body("endereco", equalTo("Rua Principal"))
                .body("tipoCozinha", equalTo("Nordestina"))
                .body("horarioFuncionamento", equalTo("10h às 22h"));
    }

    @Test
    void deveBuscarRestaurantePorId() {
        String uuid = RestauranteRequestHelper.UUID_CAMAROES;

        given()
                .when()
                .get("/restaurantes/{id}", uuid)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", notNullValue())
                .body("nome", equalTo("Camarões"))
                .body("endereco", equalTo("Rua Principal"))
                .body("tipoCozinha", equalTo("Nordestina"))
                .body("horarioFuncionamento", equalTo("10h às 22h"));
    }

    @Test
    void deveBuscarRestaurantesPorNome() {
        String nome = "Camarões";

        given()
                .when()
                .queryParam("nome", nome)
                .get("/restaurantes")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("[0].id", equalTo(RestauranteRequestHelper.UUID_CAMAROES))
                .body("[0].nome", equalTo("Camarões"))
                .body("[0].endereco", equalTo("Rua Principal"))
                .body("[0].tipoCozinha", equalTo("Nordestina"))
                .body("[0].horarioFuncionamento", equalTo("10h às 22h"));
    }

    @Test
    void deveAtualizarRestauranteComSucesso() {
        AtualizarRestauranteRequest atualizarRequest = RestauranteRequestHelper.getAtualizarRestauranteRequest();

        given()
                .contentType(ContentType.JSON)
                .body(atualizarRequest)
                .when()
                .put("/restaurantes/{id}", RestauranteRequestHelper.UUID_CAMAROES)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(RestauranteRequestHelper.UUID_CAMAROES))
                .body("nome", equalTo("Braz Elettrica"))
                .body("endereco", equalTo("Av. Paulista"))
                .body("tipoCozinha", equalTo("Italiana"))
                .body("horarioFuncionamento", equalTo("10h às 22h"));
    }

    @Test
    void deveDeletarRestauranteComSucesso() {
        given()
                .when()
                .delete("/restaurantes/{id}", RestauranteRequestHelper.UUID_CAMAROES)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        // Verifica se o restaurantes foi realmente deletado
        given()
                .when()
                .get("/restaurantes/{id}", RestauranteRequestHelper.UUID_CAMAROES)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}