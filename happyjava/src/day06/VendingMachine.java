package day06;

public class VendingMachine {
    // 필드
    String product;
    int price;

    // 메소드 추가
    public String pushProductButton(int menuId) {
        if(menuId == 1) return "콜라";
        else if(menuId == 2) return "사이다";
        else if(menuId == 3) return "환타";
        else return "없는 상품";
    }
}

