package pe.gob.minsa.microservicio.enums;

public enum SearchParamTypeEnum {
    NUM_PARAM("N-"),
    STRING_PARAM("S-"),
    DATE_TIME_PARAM("D-"),
    BOOLEAN_PARAM("B-");

    private final String type;

    SearchParamTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static SearchParamTypeEnum valueOfType(String label) {
        for (SearchParamTypeEnum searchParamTypeEnum : values())
            if (searchParamTypeEnum.type.equals(label))
                return searchParamTypeEnum;
        return null;
    }
}
