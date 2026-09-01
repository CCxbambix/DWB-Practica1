package prueba;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CategoryService service = new CategoryService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- GESTOR DE CATEGORÍAS ---");
            System.out.println("1. Registrar categoría (createCategory)");
            System.out.println("2. Ver categorías activas (getCategories)");
            System.out.println("3. Ver categorías hijas (getChildCategories)");
            System.out.println("4. Eliminar categoría (deleteCategory)");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");

            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.print("Ingrese el nombre de la categoría: ");
                    String categoryStr = scanner.nextLine().trim();

                    System.out.print("Ingrese el tag de la categoría: ");
                    String tagStr = scanner.nextLine().trim();

                    System.out.print("Ingrese el ID del padre (presione ENTER si no tiene padre): ");
                    String parentInput = scanner.nextLine().trim();
                    Integer parentId = null;

                    try {
                        if (!parentInput.isEmpty()) {
                            parentId = Integer.parseInt(parentInput);
                        }
                        
                        Category newCategory = new Category(categoryStr, tagStr, parentId);
                        service.createCategory(newCategory);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: El ID del padre debe ser un número entero válido o vacío.");
                    }
                    break;

                case "2":
                    service.getCategories();
                    break;

                case "3":
                    System.out.print("Ingrese el ID de la categoría padre: ");
                    try {
                        Integer parentSearchId = Integer.parseInt(scanner.nextLine().trim());
                        service.getChildCategories(parentSearchId);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Debe ingresar un valor numérico válido.");
                    }
                    break;

                case "4":
                    System.out.print("Ingrese el ID de la categoría a eliminar: ");
                    try {
                        Integer deleteId = Integer.parseInt(scanner.nextLine().trim());
                        service.deleteCategory(deleteId);
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Debe ingresar un valor numérico válido.");
                    }
                    break;

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