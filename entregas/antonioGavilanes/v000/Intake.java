package entregas.v000;

class Intake {
    private FoodNode first;

    public Intake() {
        first = null;
    }

    public void addFood(Food food) {
        FoodNode newFoodNode = new FoodNode(food);
        if (first == null) {
            first = newFoodNode;
        } else {
            FoodNode current = first;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newFoodNode);
        }
    }

    public void printFoodListing() {
        System.out.println(this.toString());
    }

    public void createIntake() {
        boolean creating = true;
        while (creating) {
            System.out.println("Nombre del alimento (-1 para terminar)");
            String foodName = System.console().readLine();
            if (foodName.equals("-1")) {
                creating = !creating;
            } else {
                Food food = new Food(foodName);
                addFood(food);
            }
        }
    }

    public void editIntake() {
        boolean editing = true;
        while (editing) {
            System.out.println("Nombre del alimento a editar (-1 para terminar)");
            String foodName = System.console().readLine();
            if (foodName.equals("-1")) {
                editing = !editing;
            } else {
                System.out.println("Nuevo nombre del alimento");
                String newFoodName = System.console().readLine();
                FoodNode current = first;
                while (current != null) {
                    if (current.getFood().getName().equals(foodName)) {
                        current.getFood().setName(newFoodName);
                    }
                    current = current.getNext();
                }
            }
        }
    }

    public void deleteIntake() {
        boolean deleting = true;
        while (deleting) {
            System.out.println("Nombre del alimento a eliminar (-1 para terminar)");
            String foodName = System.console().readLine();
            if (foodName.equals("-1")) {
                deleting = !deleting;
            } else {
                FoodNode current = first;
                FoodNode previous = null;
                while (current != null) {
                    if (current.getFood().getName().equals(foodName)) {
                        if (previous == null) {
                            first = current.getNext();
                        } else {
                            previous.setNext(current.getNext());
                        }
                    }
                    previous = current;
                    current = current.getNext();
                }
            }
        }
    }

    public void deleteAllIntake() {
        first = null;
    }

    @Override
    public String toString() {
        String foodListing = "";
        FoodNode current = first;
        while (current != null) {
            foodListing = foodListing + current.getFood().toString() + "\n";
            current = current.getNext();
        }
        return foodListing;
    }

    public static void main(String[] args) {
        
        Intake intake = new Intake();

        Food alimento1 = new Food("Manzana");
        Food alimento2 = new Food("Banana");
        Food alimento3 = new Food("Naranja");
        Food alimento4 = new Food("Seco de chabelo");
        Food alimento5 = new Food("Ceviche");

        intake.addFood(alimento1);
        intake.addFood(alimento2);
        intake.addFood(alimento3);
        intake.addFood(alimento4);
        intake.addFood(alimento5);

        System.out.println("Alimentos en la ingesta:");
        intake.printFoodListing();
        intake.deleteIntake();
        intake.printFoodListing();
        intake.createIntake();
        intake.printFoodListing();
        intake.editIntake();
        intake.printFoodListing();
        intake.deleteAllIntake();
        intake.printFoodListing();
    }
}

