package com.example.demoapp.services;

import com.example.demoapp.entities.Adn;
import com.example.demoapp.entities.dto.StatsDto;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AdnService extends BaseService<Adn,Long>{
    Adn saveAdn(List<String> adn) throws Exception;
    Optional<Adn> findByAdnValue(@Param("adnValue") String adnValue) throws Exception;
    StatsDto getStats() throws Exception;

}
