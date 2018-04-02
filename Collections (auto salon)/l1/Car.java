package l1;
import java.io.Serializable;

public class Car implements Serializable {

    private Long carId;
    private String name;
    private String model;
    private String date;
    private String color;
    private String VIN;

    public Car(String name, String model, String date, String color, String VIN) throws MyEx {//конструктор с исключениями
        if (name.equals("")) throw new MyEx("Марка");
        if (model.equals("")) throw new MyEx("Модель");
        if (Integer.parseInt(date) > 2019) throw new MyEx("Год выпуска");
        if (color.equals("")) throw new MyEx("Год выпуска");
        this.name = name;
        this.model = model;
        this.date = date;
        this.color = color;
        this.VIN = VIN;
    }

    public Car(){

    }

    public Car(Long id, String name, String model, String date, String color, String VIN){


        this.carId = id;
        this.name = name;
        this.model = model;
        this.date = date;
        this.color = color;
        this.VIN = VIN;
    }

    public void setCarId(Long id){
        this.carId = id;
    }


    public Long getCarId(){
        return carId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getModel(){
        return model;
    }

    public void setModel(String model){
        this.model = model;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getColor(){
        return color;
    }

    public void setColor(String color){
        this.color = color;
    }

    public String getVIN(){
        return VIN;
    }

    public void setVIN(String VIN){
        this.VIN = VIN;
    }

    public String toString() {
        return "Car{" + ", Name=" + name + ", Model=" + model + ", Date=" + date + ", Color=" + color + ", VIN=" + VIN + '}';
    }
}
