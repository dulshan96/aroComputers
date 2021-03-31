package lk.aro_computers.asset.common_asset.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CivilStatus {
    MARRIED("Married"),
    SINGLE("Single"),
    WIDOW("Widow");

    private final String civilStatus;
}
