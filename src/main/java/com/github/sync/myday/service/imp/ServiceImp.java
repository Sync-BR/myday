package com.github.sync.myday.service.imp;

public interface ServiceImp<E> {
    void save(E entity);
    void update(E entity);
    void disable(E entity);
    void enable(E entity);
    void delete(E entity);
}
