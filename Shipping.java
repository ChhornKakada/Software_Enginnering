
public class Shipping {

    private static final double maxPetrol = 5000;
    public static double getMaxPetrol() { return maxPetrol; }

    private double distanceAB, distanceBC, weight;

    public Shipping() {}
    public Shipping(double distanceAB, double distanceBC, double weight) {
        this.distanceAB = distanceAB;
        this.distanceBC = distanceBC;
        this.weight = weight;
    }

    public double getDistanceAB() { return distanceAB; }
    public void setDistanceAB(double distanceAB) { this.distanceAB = distanceAB; }
    public double getDistanceBC() { return distanceBC; }
    public void setDistanceBC(double distanceBC) { this.distanceBC = distanceBC; }
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    public boolean isLoadable() { return this.weight <= 30000 ? true : false; }

    public double getPetrolAmount() {
        if(this.isLoadable()){
            if(this.weight <= 5000) return 10;
            else if(this.weight <= 10000) return 20;
            else if(this.weight <= 20000) return 25;
            else if(this.weight <= 30000) return 35;
        } 
        return 10;
    }

    public double petrolUsed(double distance) {
        if(this.isLoadable()) return distance * getPetrolAmount();
        else return 0;
    }

    public double petrolNeedInAB() { return this.petrolUsed(this.distanceAB); }
    public double petrolNeedInBC() { return this.petrolUsed(this.distanceBC); }
    public double petrolLeftFromAB() { return maxPetrol - this.petrolNeedInAB(); }
    public double petrolNeedToRefill() { return this.petrolNeedInBC() - this.petrolLeftFromAB(); }

    public static void main(String[] args) {
        Util util = new Util();
        util.clsScr();
        // Scanner input = new Scanner(System.in);
        Shipping shipping = new Shipping();
        String errorDouble = "    X - Invalid input, please input again: ";

        System.out.println("""
            === Calculate the minimum number of liters needed 
                to refill at point B in order to reach point C. ===
                """);
        System.out.println("Please input info here...");
        shipping.setDistanceAB(util.getDouble("    Distance from A to B in Km: ", errorDouble));
        shipping.setDistanceBC(util.getDouble("    Distance from B to C in Km: ", errorDouble));
        shipping.setWeight(util.getDouble("    Weight of goods: ", errorDouble));

        if(shipping.getWeight() <= 0 || shipping.getDistanceAB() <= 0 || shipping.getDistanceBC() <= 0) {
            System.out.println("\n: The input data need to be greater than 0.\n");
        }
        else if(shipping.isLoadable()) {
            if(shipping.petrolNeedInAB() > 5000) System.out.println("\nResutl: The ship cannot be reached to point B.\n");
            else {
                if(shipping.petrolLeftFromAB() > shipping.petrolNeedInBC()) {
                    System.out.printf("""
                        \nResult: You can reach point C without refilling the petrol and you have left %.2f leters.\n\n
                            """, shipping.petrolLeftFromAB() - shipping.petrolNeedInBC());
                }
                else System.out.println("\nResult: You need to refill " +shipping.petrolNeedToRefill()+ " leters of petrol to reach point C.\n");
            }
        }
        else System.out.println("\nThe weight is so heavy, cannot be loaded.\n");
    }
    
}
