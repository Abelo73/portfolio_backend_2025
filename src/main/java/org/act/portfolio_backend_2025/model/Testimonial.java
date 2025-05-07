package org.act.portfolio_backend_2025.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "testimonials")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image; // ✅ This will store the Cloudinary URL

    private String name;
    private String role;
    private String quote;
    private String linkedin;
    private String github;
}
