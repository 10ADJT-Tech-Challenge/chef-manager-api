package com.adjt.chefmanagerapi.infrastructure.api.controller.tipousuario;

import com.adjt.chefmanagerapi.TipoUsuarioApi;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.atualizar.AtualizarTipoUsuario;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.atualizar.AtualizarTipoUsuarioOutput;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.buscar.BuscarTipoUsuarioOutput;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.buscar.BuscarTipoUsuarioPorId;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.buscar.BuscarTodosTipoUsuario;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.cadastrar.CadastrarTipoUsuario;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.cadastrar.CadastrarTipoUsuarioOutput;
import com.adjt.chefmanagerapi.core.usecases.tipousuario.deletar.DeletarTipoUsuario;
import com.adjt.chefmanagerapi.model.TipoUsuarioRequest;
import com.adjt.chefmanagerapi.model.TipoUsuarioResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@RestController
public class TipoUsuarioController implements TipoUsuarioApi {

    private final CadastrarTipoUsuario cadastrarTipoUsuarioUseCase;
    private final AtualizarTipoUsuario atualizarTipoUsuarioUseCase;
    private final BuscarTipoUsuarioPorId buscarTipoUsuarioPorIdUseCase;
    private final BuscarTodosTipoUsuario buscarTodosTipoUsuarioUseCase;
    private final DeletarTipoUsuario deletarTipoUsuarioUseCase;
    private final TipoUsuarioApiMapper mapper;

    public TipoUsuarioController(CadastrarTipoUsuario cadastrarTipoUsuarioUseCase,
                                 AtualizarTipoUsuario atualizarTipoUsuarioUseCase,
                                 BuscarTipoUsuarioPorId buscarTipoUsuarioPorIdUseCase,
                                 BuscarTodosTipoUsuario buscarTodosTipoUsuarioUseCase,
                                 DeletarTipoUsuario deletarTipoUsuarioUseCase,
                                 TipoUsuarioApiMapper mapper) {
        this.cadastrarTipoUsuarioUseCase = cadastrarTipoUsuarioUseCase;
        this.atualizarTipoUsuarioUseCase = atualizarTipoUsuarioUseCase;
        this.buscarTipoUsuarioPorIdUseCase = buscarTipoUsuarioPorIdUseCase;
        this.buscarTodosTipoUsuarioUseCase = buscarTodosTipoUsuarioUseCase;
        this.deletarTipoUsuarioUseCase = deletarTipoUsuarioUseCase;
        this.mapper = mapper;
    }


    @Override
    public ResponseEntity<List<TipoUsuarioResponse>> tiposUsuarioGet() {
        Iterable<BuscarTipoUsuarioOutput> tiposUsuarios = buscarTodosTipoUsuarioUseCase.executar(null);
        return ResponseEntity.status(HttpStatus.OK).body(StreamSupport.stream(tiposUsuarios.spliterator(), false)
                .map(mapper::toResponse)
                .toList());
    }

    @Override
    public ResponseEntity<Void> tiposUsuarioIdDelete(UUID id) {
        deletarTipoUsuarioUseCase.executar(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<TipoUsuarioResponse> tiposUsuarioIdGet(UUID id) {
        BuscarTipoUsuarioOutput tipoUsuario = buscarTipoUsuarioPorIdUseCase.executar(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(tipoUsuario));
    }

    @Override
    public ResponseEntity<TipoUsuarioResponse> tiposUsuarioIdPut(UUID id, TipoUsuarioRequest tipoUsuarioRequest) {
        AtualizarTipoUsuarioOutput tipoUsuario = atualizarTipoUsuarioUseCase.executar(mapper.toInput(id, tipoUsuarioRequest));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toResponse(tipoUsuario));
    }

    @Override
    public ResponseEntity<TipoUsuarioResponse> tiposUsuarioPost(TipoUsuarioRequest tipoUsuarioRequest) {
        CadastrarTipoUsuarioOutput tipoUsuario = cadastrarTipoUsuarioUseCase.executar(mapper.toInput(tipoUsuarioRequest));
        TipoUsuarioResponse response = mapper.toResponse(tipoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
