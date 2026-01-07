package com.adjt.chefmanagerapi.infraestructure.api.controller.usuario;

import com.adjt.chefmanagerapi.infraestructure.BaseIntegrationTest;
import com.adjt.chefmanagerapi.model.AlterarSenhaRequest;
import com.adjt.chefmanagerapi.model.AtualizarUsuarioRequest;
import com.adjt.chefmanagerapi.model.UsuarioRequest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.adjt.chefmanagerapi.infraestructure.api.controller.usuario.UsuarioRequestHelper.UUID_JOAO_SILVA;
import static com.adjt.chefmanagerapi.infraestructure.api.controller.usuario.UsuarioRequestHelper.UUID_PEDRO_SILVA;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

class UsuarioControllerTest extends BaseIntegrationTest {

    @Test
    void deveCriarUsuarioComSucesso() {
        UsuarioRequest usuarioRequest = UsuarioRequestHelper.getUsuarioRequest();

        given()
                .contentType(ContentType.JSON)
                .body(usuarioRequest)
        .when()
                .post("/usuarios")
        .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("nome", equalTo("João Silva"))
                .body("email", equalTo("joao@email.com"));

    }

    @Test
    void deveBuscarUsuarioPorId() {
        String uuid = UsuarioRequestHelper.UUID_JOAO_SILVA;
        given()
        .when()
                .get("/usuarios/{id}", uuid)
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(uuid))
                .body("nome", equalTo("João Silva"))
                .body("email", equalTo("joaosilva@email.com"))
                .body("login", equalTo("joaosilva"))
                .body("tipo", equalTo("CLIENTE"))
                .body("endereco.rua", equalTo("Rua A"))
                .body("endereco.numero", equalTo("100"))
                .body("endereco.cidade", equalTo("São Paulo"))
                .body("endereco.cep", equalTo("12345-678"))
                .body("endereco.uf", equalTo("SP"));
    }

    @Test
    void deveBuscarUsuarioPorNome() {
        String nome = "João Silva";

        given()
        .when()
                .queryParam("nome", nome)
                .get("/usuarios")
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(UUID_JOAO_SILVA))
                .body("nome", equalTo(nome))
                .body("email", equalTo("joaosilva@email.com"))
                .body("login", equalTo("joaosilva"))
                .body("tipo", equalTo("CLIENTE"))
                .body("endereco.rua", equalTo("Rua A"))
                .body("endereco.numero", equalTo("100"))
                .body("endereco.cidade", equalTo("São Paulo"))
                .body("endereco.cep", equalTo("12345-678"))
                .body("endereco.uf", equalTo("SP"));
    }

    @Test
    void deveAtualizarUsuarioComSucesso() {
        AtualizarUsuarioRequest atualizarRequest = UsuarioRequestHelper.getAtualizarUsuarioRequest();

        given()
                .contentType(ContentType.JSON)
                .body(atualizarRequest)
        .when()
                .put("/usuarios/{id}", UsuarioRequestHelper.UUID_MARIA_SILVA)
        .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo(atualizarRequest.getNome()))
                .body("email", equalTo(atualizarRequest.getEmail()))
                .body("login", equalTo(atualizarRequest.getLogin()))
                .body("tipo", equalTo(atualizarRequest.getTipo().toString()))
                .body("endereco.rua", equalTo(atualizarRequest.getEndereco().getRua()))
                .body("endereco.numero", equalTo(atualizarRequest.getEndereco().getNumero()))
                .body("endereco.cidade", equalTo(atualizarRequest.getEndereco().getCidade()))
                .body("endereco.cep", equalTo(atualizarRequest.getEndereco().getCep()))
                .body("endereco.uf", equalTo(atualizarRequest.getEndereco().getUf()));
    }

    @Test
    void deveDeletarUsuarioComSucesso() {
        given()
                .when()
                .delete("/usuarios/{id}", UUID_PEDRO_SILVA)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        // Verifica se o usuário foi realmente deletado
        given()
                .when()
                .get("/usuarios/{id}", UUID_PEDRO_SILVA)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void deveAlterarSenhaUsuarioComSucesso() {
        AlterarSenhaRequest alterarSenhaRequest = UsuarioRequestHelper.getAlterarSenhaRequest();

        given()
                .contentType(ContentType.JSON)
                .body(alterarSenhaRequest)
        .when()
                .patch("/usuarios/{id}/senha", UUID_JOAO_SILVA)
        .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}