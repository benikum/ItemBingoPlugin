package de.benikum.itembingo;

import org.bukkit.Material;

import java.util.HashSet;
import java.util.Set;

public class FoundItemController {
    private Set<Material> foundMaterials = new HashSet<>();
    
    public void registerItem(Material material) {
        foundMaterials.add(material);
    }
    public boolean getIfFoundAllItems(ItemSelector items) {
        for (Material m : items.getRandomMaterials()) {
            if (!foundMaterials.contains(m)) {
                return false;
            }
        }
        return true;
    }
    public Set<Material> getNotFoundItems(ItemSelector items) {
        Set<Material> notFound = new HashSet<>();
        for (Material m : items.getRandomMaterials()) {
            if (!foundMaterials.contains(m)) {
                notFound.add(m);
            }
        }
        return notFound;
    }
}
