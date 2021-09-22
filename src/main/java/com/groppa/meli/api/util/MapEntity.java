package com.groppa.meli.api.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MapEntity {
    final static ModelMapper modelMapper = new ModelMapper();

    public static <E> E map(Object source, Class<E> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    public static <E> E map(Object source, Type destinationType) {
        return modelMapper.map(source, destinationType);
    }

    public static <E> void map(Object source, E destinationObject) {
        modelMapper.map(source, destinationObject);
    }
}
