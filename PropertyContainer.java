/**
 * PropertyContainer.
 */
class PropertyContainer {
    Property[] properties;

    /**
     * Constructor.
     * 
     * @param properties Properties in the container
     */
    PropertyContainer(Property[] properties) {
        this.properties = properties;
    }

    Property getProperty(String key) {
        for (Property property : properties) {
            if (property.getKey().equals(key)) {
                return property;
            }
        }
        return null;
    }
}
