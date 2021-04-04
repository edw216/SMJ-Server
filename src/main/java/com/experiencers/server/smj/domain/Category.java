package com.experiencers.server.smj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    public Long id;

    @NotNull
    @Column(length = 255,unique = true,nullable = false)
    public String name;

    @JsonIgnore
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    public List<Board> boards = new ArrayList<>();


    public Category() {
        this.name = "";
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public static class Builder{
        private String name;

        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Category build(){
            return new Category(this);
        }
    }

    private Category(Builder builder){
        this.name = builder.name;
    }
    public static Builder builder(){
        return new Builder();
    }
}
