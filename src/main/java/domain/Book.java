package domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorValue("B")
@PrimaryKeyJoinColumn(name = "BOOK_ID")//<-키본 키 칼럼명을 변경하고 싶으면
public class Book extends Item{
    private String author;
    private String isbn;
}
