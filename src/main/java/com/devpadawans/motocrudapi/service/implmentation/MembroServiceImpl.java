package com.devpadawans.motocrudapi.service.implmentation;

import com.devpadawans.motocrudapi.model.Membro;
import com.devpadawans.motocrudapi.repository.MembroRepository;
import com.devpadawans.motocrudapi.service.MembroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MembroServiceImpl implements MembroService {

    private final MembroRepository membroRepository;

    @Override
    public Membro save(Membro entity) {
        return membroRepository.save(entity);
    }

    @Override
    public void update(Membro entity) {

    }

    @Override
    public void delete(Membro entity) {

    }

    @Override
    public Optional<Membro> findById(Long id) {
        return membroRepository.findById(id);
    }

    @Override
    public List<Membro> findAll() {
        return membroRepository.findAll();
    }

    @Override
    public Page<Membro> paginate(Pageable pageable) {
        return null;
    }
}
