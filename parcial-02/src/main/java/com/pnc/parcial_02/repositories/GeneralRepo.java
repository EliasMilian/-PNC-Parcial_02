package com.pnc.parcial_02.repositories;

import org.hibernate.sql.results.graph.entity.internal.DiscriminatedEntityFetch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import  java.util.Optional;

@NoRepositoryBean
public interface GeneralRepo <T, ID> extends JpaRepository<T,ID> {

    default public T findByIdOrThrow (ID id) throws Exception {
        return findById(id).orElseThrow(() -> new Exception(getClassName() + "model not found"));
    }

    private String getClassName() {
        return this.getClass()
                .getGenericInterfaces()[0]
                .getTypeName()
                .replace("com.practica.parcial2.repositories.GenericRepository<", "")
                .replace(">", "");
    }


}