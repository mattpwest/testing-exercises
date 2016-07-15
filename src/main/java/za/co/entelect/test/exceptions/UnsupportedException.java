package za.co.entelect.test.exceptions;

import za.co.entelect.test.Material;

import java.util.List;

public class UnsupportedException extends VehicleException {
    public UnsupportedException(List<Material> allowedTypes, Material actualType) {
        super(String.format(
                "Load of %s is not supported - vehicle only allows {%s} - loading aborted",
                actualType.name(),
                String.join(", ", getNamesArrayFromList(allowedTypes))
        ));
    }

    private static String[] getNamesArrayFromList(List<Material> materials) {
        String[] result = new String[materials.size()];
        for (int i = 0; i < materials.size(); i++) {
            result[i] = materials.get(i).name();
        }
        return result;
    }
}
