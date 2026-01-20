
package com.adjt.chefmanagerapi.core.domain.entities.cardapio;

import com.adjt.chefmanagerapi.core.exceptions.DescricaoObrigatoriaException;
import com.adjt.chefmanagerapi.core.exceptions.NomeObrigatorioException;
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
        this(UUID.randomUUID(), nome, descricao, preco, consumoLocal, caminhoFoto, restauranteId);
    }

    @SuppressWarnings("java:S107")
    public static ItemCardapio reconstituir(
            UUID id,
            String nome,
            String descricao,
            BigDecimal preco,
            boolean consumoLocal,
            String caminhoFoto,
            UUID restauranteId,
            OffsetDateTime dataUltimaAlteracao
    ) {
        ItemCardapio item = new ItemCardapio(id, nome, descricao, preco, consumoLocal, caminhoFoto, restauranteId);
        item.dataUltimaAlteracao = (dataUltimaAlteracao != null) ? dataUltimaAlteracao : OffsetDateTime.now();
        return item;
    }


    public ItemCardapio(UUID id, String nome, String descricao, BigDecimal preco,
                        boolean consumoLocal, String caminhoFoto, UUID restauranteId) {
        this.id = id;

        // Setters privados (não atualizam a data)
        setNome(nome);
        setDescricao(descricao);
        setPreco(preco);
        setConsumoLocal(consumoLocal);
        setCaminhoFoto(caminhoFoto);
        setRestauranteId(restauranteId);

        // Define a data de criação/última alteração uma única vez na construção
        this.dataUltimaAlteracao = OffsetDateTime.now();
    }

    // ========= Atualizações públicas (alteram a data) =========

    public void atualizarNome(String novoNome) {
        setNome(novoNome);
        atualizarDataAlteracao();
    }

    public void atualizarDescricao(String novaDescricao) {
        setDescricao(novaDescricao);
        atualizarDataAlteracao();
    }

    public void atualizarPreco(BigDecimal novoPreco) {
        setPreco(novoPreco);
        atualizarDataAlteracao();
    }

    public void atualizarDisponibilidade(boolean apenasNoRestaurante) {
        setConsumoLocal(apenasNoRestaurante);
        atualizarDataAlteracao();
    }

    public void atualizarCaminhoFoto(String novoCaminhoFoto) {
        setCaminhoFoto(novoCaminhoFoto);
        atualizarDataAlteracao();
    }

    public void atualizarRestaurante(UUID novoRestauranteId) {
        setRestauranteId(novoRestauranteId);
        atualizarDataAlteracao();
    }

    public void atualizarDataAlteracao() {
        this.dataUltimaAlteracao = OffsetDateTime.now();
    }

    // ========= Setters privados (garantem invariantes, NÃO atualizam data) =========

    private void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty())
            throw new NomeObrigatorioException();
        this.nome = nome.trim();
    }

    private void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty())
            throw new DescricaoObrigatoriaException();
        this.descricao = descricao.trim();
    }

    private void setPreco(BigDecimal preco) {
        if (preco == null || preco.compareTo(BigDecimal.ZERO) <= 0)
            throw new PrecoObrigatorioOuInvalidoException();
        this.preco = preco.setScale(2, RoundingMode.HALF_UP);
    }

    private void setConsumoLocal(boolean consumoLocal) {
        this.consumoLocal = consumoLocal;
    }

    private void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = (caminhoFoto == null || caminhoFoto.trim().isEmpty())
                ? null
                : caminhoFoto.trim();
    }

    private void setRestauranteId(UUID restauranteId) {
        this.restauranteId = restauranteId;
    }
}
