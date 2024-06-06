package com.acqualand.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acqualand.demo.model.Attrazione;
import com.acqualand.demo.repository.AttrazioneRepository;

@Service
public class AttrazioneService {
    @Autowired
    private AttrazioneRepository attrazioneRepository;

    public List<Attrazione> getAllOpenAttractions() {
        return attrazioneRepository.findByIsOpenTrue();
    }
}