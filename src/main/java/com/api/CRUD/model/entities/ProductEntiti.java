package com.api.CRUD.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.annotation.PreDestroy;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
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
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private Float price;

    @Column(name = "ISDELETE")
    private boolean isDelete;


    @Column(name = "DataInsercao")
    private Date dataInsercao;

    @Column(name = "DataAlteracao")
    private Date dataAlteracao;

    @Column(name = "DataExclusao")
    private Date dataExclusao;

    @PrePersist
    public void prePersist(){
        dataInsercao = new Date();
    }

    @PreUpdate
    public void preUpdate(){
        dataAlteracao = new Date();
        System.out.println("excluir");
    }

    @PreRemove
    public void preRemove(){
        dataExclusao = new Date();
    }





}
