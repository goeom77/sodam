package com.samsung.sodam.db.converter;


import javax.persistence.AttributeConverter;
import java.util.Arrays;

public abstract class EntityConverter<X extends Enum<X> & BaseEnumCode<Y,Z>, Y,Z> implements AttributeConverter<X, Y> {

    protected abstract X[] getValueList();

    protected abstract Z[] getNameList();

    @Override
    public Y convertToDatabaseColumn(X attribute) {
        if(attribute==null) return null;
        return attribute.getValue();
    }

    @Override
    public X convertToEntityAttribute(Y dbData) {
        return Arrays.stream(getValueList())
                .filter(e -> e.getValue().equals(dbData))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Unsupported type for %s.", dbData)));
    }
}