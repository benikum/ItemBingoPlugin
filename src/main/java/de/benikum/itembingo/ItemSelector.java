package de.benikum.itembingo;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ItemSelector {
    private static final Random random = new Random();
    private static final Material[] materials = Material.values();
    private Set<Material> randomMaterials = new HashSet<>();
    private int itemSetSize;
    
    public ItemSelector(int amount) {
        this.itemSetSize = amount;
        fillSearchItems();
    }
    
    public Set<Material> getRandomMaterials() {
        return randomMaterials;
    }
    public int getItemSetSize() {
        return itemSetSize;
    }
    public void rerollItem(Material material) {
        randomMaterials.remove(material);
        fillSearchItems();
    }
    public void clear() {
        randomMaterials.clear();
        fillSearchItems();
    }
    public void fillSearchItems() {
        do {
            int randomIndex = random.nextInt(materials.length);
            Material thisMaterial = materials[randomIndex];
            ItemStack testItemStack = new ItemStack(thisMaterial);
            if (testItemStack.getType().equals(thisMaterial)) {
                randomMaterials.add(thisMaterial);
            }
        } while (randomMaterials.size() < itemSetSize);
    }
}