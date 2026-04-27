package day06;

public class VendingMachineTest {
    public static void main(String[] args) {
        // 인스턴스 생성
        VendingMachine vm1 = new VendingMachine();
        VendingMachine vm2 = new VendingMachine();

        // 메시지 전송(메소드 호출)
        String product = vm1.pushProductButton(1);
        System.out.println("선택한 상품: " + product);

        // 자판기를 5개 만들고 싶다면?
        VendingMachine[] machines = new VendingMachine[5];
        for(int i = 0; i < 5; i++) {
            machines[i] = new VendingMachine();
        }
    }
}
