package prueba;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoryService {

    /**
     * Atributos
     */

    //HashMap con clve un Integer ligado a la categoria correspondiente 
    private Map<Integer, Category> categoryMap = new HashMap<>();
    //int con valor para el categoryId de la proxima categoria a crear
    private int globalIdCounter = 1;

    /**
     * Metodos 
     */

    /**
     * Verifica que una categoria pueda ser creada correctamente, le asigan un id unico y la agrega al hashamp de categorias
     * despues imprime un mensaje de exito, en caso de incumplir alguna condicion de creacion imprime un mensaje de 
     * error y no lo registra
     * @param Categoria creada por el usuario
     */
    public void createCategory(Category newCategory) {
        // Validar que la categoría no sea su propio padre antes de cualquier operación
        if (newCategory.getParentCategoryId() != null && newCategory.getParentCategoryId().equals(globalIdCounter)) {
            System.out.println("Error: Una categoría no puede ser padre de sí misma.");
            return;
        }

        // Unicidad de category y tag
        for (Category c : categoryMap.values()) {
            if (c.getCategory().equalsIgnoreCase(newCategory.getCategory())) {
                System.out.println("Error: Ya existe una categoría con el nombre '" + newCategory.getCategory() + "'.");
                return;
            }
            if (c.getTag().equalsIgnoreCase(newCategory.getTag())) {
                System.out.println("Error: Ya existe una categoría con el tag '" + newCategory.getTag() + "'.");
                return;
            }
        }

        // Validar existencia del parentCategoryId
        if (newCategory.getParentCategoryId() != null) {
            Category parent = categoryMap.get(newCategory.getParentCategoryId());
            if (parent == null || parent.getStatus() != 1) {
                System.out.println("Error: El parentCategoryId especificado no existe o fue eliminado.");
                return;
            }
        }

        // Autoincrementar asignando el ID único
        Integer newId = globalIdCounter++;
        newCategory.setCategoryId(newId);
        
        //registra en el HashMap la categoria creada
        categoryMap.put(newId, newCategory);
        //imprime mensaje de exito
        System.out.println("Categoría registrada correctamente: " + newCategory);
    }

    /**
     * Metodo que obtiene la lista de todas las categorias creadas hasta el momento y que tienen un estattus activo,
     * para despues imprimir en pantalla todas las categorias, en caso de que no haya ninguna categoria activa o creada
     * imprime un mensaje que diciendo que no hay categorias.
     */
    public void getCategories() {
        List<Category> activeCategories = new ArrayList<>();
        for (Category c : categoryMap.values()) {
            if (c.getStatus() == 1) {
                activeCategories.add(c);
            }
        }

        if (activeCategories.isEmpty()) {
            //Caso no hay categorias por mostrar
            System.out.println("No existen categorías registradas");
        } else {
            //caso si hya al menos 1 categoria que mostrar
            System.out.println(activeCategories);
        }
    }

    /**
     * Obtiene todas las categorias hijas de la categoria pasada como parametro e imprime en pantalla
     * una lista de los hijos, en caso de no tener hijos imprimira una lista vacia
     * @param Category a la cual queremos obtener sus hijos
     */
    public void getChildCategories(Integer categoryId) {
        List<Category> children = new ArrayList<>();
        for (Category c : categoryMap.values()) {
            if (c.getStatus() == 1 && categoryId.equals(c.getParentCategoryId())) {
                children.add(c);
            }
        }
        //imprime los hijos de la categoria
        System.out.println(children);
    }

    /**
     * Obtiene una categoria a la cual cambiara su estatus a 0, siendo 0 que esta categoria
     * ya no esta activa, si la categoria tiene hijos imprimira un mensaje de error y no se cambiara el status
     * de la categoria
     * @param Id de la categoria a borrar
     */
    public void deleteCategory(Integer id) {
        Category target = categoryMap.get(id);

        //buscamos que si exista la categoria 
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
        //cambiamos el status a 0
        target.setStatus(0);
        //imprimimos mensaje de exito
        System.out.println("Categoría eliminada con éxito.");
    }
}