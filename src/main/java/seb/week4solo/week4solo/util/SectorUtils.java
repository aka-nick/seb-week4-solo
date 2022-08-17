package seb.week4solo.week4solo.util;

import org.springframework.stereotype.Service;
import seb.week4solo.week4solo.domain.Sector;
import seb.week4solo.week4solo.repository.SectorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SectorUtils {

    private final SectorRepository sectorRepository;

    public SectorUtils(SectorRepository sectorRepository) {
        this.sectorRepository = sectorRepository;
    }

    public Optional<Sector> find(Long sectorCode) {
        return sectorRepository.findById(sectorCode);
    }

    public List<Sector> findAll() {
        return sectorRepository.findAll();
    }
}
