package com.capstone.alta.hms.api.v1.patients.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class BloodTypeConverter implements AttributeConverter<BloodType, String> {
    @Override
    public String convertToDatabaseColumn(BloodType bloodType) {
        if (bloodType == null) {
            return null;
        }
        return bloodType.getCode();
    }

    @Override
    public BloodType convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(BloodType.values())
            .filter(bloodType -> bloodType.getCode().equals(code))
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }
}