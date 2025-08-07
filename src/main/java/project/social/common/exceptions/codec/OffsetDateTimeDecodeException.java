package project.social.common.exceptions.codec;

import org.springframework.core.codec.CodecException;

public class OffsetDateTimeDecodeException extends CodecException {
    public OffsetDateTimeDecodeException(String message) {
        super(message);
    }
}
