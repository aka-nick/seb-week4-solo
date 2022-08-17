package seb.week4solo.week4solo.domain;

import lombok.Getter;

import java.util.List;

@Getter
public enum Gender {

    MALE(1, "GENDER_MALE", "남자"),
    FEMALE(2, "GENDER_FEMALE", "여자"),
    NO_SELECTION(3, "GENDER_NO_SELECTION", "선택하지 않음"),
    ;

    private final int genderNumber;
    private final String genderString;
    private final String genderKoreanString;

    Gender(int genderNumber, String genderString, String genderKoreanString) {
        this.genderNumber = genderNumber;
        this.genderString = genderString;
        this.genderKoreanString = genderKoreanString;
    }

}
