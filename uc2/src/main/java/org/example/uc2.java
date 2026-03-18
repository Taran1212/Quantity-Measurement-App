package org.example;

public class uc2 {

    public static class Feet {
        private final double value;

        Feet(double value) {

            if (Double.isNaN(value)) {
                throw new IllegalArgumentException("Invalid Number");
            }
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null)return false;

            if (this == obj) return true;

            Feet other;
            if(obj instanceof Feet){
                other = (Feet)obj;
            }else{
                return false;
            }

            if(Double.compare(this.value,other.value) == 0){
                return true;
            }

            return false;
        }
    }

    public static class Inch {
        private final double value;

        Inch(double value) {

            if (Double.isNaN(value)) {
                throw new IllegalArgumentException("Invalid Number");
            }

            this.value = value;
        }

        @Override
        public boolean equals(Object obj){
            if(obj == null) return false;

            if(this == obj) return true;

            Inch other;

            if(obj instanceof Inch){
                other = (Inch)obj;
            }else{
                return false;
            }

            if(Double.compare(this.value,other.value) == 0) return true;

            return false;
        }
    }

    public static void demonstrateFeetEquality() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(2.0);

        System.out.println(f1.equals(f2));
    }

    public static void demonstrateInchEquality() {
        Inch f1 = new Inch(1.0);
        Inch f2 = new Inch(1.0);

        System.out.println(f1.equals(f2));
    }

    public static void main(String[] args) {

    }
}
