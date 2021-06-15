package com.testol.auto_cher.Dao;

import com.testol.auto_cher.Enity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;


@Repository
public interface TestCaseDao extends JpaRepository<TestCase,Long> {
    @Transactional
    @Modifying
    @Query( "UPDATE TestCase SET actual = :actual WHERE id = :id" )
    void updateActual(@Param("actual") String actual, @Param("id") Long id);
}
