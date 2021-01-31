package com.experiencers.server.smj.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    public Long id;
    @Column(nullable = false, length = 255)
    public String name;

    @OneToMany(mappedBy = "category")
    public List<Board> boards = new ArrayList<>();

    public List<Board> getBoards() {
        return boards;
    }

    public void addBoards(Board board) {
        this.boards.add(board);

        if (board.getCategory() != this) {
            board.setCategory(this);
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
