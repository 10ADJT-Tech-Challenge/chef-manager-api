package com.adjt.chefmanagerapi.core.usecases.common;

public interface UseCase<I, O> {
    O executar(I input);
}
