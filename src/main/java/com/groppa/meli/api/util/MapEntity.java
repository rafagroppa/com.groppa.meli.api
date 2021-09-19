package com.groppa.meli.api.util;

import org.modelmapper.ModelMapper;

public class MapEntity {
    final static ModelMapper modelMapper = new ModelMapper();

    public static <E> E map(Object source, Class<E> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    public static <E> void map(Object source, E destinationObject) {
        modelMapper.map(source, destinationObject);
    }
}
