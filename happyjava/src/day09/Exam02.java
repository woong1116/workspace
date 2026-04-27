package day09;

class Pen{
    String color;
    String 제조사;
    String 상품명;

    public Pen(String color,String 제조사, String 상품명){
        this.color = color;
        this.제조사 = 제조사;
        this.상품명 = 상품명;
    }

    @Override
    public String toString() {
        return "이 펜은 " + 제조사 + "에서 만든 " + 상품명 + "입니다. 색상은 " + color + "입니다.";
    }
}
public class Exam02 {
    public static void main(String[] args) {
        Pen pen = new Pen("빨강","모나미","볼펜");
        System.out.println(pen);
    }

}