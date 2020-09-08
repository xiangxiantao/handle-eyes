package com.eyeson.handle.web.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Configuration
public class EyesWebMvcConfig implements WebMvcConfigurer {

    @Bean
    public GsonHttpMessageConverter gsonHttpMessageConverter(){
        Gson gson = new GsonBuilder()
                .serializeNulls()  //将空属性序列化
                .enableComplexMapKeySerialization()
                .registerTypeAdapterFactory(new HttpGsonTypeAdapterFactory())    //添加指定类型自定义的gson序列化方式
                .registerTypeAdapter(Json.class, new SpringfoxJsonToGsonAdapter())
                .setDateFormat("yyyy-MM-dd HH:mm:ss")  //指定日期类型(只支持Date，不支持localDate)的序列化和反序列化格式
                .create();
        return new GsonHttpMessageConverter(gson);
    }

    /**
     * 添加一个gsonHttpMessageConverter并放置于其他HttpMessageConverter的最上层，
     * 搭配ResponseBodyAdvice实现RESTful接口全局统一响应格式
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(gsonHttpMessageConverter());
    }

    /**
     * gson类型转换器注册工厂
     */
    public class HttpGsonTypeAdapterFactory implements TypeAdapterFactory{

        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            Class<T> rawType = (Class<T>) type.getRawType();
            if(LocalDate.class ==rawType){
                return (TypeAdapter<T>) new LocalDateAdapter();
            }
            if (LocalDateTime.class == rawType){
                return (TypeAdapter<T>) new LocalDateTimeAdapter();
            }
            return null;
        }
    }

    /**
     * LocalDate类型的gson转换器
     */
    public class LocalDateAdapter extends TypeAdapter<LocalDate>{

        DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        @Override
        public void write(JsonWriter out, LocalDate value) throws IOException {
            if (value == null){
                out.nullValue();
            }else {
                out.value(value.format(DEFAULT_DATE_FORMATTER));
            }

        }

        @Override
        public LocalDate read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            } else {
                return LocalDate.parse(in.nextString(), DEFAULT_DATE_FORMATTER);
            }
        }
    }

    /**
     * LocalDateTime类型的gson转换器
     */
    public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime>{

        DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        @Override
        public void write(JsonWriter out, LocalDateTime value) throws IOException {
            if (value == null){
                out.nullValue();
            }else {
                out.value(value.format(DEFAULT_DATE_TIME_FORMATTER));
            }
        }

        @Override
        public LocalDateTime read(JsonReader in) throws IOException {
            if (in.peek() == JsonToken.NULL) {
                in.nextNull();
                return null;
            } else {
                return LocalDateTime.parse(in.nextString(), DEFAULT_DATE_TIME_FORMATTER);
            }
        }
    }

    /**
     * swagger json的gson输出格式适配器
     */
    public class SpringfoxJsonToGsonAdapter implements JsonSerializer<Json>{

        @Override
        public JsonElement serialize(Json src, Type typeOfSrc, JsonSerializationContext context) {
            final JsonParser parser = new JsonParser();
            return parser.parse(src.value());
        }
    }
}
