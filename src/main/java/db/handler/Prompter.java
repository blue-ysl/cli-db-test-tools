package db.handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Prompter
{
    private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public Map<String, Object> prompt(Class<?> clazz) throws IOException
    {
        List<Field> fields = collectFields(clazz);
        Map<String, Object> values = new LinkedHashMap<>();
        for (Field field : fields)
        {
            System.out.print("Enter " + field.getName() + " (" + field.getType().getSimpleName() + "): ");
            String input = reader.readLine().trim();
            values.put(field.getName(), convertValue(input, field.getType()));
        }

        return values;
    }

    private List<Field> collectFields(Class<?> clazz)
    {
        List<Class<?>> hierarchy = new ArrayList<>();
        Class<?> current = clazz;

        while (current != null && current != Object.class)
        {
            hierarchy.add(0, current);
            current = current.getSuperclass();
        }

        List<Field> fields = new ArrayList<>();
        for (Class<?> c : hierarchy)
        {
            for (Field field : c.getDeclaredFields())
            {
                if (!Modifier.isStatic(field.getModifiers()))
                    fields.add(field);
            }
        }

        return fields;
    }

    private Object convertValue(String input, Class<?> type)
    {
        if (type == String.class)
            return input;

        if (type == int.class || type == Integer.class)
            return Integer.parseInt(input);

        if (type == boolean.class || type == Boolean.class)
            return Boolean.parseBoolean(input);

        if (type.isEnum())
        {
            for (Object constant : type.getEnumConstants())
            {
                if (((Enum<?>) constant).name().equalsIgnoreCase(input))
                    return constant;
            }
        }

        return input;
    }
}
