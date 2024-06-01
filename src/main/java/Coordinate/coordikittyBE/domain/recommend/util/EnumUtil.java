package Coordinate.coordikittyBE.domain.recommend.util;

public class EnumUtil {
    public static <T extends Enum<T>> boolean isInEnum(String value, Class<T> enumClass) {
        for (T enumValue : enumClass.getEnumConstants()) {
            if (enumValue.name().equals(value)) {
                return true;
            }
        }
        return false;
    }
}
