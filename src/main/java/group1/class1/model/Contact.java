package group1.class1.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="contact")
public class Contact {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String photo;
}
