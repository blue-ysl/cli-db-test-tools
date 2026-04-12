package prompt;

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

    public Map<String, Object> getObjectValuesOrNull(Class<?> clazz)
    {
        List<Field> fields = collectClassFields(clazz);
        Map<String, Object> values = new LinkedHashMap<>();
        for (Field field : fields)
        {
            if (field.isAnnotationPresent(SkipPrompt.class))
            {
                continue;
            }

            if (field.getType().isEnum())
            {
                String enumName = field.getName();
                Object selectedEnum = getEnumChoiceOrNull(enumName, field.getType().getEnumConstants());
                if (selectedEnum == null)
                {
                    System.err.println("Enum '" + enumName + "' not found");
                    return null;
                }
                values.put(enumName, selectedEnum);
                continue;
            }

            Object valval = getChoiceOrNull(field.getName(), field.getType());
            values.put(field.getName(), valval);
        }

        return values;
    }

    private List<Field> collectClassFields(Class<?> clazz)
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
                {
                    fields.add(field);
                }
            }
        }

        return fields;
    }

    private Object getChoiceOrNull(String fieldName, Class<?> fieldType)
    {
        int tryCount = 0;
        while (true)
        {
            try
            {
                System.out.printf("Enter [%s] (type: %s): ", fieldName, fieldType.getSimpleName());
                String input = reader.readLine().trim();

                return convertValue(input, fieldType);
            } catch (IOException ioException)
            {
                return null;
            } catch (Exception exception)
            {
                if (tryCount == 3)
                {
                    return null;
                }
                tryCount++;
                System.out.println("Something wrong while processing, try again.");
            }
        }
    }

    private Object convertValue(String input, Class<?> type)
    {
        if (type == String.class)
        {
            return input;
        }

        if (type == int.class || type == Integer.class)
        {
            return Integer.parseInt(input);
        }

        if (type == boolean.class || type == Boolean.class)
        {
            return Boolean.parseBoolean(input);
        }

        return input;
    }

    private Object getEnumChoiceOrNull(String enumName, Object[] enumConstants)
    {
        System.out.println("Choose " + enumName + ":");
        for (int i = 0; i < enumConstants.length; i++)
        {
            System.out.printf(" %.2s. %s\n", i + 1, enumConstants[i]);
        }

        int tryCount = 0;
        while (true)
        {
            try
            {
                System.out.print("Enter number (1-" + enumConstants.length + "): ");
                String input = reader.readLine().trim();
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= enumConstants.length)
                {
                    return enumConstants[choice - 1];
                }
            } catch (NumberFormatException formatException)
            {
                tryCount++;
                System.out.println("Invalid choice, try again.");
                if (tryCount == 3)
                {
                    return null;
                }
            } catch (IOException ioException)
            {
                return null;
            }
        }
    }
}
