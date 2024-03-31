package ru.kuzmin.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
        MyRepo mr = ctx.getBean(MyRepo.class);
        //записать новую запись в БД
        mr.save(new Department(null, "Dev", "2 floor"));

        //...scripts in pga

        // получить 1 запись и обновить её
        Optional<Department> d = mr.findById(3);
        d.ifPresent(System.out::println);
        d.ifPresent(dep -> {
            dep.setName(dep.getName() + "!!!");
            mr.save(dep);
        });
        //получить все записи
        List<Department> ls = mr.findAll(Sort.by(Sort.Order.asc("name")));
        System.out.println("\n\n");
        ls.forEach(System.out::println);
        //изменим коллекцию
        ls.forEach(dep->{dep.setName("DEPARTMENT: "+dep.getName());});
        //записать коллекцию в БД
        mr.saveAll(ls);
        //получить все записи
        ls = mr.findAll(Sort.by(Sort.Order.asc("name")));
        System.out.println("\n\n");
        ls.forEach(System.out::println);

        //добавим новую запись через нативный SQL
        int res = mr.insertDepartment("HR", "floor 8");
        ls = mr.findAll(Sort.by(Sort.Order.asc("name")));
        System.out.println("\n\n");
        ls.forEach(System.out::println);

        //обновим запись через нативный SQL
        res = mr.updateDepartmentsSetNameForIdNative("HR is the best", 2);
        ls = mr.findAll(Sort.by(Sort.Order.asc("name")));
        System.out.println("\n\n");
        ls.forEach(System.out::println);

//		int res = mr.updateDepartmentsSetNameForIdNative("Dev Ops", 7);
//        ls = mr.findAll(Sort.by(Sort.Order.asc("name")));
//        System.out.println("\n\n");
//        ls.forEach(System.out::println);
    }

}
