package com._ds.system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "card_ranges")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardRangeData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long startRange;

    @Column(nullable = false)
    private Long endRange;

    @Column(nullable = false, length = 500)
    private String threeDSMethodURL;
}
