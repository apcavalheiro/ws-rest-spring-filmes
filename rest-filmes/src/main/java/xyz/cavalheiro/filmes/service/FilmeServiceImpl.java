package xyz.cavalheiro.filmes.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.cavalheiro.filmes.dao.FilmeDao;
import xyz.cavalheiro.filmes.domain.Filme;
import xyz.cavalheiro.filmes.exception.IdNaoValidoServiceException;

import java.util.List;

@Service
@Transactional
public class FilmeServiceImpl implements FilmeService{

    @Autowired
    private FilmeDao dao;
    @Override
    public void save(Filme filme) {
        dao.save(filme);
    }

    @Override
    public void update(Long id, Filme filme) {
        filme.setId(idValido(id));
        dao.findById(id);
        dao.update(filme);
    }

    @Override
    public void delete(Long id) {
        dao.delete(idValido(id));
    }

    @Override @Transactional(readOnly = true)
    public Filme findById(Long id) {
        return dao.findById(idValido(id));
    }

    @Override @Transactional(readOnly = true)
    public List<Filme> findAll() {
        return dao.findAll();
    }

    private Long idValido(Long id) {
        if (id <= 0) {
            throw new IdNaoValidoServiceException("Valor do campo id está inválido. Deve ser um valor inteiro maior que zero.");
        }
        return id;
    }
}
