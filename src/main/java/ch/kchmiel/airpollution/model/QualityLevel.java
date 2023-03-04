package ch.kchmiel.airpollution.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum QualityLevel {

    VERY_G0OD("Bardzo dobry", 0),
    GOOD("Dobry", 1),
    MODERATE("Umiarkowany", 2),
    SUFFICIENT("Dostateczny", 3),
    BAD("Zły", 4),
    VERY_BAD("Bardzo zły", 5),
    UNDEFINED("Brak indeksu", -1);

    private String description;

    private int index;

    QualityLevel(String description, int index) {
        this.description = description;
        this.index = index;
    }

    public static QualityLevel getByIndex(int index) {
        return Arrays.stream(QualityLevel.values()).filter(x -> x.index == index).findFirst().orElseThrow(() -> new IllegalStateException("Wrong quality level index=" + index));
    }

}
