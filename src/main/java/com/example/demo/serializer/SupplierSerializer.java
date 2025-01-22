package com.example.demo.serializer;

import com.example.demo.model.Supplier;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Set;

public class SupplierSerializer extends JsonSerializer<Set<Supplier>> {
    @Override
    public void serialize(Set<Supplier> suppliers, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartArray();
        for (Supplier supplier : suppliers) {
            jsonGenerator.writeNumber(supplier.getId()); // Сериализация только ID
        }
        jsonGenerator.writeEndArray();
    }
}
