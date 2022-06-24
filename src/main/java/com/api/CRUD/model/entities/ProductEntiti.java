package com.api.CRUD.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "Products")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update PRODUCTS set ISDELETE=true where id = ?")
@Where(clause = "isDelete = false")
public class ProductEntiti implements Serializable{

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private Float price;

    @Column(name = "ISDELETE")
    private boolean isDelete;


}
