package br.com.brendaStefany.aluraTech.repository;

import br.com.brendaStefany.aluraTech.domain.Registrations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationsRepository extends JpaRepository<Registrations, Long> {}
