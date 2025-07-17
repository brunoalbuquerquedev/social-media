package project.social.common.codecs;

import lombok.RequiredArgsConstructor;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import project.social.common.exceptions.codec.CodecDecodeException;
import project.social.common.utils.EnumUtils;

@RequiredArgsConstructor
public class EnumCodec<T extends Enum<T>> implements Codec<T> {

    private final Class<T> enumType;

    @Override
    public T decode(BsonReader bsonReader, DecoderContext decoderContext) {
        String value = bsonReader.readString();
        return EnumUtils.safeValueOf(enumType, value)
                .orElseThrow(() -> new CodecDecodeException("Enum decoding error."));
    }

    @Override
    public void encode(BsonWriter bsonWriter, T value, EncoderContext encoderContext) {
        bsonWriter.writeString(value.name());
    }

    @Override
    public Class<T> getEncoderClass() {
        return enumType;
    }
}
