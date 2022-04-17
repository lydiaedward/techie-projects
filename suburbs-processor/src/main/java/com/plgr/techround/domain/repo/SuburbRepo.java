package com.plgr.techround.domain.repo;

import com.plgr.techround.domain.entity.Suburb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Suburbs Repo
 */
@Repository
public interface SuburbRepo extends JpaRepository<Suburb, Integer> {

    @Query(value="select name from Suburb where postCode between :startcode and :endcode")
    List<String> findByPostCodeRange(@Param("startcode") Integer startcode, @Param("endcode") Integer endcode);

}
