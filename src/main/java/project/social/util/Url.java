package project.social.util;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class Url {

    public static String decodeParam(String text) {
        return URLDecoder.decode(text, StandardCharsets.UTF_8);
    }
}
