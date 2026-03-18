package org.example;

public class uc1 {

    public static class Feet {
        private final double value;

        public Feet(double value) {

            if(Double.isNaN(value)){
                throw new IllegalArgumentException("Illegal value");
            }
            this.value = value;

        }

        @Override
        public boolean equals(Object obj){

            if(obj == null) return false;

            if(obj == this){
                return true;
            }

            Feet other;

            if(obj instanceof Feet){
                other = (Feet)obj;
            }else{
                return false;
            }

            if(Double.compare(other.value,this.value) == 0){
                return true;
            }

            return false;

        }
    }

    public static void main(String[] args) {
    }
}
