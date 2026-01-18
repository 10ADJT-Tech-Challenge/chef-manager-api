
package com.adjt.chefmanagerapi.infrastructure.config;

import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGateway;
import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGatewayImpl;
import com.adjt.chefmanagerapi.core.gateways.cardapio.ItemCardapioGatewayMapper;
import com.adjt.chefmanagerapi.core.gateways.interfaces.ItemCardapioRepositoryGateway;

import com.adjt.chefmanagerapi.core.gateways.restaurante.RestauranteGateway;
import com.adjt.chefmanagerapi.core.gateways.restaurante.RestauranteGatewayImpl;
import com.adjt.chefmanagerapi.core.gateways.interfaces.RestauranteRepositoryGateway;
import com.adjt.chefmanagerapi.core.gateways.restaurante.RestauranteGatewayMapper;

import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGateway;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGatewayImpl;
import com.adjt.chefmanagerapi.core.gateways.interfaces.UsuarioRepositoryGateway;
import com.adjt.chefmanagerapi.core.gateways.usuario.UsuarioGatewayMapper;

import com.adjt.chefmanagerapi.infrastructure.dataprovider.cardapio.ItemCardapioPersistenceMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewaysConfig {

    // =========================
    // Cardápio
    // =========================
    @Bean
    public ItemCardapioGateway itemCardapioGateway(ItemCardapioRepositoryGateway repo,
                                                   ItemCardapioPersistenceMapper mapper) {
        // Impl do core (sem @Component)
        return new ItemCardapioGatewayImpl(repo, mapper);
    }

    /**
     * Caso o seu ItemCardapioGatewayMapper NÃO seja @Component no core,
     * descomente este @Bean para expor o mapper como bean Spring.
     */
    // @Bean
    // public ItemCardapioGatewayMapper itemCardapioGatewayMapper() {
    //     return new ItemCardapioGatewayMapper();
    // }

    // =========================
    // Restaurante
    // =========================
    @Bean
    public RestauranteGateway restauranteGateway(RestauranteRepositoryGateway repo,
                                                 RestauranteGatewayMapper mapper) {
        return new RestauranteGatewayImpl(repo, mapper);
    }

    /**
     * Caso o seu RestauranteGatewayMapper NÃO seja @Component no core,
     * descomente este @Bean para expor o mapper como bean Spring.
     */
    // @Bean
    // public RestauranteGatewayMapper restauranteGatewayMapper() {
    //     return new RestauranteGatewayMapper();
    // }

    // =========================
    // Usuário
    // =========================
    @Bean
    public UsuarioGateway usuarioGateway(UsuarioRepositoryGateway repo,
                                         UsuarioGatewayMapper mapper) {
        return new UsuarioGatewayImpl(repo, mapper);
    }

    /**
     * Caso o seu UsuarioGatewayMapper NÃO seja @Component no core,
     * descomente este @Bean para expor o mapper como bean Spring.
     */
    // @Bean
    // public UsuarioGatewayMapper usuarioGatewayMapper() {
    //     return new UsuarioGatewayMapper();
    // }
}
