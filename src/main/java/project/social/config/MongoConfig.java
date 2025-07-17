package project.social.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.lang.NonNull;
import project.social.common.codecs.OffsetDateTimeCodec;
import project.social.config.converters.DateToOffsetDateTimeConverter;
import project.social.config.converters.OffsetDateTimeToDateConverter;

import java.util.List;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    @NonNull
    protected String getDatabaseName() {
        return "mus_db";
    }

    @Override
    @Bean
    @NonNull
    public MongoClient mongoClient() {
        CodecRegistry defaultCodecRegistry = MongoClientSettings.getDefaultCodecRegistry();
        CodecRegistry customCodecRegistry = CodecRegistries.fromRegistries(
                CodecRegistries.fromCodecs(new OffsetDateTimeCodec()),
                defaultCodecRegistry
        );

        MongoClientSettings settings = MongoClientSettings.builder()
                .codecRegistry(customCodecRegistry)
                .build();

        return MongoClients.create(settings);
    }

    @Bean
    public @NotNull MongoCustomConversions customConversions() {
        return new MongoCustomConversions(List.of(
                new DateToOffsetDateTimeConverter(),
                new OffsetDateTimeToDateConverter()
        ));
    }


    @Override
    protected boolean autoIndexCreation() {
        return true;
    }
}
