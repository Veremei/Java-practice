
import java.util.*;

public class CarSimpleDAO {

    public final List<Car> cars = new ArrayList<>();
    public final List<Car> fcars = new ArrayList<>();


    public CarSimpleDAO() throws MyEx {
        addCar(new Car("Audi", "Q5", "2016", "Черный", "1-9BI"));
        addCar(new Car("Bentley", "Bayega", "2017", "Белый", "123II"));
        addCar(new Car("BMW", "320i", "2005", "Синий", "333NN"));
        addCar(new Car("BMW", "X6", "2012", "Черный", "505i"));
        addCar(new Car("Merc-Benz", "E300", "2010", "Серый", "X&i"));
    }

    public List<Car> findCars() {
        return cars;
    }

    public Car getCar(String CarName) {
        Car p1 = new Car();
        for(Car car : cars) {
            if(car.getName().equals(CarName)) {
                p1 = car;
            }
        }
        return p1;
    }

    public List<Car> fCars(String str) {
        String str1 = str;
        getFindingCars(str1);
        return fcars;
    }



    public void getFindingCars(String str){
        for(Car car : cars) {
            if(car.getName().equals(str)) {
                System.out.println("Машина найдена");
                fcars.add(car);
            }
        }
    }

    public void sortCars(){
        Collections.sort(cars,
                new Comparator<Car>() {
                    public int compare(Car o1, Car o2) {
                        return o1.getDate().compareTo(o2.getDate());
                    }
                }
        );
    }

    public Long addCar(Car car) {
        Long id = generateCarId();
        car.setCarId(id);
        cars.add(car);
        return id;
    }

    private Long generateCarId() {
        Long carId = Math.round(Math.random() * 1000 + System.currentTimeMillis());
        return carId;
    }

    public void deleteCar(String str) {                          //iterator()--получаем указатель на начало коллекции
        for(Iterator<Car> it = cars.iterator(); it.hasNext();) {//hasNext() возвращает true в случае, если итератор может переместиться к следующему элементу
            Car car = it.next();//next() перемещается на следующий элемент и возвращает его значение
            if(car.getName().equals(str)) {
                it.remove();
            }
        }
    }

    public void updateCar(Car car) {
        Car oldCar = getCar(car.getName());
        if(oldCar != null) {
            oldCar.setName(car.getName());
            oldCar.setModel(car.getModel());
            oldCar.setDate(car.getDate());
            oldCar.setColor(car.getColor());
            oldCar.setVIN(car.getVIN());
        }
    }
}
