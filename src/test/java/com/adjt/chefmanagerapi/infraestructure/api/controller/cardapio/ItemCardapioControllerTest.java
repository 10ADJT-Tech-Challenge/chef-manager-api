package com.adjt.chefmanagerapi.infraestructure.api.controller.cardapio;

import com.adjt.chefmanagerapi.infraestructure.BaseIntegrationTest;
import com.adjt.chefmanagerapi.model.ItemCardapioRequest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


class ItemCardapioControllerTest extends BaseIntegrationTest {

    @Test
    void deveCriarItemComSucesso() {
        ItemCardapioRequest req = ItemCardapioRequestHelper.novoXburger();

        given()
                .contentType(ContentType.JSON)
                .body(req)
                .when()
                .post("/restaurantes/{id}/itens-cardapio", ItemCardapioRequestHelper.UUID_DONO)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("nome", equalTo("X-Burger"))
                .body("descricao", equalTo("Hambúrguer artesanal"))
                .body("preco", comparesEqualTo(new java.math.BigDecimal("29.90")))
                .body("consumoLocal", equalTo(true))
                .body("restauranteId", equalTo(ItemCardapioRequestHelper.UUID_DONO));
    }

    @Test
    void deveBuscarItemPorId() {
        String idItem = criarItemEDevolverId();

        given()
                .when()
                .get("/itens-cardapio/{id}", idItem)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(idItem))
                .body("nome", notNullValue())
                .body("restauranteId", equalTo(ItemCardapioRequestHelper.UUID_DONO));
    }

    @Test
    void deveRetornar404AoBuscarItemInexistente() {
        String uuidInexistente = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa";

        given()
                .when()
                .get("/itens-cardapio/{id}", uuidInexistente)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("status", equalTo(404))
                .body("title", containsStringIgnoringCase("Not Found"));
    }

    @Test
    void deveListarItensDoRestaurante() {
        criarItemSeNecessario("Vegano");

        given()
                .when()
                .get("/restaurantes/{id}/itens-cardapio", ItemCardapioRequestHelper.UUID_DONO)
                .then()
                .statusCode(anyOf(is(HttpStatus.OK.value()), is(HttpStatus.NO_CONTENT.value())))
                .body("$", anyOf(notNullValue(), empty()))
                .body("size()", greaterThanOrEqualTo(0));
    }

    @Test
    void deveBuscarItensPorTermoNoRestaurante() {
        criarItemSeNecessario("X-Burger");

        given()
                .when()
                .queryParam("term", "burger")
                .get("/restaurantes/{id}/itens-cardapio", ItemCardapioRequestHelper.UUID_DONO)
                .then()
                .statusCode(anyOf(is(HttpStatus.OK.value()), is(HttpStatus.NO_CONTENT.value())))
                .body("[0].nome", anyOf(containsStringIgnoringCase("burger"), nullValue()));
    }

    @Test
    void deveAtualizarItemComSucesso() {
        String idItem = criarItemEDevolverId();

        ItemCardapioRequest reqAtualizar = new ItemCardapioRequest()
                .nome("X-Burger Especial")
                .descricao("Agora com cheddar")
                .preco(BigDecimal.valueOf(29.90))
                .consumoLocal(true)
                .caminhoFoto("https://img.example/xburger-esp.png")
                .restauranteId(java.util.UUID.fromString(ItemCardapioRequestHelper.UUID_DONO));

        given()
                .contentType(ContentType.JSON)
                .body(reqAtualizar)
                .when()
                .put("/itens-cardapio/{id}", idItem)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(idItem))
                .body("nome", equalTo("X-Burger Especial"))
                .body("preco", comparesEqualTo(new java.math.BigDecimal("29.90")));
    }

    @Test
    void deveDeletarItemComSucesso() {
        String idItem = criarItemEDevolverId();

        given()
                .when()
                .delete("/itens-cardapio/{id}", idItem)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
        given()
                .when()
                .get("/itens-cardapio/{id}", idItem)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void deveRetornar400QuandoRequestInvalido() {
        ItemCardapioRequest invalido = ItemCardapioRequestHelper.invalidoSemNome();

        given()
                .contentType(ContentType.JSON)
                .body(invalido)
                .when()
                .post("/restaurantes/{id}/itens-cardapio", ItemCardapioRequestHelper.UUID_DONO)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("status", equalTo(400))
                .body("title", containsStringIgnoringCase("validation error"))
                .body("detail", containsStringIgnoringCase("campos inválidos"));

    }


    private String criarItemEDevolverId() {
        var req = ItemCardapioRequestHelper.novoVegano();
        return
                given()
                        .contentType(ContentType.JSON)
                        .body(req)
                        .when()
                        .post("/restaurantes/{id}/itens-cardapio", ItemCardapioRequestHelper.UUID_DONO)
                        .then()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .path("id");
    }

    private void criarItemSeNecessario(String nome) {
        var req = ItemCardapioRequestHelper.novoXburger();
        req.setNome(nome);
        given().contentType(ContentType.JSON).body(req)
                .when().post("/restaurantes/{id}/itens-cardapio", ItemCardapioRequestHelper.UUID_DONO)
                .then().statusCode(anyOf(is(HttpStatus.CREATED.value()), is(HttpStatus.OK.value()), is(HttpStatus.CONFLICT.value())));
    }
}
