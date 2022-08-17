package seb.week4solo.week4solo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import seb.week4solo.week4solo.domain.Member;
import seb.week4solo.week4solo.domain.Region;
import seb.week4solo.week4solo.domain.Sector;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByUsername(String username);

    Page<Member> findAllBySectorAndRegion(Pageable pageable, Sector sectorCode, Region regionCode);

    Page<Member> findAllBySector(Pageable pageable, Sector sector);

    Page<Member> findAllByRegion(Pageable pageable, Region region);
}
