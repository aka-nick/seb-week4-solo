package seb.week4solo.week4solo.util;

import org.springframework.stereotype.Service;
import seb.week4solo.week4solo.domain.Region;
import seb.week4solo.week4solo.repository.RegionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RegionUtils {

    private final RegionRepository regionRepository;

    public RegionUtils(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    public Optional<Region> find(Long regionCode) {
        return regionRepository.findById(regionCode);
    }

    public List<Region> findAll() {
        return regionRepository.findAll();
    }
}
