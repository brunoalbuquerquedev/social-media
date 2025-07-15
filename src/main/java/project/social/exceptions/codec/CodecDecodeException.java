package project.social.exceptions.codec;

import org.springframework.core.codec.CodecException;

public class CodecDecodeException extends CodecException {
    public CodecDecodeException(String message) {
        super(message);
    }
}
