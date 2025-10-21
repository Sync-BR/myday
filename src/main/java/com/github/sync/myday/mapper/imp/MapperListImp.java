package com.github.sync.myday.mapper.imp;

import java.util.List;

public interface MapperListImp <E,D>{
    List<E> convertListToEntity(List<D> dtoList);
    List<D> convertEntityToDTO(List<E> dtoList);
}
