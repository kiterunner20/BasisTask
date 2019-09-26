package com.task.basis.core;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.Reader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/*
Created a custom GsonConverter factory class .Out of each charector recieved unneccesory slash
is removed and pass it as a proper responsel
*/
public class CustomConverterFactory extends Converter.Factory {

    private final Gson gson;

    private CustomConverterFactory(Gson gson) {
        this.gson = gson;
    }

    public static CustomConverterFactory create(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        return new CustomConverterFactory(gson);
    }


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return (Converter<ResponseBody, Object>) value -> {
            Reader reader = value.charStream();
            //Skipping the slash charector
            reader.skip(1);
            JsonReader jsonReader = gson.newJsonReader(reader);
            try {
                return adapter.read(jsonReader);
            } finally {
                value.close();
            }
        };
    }


}
