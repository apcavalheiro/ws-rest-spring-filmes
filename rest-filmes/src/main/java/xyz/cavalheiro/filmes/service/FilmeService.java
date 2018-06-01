package xyz.cavalheiro.filmes.service;

import  xyz.cavalheiro.filmes.domain.Filme;

import java.util.List;

public interface FilmeService {

    void save(Filme filme);
    void update(Long id, Filme filme);
    void delete(Long id);
    Filme findById(Long id);
    List<Filme> findAll();
}
