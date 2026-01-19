
package com.adjt.chefmanagerapi.core.domain.entities.cardapio;

import com.adjt.chefmanagerapi.core.exceptions.NomeObrigatorioException;
import com.adjt.chefmanagerapi.core.exceptions.DescricaoObrigatoriaException;
import com.adjt.chefmanagerapi.core.exceptions.PrecoObrigatorioOuInvalidoException;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
public class ItemCardapio {

    private final UUID id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private boolean consumoLocal;
    private String caminhoFoto; // opcional
    private UUID restauranteId;
    private OffsetDateTime dataUltimaAlteracao;


    public ItemCardapio(String nome, String descricao, BigDecimal preco,
                        boolean consumoLocal, String caminhoFoto, UUID restauranteId) {
        this(UUID.randomUUID(), nome, descricao, preco, consumoLocal, caminhoFoto, restauranteId, OffsetDateTime.now());
    }

    public ItemCardapio(UUID id, String nome, String descricao, BigDecimal preco,
                        boolean consumoLocal, String caminhoFoto, UUID restauranteId, OffsetDateTime dataUltimaAlteracao) {
        this.id = id;
        setNome(nome);
        setDescricao(descricao);
        setPreco(preco);
        this.consumoLocal = consumoLocal;
        this.caminhoFoto = (caminhoFoto == null || caminhoFoto.trim().isEmpty()) ? null : caminhoFoto.trim();
        this.restauranteId = restauranteId;
        this.dataUltimaAlteracao = (dataUltimaAlteracao != null) ? dataUltimaAlteracao : OffsetDateTime.now();
    }

    public void atualizarNome(String novoNome) {
        setNome(novoNome);
        atualizarDataAlteracao();
    }

    private void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new NomeObrigatorioException();
        this.nome = nome.trim();
    }

    public void atualizarDescricao(String novaDescricao) {
        setDescricao(novaDescricao);
        atualizarDataAlteracao();
    }

    private void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty())
            throw new DescricaoObrigatoriaException();
        this.descricao = descricao.trim();
    }

    public void atualizarPreco(BigDecimal novoPreco) {
        setPreco(novoPreco);
        atualizarDataAlteracao();
    }

    private void setPreco(BigDecimal preco) {
        if (preco == null || preco.compareTo(BigDecimal.ZERO) <= 0)
            throw new PrecoObrigatorioOuInvalidoException();
        this.preco = preco.setScale(2, RoundingMode.HALF_UP);
    }

    public void atualizarDisponibilidade(boolean apenasNoRestaurante) {
        this.consumoLocal = apenasNoRestaurante;
        atualizarDataAlteracao();
    }

    public void atualizarCaminhoFoto(String novoCaminhoFoto) {
        this.caminhoFoto = (novoCaminhoFoto == null || novoCaminhoFoto.trim().isEmpty())
                ? null
                : novoCaminhoFoto.trim();
        atualizarDataAlteracao();
    }

    public void atualizarDataAlteracao() {
        this.dataUltimaAlteracao = OffsetDateTime.now();
    }

    public void atualizarRestauranteId(UUID novoRestauranteId) {
        if (novoRestauranteId == null) throw new IllegalArgumentException("restauranteId é obrigatório");
        this.restauranteId = novoRestauranteId;
        atualizarDataAlteracao();
    }


}