public class RectangleClass{
    private int l;
    private int b;

    public RectangleClass(){
        this.l =0;
        this.b=0;
    }

    public int area(int l, int b){
        this.l = l;
        this.b =b;
        return l*b;
    }
    public static void main(String[] args) {
        RectangleClass r1= new RectangleClass(); // No need for * in Java; new returns a reference
        System.out.println(r1.area(4,5)); // Use . to access members

    }
}
