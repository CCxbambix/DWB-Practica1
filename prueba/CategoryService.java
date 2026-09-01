package prueba;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryService {
    private Map<Integer, Category> categoryMap = new HashMap<>();
    private int globalIdCounter = 1;

    public void createCategory(Category newCat) {
        // Validar que la categoría no sea su propio padre antes de cualquier operación
        if (newCat.getParentCategoryId() != null && newCat.getParentCategoryId() == 0) {
            System.out.println("Error: Una categoría no puede ser padre de sí misma.");
            return;
        }

        // Unicidad de category y tag
        for (Category c : categoryMap.values()) {
            if (c.getCategory().equalsIgnoreCase(newCat.getCategory())) {
                System.out.println("Error: Ya existe una categoría con el nombre '" + newCat.getCategory() + "'.");
                return;
            }
            if (c.getTag().equalsIgnoreCase(newCat.getTag())) {
                System.out.println("Error: Ya existe una categoría con el tag '" + newCat.getTag() + "'.");
                return;
            }
        }

        // Validar existencia del parentCategoryId
        if (newCat.getParentCategoryId() != null) {
            Category parent = categoryMap.get(newCat.getParentCategoryId());
            if (parent == null || parent.getStatus() != 1) {
                System.out.println("Error: El parentCategoryId especificado no existe o fue eliminado.");
                return;
            }
        }

        // Autoincrementar asignando el ID único
        Integer newId = globalIdCounter++;
        newCat.setCategoryId(newId);
        
        // Validación extra: asegurarse de que no intente autoreferenciarse si usara su mismo ID asignado
        if (newId.equals(newCat.getParentCategoryId())) {
            System.out.println("Error: Una categoría no puede ser padre de sí misma.");
            globalIdCounter--; // revertir ID
            return;
        }

        categoryMap.put(newId, newCat);
        System.out.println("Categoría registrada correctamente: " + newCat);
    }

    public void getCategories() {
        List<Category> activeCategories = new ArrayList<>();
        for (Category c : categoryMap.values()) {
            if (c.getStatus() == 1) {
                activeCategories.add(c);
            }
        }

        if (activeCategories.isEmpty()) {
            System.out.println("No existen categorías registradas");
        } else {
            System.out.println(activeCategories);
        }
    }

    public void getChildCategories(Integer categoryId) {
        List<Category> children = new ArrayList<>();
        for (Category c : categoryMap.values()) {
            if (c.getStatus() == 1 && categoryId.equals(c.getParentCategoryId())) {
                children.add(c);
            }
        }
        System.out.println(children);
    }

    public void deleteCategory(Integer id) {
        Category target = categoryMap.get(id);

        if (target == null || target.getStatus() == 0) {
            System.out.println("Error: La categoría con ID " + id + " no existe o ya fue eliminada.");
            return;
        }

        // Validar si tiene categorías hijas activas
        for (Category c : categoryMap.values()) {
            if (c.getStatus() == 1 && id.equals(c.getParentCategoryId())) {
                System.out.println("Error: No es posible eliminar una categoría si tiene categorías hijas.");
                return;
            }
        }

        target.setStatus(0);
        System.out.println("Categoría eliminada con éxito.");
    }
}