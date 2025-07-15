package project.social.codecs;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import project.social.exceptions.codec.OffsetDateTimeDecodeException;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

public class OffsetDateTimeCodec implements Codec<OffsetDateTime> {

    @Override
    public OffsetDateTime decode(BsonReader bsonReader, DecoderContext context) {
        long millis = bsonReader.readDateTime();
        Optional<OffsetDateTime> date = Optional.of(
                OffsetDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneOffset.UTC)
        );
        return date.orElseThrow(() -> new OffsetDateTimeDecodeException("Date decoding error."));
    }

    @Override
    public void encode(BsonWriter bsonWriter, OffsetDateTime value, EncoderContext encoderContext) {
        bsonWriter.writeDateTime(value.toInstant().toEpochMilli());
    }

    @Override
    public Class<OffsetDateTime> getEncoderClass() {
        return OffsetDateTime.class;
    }
}
