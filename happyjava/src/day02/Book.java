package day02;

public class Book {
    //변수는 타입을 가진다
    //강형언어 - 타입을 반드시 지정해서 사용하는 언어
    //약형언어 - 그릇에 타입이 지정되지 않고 사용하는 언어
    private String title;
    private int price;

    public int getPrice() {
        return this.price * 2;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}