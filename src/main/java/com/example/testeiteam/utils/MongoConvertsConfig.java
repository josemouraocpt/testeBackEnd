package com.example.testeiteam.utils;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;


@Configuration
public class MongoConvertsConfig {
    @Bean
    public MongoCustomConversions mongoCustomConversions(){
        return new MongoCustomConversions(List.of(
            new OffsetDateTimeReadConverter(),
            new OffsetDateTimeWriteConverter(),
            new LongWriteConverter(),
            new LongReadConverter()
        ));
    }
    
    static class OffsetDateTimeWriteConverter implements Converter<OffsetDateTime, String> {
        @Override
        public String convert(OffsetDateTime source) {
            return source.toString();
        }
    }

    static class OffsetDateTimeReadConverter implements Converter<String, OffsetDateTime> {
        @Override
        public OffsetDateTime convert(String source) {
            return OffsetDateTime.parse(source);
        }
    }

    static class LongWriteConverter implements  Converter<Long, String>{
        @Override
        public String convert(Long source){
            return source.toString();
        }
    }

    static class LongReadConverter implements  Converter<Long, Long>{
        @Override
        public Long convert(Long source){
            return  source;
        }
    }
    
}
