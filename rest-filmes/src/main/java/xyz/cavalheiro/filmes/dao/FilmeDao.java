package xyz.cavalheiro.filmes.dao;

import xyz.cavalheiro.filmes.domain.Filme;

import java.util.List;

public interface FilmeDao {
    void save(Filme filme);
    void update(Filme filme);
    void delete(Long id);
    Filme findById(Long id);
    List<Filme> findAll();
}
