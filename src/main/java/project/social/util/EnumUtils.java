package project.social.util;

import java.util.Optional;

public class EnumUtils {

    public static <E extends Enum<E>> Optional<E> safeValueOf(Class<E> enumClass, String name) {
        try {
            return Optional.of(Enum.valueOf(enumClass, name.toUpperCase()));
        } catch (IllegalArgumentException | NullPointerException e) {
            return Optional.empty();
        }
    }
}
