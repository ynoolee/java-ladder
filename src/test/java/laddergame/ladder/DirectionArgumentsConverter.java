package laddergame.ladder;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

class DirectionArgumentsConverter implements ArgumentConverter {

    @Override
    public Object convert(final Object source, final ParameterContext context) throws ArgumentConversionException {
        if (!(source instanceof String)) {
            throw new IllegalArgumentException(
                "The argument should be a string: " + source);
        }
        Direction result = null;
        try {
            String direction = (String) source;

            switch (direction) {
                case "NONE":
                    result = Direction.NONE;
                    break;
                case "LEFT":
                    result = Direction.LEFT;
                    break;
                case "RIGHT":
                    result = Direction.RIGHT;
                    break;
            }
        } catch (Exception ex) {

        }
        return result;
    }
}
