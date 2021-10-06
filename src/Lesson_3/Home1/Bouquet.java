package Lesson_3.Home1;

import java.util.*;


public class Bouquet implements Component {
    private List<Component> flowers = new ArrayList<>();

    public void add(Component... component) {
        flowers.addAll(Arrays.asList(component));
    }

    public void remove(Component component) {
        flowers.remove(component);
    }


    @Override
    public void show() {
        System.out.println("Букет: ");
        showName();
        System.out.println("Стоимость: " + getPrice());
    }

    @Override
    public void showName() {
        for(Component fl : flowers){fl.showName();}

    }

    @Override
    public int getPrice() {
        int price = 0;
        for(Component fl : flowers){price += fl.getPrice();}
        return price;
    }

    @Override
    public int getFreshness() {
        return 0;
    }

    public void checkFreshness() {
        flowers.sort(Comparator.comparing(Component::getPrice));
        showName();
    }


    public int getStemLength() {
        return 0;
    }
    public void findFlowerByStem(int lengthMin, int lengthMax){
        for(Component fl : flowers) {
            if (fl.getStemLength() > lengthMin && fl.getStemLength() < lengthMax) fl.showName();
        }
    }
}
