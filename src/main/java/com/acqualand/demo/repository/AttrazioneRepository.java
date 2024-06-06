package com.acqualand.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.acqualand.demo.model.Attrazione;
@Repository
public interface AttrazioneRepository extends JpaRepository<Attrazione, Long> {
    List<Attrazione> findByIsOpenTrue();
}
