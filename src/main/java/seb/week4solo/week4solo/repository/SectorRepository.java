package seb.week4solo.week4solo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import seb.week4solo.week4solo.domain.Sector;

@Repository
public interface SectorRepository extends JpaRepository<Sector, Long> {
}
