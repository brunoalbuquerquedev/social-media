package project.social.common.utils;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class UrlDecoder {

    public static String decodeParam(String text) {
        return URLDecoder.decode(text, StandardCharsets.UTF_8);
    }
}
