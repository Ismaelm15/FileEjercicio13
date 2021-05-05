/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.fileej13_ismael;

/**
 *
 * @author ismae
 */
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    protected LocalDateDeserializer() {//constructor
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser parser, DeserializationContext context) throws IOException {//deserializador de fechas
        return LocalDate.parse(parser.readValueAs(String.class));
    }
}