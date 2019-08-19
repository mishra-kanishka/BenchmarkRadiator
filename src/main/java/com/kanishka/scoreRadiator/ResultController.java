package com.kanishka.scoreRadiator;

import com.kanishka.scoreRadiator.domain.Benchmark;
import com.kanishka.scoreRadiator.repositories.BenchMarkRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
public class ResultController {

    private final BenchMarkRepository repo;

    @Inject
    public ResultController(BenchMarkRepository repo) {
        this.repo = repo;
    }

    @RequestMapping("/getBenchmarkScore")
    public List<Benchmark> getScores() {
        return repo.getScores();
    }

}

