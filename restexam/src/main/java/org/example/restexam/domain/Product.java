package org.example.restexam.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.restexam.dto.ProductDTO;

@Entity
@Table(name = "products")
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100)
    private String name;
    private int price;

    // DTO -> Entity
    public static Product fromDTO(ProductDTO dto) {
        return Product.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .build();
    }
}
