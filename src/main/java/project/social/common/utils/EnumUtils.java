package project.social.common.utils;

import java.util.Optional;

public class EnumUtils {

    /**
     * Safely retrieves an enum constant by its name, ignoring case.
     * Returns an empty Optional if the name does not match any constant.
     *
     * @param enumClass the class of the enum
     */
    public static <E extends Enum<E>> Optional<E> safeValueOf(Class<E> enumClass, String name) {
        try {
            return Optional.of(Enum.valueOf(enumClass, name.toUpperCase()));
        } catch (IllegalArgumentException | NullPointerException e) {
            return Optional.empty();
        }
    }
}
