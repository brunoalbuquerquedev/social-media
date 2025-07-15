package project.social.exceptions.codec;

import org.springframework.core.codec.CodecException;

public class OffsetDateTimeDecodeException extends CodecException {
    public OffsetDateTimeDecodeException(String message) {
        super(message);
    }
}
