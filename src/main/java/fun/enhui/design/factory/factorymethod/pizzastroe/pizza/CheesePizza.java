package fun.enhui.design.factory.factorymethod.pizzastroe.pizza;

public class CheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println(" 给奶酪披萨 准备原材料");
    }
}
