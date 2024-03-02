package de.benikum.itembingo;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ItemSelector {
    private static final Random random = new Random();
    private static final Material[] materials = Material.values();
    private Set<Material> searchItems = new HashSet<>();
    int itemSetSize;
    
    public ItemSelector(int amount) {
        this.itemSetSize = amount;
        fillSearchItems();
    }
    
    public Set<Material> getSearchItems() {
        return searchItems;
    }
    public void fillSearchItems() {
        while (searchItems.size() < itemSetSize) {
            int randomIndex = random.nextInt(materials.length);
            Material thisMaterial = materials[randomIndex];
            ItemStack testItemStack = new ItemStack(thisMaterial);
            if (testItemStack.getType().equals(thisMaterial)) {
                searchItems.add(thisMaterial);
            }
        }
    }
    public void changeItem(Material material) {
        if (!searchItems.contains(material)) return;
        searchItems.remove(material);
        fillSearchItems();
    }
    public void clear() {
        searchItems.clear();
    }
}