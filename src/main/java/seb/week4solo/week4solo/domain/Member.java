package seb.week4solo.week4solo.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, unique = true, updatable = false)
    public String username;

    @Column(nullable = false)
    public String password;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
//    public Role role;
    public String role;

    @Column(nullable = false)
//    public Gender gender;
    public int gender;

    @Column(nullable = false)
    public String companyName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sector_code")
    public Sector sector;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "region_code")
    public Region region;

    @Builder
    protected Member(String username, String password, String name, String role, int gender, String companyName, Sector sector, Region region) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role;
        this.gender = gender;
        this.companyName = companyName;
        this.sector = sector;
        this.region = region;
    }
}
