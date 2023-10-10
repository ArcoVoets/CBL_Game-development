/**
 * PropertyContainer.
 */
public class PropertyContainer {
    Property[] properties;

    PropertyContainer(Property[] properties) {
        this.properties = properties;
    }

    PropertyContainer() {
        this(new Property[0]);
    }
}
