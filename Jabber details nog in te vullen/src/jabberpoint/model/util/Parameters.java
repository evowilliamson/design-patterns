package jabberpoint.model.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to encapsulate a Map that clarifies the usage of parameters in the application
 */
public class Parameters {

    private Map<Parameter, Object> parameters = new HashMap<Parameter, Object>();

    /**
     * Stores a parameter
     * @param name the name to identify the parameter
     * @param object the value of the parameter
     */
    public void setValue(Parameter parameter, Object object) {
        parameters.put(parameter, object);
    }

    /**
     * Retrieves the parameter as a string value
     * @param parameter the name of the parameter
     * @return the string representation of the value of the parameter
     */
    public String getString(Parameter parameter) {
        return (String) parameters.get(parameter);
    }

    public Integer getInteger(Parameter parameter) {
        return (Integer) parameters.get(parameter);
    }


    public enum Parameter {
        SLIDESHOW_NAME,
        FILE_NAME
    }

}
