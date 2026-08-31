import java.util.ArrayList;
import java.util.List;

public class ServiciosCategory{

    /**
     * Atributos
     */
    private List<Category> listCategory;

    /**
     * Metodos
     */
    public String getCategories(){
        String allCategories = "[";
        for(int i = 0; i<=listCategory.size()-1; i++){
            Category category = listCategory.get(i);
            allCategories += category.toString();
            if(i!=listCategory.size){
                allCategories += ",";
            }
        }
        allCategories += "]";
        return allCategories;
    }


}