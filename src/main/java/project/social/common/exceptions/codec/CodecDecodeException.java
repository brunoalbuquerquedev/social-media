package project.social.common.exceptions.codec;

import org.springframework.core.codec.CodecException;

public class CodecDecodeException extends CodecException {
    public CodecDecodeException(String message) {
        super(message);
    }
}
