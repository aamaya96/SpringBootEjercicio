package com.agilesolutions.exersice2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractService<T,K>{

    private final JpaRepository repository;
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected AbstractService(JpaRepository repository) {
        this.repository = repository;
    }

    public T create(T entity){
        T saved = (T) repository.save(entity);
        logger.info(String.format("New row type %s added", saved.getClass().getSimpleName()), saved.getClass().getSimpleName());
        return saved;
    }

    public  T update(T entity) throws EntityNotFoundException{

        T saved = (T) repository.save(entity);
        logger.info(String.format("Row type %s updated", saved.getClass().getSimpleName()));
        return saved;

    }

    public boolean delete(K id) throws EntityNotFoundException{
        if(repository.exists((Serializable) id)) {
            repository.delete((Serializable) id);
            logger.info(String.format("Row deleted"));
        }else{
            throw new EntityNotFoundException(id.toString());
        }
        return true;
    }

    public  T findById(K id) throws  EntityNotFoundException{
        if(repository.exists((Serializable) id)){
            return (T) repository.findOne((Serializable) id);

        }else{
            throw new EntityNotFoundException(id.toString());
        }
    }

    public  List<T> getAll(){
        return repository.findAll();
    }
}
