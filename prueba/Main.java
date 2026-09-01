package prueba;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /**
         * Atributos
         */
        //Instancia de la clase CAtegoryServices para el uso de metodos de nuestras categorias
        CategoryService service = new CategoryService();
        //Instancia de la clase scanner
        Scanner scanner = new Scanner(System.in);
        //Atributo booleano para poder detener el programa
        boolean running = true;

        while (running) {
            System.out.println("\n--- GESTOR DE CATEGORÍAS ---");
            System.out.println("1. Registrar categoría (createCategory)");
            System.out.println("2. Ver categorías activas (getCategories)");
            System.out.println("3. Ver categorías hijas (getChildCategories)");
            System.out.println("4. Eliminar categoría (deleteCategory)");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");

            //recibimos entrada del usuario
            String input = scanner.nextLine();

            switch (input) {
                //Caso 1, Registramos una nueva categoria
                case "1":
                
                    System.out.print("Ingrese el nombre de la categoría: ");
                    //Recibimos entrada del usuario para el nombre de la actegoria
                    String categoryStr = scanner.nextLine().trim();

                    System.out.print("Ingrese el tag de la categoría: ");
                    //Recibimos entrada del usuario para el tag
                    String tagStr = scanner.nextLine().trim();
                    //Verificamos que la entrada del usuario no sea vacia
                    if (categoryStr.isEmpty() || tagStr.isEmpty()) {
                        System.out.println("Error: El nombre de la categoría y el tag no pueden estar vacíos.");
                        break;
                    }
    
                    System.out.print("Ingrese el ID del padre (presione ENTER si no tiene padre): ");
                    //Recibimos la entrada del usuario  para ver si la actegoria es hijo o no de un categoria
                    String parentInput = scanner.nextLine().trim();
                    Integer parentId = null;

                    try {
                        //If para cambiar el valor del parent Id por la entrada del usario
                        if (!parentInput.isEmpty()) {
                            parentId = Integer.parseInt(parentInput);
                        }
                        
                        Category newCategory = new Category(categoryStr, tagStr, parentId);
                        //Comprobamos que la nueva categoria sea valida 
                        service.createCategory(newCategory);
                    } catch (NumberFormatException e) {
                        //la entrada del Id del padre es invalida
                        System.out.println("Error: El ID del padre debe ser un número entero válido o vacío.");
                    }
                    break;
                //Caso 2, Imprimimos las categorias activas
                case "2":
                    service.getCategories();
                    break;
                //Caso 3, imprimimos los hijos de una categoria 
                case "3":
                    System.out.print("Ingrese el ID de la categoría padre: ");
                    try {
                        Integer parentSearchId = Integer.parseInt(scanner.nextLine().trim());
                        service.getChildCategories(parentSearchId);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Debe ingresar un valor numérico válido.");
                    }
                    break;
                //Caso 4, Cmabiamos el status de una categoria
                case "4":
                    System.out.print("Ingrese el ID de la categoría a eliminar: ");
                    try {
                        Integer deleteId = Integer.parseInt(scanner.nextLine().trim());
                        service.deleteCategory(deleteId);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Debe ingresar un valor numérico válido.");
                    }
                    break;
                //caso5, terminamos el programa
                case "5":
                    running = false;
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
        scanner.close();
    }
}