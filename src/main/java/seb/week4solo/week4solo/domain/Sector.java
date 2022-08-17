package seb.week4solo.week4solo.domain;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
public class Sector {

    @Id
    @Column(name = "sector_code")
    private Long sectorCode;

    @Column(nullable = false)
    private String sectorName;

    protected Sector() {
    }

    public Sector(Long sectorCode, String sectorName) {
        this.sectorCode = sectorCode;
        this.sectorName = sectorName;
    }
}
