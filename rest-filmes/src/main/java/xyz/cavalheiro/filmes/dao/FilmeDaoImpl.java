package xyz.cavalheiro.filmes.dao;

import xyz.cavalheiro.filmes.domain.Filme;
import xyz.cavalheiro.filmes.exception.NaoExisteDaoException;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class FilmeDaoImpl implements FilmeDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Filme filme) {
        em.persist(filme);
    }

    @Override
    public void update(Filme filme) {
        em.merge(filme);
    }

    @Override
    public void delete(Long id) {
        try {
            em.remove(em.getReference(Filme.class,id));
        }catch (EntityNotFoundException ex){
            throw new NaoExisteDaoException("Filme não encontrado para id = " + id + ".");
        }

    }

    @Override
    public Filme findById(Long id) {
        Filme filme = em.find(Filme.class,id);
        if(filme == null){
            throw new NaoExisteDaoException("Filme não encontrado para id = " + id + ".");

        }
        return filme;
    }

    @Override
    public List<Filme> findAll() {
        return em
                .createQuery("select f from Filme f", Filme.class)
                .getResultList();
    }
}
