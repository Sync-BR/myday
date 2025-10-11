package com.github.sync.myday.mapper.imp;

import java.util.List;

public interface MapperImp<E,D> {
    D convertToDto(E object);
    E convertToEntity(D object);

}
