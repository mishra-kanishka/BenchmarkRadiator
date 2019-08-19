package com.kanishka.scoreRadiator.repositories;

import com.kanishka.scoreRadiator.components.BenchMarkDAO;
import com.kanishka.scoreRadiator.domain.Benchmark;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

@Component
public class BenchMarkRepository {

    final BenchMarkDAO dao;

    @Inject
    public BenchMarkRepository(BenchMarkDAO dao) {
        this.dao = dao;
    }

    public List<Benchmark> getScores() {
        return dao.getDataFromDb();
    }
}
