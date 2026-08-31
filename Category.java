public class Category{

    /**
     * Atributos
     */
    private Integer categoryId  = 1 ;
    private String category;
    private String tag;
    private Integer parentCategoryId;
    private Integer status;

    /**
     * Metodos
     */

    public Integer getCategoryId(){
        return this.categoryId;
    }

    public String getCategory(){
        return this.category;
    }

    public String getTag(){
        return this.tag;
    }

    public Integer getParentCategoryId(){
        return this.parentCategoryId;
    }

    public Integer getStatus(){
        return this.status;
    }


    public category(String category , String tag, Integer parentCategoryId){
        this.categoryId = this.categoryId;
        incrementoId();
        this.category = category;
        this.tag = tag;
        this.parentCategoryId = parentCategoryId;
        this.status = 1;
    }

    public void deleteCategory(Integer status){
        this.status = 0;
    }

    public void incrementoId(){
        this.categoryId =this.category++;
    }

    public String toString(){
        String category = "{";
        category += getCategoryId() + ",\"" + getCategory()+ "\",\"" + getTag() + "\""+ getParentCategoryId() + getStatus() +"}";
        return category;
    }
    
}