package com.adjt.chefmanagerapi.infrastructure.api.controller.usuario;

import com.adjt.chefmanagerapi.UsuarioApi;
import com.adjt.chefmanagerapi.core.usecases.usuario.alterarsenha.AlterarSenha;
import com.adjt.chefmanagerapi.core.usecases.usuario.atualizar.AtualizarUsuario;
import com.adjt.chefmanagerapi.core.usecases.usuario.atualizar.AtualizarUsuarioOutput;
import com.adjt.chefmanagerapi.core.usecases.usuario.buscar.BuscarUsuarioOutput;
import com.adjt.chefmanagerapi.core.usecases.usuario.buscar.BuscarUsuarioPorId;
import com.adjt.chefmanagerapi.core.usecases.usuario.buscar.BuscarUsuarioPorNome;
import com.adjt.chefmanagerapi.core.usecases.usuario.deletar.DeletarUsuarioPorId;
import com.adjt.chefmanagerapi.core.usecases.usuario.cadastrar.CadastrarUsuario;
import com.adjt.chefmanagerapi.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class UsuarioController implements UsuarioApi {
    private final CadastrarUsuario cadastrar;
    private final AtualizarUsuario atualizar;
    private final BuscarUsuarioPorId buscar;
    private final BuscarUsuarioPorNome buscarPorNome;
    private final DeletarUsuarioPorId deletar;
    private final AlterarSenha alterarSenha;
    private final UsuarioApiMapper mapper;

    public UsuarioController(
            CadastrarUsuario cadastrar,
            AtualizarUsuario atualizar,
            BuscarUsuarioPorId buscar,
            BuscarUsuarioPorNome buscarPorNome,
            DeletarUsuarioPorId deletar,
            AlterarSenha alterarSenha,
            UsuarioApiMapper mapper) {
        this.cadastrar = cadastrar;
        this.atualizar = atualizar;
        this.buscar = buscar;
        this.buscarPorNome = buscarPorNome;
        this.deletar = deletar;
        this.alterarSenha = alterarSenha;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<Void> alterarSenha(UUID id, AlterarSenhaRequest alterarSenhaRequest) {
        alterarSenha.executar(mapper.toInput(id, alterarSenhaRequest));
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UsuarioResponse> atualizar(UUID id, AtualizarUsuarioRequest atualizarUsuarioRequest) {
        AtualizarUsuarioOutput usuario = atualizar.executar(mapper.toInput(id, atualizarUsuarioRequest));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(usuario));
    }

    @Override
    public ResponseEntity<UsuarioResponse> buscarPorNome(String nome) {
        BuscarUsuarioOutput usuario = buscarPorNome.executar(nome);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(usuario));
    }

    @Override
    public ResponseEntity<UsuarioResponse> buscarUsuarioPorId(UUID id) {
        var usuario = buscar.executar(id);
        var response = mapper.toResponse(usuario);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<UsuarioResponse> criarUsuario(UsuarioRequest usuarioRequest) {
        var usuario = cadastrar.executar(mapper.toInput(usuarioRequest));
        var response = mapper.toResponse(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<Void> deletarUsuarioPorId(UUID id) {
        deletar.executar(id);
        return ResponseEntity.noContent().build();
    }
}
