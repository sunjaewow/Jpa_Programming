package domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table
@Getter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
