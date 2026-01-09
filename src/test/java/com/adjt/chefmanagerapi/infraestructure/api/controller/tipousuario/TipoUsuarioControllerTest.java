package com.adjt.chefmanagerapi.infraestructure.api.controller.tipousuario;

import com.adjt.chefmanagerapi.infraestructure.BaseIntegrationTest;
import com.adjt.chefmanagerapi.model.TipoUsuarioRequest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.adjt.chefmanagerapi.infraestructure.api.controller.tipousuario.TipoUsuarioRequestHelper.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

class TipoUsuarioControllerTest extends BaseIntegrationTest {

    @Test
    void deveCriarTipoUsuarioComSucesso() {
        TipoUsuarioRequest tipoUsuarioRequest = TipoUsuarioRequestHelper.getTipoUsuarioClienteRequest();

        given()
                .contentType(ContentType.JSON)
                .body(tipoUsuarioRequest)
                .when()
                .post("/tipos-usuario")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("id", notNullValue())
                .body("nome", equalTo(tipoUsuarioRequest.getNome()))
                .body("categoriaUsuario", equalTo(tipoUsuarioRequest.getCategoriaUsuario().toString()));
    }

    @Test
    void deveAtualizarTipoUsuarioComSucesso() {
        TipoUsuarioRequest atualizarRequest = TipoUsuarioRequestHelper.getAtualizarTipoUsuarioRequest();

        given()
                .contentType(ContentType.JSON)
                .body(atualizarRequest)
                .when()
                .put("/tipos-usuario/{id}", UUID_TIPO_USUARIO_CLIENTE)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo(atualizarRequest.getNome()))
                .body("categoriaUsuario", equalTo(atualizarRequest.getCategoriaUsuario().name()));
    }

    @Test
    void deveBuscarTipoUsuarioPorId() {
        String uuid = UUID_TIPO_USUARIO_DONO_RESTAURANTE;
        given()
                .when()
                .get("/tipos-usuario/{id}", uuid)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", equalTo(uuid))
                .body("nome", equalTo("DONO_RESTAURANTE"))
                .body("categoriaUsuario", equalTo("DONO_RESTAURANTE"));
    }

    @Test
    void deveBuscarTodosTiposUsuarios() {
        given()
                .when()
                .get("/tipos-usuario")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("size()", equalTo(4))

                .body("[0].id", equalTo(UUID_TIPO_USUARIO_ADMIN))
                .body("[0].nome", equalTo("ADMIN"))
                .body("[0].categoriaUsuario", equalTo("ADMIN"))

                .body("[1].id", equalTo(UUID_TIPO_USUARIO_CLIENTE_TESTE))
                .body("[1].nome", equalTo("CLIENTE_TESTE"))
                .body("[1].categoriaUsuario", equalTo("CLIENTE"))

                .body("[2].id", equalTo(UUID_TIPO_USUARIO_CLIENTE))
                .body("[2].nome", equalTo("CLIENTE"))
                .body("[2].categoriaUsuario", equalTo("CLIENTE"))

                .body("[3].id", equalTo(UUID_TIPO_USUARIO_DONO_RESTAURANTE))
                .body("[3].nome", equalTo("DONO_RESTAURANTE"))
                .body("[3].categoriaUsuario", equalTo("DONO_RESTAURANTE"));
    }

    @Test
    void deveDeletarTipoUsuarioComSucesso() {
        given()
                .when()
                .delete("/tipos-usuario/{id}", UUID_TIPO_USUARIO_CLIENTE_TESTE)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());

        // Verifica se o tipo usuário foi realmente deletado
        given()
                .when()
                .get("/tipos-usuario/{id}", UUID_TIPO_USUARIO_CLIENTE_TESTE)
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}
