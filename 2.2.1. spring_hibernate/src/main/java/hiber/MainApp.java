package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        Car car1 = new Car("car1", 1);
        carService.add(car1);
        Car car2 = new Car("car2", 2);
        carService.add(car2);

        userService.add(new User("User1", "Lastname1", "user1@mail.ru", car2));
        userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
        userService.add(new User("User3", "Lastname3", "user3@mail.ru", car1));
        userService.add(new User("User4", "Lastname4", "user4@mail.ru"));


        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            if (user.getCar() != null){
                System.out.println("Car = " + user.getCar());
            }
            System.out.println();
        }

//        List<Car> cars = carService.listCars();
//        for (Car car : cars) {
//            System.out.println("Id = " + car.getId());
//            System.out.println("Model = " + car.getModel());
//            System.out.println("Series = " + car.getSeries());
//            System.out.println("User = " + car.getUser().getFirstName());
//        }

        context.close();
    }
}
