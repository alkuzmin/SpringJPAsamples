package ru.kuzmin.demo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Departments")
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Getter
    @Setter
    @Column(name = "name")
    String name;
    @Getter
    @Setter
    @Column(name = "address")
    String address;
    @Override
    public String toString()
    {
        return name+ " "+address;
    }
}
