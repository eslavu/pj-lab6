package problema;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.util.List;

public class Json
{
    public static void scriereJson(List<Angajat> angajati)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            File file = new File("src/main/resources/angajat.json");

            mapper.writeValue(file, angajati);
        }
        catch (Exception ex) { ex.printStackTrace(); }
    }

    public static List<Angajat> citireJson()
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            File file = new File("src/main/resources/angajati.json");

            List<Angajat> angajati = mapper.readValue(file, new TypeReference<List<Angajat>>(){});
            return angajati;
        }
        catch (Exception ex) { ex.printStackTrace(); }
        return null;
    }
}