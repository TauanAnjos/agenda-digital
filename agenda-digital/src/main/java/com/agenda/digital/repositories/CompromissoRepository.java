package com.agenda.digital.repositories;

import com.agenda.digital.models.CompromissoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CompromissoRepository extends JpaRepository<CompromissoModel, Long> {
    @Query("SELECT cp FROM CompromissoModel cp " +
            "JOIN cp.usuario u " +
            "WHERE u.id = :user_id ")
    List<CompromissoModel> findAllByUserId(Long user_id);
}
