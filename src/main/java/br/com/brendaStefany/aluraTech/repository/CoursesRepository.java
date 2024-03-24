package br.com.brendaStefany.aluraTech.repository;

import br.com.brendaStefany.aluraTech.domain.Courses;

import br.com.brendaStefany.aluraTech.domain.CoursesStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, String>{
    Optional<Courses> findByCode(String code);
    Page<Courses> findByStatus(CoursesStatus status, Pageable pageable);

    @Query("SELECT c.status FROM Courses c WHERE c.code = :code")
    Optional<CoursesStatus> findStatusByCode(@Param("code") String code);

    Page<Courses> findAll(Pageable pageable);

}
