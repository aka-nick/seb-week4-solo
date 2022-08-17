package seb.week4solo.week4solo.domain;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity
public class Region {

    @Id
    @Column(name = "region_code")
    private Long regionCode;

    @Column(nullable = false)
    private String regionName;

    protected Region() {
    }

    public Region(Long regionCode, String regionName) {
        this.regionCode = regionCode;
        this.regionName = regionName;
    }
}
