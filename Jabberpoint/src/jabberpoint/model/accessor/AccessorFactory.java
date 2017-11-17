package jabberpoint.model.accessor;

import jabberpoint.model.Configuration;
import jabberpoint.model.drawingdriver.DrawingDriver;
import jabberpoint.model.exception.ConfigurationException;

/**
 * Factory for the creation of implementations of {@link DrawingDriver}
 * 
 * @author Ivo Willemsen
 */
public class AccessorFactory {

    private static final DemoAccessor DEMO_ACCESSOR = new DemoAccessor();
    private static final XMLAccessor XML_ACCESSOR = new XMLAccessor();
    private static final XMLAccessorOld XML_ACCESSOR_OLD = new XMLAccessorOld();

    /**
     * Returns an instance of a {@link Accessor}
     * 
     * @return the {@link Accessor} instance
     */
    public static Accessor getInstance() {
    	System.out.println("Returning acessor depending on config");
        if (Configuration.getAccessorConfig().equals(Configuration.AccessorConfig.XML)) {
        	if (XML_ACCESSOR == null){
        		System.out.println("XML ACCESSOR IS NULL!!!");
        	} else {
        		System.out.println("Returning XML accessor");
        	}
            return XML_ACCESSOR;
        } else if (Configuration.getAccessorConfig().equals(Configuration.AccessorConfig.OLDXML)){
        	if (XML_ACCESSOR == null){
        		System.out.println("XML ACCESSOR FOR OLD VERSION IS NULL!!!");
        	} else {
        		System.out.println("Returning XML accessor FOR OLD VERSION");
        	}
            return XML_ACCESSOR_OLD;
        } else {
            throw new ConfigurationException("Incorrect Accessor configuration");
        }

    }

    public static DemoAccessor getDemoAccessor() {
    	System.out.println("Returning demo");
        return DEMO_ACCESSOR;
    }

}
