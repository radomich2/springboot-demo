package com.opuscapita.demo;

import com.opuscapita.demo.products.model.*;
import com.opuscapita.demo.products.order.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

 //   @Bean
 //  CommandLineRunner commandLineRunner(OrderProductRepository orderProductRepository,StudentCourseRepository studentCourseRepository,CourseRepository courseRepository,StudentRepository studentRepository,ProductsRepository productsRepository, OrderRepository orderRepository){
 //       return args ->{


    //    Optional<Product> p=productsRepository.findById(1L);
   //         System.out.println("des"+ p.get().getDescription());
   //         Orders order1=new Orders();
  //          System.out.println(order1.getProductSet().size());
  //          order1.getProductSet().add(p.get());
  //          System.out.println(order1.getProductSet().size());
  //          orderRepository.save(order1);



   //     Student student1=new Student();
   //     student1.setName("Gholi");
   //     StudentCourse studentCourse=new StudentCourse();
   //     studentCourse.setStudent(student1);
   //     studentCourse.setCourse(courseRepository.findById(2).get());
   //     studentCourse.setScore(20);
   //     StudentCourse studentCourse2=new StudentCourse();
   //     studentCourse2.setCourse(courseRepository.findById(6).get());
   //     studentCourse2.setStudent(student1);
   //     studentCourse2.setScore(15);
   //     student1.getStudentCourses().add(studentCourse);
   //     student1.getStudentCourses().add(studentCourse2);
   //     studentRepository.save(student1);

 //           Orders orders1=new Orders();
 //           OrderProduct orderProduct=new OrderProduct();
 //           orderProduct.setOneOrders(orders1);
 //           orderProduct.setProduct(productsRepository.findById(1L).get());
 //           orderProduct.setQuantity(22);
 //           orders1.getOrderProducts().add(orderProduct);
 //           OrderProduct orderProduct2=new OrderProduct();
 //           orderProduct2.setOneOrders(orders1);
 //           orderProduct2.setProduct(productsRepository.findById(2L).get());
 //           orderProduct2.setQuantity(81);
 //           orders1.getOrderProducts().add(orderProduct2);
 //           orderRepository.save(orders1);

 //      };
 //  }

}
