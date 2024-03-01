package de.benikum.itembingo;

import org.bukkit.Material;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ItemSelector {
    static final Random random = new Random();
    public static Material getRandomMaterial() {
        Material[] materials = Material.values();
        int randomIndex = random.nextInt(materials.length);
        return materials[randomIndex];
    }
    public static Set<Material> getRandomMaterialSet(int amount) {
        Set<Material> materialSet = new HashSet<>();
        while (materialSet.size() < amount) {
            materialSet.add(getRandomMaterial());
        }
        return materialSet;
    }
}
