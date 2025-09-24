package com.restaurantbackend.restaurantbackend.session;
import com.restaurantbackend.restaurantbackend.table.Table;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@jakarta.persistence.Table(name = "session_data")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sessionCode; // NFC ile açılan link için
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private Table table;

    private boolean active;
}