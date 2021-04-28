package lk.aro_computers.asset.item.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MainCategory {

    COMPUTERS("Computers"),
    COMPUTER_ACCESSORIES("Computer Accessories"),
    GAMING_DEVICES("Gaming Devices"),;


    private final String mainCategory;
}
